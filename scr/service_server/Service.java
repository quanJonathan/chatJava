package service_server;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import database.*;
import entity.*;
import event.PublicEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Service implements Runnable {

    private Map<Socket, TaiKhoan> connectionsWithName;
    private ArrayList<ConnectionHandler> connections;
    private static Service instance;
    private ServerSocket server;
    private boolean done;
    private database.database_helper dbh = new database.database_helper();

    private ExecutorService pool;

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public Service() {
        connectionsWithName = new HashMap<>();
        connections = new ArrayList<>();
        done = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(9999);
            System.out.println("SERVER CONNECTED SUCCESSFULLY!");
            pool = Executors.newCachedThreadPool();
            // new ConnectionHandler(new Socket()).run();
            while (!done) {
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connectionsWithName.put(client, null);
                pool.execute(handler);
            }
        } catch (IOException ex) {
            shutdown();
        }
    }

    public void shutdown() {
        done = true;
        if (!server.isClosed()) {
            try {
                server.close();

                for (ConnectionHandler ch : connections) {
                    ch.shutDown();
                }
            } catch (IOException ex) {

            }
        }
    }

    private Timestamp convertTime(String d) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            java.util.Date parseDate = dateFormat.parse(d);
            Timestamp timestamp = new java.sql.Timestamp(parseDate.getTime());
            return timestamp;
        } catch (ParseException ex) {
            Logger.getLogger(service_client.Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void removeClient(String name) {
        for (Entry<Socket, TaiKhoan> entry : connectionsWithName.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getUsername().equals(name)) {
                    connectionsWithName.remove(entry.getKey(), entry.getValue());
                    break;
                }
            }
        }
        connectionsWithName.forEach((key, value) -> {
            System.out.print(key);
            System.out.println(" = " + value);
            System.out.println("");
        });

    }

    class ConnectionHandler implements Runnable {

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;

        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        private void userConnect(String username) {
            for (Entry<Socket, TaiKhoan> entry : connectionsWithName.entrySet()) {
                if (entry.getValue() != null) {
                    if (!username.equals(entry.getValue().getUsername())) {
                        PrintWriter temp2 = null;
                        try {
                            temp2 = new PrintWriter(entry.getKey().getOutputStream(), true);
                            JSONObject newObject = new JSONObject();
                            newObject.put("message", "/newUserLogin");
                            newObject.put("result", 1);
                            newObject.put("object", entry.getValue().JSONify().toString());
                            temp2.println(newObject.toString());
                        } catch (IOException ex) {
                            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            System.out.println("succeed");
                        }
                    }
                }
            }
        }

        private void userDisconnect(String username) {
            for (Entry<Socket, TaiKhoan> entry : connectionsWithName.entrySet()) {
                if (entry.getValue() != null) {
                    if (!username.equals(entry.getValue().getUsername())) {
                        PrintWriter temp2 = null;
                        try {
                            temp2 = new PrintWriter(entry.getKey().getOutputStream(), true);
                            JSONObject newObject = new JSONObject();
                            newObject.put("message", "/userLogout");
                            newObject.put("result", 1);
                            newObject.put("object", entry.getValue().JSONify().toString());
                            temp2.println(newObject.toString());
                        } catch (IOException ex) {
                            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                        } finally {
                            System.out.println("succeed");
                        }
                    }
                }
            }
        }

        @Override
        public void run() {
            try {
                // send message to client/user
                out = new PrintWriter(client.getOutputStream(), true, StandardCharsets.UTF_8);

                //get message from client/user
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                while (true) {
                    String command = in.readLine();
                    System.out.println(command);
                    if (command == null) {
                        continue;
                    }

                    var list = command.split(" ", 2);
                    var action = list[0];
                    if (action.startsWith("/login")) {
                        try {
                            JSONObject object = new JSONObject(list[1]);
                            String email = object.getString("email");
                            String password = object.getString("password");
                            int result = processLoginCommand(email, password);
                            if (result == 1) {
                                DAO_TaiKhoan dao_acc = new DAO_TaiKhoan();
                                List<TaiKhoan> listAcc = dao_acc.select(" where '" + email + "' = Email");

                                TaiKhoan user = listAcc.get(0);
                                updateStatus(user, 1);
                                user.setTrangThai(1);
                                JSONObject userObject = user.JSONify();

                                Date time = new Date(System.currentTimeMillis());
                                // writeLoginHistory(user.getUsername(), time);

                                connectionsWithName.replace(client, user);
                                sendMessage(list[0], result, userObject);
                                userConnect(user.getUsername());
//                               
                            } else if (result == 0) {
                                sendMessage(list[0], result, new JSONObject().put("error", "Wrong credentials"));
                                shutDown();
                            } else {
                                sendMessage(list[0], 0, new JSONObject().put("error", "Account has been blocked"));
                                shutDown();
                            }

                        } catch (JSONException ex) {
                            System.out.println("Null object");
                        }

                    } else if (action.startsWith("/logout")) {
                        JSONObject object = new JSONObject(list[1]);
                        String name = object.getString("username");
                        var t = convertTime(object.getString("logInTime"));

                        userDisconnect(name);
                        removeClient(name);
                        updateStatus((new TaiKhoan(name, object.getString("password"), object.getString("email"))
                                .setFullName(object.getString("fullName"))
                                .setDiaChi(object.getString("diaChi"))
                                .setGioiTinh(object.getBoolean("gioiTinh"))
                                .setTrangThai(0)),
                                0);
                        writeLoginHistory(name, t);

                    } else if (action.startsWith("/register")) {
                        JSONObject object = new JSONObject(list[1]);
                        String username = object.getString("username");
                        TaiKhoan tk = new TaiKhoan(object.getString("username"), object.getString("password"), object.getString("email"))
                                .setFullName(object.getString("fullName"))
                                .setDiaChi(object.getString("diaChi"))
                                .setGioiTinh(object.getBoolean("gioiTinh"))
                                .setTrangThai(0);

                        int result = Register(tk);
                        sendMessage(list[0], result, tk.JSONify());

                    } else if (action.startsWith("/changePass")) {
                        JSONObject object = new JSONObject(list[1]);
                        var newPass = object.getString("newPass");
                        var oldPass = object.getString("oldPass");
                        var user = object.getString("user");

                        if (!new DAO_TaiKhoan().select("where  password=N'" + oldPass + "' and username = N'" + user + "'").isEmpty()) {
                            try {
                                var result = database_helper.insert("update taikhoan set password=N'" + newPass + "' where username = N'" + user + "'");
                                sendMessage("/changePass", result, new JSONObject());
                            } catch (Exception ex) {
                            }
                        } else {
                            sendMessage("/changePass", 0, new JSONObject());
                        }

                    } else if (action.startsWith("/findAccount")) {
                        JSONObject object = new JSONObject(list[1]);
                        var user = object.getString("user");
                        var acc = new DAO_TaiKhoan().select("where username = N'" + user + "'");
                        if (!acc.isEmpty()) {
                            try {
                                sendMessage("/findAccount", 1, new JSONObject()
                                        .put("user", acc.get(0).getUsername())
                                        .put("email", acc.get(0).getEmail()));
                            } catch (Exception ex) {
                            }
                        } else {
                            sendMessage("/findAccount", 0, new JSONObject());
                        }

                    } else if (action.startsWith("/resetPass")) {
                        JSONObject object = new JSONObject(list[1]);
                        var oldPass = object.getString("password");
                        var user = object.getString("user");

                        try {
                            var result = database_helper.insert("update taikhoan set password=N'" + oldPass + "' where username = N'" + user + "'");
                            sendMessage("/resetPass", result, new JSONObject());
                        } catch (Exception ex) {

                            sendMessage("/resetPass", 0, new JSONObject());
                        }

                    } else if (action.startsWith("/changeUsername")) {
                        JSONObject object = new JSONObject(list[1]);
                        var pass = object.getString("password");
                        var newName = object.getString("newName");
                        var user = object.getString("user");

                        if (new DAO_TaiKhoan().select("where password=N'" + pass + "' and username = N'" + user + "'").size() > 0) {
                            try {
                                var result = database_helper.insert("update taikhoan set username=N'" + newName + "' where username=N'" + user + "'");
                                sendMessage("/changeUsername", result, new JSONObject());
                            } catch (Exception ex) {
                                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            sendMessage("/changePass", 0, new JSONObject());
                        }

                    } else if (action.startsWith("/sendPasswordResetCode")) {
                        JSONObject object = new JSONObject(list[1]);
                        var username = object.getString("user");
                        var code = object.getString("code");
                        var id = savePasswordResetCode(username, code);
                        sendMessage("/resetPasswordID", 1, new JSONObject().put("id", id));

                    } else if (action.startsWith("/checkPasswordResetCode")) {
                        JSONObject object = new JSONObject(list[1]);
                        var id = object.getString("id");
                        var enterCode = object.getString("code");
                        var result = checkPasswordResetCode(id, enterCode);
                        sendMessage("/checkPasswordResetCodeReceived", result, new JSONObject().put("id", id));

                    } else if (action.startsWith("/getChatList")) {
                        JSONObject object = new JSONObject(list[1]);
                        TaiKhoan user = new TaiKhoan(object.getString("username"), "", "");
                        ArrayList<TaiKhoan> users = getChatList(user);
                        JSONArray array = new JSONArray();

                        for (TaiKhoan tk : users) {
                            var objectMessage = tk.JSONify();
                            array.put(objectMessage);
                        }
                        sendManyObject("/chatListReceived", array);

                    } else if (action.startsWith("/sendMessage")) {
                        JSONObject object = new JSONObject(list[1]);
                        String id = object.getString("ID");
                        String text = object.getString("noiDung");
                        String receiver = object.getString("nguoiNhan");
                        String sender = object.getString("nguoiGui");
                        var time = convertTime(object.getString("thoiGian"));
                        String idGroup = object.getString("IDNhom");
                        TinNhan mess = new TinNhan(id, time, text, sender, receiver, idGroup, sender);

                        Socket temp = null;
                        for (Entry<Socket, TaiKhoan> entry : connectionsWithName.entrySet()) {
                            if (entry.getValue() != null) {
                                if (receiver.equals(entry.getValue().getUsername())) {
                                    temp = entry.getKey();
                                    break;
                                }
                            }
                        }
                        if (temp != null) {
                            PrintWriter temp2 = new PrintWriter(temp.getOutputStream(), true);

                            JSONObject newObject = new JSONObject();
                            newObject.put("message", "/messageReceived");
                            newObject.put("result", 1);
                            newObject.put("object", mess.JSONify().toString());
                            System.out.println(newObject.toString());
                            temp2.println(newObject.toString());
                        } else {
                            System.out.println("User not online");
                        }
                        writeMessageToDb(mess);

                    } else if (action.startsWith("/getFriendList")) {
                        JSONObject object = new JSONObject(list[1]);
                        TaiKhoan user = new TaiKhoan(object.getString("username"), "", "");
                        ArrayList<BanBe> friendList = getFriendList(user);
                        JSONObject newObject = new JSONObject();
                        JSONArray array = new JSONArray();
                        JSONArray status = new JSONArray();
                        var dtk = new DAO_TaiKhoan();
                        for (BanBe b : friendList) {
                            var objectFriend = b.JSONify();
                            array.put(objectFriend);
                            status.put(dtk.select("where username=N'" + b.getUsernameBanBe() + "'").get(0).getTrangThai());
                        }

                        newObject.put("array", array);
                        newObject.put("status", status);

                        sendMessage("/friendListReceived", 1, newObject);

                    } else if (action.startsWith("/getSearchFriendList")) {
                        JSONObject object = new JSONObject(list[1]);
                        ArrayList<TaiKhoan> friendList = getFriendListSearch(object.getString("text"), object.getString("username"));
                        JSONArray array = new JSONArray();

                        for (TaiKhoan b : friendList) {
                            var objectFriend = b.JSONify();
                            array.put(objectFriend);
                        }

                        sendManyObject("/searchFriendListReceived", array);
                    } else if (action.startsWith("/getFriendRequestList")) {
                        JSONObject object = new JSONObject(list[1]);
                        String user = object.getString("username");
                        ArrayList<BanBe> friendList = getFriendRequestList(user);
                        JSONArray array = new JSONArray();

                        for (BanBe b : friendList) {
                            var objectFriend = b.JSONify();
                            array.put(objectFriend);
                        }

                        sendManyObject("/friendRequestListReceived", array);

                    } else if (action.startsWith("/getChatData")) {
                        JSONObject object = new JSONObject(list[1]);
                        TaiKhoan user1 = new TaiKhoan(object.getString("current"), "", "");
                        TaiKhoan user2 = new TaiKhoan(object.getString("chatter"), "", "");
                        ArrayList<TinNhan> messages = getChatData(user1, user2);
                        JSONArray array = new JSONArray();

                        for (TinNhan tn : messages) {
                            var objectMessage = tn.JSONify();
                            array.put(objectMessage);
                        }

                        sendManyObject("/messagesChatReceived", array);

                    } else if (action.startsWith("/unfriend")) {
                        JSONObject object = new JSONObject(list[1]);
                        BanBe bb = new BanBe(
                                object.getString("username"),
                                object.getString("usernameBanBe"),
                                new java.util.Date(0)
                        );
                        unfriend(bb);

                        for (Entry<Socket, TaiKhoan> entry : connectionsWithName.entrySet()) {
                            if (entry.getValue() != null) {
                                if (entry.getValue().getUsername().equals(bb.getUsernameChinh())) {
                                    break;
                                }
                            }
                        }

                    } else if (action.startsWith("/addFriend")) {
                        JSONObject object = new JSONObject(list[1]);
                        BanBe bb = new BanBe(
                                object.getString("username"),
                                object.getString("usernameBanBe"),
                                new Date(System.currentTimeMillis())
                        );
                        addFriend(bb);

                    } else if (action.startsWith("/deleteChatHistory")) {
                        JSONObject object = new JSONObject(list[1]);
                        var chatter = object.get("chatter");
                        var username = object.get("user");

                        database_helper.delete("delete from DanhSachTinNhan where ((NguoiNhan = N'" + username + "' and NguoiGui = N'" + chatter + "') or (NguoiNhan = N'" + chatter + "' and NguoiGui = N'" + username + "')) and BanSao = N'" + username + "' and idNhom is null");

                    } else if (action.startsWith("/getMessageSearch")) {
                        JSONObject object = new JSONObject(list[1]);
                        var username = object.getString("username");
                        var usernameBanBe = object.getString("usernameBanBe");
                        var text = object.getString("text");
                        var messages = searchMessage(username, usernameBanBe, text);
                        JSONArray array = new JSONArray();
                        for (TinNhan tn : messages) {
                            var objectMessage = tn.JSONify();
                            array.put(objectMessage);
                        }

                        sendManyObject("/messageSearchReceived", array);

                    } else if (action.startsWith("/getAllMessageSearch")) {
                        JSONObject object = new JSONObject(list[1]);
                        var username = object.getString("username");
                        var text = object.getString("text");
                        var messages = searchAllMessage(username, text);
                        JSONArray array = new JSONArray(), groupName = new JSONArray();
                        for (TinNhan tn : messages) {
                            String gp;
                            if (tn.getIDNhom() != null && !tn.getIDNhom().isEmpty()) {
                                gp = new DAO_NhomChat().select("where idnhom='" + tn.getIDNhom() + "'").get(0).getTenNhom();
                            } else {
                                gp = "";
                            }
                            var objectMessage = tn.JSONify();
                            array.put(objectMessage);
                            groupName.put(gp);
                        }

                        sendMessage("/allMessageSearchReceived", 1, new JSONObject().put("array", array).put("groupName", groupName));

                    } else if (action.startsWith("/shutDown")) {
                        JSONObject object = new JSONObject(list[1]);
                        var name = object.getString("username");
                        var t = convertTime(object.getString("logInTime"));
                        updateStatus((new TaiKhoan(name, object.getString("password"), object.getString("email"))
                                .setFullName(object.getString("fullName"))
                                .setDiaChi(object.getString("diaChi"))
                                .setGioiTinh(object.getBoolean("gioiTinh"))),
                                0);
                        removeClient(name);
                        writeLoginHistory(name, t);

                    } else if (action.equals("/createGroup")) {
                        JSONObject object = new JSONObject(list[1]);
                        var groupObject = object.getJSONObject("group");
                        JSONArray newObject = object.getJSONArray("members");

                        var newGroup = new NhomChat(groupObject.getString("IDNhom"), groupObject.getString("tenNhom"), Timestamp.valueOf(groupObject.getString("ngayTao")));
                        ArrayList<ThanhVienNhomChat> members = new ArrayList<>();

                        for (int i = 0; i < newObject.length(); i++) {
                            var memberObject = newObject.getJSONObject(i);
                            var idGroup = memberObject.getString("IDNhom");
                            var username = memberObject.getString("username");
                            var date = Timestamp.valueOf(memberObject.getString("ngayThem"));
                            boolean role = memberObject.getBoolean("chucNang");
                            members.add(new ThanhVienNhomChat(idGroup, username, role, date));
                        }

                        createGroup(newGroup, members);

                    } else if (action.equals("/addNewAdmin")) {
                        JSONObject object = new JSONObject(list[1]);
                        var groupObject = object.getJSONObject("group");
                        JSONArray newObject = object.getJSONArray("members");

                        var newGroup = new NhomChat(groupObject.getString("IDNhom"), groupObject.getString("tenNhom"), Timestamp.valueOf(groupObject.getString("ngayTao")));
                        ArrayList<ThanhVienNhomChat> members = new ArrayList<>();

                        for (int i = 0; i < newObject.length(); i++) {
                            var memberObject = newObject.getJSONObject(i);
                            var idGroup = memberObject.getString("IDNhom");
                            var username = memberObject.getString("username");
                            var date = new Date(0);
                            boolean role = memberObject.getBoolean("chucNang");
                            members.add(new ThanhVienNhomChat(idGroup, username, role, date));
                        }

                        updateAdmin(newGroup, members);

                    } else if (action.equals("/getGroupChatList")) {
                        JSONObject object = new JSONObject(list[1]);
                        var name = object.getString("username");
                        ArrayList<NhomChat> groupChatList = getGroupChatList(name);
                        JSONArray array = new JSONArray();
                        //System.out.println(groupChatList);
                        for (NhomChat g : groupChatList) {
                            String id = g.getIDNhom();

                            var rs = database_helper.select("select Chucnang from ThanhVienNhomChat where username = N'" + name + "' and idNhom=N'" + id + "'");
                            try {
                                while (rs.next()) {
                                    boolean role = rs.getBoolean("chucNang");
                                    JSONObject newObject = new JSONObject();
                                    newObject.put("role", role);
                                    newObject.put("group", g.JSONify());
                                    array.put(newObject);
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        System.out.println(array);
                        sendManyObject("/groupChatListReceived", array);

                    } else if (action.equals("/getGroupData")) {
                        JSONObject object = new JSONObject(list[1]);
                        var name = object.getString("name");
                        var groupObject = object.getJSONObject("group");
                        var groupName = groupObject.getString("tenNhom");
                        var groupID = groupObject.getString("IDNhom");
                        getGroupData(new NhomChat(groupID, groupName, null), name);

                    } else if (action.equals("/changeGroupName")) {
                        JSONObject object = new JSONObject(list[1]);
                        var newName = object.getString("newName");
                        var groupObject = object.getJSONObject("group");
                        var groupID = groupObject.getString("IDNhom");
                        var date = convertTime(groupObject.getString("ngayTao"));
                        new DAO_NhomChat().update(new NhomChat(groupID, newName, date));

                    } else if (action.equals("/groupChatSendMessage")) {
                        JSONObject object = new JSONObject(list[1]);
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

                        var members = getGroupMember(groupID);
                        writeGroupMessageToDb(mess, members);

                        for (int i = 0; i < members.size(); i++) {
                            if (members.get(i).getUsername() == null ? sender == null : members.get(i).getUsername().equals(sender)) {
                                members.remove(i);
                                break;
                            }
                        }

                        for (Entry<Socket, TaiKhoan> entry : connectionsWithName.entrySet()) {
                            if (entry.getValue() != null) {
                                for (int i = 0; i < members.size(); i++) {
                                    if (entry.getValue().getUsername().equals(members.get(i).getUsername())) {
                                        if (entry.getValue().getUsername().equals(sender)) {
                                            continue;
                                        }
                                        PrintWriter temp2 = null;
                                        try {
                                            temp2 = new PrintWriter(entry.getKey().getOutputStream(), true);
                                            JSONObject newObject = new JSONObject();
                                            newObject.put("message", "/groupChatMessageReceived");
                                            newObject.put("result", 1);
                                            newObject.put("object", object);
                                            temp2.println(newObject.toString());
                                            members.remove(i);
                                            break;
                                        } catch (IOException ex) {
                                            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                                        } finally {
                                            System.out.println("succeed");
                                        }
                                    }
                                }
                            }
                        }

                    } else if (action.equals("/getMember")) {
                        JSONObject object = new JSONObject(list[1]);
                        var groupID = object.getString("IDNhom");
                        var groupName = object.getString("tenNhom");
                        var memberArray = new JSONArray();
                        var members = getGroupMember(groupID);
                        for (ThanhVienNhomChat m : members) {
                            memberArray.put(m.JSONify());
                        }
                        sendManyObject("/groupMemberReceived", memberArray);

                    } else if (action.equals("/addMember")) {
                        JSONObject object = new JSONObject(list[1]);
                        var id = object.getString("group");
                        JSONArray newObject = object.getJSONArray("user");

                        ArrayList<ThanhVienNhomChat> members = new ArrayList<>();

                        for (int i = 0; i < newObject.length(); i++) {
                            var memberObject = newObject.getJSONObject(i);
                            var idGroup = memberObject.getString("IDNhom");
                            var username = memberObject.getString("username");
                            var date = new Date(System.currentTimeMillis());
                            boolean role = memberObject.getBoolean("chucNang");
                            members.add(new ThanhVienNhomChat(idGroup, username, role, date));
                        }

                        addMember(id, members);

                    } else if (action.equals("/removeMember")) {
                        JSONObject object = new JSONObject(list[1]);
                        var id = object.getString("group");
                        JSONArray newObject = object.getJSONArray("user");

                        ArrayList<ThanhVienNhomChat> members = new ArrayList<>();

                        for (int i = 0; i < newObject.length(); i++) {
                            var memberObject = newObject.getJSONObject(i);
                            var idGroup = memberObject.getString("IDNhom");
                            var username = memberObject.getString("username");
                            var date = new Date(0);
                            boolean role = memberObject.getBoolean("chucNang");
                            members.add(new ThanhVienNhomChat(idGroup, username, role, date));
                        }

                        removeMember(id, members);

                    }
                }

            } catch (IOException e) {

            }
        }

        public void sendManyObject(String message, JSONArray array) {
            try {
                JSONObject newObject = new JSONObject();
                newObject.put("message", message);
                newObject.put("result", 1);
                newObject.put("object", array.toString());
                out.println(newObject.toString());
            } catch (JSONException ex) {
            }
        }

        public void sendMessage(String message, int result, JSONObject object) {
            try {
                JSONObject newObject = new JSONObject();
                newObject.put("message", message);
                newObject.put("result", result);
                newObject.put("object", object.toString());
                out.println(newObject.toString());
            } catch (JSONException ex) {

            }
        }

        public void shutDown() {
            try {
                in.close();
                out.close();
                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException ex) {
                shutdown();
            }
        }

        public int getResult(Callable<Integer> func) {
            try {
                return func.call();
            } catch (Exception ex) {

            }
            return 0;
        }

        private int processLoginCommand(String username, String password) {
            var daoAcc = new DAO_TaiKhoan();

//            daoAcc.selectAll().forEach((account) -> {
//                System.out.println(account);
//            });
            daoAcc.select("where Username='" + username + "'").forEach((account) -> {
                System.out.println(account);
            });

            var queryResult = daoAcc.select("where Email='" + username + "'" + " and password='" + password + "'");
            if (!queryResult.isEmpty()) {
                if (queryResult.get(0).getTrangThai() == -1) {
                    System.out.println("this account is block");
                    return -1;
                }
                return 1;
            } else {
                System.out.println("Wrong username/password");
                return 0;
            }
        }

        private void writeMessageToDb(TinNhan message) {
            DAO_TinNhan dao_tn = new DAO_TinNhan();

            dao_tn.insert(message);
        }

        private void logout() {

        }

        private void addLoginHistory(TaiKhoan user) {

        }

        private ArrayList<BanBe> getFriendList(TaiKhoan user) {
            ArrayList<BanBe> friendList;
            var daoFriend = new DAO_BanBe();

            friendList = daoFriend.select("b1 where username='" + user.getUsername() + "' and UsernameBanBe in (\n"
                    + "	select Username from danhsachbanbe b2 where b2.UsernameBanBe = b1.Username\n"
                    + ")");

            friendList.forEach((account) -> {
                System.out.println(account);
            });
            return friendList;
        }

        private ArrayList<TaiKhoan> getFriendListSearch(String text, String username) {
            ArrayList<TaiKhoan> friendList;
            var daoFriend = new DAO_TaiKhoan();

            friendList = daoFriend.select("tk1 " + text + " and username!=N'" + username + "' and username not in (select tk2.usernameBanBe from danhsachbanbe tk2 where tk1.username = tk2.usernameBanBe and tk2.username=N'" + username + "')");

            friendList.forEach((account) -> {
                System.out.println(account);
            });
            return friendList;
        }

        private ArrayList<BanBe> getFriendRequestList(String username) {
            ArrayList<BanBe> friendList;
            var daoFriend = new DAO_BanBe();

            friendList = daoFriend.select("bb1 where bb1.usernameBanBe=N'" + username + "' and not exists (\n"
                    + "	select * from DanhSachBanBe bb2 where bb2.Username=N'" + username + "' and bb2.UsernameBanBe = bb1.username\n"
                    + ")");

            friendList.forEach((account) -> {
                System.out.println(account);
            });
            return friendList;
        }

        private void addFriend(BanBe bb) {
            DAO_BanBe dao_bb = new DAO_BanBe();
            dao_bb.insert(bb);
        }

        private void unfriend(BanBe bb) {
            DAO_BanBe dao_bb = new DAO_BanBe();
            dao_bb.delete(bb);
        }

        private ArrayList<TinNhan> getChatData(TaiKhoan user1, TaiKhoan user2) {
            DAO_TinNhan dao_tn = new DAO_TinNhan();
            return dao_tn.selectAll(user1.getUsername(), user2.getUsername());
        }

        private ArrayList<TinNhan> searchMessage(String username, String usernameBanBe, String text) {
            var messages = new DAO_TinNhan().searchFromAUser(username, usernameBanBe, text);
            return messages;
        }

        private ArrayList<TinNhan> searchAllMessage(String username, String text) {
            var messages = new DAO_TinNhan().searchFromAllUser(username, text);
            return messages;
        }

        private ArrayList<TinNhan> getGroupData(NhomChat nhomChat, String name) {
            var resultSet = database_helper.select("select TinNhan.ID, TinNhan.noidung, TinNhan.ThoiGian,"
                    + " DanhSachTinNhan.NguoiGui, "
                    + " DanhSachTinNhan.BanSao, DanhSachTinNhan.IDNhom from TinNhan " + " "
                    + " inner join DanhSachTinNhan "
                    + " on DanhSachTinNhan.ID = TinNhan.ID where DanhSachTinNhan.IDNhom = N'" + nhomChat.getIDNhom() + "' and "
                    + " DanhSachTinNhan.BanSao = N'" + name + "' order by TinNhan.ThoiGian asc");
            JSONArray messages = new JSONArray();
            try {
                while (resultSet.next()) {
                    messages.put(
                            (new TinNhan(resultSet.getNString("ID"),
                                    resultSet.getTimestamp("ThoiGian"),
                                    resultSet.getNString("noiDung"),
                                    resultSet.getNString("nguoiGui"),
                                    "",
                                    resultSet.getNString("IDNhom"),
                                    resultSet.getNString("BanSao"))).JSONify()
                    );
//                    System.out.println(new TinNhan(resultSet.getNString("ID"),
//                                    resultSet.getTimestamp("ThoiGian"),
//                                    resultSet.getNString("noiDung"),
//                                    resultSet.getNString("nguoiGui"),
//                                    "",
//                                    resultSet.getNString("IDNhom"),
//                                    resultSet.getNString("BanSao")));
                }
                sendManyObject("/groupDataReceived", messages);
            } catch (SQLException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        private ArrayList<TaiKhoan> getChatList(TaiKhoan user) {
            try {
                String name = user.getUsername();
                var resultset = database_helper.select(String.format("select distinct nguoigui from DanhSachTinNhan where (nguoigui!=N'%s' and bansao=N'%s' and idnhom is null) union select distinct nguoinhan from DanhSachTinNhan where (nguoinhan!=N'%s' and bansao=N'%s' and idnhom is null)",
                        name, name, name, name));

                ArrayList<TaiKhoan> users = new ArrayList<>();
                while (resultset.next()) {
                    try {
                        var username = resultset.getNString(1);
                        var rs = database_helper.select("Select trangThai from taikhoan where username=N'" + username + "'");
                        while (rs.next()) {
                            System.out.println(rs.getInt(1));
                            users.add((new TaiKhoan(username, "", "")).setTrangThai(rs.getInt(1)));
                        }

                    } catch (SQLException ex) {

                    }
                }
                return users;
            } catch (SQLException ex) {

            }
            return null;
        }

        private ArrayList<NhomChat> getGroupChatList(String name) {
            DAO_NhomChat dao_nc = new DAO_NhomChat();
            return dao_nc.selectAllGroupOfAUser(name);
        }

        private void writeLoginHistory(String user, Timestamp t) {
            var dls = new DAO_LSDangNhap();

            dls.insert(new LichSuDangNhap(user, t, new Timestamp(System.currentTimeMillis())));

        }

        private void updateStatus(TaiKhoan user, int i) {
            DAO_TaiKhoan dao_tn = new DAO_TaiKhoan();
            dao_tn.updateStatus(user, i);
        }

        private ArrayList<ThanhVienNhomChat> getGroupMember(String groupID) {
            DAO_NhomChat daonc = new DAO_NhomChat();
            ArrayList<ThanhVienNhomChat> members = daonc.selectAllMembers(groupID, "");
            return members;
        }

        private void writeGroupMessageToDb(TinNhan mess, ArrayList<ThanhVienNhomChat> members) {
            try {
                var rs = database_helper.insert(database_query_builder.insert("TinNhan",
                        mess.toDelimitedList()));
                for (ThanhVienNhomChat m : members) {
                    try {
                        TinNhan temp = new TinNhan(
                                mess.getID(),
                                mess.getThoiGian(),
                                mess.getNoiDung(),
                                mess.getNguoiGui(),
                                "/",
                                m.getIDNhom(),
                                m.getUsername());

                        String insertQuery = temp.toDelimitedList2();
                        database_helper.insert(database_query_builder.insert("DanhSachTinNhan",
                                insertQuery));
                    } catch (Exception ex) {
                        Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } catch (Exception ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private int Register(TaiKhoan tk) {
            var dbh = new database.database_helper();
            String message;
            var daoAcc = new DAO_TaiKhoan();
            var queryResult = daoAcc.insert(tk);

            if (queryResult.size() > 0) {

                message = "register succesfully";
                System.out.println(message);
                return 1;
            } else {
                message = "Register error";
                System.out.println(message);
                return 0;
            }
        }

        private void createGroup(NhomChat newGroup, ArrayList<ThanhVienNhomChat> members) {
            new DAO_NhomChat().insert(newGroup);
            new DAO_NhomChat().insertMember(newGroup.getIDNhom(), members);
        }

        private void addMember(String id, ArrayList<ThanhVienNhomChat> members) {
            new DAO_NhomChat().insertMember(id, members);
        }

        private void removeMember(String id, ArrayList<ThanhVienNhomChat> members) {
            new DAO_NhomChat().deleteMember(id, members);
        }

        private void updateAdmin(NhomChat newGroup, ArrayList<ThanhVienNhomChat> members) {
            var dnc = new DAO_NhomChat();
            members.forEach(mem -> {
                dnc.updateAdmin(newGroup.getIDNhom(), mem.getUsername(), mem.getChucNang());

            });
        }

        private String savePasswordResetCode(String username, String code) {
            var id = IDPrefix.getIDCode();
            new DAO_MatKhau().insert(new MaXacNhan(id, username, code, new java.util.Date(System.currentTimeMillis())));
            return id;
        }

        private int checkPasswordResetCode(String id, String enterCode) {
            try {
                if (new DAO_MatKhau().selectWithID(id).get(0).getCode().equals(enterCode)) {
                    return 1;
                }
                return 0;
            } catch (Throwable t) {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        Service.getInstance().run();
    }
}
