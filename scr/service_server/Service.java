package service_server;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import database.*;
import entity.*;
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

        private void userDisconnect(int userID) {

        }

        @Override
        public void run() {
            try {
                //getGroupData(new NhomChat("grp212s", "", null));
                // send message to client/user
                out = new PrintWriter(client.getOutputStream(), true, StandardCharsets.UTF_8);

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
                                updateStatus(user, 1);
                                user.setTrangThai(1);
                                JSONObject userObject = user.JSONify();  
                                
                                Date time = new Date(System.currentTimeMillis());
                                writeLoginHistory(user, time);

                                connectionsWithName.replace(client, user);
                                sendMessage(list[0], result, userObject);
                                userConnect(user.getUsername());
//                               
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
                        JSONObject object = new JSONObject(list[1]);
                        String name = object.getString("username");
                        removeClient(name);
                        updateStatus((new TaiKhoan(name, object.getString("password"), object.getString("email"))
                                    .setDiaChi(object.getString("diaChi"))
                                     .setGioiTinh(object.getBoolean("gioiTinh"))
                                     )
                                , 0);
                    } else if (action.startsWith("/register")) {

                    } else if (action.startsWith("/changePassword")) {

                    } else if (action.startsWith("resetPassword")) {

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
                        JSONArray array = new JSONArray();

                        for (BanBe b : friendList) {
                            var objectFriend = b.JSONify();
                            array.put(objectFriend);
                        }

                        sendManyObject("/friendListReceived", array);
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
                                convertTime(object.getString("ngayKetBan"))
                        );
                        unfriend(bb);

                        for (Entry<Socket, TaiKhoan> entry : connectionsWithName.entrySet()) {
                            if (entry.getValue() != null) {
                                if (entry.getValue().getUsername().equals(bb.getUsernameChinh())) {
                                    break;
                                }
                            }
                        }
                    } else if (action.startsWith("/deleteChatRoom")) {

                    } else if (action.startsWith("/searchWordInChatRoom")) {

                    } else if (action.startsWith("/shutDown")) {
                        JSONObject object = new JSONObject(list[1]);
                        var name = object.getString("username");
                        updateStatus((new TaiKhoan(name, object.getString("password"), object.getString("email"))
                                    .setDiaChi(object.getString("diaChi"))
                                     .setGioiTinh(object.getBoolean("gioiTinh"))
                                     )
                                , 0);
                        removeClient(name);
                    } else if (action.equals("/getGroupChatList")) {
                        JSONObject object = new JSONObject(list[1]);
                        var name = object.getString("username");
                        ArrayList<NhomChat> groupChatList = getGroupChatList(name);
                        JSONArray array = new JSONArray();
                        //System.out.println(groupChatList);
                        for(NhomChat g: groupChatList){
                            String id = g.getIDNhom();
                            
                            var rs = database_helper.select("select Chucnang from ThanhVienNhomChat where username = N'" + name + "' and idNhom=N'" + id + "'");
                            try {
                                while(rs.next()){
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
                        var groupName = object.getString("tenNhom");
                        var groupID = object.getString("IDNhom");
                        getGroupData(new NhomChat(groupName, groupID, null));
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

        private void searchWordChat(String text) {

        }

        private ArrayList<TinNhan> getGroupData(NhomChat nhomChat) {
            var resultSet = database_helper.select("select TinNhan.ID, TinNhan.noidung, TinNhan.ThoiGian,"
                    + " DanhSachTinNhan.NguoiGui, "
                    + " DanhSachTinNhan.BanSao, DanhSachTinNhan.IDNhom from TinNhan " + " "
                    + " inner join DanhSachTinNhan "
                    + " on DanhSachTinNhan.ID = TinNhan.ID where DanhSachTinNhan.IDNhom = N'" + nhomChat.getIDNhom() + "'");
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
                    sendManyObject("/groupDataReceived", messages);
//                    System.out.println(new TinNhan(resultSet.getNString("ID"),
//                                    resultSet.getTimestamp("ThoiGian"),
//                                    resultSet.getNString("noiDung"),
//                                    resultSet.getNString("nguoiGui"),
//                                    "",
//                                    resultSet.getNString("IDNhom"),
//                                    resultSet.getNString("BanSao")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        private ArrayList<TaiKhoan> getChatList(TaiKhoan user) {
            try {
                String name = user.getUsername();
                var resultset = database_helper.select(String.format("select distinct nguoigui from DanhSachTinNhan where (nguoigui!=N'%s' and bansao=N'%s') union select distinct nguoinhan from DanhSachTinNhan where (nguoinhan!=N'%s' and bansao=N'%s')",
                        name, name, name, name));

                ArrayList<TaiKhoan> users = new ArrayList<>();
                while (resultset.next()) {
                    try {
                        var username = resultset.getNString(1);
                        var rs = database_helper.select("Select trangThai from taikhoan where username=N'"+ username +"'");
                        while(rs.next()){
                            System.out.println(rs.getInt(1));
                            users.add((
                                new TaiKhoan(username, "", "")
                                    ).setTrangThai(rs.getInt(1)));
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

        private void writeLoginHistory(TaiKhoan user, Date time) {

        }

        private void updateStatus(TaiKhoan user, int i) {
            DAO_TaiKhoan dao_tn = new DAO_TaiKhoan();
            dao_tn.updateStatus(user, 1);
        }
    }

    public static void main(String[] args) {
        Service.getInstance().run();
    }
}
