package service_server;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import database.DAO_BanBe;
import database.DAO_TaiKhoan;
import database.DAO_TinNhan;
import database.database_helper;
import entity.BanBe;
import entity.TaiKhoan;
import entity.TinNhan;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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

    public String removeClient(Socket client) {
        for (ConnectionHandler c : connections) {
            if (c.client == client) {
                connections.remove(c);
                String username = connectionsWithName.get(client).getUsername();
                connectionsWithName.remove(client);
                return username;
            }
        }
        return "";
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
                        sendMessage("/newUserLogin", 1, entry.getValue().JSONify());
                    }
                }
            }
        }

        private void userDisconnect(int userID) {

        }

        @Override
        public void run() {
            try {
                // send message to client/user
                out = new PrintWriter(client.getOutputStream(), true);

                //get message from client/user
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                while (true) {
                    String command = in.readLine();
                    var list = command.split(" ", 2);
                    var action = list[0];
                    System.out.println(command);
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
                                JSONObject userObject = user.JSONify();
                                user.setTrangThai(1);
                                userConnect(user.getUsername());
                                System.out.println(user);
                                connectionsWithName.replace(client, user);
                                sendMessage(list[0], result, userObject);

                            } else {
                                sendMessage(list[0], result, new JSONObject().put("error", "Wrong credentials"));
                            }

                        } catch (JSONException ex) {
                            System.out.println("Null object");
                        }
//                    (new Thread() {
//                        public void run() {
//                          while(true) {
//                              try {
//                                  sleep(5000);
//                                  sendMessage("Testing", 1, new JSONObject().put("e", "e"));
//                              } catch (InterruptedException ex) {
//                                  Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
//                              }
//
//                          }
//                        }
//                    }).start();
                    
                    } else if (action.startsWith("/logout")) {

                    } else if (action.startsWith("/register")) {

                    } else if (action.startsWith("/changePassword")) {

                    } else if (action.startsWith("resetPassword")) {

                    }else if(action.startsWith("/getChatList")){
                         JSONObject object = new JSONObject(list[1]);
                         TaiKhoan user = new TaiKhoan(object.getString("username"), "", "");
                         
                    }else if (action.startsWith("/sendMessage")) {
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
                        JSONArray array = new JSONArray();

                        for (BanBe b : friendList) {
                            var objectFriend = b.JSONify();
                            array.put(objectFriend);
                        }

                        sendManyObject("/friendListReceived", array);
                    } else if (action.startsWith("/getChatData")){
                        JSONObject object = new JSONObject(list[1]);
                        TaiKhoan user1 = new TaiKhoan(object.getString("current"), "", "");
                        TaiKhoan user2 = new TaiKhoan(object.getString("chatter"), "", "");
                        ArrayList<TinNhan> messages = getChatData(user1, user2);
                        JSONArray array = new JSONArray();
                        
                        for(TinNhan tn: messages){
                            var objectMessage = tn.JSONify();
                            array.put(objectMessage);
                        }
                        
                        sendManyObject("/messagesChatReceived", array);
                    } else if (action.startsWith("/unfriend")) {
                        JSONObject object = new JSONObject(list[1]);
                        BanBe bb = new BanBe(
                                object.getString("username"),
                                object.getString("usernameBanBe"), 
                                    convertTime(object.getString("ngayKetBan"))
                        );
                        unfriend(bb);
                        
                        for(Entry<Socket, TaiKhoan> entry: connectionsWithName.entrySet()){
                            if(entry.getValue()!= null){
                                if(entry.getValue().getUsername() == bb.getUsernameChinh()){
                                    break;
                                }
                            }
                        }
                    } else if (action.startsWith("/deleteChatRoom")) {

                    } else if (action.startsWith("/searchWordInChatRoom")) {

                    }else if(action.startsWith("/shutdown")){
                        JSONObject object = new JSONObject(list[1]);
                        var host = object.getString("host");
                        var localPort = object.getInt("localPort");
                        var port = object.getInt("port");
                        Socket client = new Socket(host, port);
                        removeClient(client);
                        this.shutDown();
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

            daoAcc.selectAll().forEach((account) -> {
                System.out.println(account);
            });

            daoAcc.select("where Username='" + username + "'").forEach((account) -> {
                System.out.println(account);
            });

            var queryResult = daoAcc.select("where Email='" + username + "'" + " and password='" + password + "'");
            if (!queryResult.isEmpty()) {
                System.out.println("login succesfully");
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

            friendList = daoFriend.select(" where username = '" + user.getUsername() + "'");

            friendList.forEach((account) -> {
                System.out.println(account);
            });
            return friendList;
        }

        private void unfriend(BanBe bb) {
            DAO_BanBe dao_bb = new DAO_BanBe();
            dao_bb.delete(bb);
        }

        private ArrayList<TinNhan> getChatData(TaiKhoan user1, TaiKhoan user2) {
            DAO_TinNhan dao_tn = new DAO_TinNhan();
            return dao_tn.selectAll(user1.getUsername(), user2.getUsername());         
        }
        
        private ArrayList<TaiKhoan> getChatList(TaiKhoan user){
            String name = user.getUsername();
            var resultSet = database_helper.select("select NguoiNhan from danhsachtinnhan where NguoiGui='" + name + "'");
            ArrayList<TaiKhoan> tk = new ArrayList<>();
            try {
                while(resultSet.next()){
                    //tk.add(new TaiKhoan(resultSet.getNString("NguoiNhan")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        private void searchWordChat(String text) {

        }

    }

    public static void main(String[] args) {
        Service.getInstance().run();
    }
}
