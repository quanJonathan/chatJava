/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service_client;

import entity.BanBe;
import entity.TaiKhoan;
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
    private database.database_helper dbh = new database.database_helper();

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
    
    public String getHost(){
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
            cmd = new PriorityBlockingQueue<>();
            al = new ActionListener(client);

            Thread t = new Thread(al);
            t.start();

        } catch (IOException ex) {
            shutDown();
        }
    }

    public void shutDown() {
        try {
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

        public ActionListener(Socket client) {
            this.client = client;
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

            }
        }

        public void shutDown() {
            try {
                in.close();
                out.close();
            } catch (IOException e) {

            }
        }

        public void sendCommand(String command, JSONObject object) {
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
            System.out.println("LOOP IN");

            (new Thread() {
                public void run() {
                    while (!client.isClosed()) {
                        try {
                            System.out.println("Waiting for commands");
                            String readCommand = in.readLine();
                            if (readCommand != null) {
                                if (!readCommand.isBlank()) {
                                    cmd.add(readCommand);
                                    System.out.println("Queue added loop: " + readCommand);

                                    var command = new JSONObject(readCommand);
                                    //System.out.println(command);
                                    var action = command.getString("message");
                                    var result = command.getInt("result");
//                                    System.out.println(object);
                                    switch (action) {
                                        case "/login": {
                                            var object = new JSONObject(command.getString("object"));
                                            if (result == 0) {
//                                                var error = object.getJSONObject("object").getString("error");
//                                                System.out.println("error" + error);
                                            } else {
//                                          System.out.println(resultSet.get("object").getClass());
                                                TaiKhoan user = new TaiKhoan(object.getString("username"), object.getString("password"), object.getString("email"));
                                                System.out.println(username + " login successfully");

                                                PublicEvent.getInstance().getEventLogin().goLogin(user);
                                            }
                                            break;
                                        }
                                        case "/newUserLogin":{
                                            var object = new JSONObject(command.getString("object"));
                                            TaiKhoan user = new TaiKhoan(object.getString("username"), object.getString("password"), object.getString("email"));
                                            user.setTrangThai(object.getInt("trangThai"));
                                           // PublicEvent.getInstance().getEventChatList().userConnect(user);
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
                                        
                                        case "/messagesChatReceived":{
                                            var object = new JSONArray(command.getString("object"));
                                            ArrayList<TinNhan> messages = new ArrayList<>();
                                            for(int i=0;i<object.length(); i++){
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
                                        
                                        case "/groupDataReceived":{
                                            var object = new JSONArray(command.getString("object"));
                                            ArrayList<TinNhan> messages = new ArrayList<>();
                                            for(int i=0;i<object.length(); i++){
                                                var newObject = object.getJSONObject(i);
                                                var date = convertTime(newObject.getString("thoiGian"));
                                                String sender = newObject.getString("nguoiGui");
                                                String receiver = newObject.getString("nguoiNhan");
                                                String id = newObject.getString("ID");
                                                String text = newObject.getString("noiDung");
                                                String banSao = newObject.getString("banSao");
                                                String idNhom = newObject.getString("IDNhom");
                                                TinNhan tn = new TinNhan(id, date, text, sender, receiver, idNhom, banSao);
                                                messages.add(tn);
                                            }
                                            System.out.println(messages);
                                            PublicEvent.getInstance().getEventGroupChat().setGroupChatData(messages);
                                            break;
                                        }
                                        case "/friendListReceived": {
                                            var object = new JSONArray(command.getString("object"));
                                            ArrayList<BanBe> friendList = new ArrayList<>();
                                            for(int i=0;i<object.length(); i++){
                                                var newObject = object.getJSONObject(i);
                                                String main = newObject.getString("username");
                                                String friend = newObject.getString("usernameBanBe");
                                                var date = convertTime(newObject.getString("ngayKetBan"));
                                                BanBe b = new BanBe(main, friend, date);
                                                friendList.add(b);
                                            }
                                            System.out.println(friendList);
                                            PublicEvent.getInstance().getEventFriend().setData(friendList);
                                            break;
                                        }
                                        default:
                                            break;
                                    }
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                            break;
                        }
                    }
                }
            }).start();
        }
    }
}
