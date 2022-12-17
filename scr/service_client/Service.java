/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service_client;

import com.microsoft.sqlserver.jdbc.StringUtils;
import entity.BanBe;
import entity.NhomChat;
import entity.TaiKhoan;
import entity.ThanhVienNhomChat;
import entity.TinNhan;
import event.PublicEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class Service implements Runnable {

    private Socket client;
    private static Service instance;
    public static final int PORT_NUMBER = 9999;
    static final String HOST = "127.0.0.1";
    private BufferedReader in;
    private PrintWriter out;
    public ActionListener al;
    public BlockingQueue<String> cmd;
    public String username;
    Thread t;

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    private Timestamp convertTime(String d) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parseDate = dateFormat.parse(d);
            Timestamp timestamp = new java.sql.Timestamp(parseDate.getTime());
            return timestamp;
        } catch (ParseException ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getHost() {
        return HOST;
    }

    public String getCurrentUser() {
        return this.username;
    }

    public void setCurrentUser(String user) {
        this.username = user;
    }

    @Override
    public void run() {
        try {
            client = new Socket(HOST, PORT_NUMBER);
            System.out.println("client conencted " + client.toString());

            if (al != null) {
                System.out.println("al shutting down");
                al.alThread.interrupt();
                al.alThread = null;
                System.out.println("thread stopped");
                al.shutDown();
                System.out.println("shut down successfully");
            }
            al = new ActionListener(client);
            System.out.println("al connected" + al);
            if (t != null) {
                t.interrupt();
                t = null;
            }
            t = new Thread(al);
            t.start();

        } catch (IOException ex) {
            shutDown();
        } catch (Throwable nu) {
        }
    }

    public boolean isServerRunning() {
        return client != null;
    }

    public void shutDown() {
        try {
            if (!isServerRunning()) {
                return;
            }
            if (!client.isClosed()) {
                client.close();
                al.shutDown();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Socket getSocket() {
        return this.client;
    }

    public class ActionListener implements Runnable {

        private BufferedReader in;
        private PrintWriter out;
        private Socket client;
        public boolean isLogin = false;
        private Thread alThread;

        public ActionListener(Socket client) {
            this.client = client;
        }

        public boolean isFinishBooting() {
            return in != null && out != null;
        }

        @Override
        public void run() {
            try {
                // send message to server
                out = new PrintWriter(client.getOutputStream(), true);

                //get message from server
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));

//                For testing only
//                out.println("connect To server");
//                System.out.println(in.readLine());
                getCommandLoop();

            } catch (IOException ex) {
                System.out.println(ex.getStackTrace());
            }
        }

        public void shutDown() {
            try {

            } catch (Throwable e) {

            } finally {
                in = null;
                out = null;
            }
        }

        public void sendCommand(String command, JSONObject object) {
            System.out.println(command + " " + object.toString() + Thread.currentThread().getId());
            out.println(command + " " + object.toString());
        }

        public String getCommand() {
            try {
                var readCommand = cmd.take();

                return readCommand;

            } catch (Exception ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "";
        }

        public void getCommandLoop() {
            System.out.println("LOOP IN" + Thread.currentThread().getId());

            alThread = new Thread() {
                public void run() {
                    while (!client.isClosed()) {
                        try {
                            System.out.println("Waiting for commands");
                            String readCommand = in.readLine();
                            if (readCommand != null) {
                                if (!readCommand.isBlank()) {
                                    //cmd.add(readCommand);
                                    System.out.println("Queue added loop: " + readCommand);

                                    var command = new JSONObject(readCommand);
                                    //System.out.println(command);
                                    var action = command.getString("message");
                                    var result = command.getInt("result");
//                                    System.out.println(searchList);
                                    switch (action) {
                                        case "/login": {
                                            var object = new JSONObject(command.getString("object"));
                                            if (result == 0) {
                                                var error = object.getString("error");
                                                PublicEvent.getInstance().getEventLogin().goLogin(null, error);
                                            } else {
//                                          System.out.println(resultSet.get("searchList").getClass());
                                                TaiKhoan user = new TaiKhoan(object.getString("username"), object.getString("password"), object.getString("email"));
                                                user.setFullName(object.getString("fullName"));
                                                user.setGioiTinh(object.getBoolean("gioiTinh"));
                                                user.setDiaChi(object.getString("diaChi"));
                                                user.setTrangThai(1);
                                                System.out.println(username + " login successfully");

                                                PublicEvent.getInstance().getEventLogin().goLogin(user, username + " login successfully");
                                            }
                                            break;
                                        }

                                        case "/register": {
                                            var object = new JSONObject(command.getString("object"));
                                            if (result == 0) {
                                                var error = object.getString("error");
                                                PublicEvent.getInstance().getEventRegister().showDialog("Register failed", "Register");
                                            } else {
                                                PublicEvent.getInstance().getEventRegister().showDialog("Register successfully", "Register");
                                            }
                                            break;
                                        }

                                        case "/resetPasswordID": {
                                            var object = new JSONObject(command.getString("object"));
                                            PublicEvent.getInstance().getEventForgetPass().setID(object.getString("id"));

                                            break;
                                        }

                                        case "/checkPasswordResetCodeReceived": {
                                            var object = new JSONObject(command.getString("object"));
                                            PublicEvent.getInstance().getEventForgetPass().codeChecked(object.getString("id"), (result == 1));

                                            break;
                                        }

                                        case "/newUserLogin": {
                                            var object = new JSONObject(command.getString("object"));
                                            TaiKhoan user = new TaiKhoan(object.getString("username"), object.getString("password"), object.getString("email"));
                                            user.setTrangThai(1);
                                            PublicEvent.getInstance().getEventChatList().userConnect(user);
                                            break;
                                        }

                                        case "/changePass": {
                                            String message;
                                            if (result == 0) {
                                                message = "Failed to change password.";
                                            } else {
                                                message = "Change password successfully";
                                            }
                                            PublicEvent.getInstance().getEventMain().showDialog(message, "Result");
                                            break;
                                        }

                                        case "/findAccount": {
                                            var object = new JSONObject(command.getString("object"));
                                            if (result == 0) {
                                                PublicEvent.getInstance().getEventForgetPass().accountResult("", false);
                                            } else {
                                                var email = object.getString("email");
                                                var user = object.getString("user");

                                                PublicEvent.getInstance().getEventForgetPass().accountResult(email, true);
                                            }

                                            break;
                                        }

                                        case "/resetPass": {
                                            PublicEvent.getInstance().getEventForgetPass().checkResult((result >= 0));
                                            break;
                                        }

                                        case "/changeUsername": {
                                            String message;
                                            if (result == 0) {
                                                message = "Failed to change username.";
                                            } else {
                                                message = "Change username successfully";
                                            }
                                            PublicEvent.getInstance().getEventMain().showDialog(message, "Result");
                                            break;
                                        }

                                        case "/chatListReceived": {
                                            var object = new JSONArray(command.getString("object"));
                                            ArrayList<TaiKhoan> users = new ArrayList<>();
                                            for (int i = 0; i < object.length(); i++) {
                                                var newObject = object.getJSONObject(i);
                                                String username = newObject.getString("username");
                                                int status = newObject.getInt("trangThai");
                                                if (username.isBlank() || StringUtils.isEmpty(username) || username.isEmpty() || username.equals("/")) {
                                                    continue;
                                                }
                                                System.out.println(username);
                                                users.add((new TaiKhoan(username, "", "")).setTrangThai(status));
                                            }
                                            PublicEvent.getInstance().getEventChatList().newUser(users);
                                            break;
                                        }

                                        case "/messageReceived": {
                                            var object = new JSONObject(command.getString("object"));
                                            String ID = object.getString("ID");
                                            String text = object.getString("noiDung");
                                            var time = convertTime(object.getString("thoiGian"));
                                            String sender = object.getString("nguoiGui");
                                            String receiver = object.getString("nguoiNhan");
                                            String banSao = object.getString("banSao");
                                            TinNhan objectMess = new TinNhan(ID, time, text, sender, receiver, "", banSao);
                                            PublicEvent.getInstance().getEventChat().receiveMessage(objectMess);
                                            break;
                                        }

                                        case "/messagesChatReceived": {
                                            var object = new JSONArray(command.getString("object"));
                                            ArrayList<TinNhan> messages = new ArrayList<>();
                                            for (int i = 0; i < object.length(); i++) {
                                                var newObject = object.getJSONObject(i);
                                                var date = convertTime(newObject.getString("thoiGian"));
                                                String sender = newObject.getString("nguoiGui");
                                                String receiver = newObject.getString("nguoiNhan");
                                                String id = newObject.getString("ID");
                                                String text = newObject.getString("noiDung");
                                                String banSao = newObject.getString("banSao");
                                                TinNhan tn = new TinNhan(id, date, text, sender, receiver, "", banSao);
                                                messages.add(tn);
                                            }
                                            System.out.println(messages);
                                            PublicEvent.getInstance().getEventChat().setChatData(messages);
                                            break;
                                        }

                                        case "/groupChatListReceived": {
                                            var object = new JSONArray(command.getString("object"));
                                            var roleList = new ArrayList<Boolean>();
                                            ArrayList<NhomChat> groups = new ArrayList<>();
                                            for (int i = 0; i < object.length(); i++) {
                                                var newObject = object.getJSONObject(i);
                                                var groupObject = newObject.getJSONObject("group");
                                                String idGroup = groupObject.getString("IDNhom");
                                                String nameGroup = groupObject.getString("tenNhom");
                                                var date = groupObject.getString("ngayTao");
                                                roleList.add(newObject.getBoolean("role"));
                                                groups.add(new NhomChat(idGroup, nameGroup, convertTime(date)));
                                            }
                                            System.out.println(groups);
                                            PublicEvent.getInstance().getEventGroupChatList().setData(groups, roleList);
                                            break;
                                        }

                                        case "/messageSearchReceived": {
                                            var object = new JSONArray(command.getString("object"));
                                            ArrayList<TinNhan> messages = new ArrayList<>();
                                            for (int i = 0; i < object.length(); i++) {
                                                var newObject = object.getJSONObject(i);
                                                var date = convertTime(newObject.getString("thoiGian"));
                                                String sender = newObject.getString("nguoiGui");
                                                String receiver = newObject.getString("nguoiNhan");
                                                String id = newObject.getString("ID");
                                                String text = newObject.getString("noiDung");
                                                String idnhom;
                                                try {
                                                    idnhom = newObject.getString("IDNhom");
                                                } catch (Throwable t) {
                                                    idnhom = "";
                                                }
                                                String banSao = newObject.getString("banSao");
                                                TinNhan tn = new TinNhan(id, date, text, sender, receiver, idnhom, banSao);
                                                messages.add(tn);
                                            }
                                            System.out.println(messages);
                                            PublicEvent.getInstance().getEventChat().setSearchData(messages, new ArrayList<>());
                                            break;
                                        }

                                        case "/allMessageSearchReceived": {
                                            var objects = new JSONObject(command.getString("object"));
                                            var object = objects.getJSONArray("array");
                                            var groupName = objects.getJSONArray("groupName");
                                            ArrayList<TinNhan> messages = new ArrayList<>();
                                            ArrayList<String> names = new ArrayList<>();
                                            for (int i = 0; i < object.length(); i++) {
                                                var newObject = object.getJSONObject(i);
                                                var date = convertTime(newObject.getString("thoiGian"));
                                                String sender = newObject.getString("nguoiGui");
                                                String receiver = newObject.getString("nguoiNhan");
                                                String id = newObject.getString("ID");
                                                String text = newObject.getString("noiDung");
                                                String idnhom;
                                                try {
                                                    idnhom = newObject.getString("IDNhom");
                                                } catch (Throwable t) {
                                                    idnhom = "";
                                                }
                                                String banSao = newObject.getString("banSao");
                                                TinNhan tn = new TinNhan(id, date, text, sender, receiver, idnhom, banSao);
                                                messages.add(tn);
                                                names.add(groupName.getString(i));
                                            }
                                            System.out.println(messages);
                                            PublicEvent.getInstance().getEventChat().setSearchData(messages, names);
                                            break;
                                        }

                                        case "/groupChatMessageReceived": {
                                            var object = command.getJSONObject("object");
                                            var groupObject = object.getJSONObject("group");
                                            var groupID = groupObject.getString("IDNhom");
                                            var groupName = groupObject.getString("tenNhom");

                                            var messageObject = object.getJSONObject("mess");

                                            String id = messageObject.getString("ID");
                                            String text = messageObject.getString("noiDung");
                                            String receiver = messageObject.getString("nguoiNhan");
                                            String sender = messageObject.getString("nguoiGui");
                                            var time = convertTime(messageObject.getString("thoiGian"));
                                            String idGroup = messageObject.getString("IDNhom");
                                            TinNhan mess = new TinNhan(id, time, text, sender, receiver, idGroup, sender);

                                            PublicEvent.getInstance().getEventGroupChat().receiveMessage(new NhomChat(groupID, groupName, null), mess);
                                            break;
                                        }
                                        case "/groupMemberReceived": {
                                            var object = new JSONArray(command.getString("object"));
                                            ArrayList<ThanhVienNhomChat> members = new ArrayList<>();
                                            for (int i = 0; i < object.length(); i++) {
                                                var newObject = object.getJSONObject(i);
                                                var groupId = newObject.getString("IDNhom");
                                                var username = newObject.getString("username");
                                                var role = newObject.getBoolean("chucNang");
                                                var date = Timestamp.valueOf(newObject.getString("ngayThem"));
                                                members.add(new ThanhVienNhomChat(groupId, username, role, date));
                                            }
                                            PublicEvent.getInstance().getEventGroupChatList().setMember(members);
                                            break;
                                        }
                                        case "/groupDataReceived": {
                                            var object = new JSONArray(command.getString("object"));
                                            ArrayList<TinNhan> messages = new ArrayList<>();
                                            for (int i = 0; i < object.length(); i++) {
                                                var newObject = object.getJSONObject(i);
                                                var date = convertTime(newObject.getString("thoiGian"));
                                                String sender = newObject.getString("nguoiGui");
                                                String receiver = newObject.getString("nguoiNhan");
                                                String id = newObject.getString("ID");
                                                String text = newObject.getString("noiDung");
                                                String banSao = newObject.getString("banSao");
                                                String idNhom = newObject.getString("IDNhom");

                                                messages.add(new TinNhan(id, date, text, sender, receiver, idNhom, banSao));
                                            }

//                                            System.out.println(messages);
                                            PublicEvent.getInstance().getEventGroupChat().setGroupChatData(messages);
                                            break;
                                        }
                                        case "/friendListReceived": {
                                            var objects = new JSONObject(command.getString("object"));
                                            var object = new JSONArray(objects.getJSONArray("array"));
                                            var stat = new JSONArray(objects.getJSONArray("status"));

                                            ArrayList<BanBe> friendList = new ArrayList<>();
                                            ArrayList<Integer> status = new ArrayList<>();
                                            for (int i = 0; i < object.length(); i++) {
                                                var newObject = object.getJSONObject(i);
                                                String main = newObject.getString("username");
                                                String friend = newObject.getString("usernameBanBe");
                                                var date = convertTime(newObject.getString("ngayKetBan"));
                                                BanBe b;
                                                if (main.equals(getCurrentUser())) {
                                                    b = new BanBe(main, friend, date);
                                                    friendList.add(b);

                                                } else if (friend.equals(getCurrentUser())) {
                                                    b = new BanBe(friend, main, date);
                                                    friendList.add(b);

                                                }
                                                status.add(stat.getInt(i));
                                            }
                                            System.out.println(friendList);
                                            PublicEvent.getInstance().getEventFriend().setData(friendList, status);
                                            break;
                                        }
                                        case "/searchFriendListReceived": {
                                            var searchList = new JSONArray(command.getString("object"));
                                            ArrayList<BanBe> friendList = new ArrayList<>();
                                            for (int i = 0; i < searchList.length(); i++) {
                                                var newObject = searchList.getJSONObject(i);
                                                String main = newObject.getString("username");
                                                String friend = "";
                                                var date = new Date();
                                                BanBe b;
                                                b = new BanBe(main, friend, date);
                                                friendList.add(b);
                                            }
                                            System.out.println(friendList);
                                            PublicEvent.getInstance().getEventFriend().setFriendSearchData(friendList);
                                            break;
                                        }

                                        case "/friendRequestListReceived": {
                                            var object = new JSONArray(command.getString("object"));
                                            ArrayList<BanBe> friendList = new ArrayList<>();
                                            for (int i = 0; i < object.length(); i++) {
                                                var newObject = object.getJSONObject(i);
                                                String main = newObject.getString("username");
                                                String friend = newObject.getString("usernameBanBe");
                                                var date = convertTime(newObject.getString("ngayKetBan"));
                                                BanBe b;
                                                b = new BanBe(main, friend, date);
                                                friendList.add(b);

                                            }
                                            System.out.println(friendList);
                                            PublicEvent.getInstance().getEventFriend().setFriendRequestData(friendList);
                                            break;
                                        }
                                        default:
                                            break;
                                    }
                                }
                            }
                        } catch (IOException ex) {

                            break;
                        }
                    }
                }
            };
            alThread.start();
        }
    }
}
