/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class Service implements Runnable {

    private Socket client;
    private static Service instance;
    static final int PORT_NUMBER = 9999;
    static final String HOST = "127.0.0.1";
    private BufferedReader in;
    private PrintWriter out;
    public ActionListener al;
    public BlockingQueue<String> cmd;
    private database.database_helper dbh = new database.database_helper();


    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
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
            out.println("/login " + object.toString());
        }

        public String getCommand() {
            try {
                var readCommand = cmd.take();
                System.out.println("LOOP OUT");

                System.out.println("Queue added: " + readCommand);
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
                            String readCommand = in.readLine();
                            if (!readCommand.isBlank()) {
                                cmd.add(readCommand);
                                System.out.println("Queue added loop: " + readCommand);
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
