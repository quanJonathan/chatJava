package service_server;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import database.DAO_TaiKhoan;
import entity.TaiKhoan;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class Service implements Runnable {

    private Map<Socket, String> connectionsWithName;
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
                connectionsWithName.put(client, "");
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

    class ConnectionHandler implements Runnable {

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;

        public ConnectionHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                // send message to client/user
                out = new PrintWriter(client.getOutputStream(), true);

                //get message from client/user
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String command = in.readLine();
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

                            var name = listAcc.get(0).getUsername();
                            sendMessage(list[0], result, new JSONObject().put("username", name));
                        } else {
                            sendMessage(list[0], result, new JSONObject().put("error", "Wrong credentials"));
                        }

                    } catch (JSONException ex) {
                        System.out.println("Null object");
                    }
                    (new Thread() {
                        public void run() {
                          while(true) {
                              try {
                                  sleep(5000);
                                  sendMessage("Testing", 1, new JSONObject().put("e", "e"));
                              } catch (InterruptedException ex) {
                                  Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
                              }

                          }
                        }
                    }).start();

                } else if (action.startsWith("/logout")) {

                } else if (action.startsWith("/register")) {

                } else if (action.startsWith("/changePassword")) {

                } else if (action.startsWith("resetPassword")) {

                } else if (action.startsWith("/sendMessage")) {
                    JSONObject object = new JSONObject(list[1]);
                    String text = object.getString("noidung");
                    String receiver = object.getString("receiver");
                    
                    Socket temp = new Socket();
                    for (Map.Entry<Socket, String> entry : connectionsWithName.entrySet()) {
                        if(entry.getValue().equals(receiver)){
                            temp = entry.getKey();
                            break;
                        }
                    }
                    PrintWriter temp2 = new PrintWriter(temp.getOutputStream(), true);
                    temp2.println("/login" + text);
                    temp2.close();
                } else if (action.startsWith("/getOnlUser")) {

                } else if (action.startsWith("/getFriendList")) {

                } else if (action.startsWith("/getChatData")) {

                } else if (action.startsWith("/unfriend")) {

                } else if (action.startsWith("/deleteChatRoom")) {

                } else if (action.startsWith("/searchWordInChatRoom")) {

                }

            } catch (IOException e) {

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
    }

    public static void main(String[] args) {
        Service.getInstance().run();
    }
}
