package service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Service implements Runnable{

    private ArrayList<ConnectionHandler> connections;
    private static Service instance;
    private ServerSocket server;
    private boolean done;
    
    private ExecutorService pool;
    
    public static Service getInstance(){
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }
    
    public Service(){
        connections = new ArrayList<>();
        done = false;
    }
    
    @Override
    public void run() {
        try {
            server = new ServerSocket(9999);
            pool = Executors.newCachedThreadPool();
            while(!done){
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } catch (IOException ex) { 
            shutdown();
        }
    }
    
    public void shutdown() {
        done = true;
        if(!server.isClosed()){
            try {
                server.close();
                
                for(ConnectionHandler ch: connections){
                    ch.shutDown();
                }
            } catch (IOException ex) {
               
            } 
        }
    }
    
    class ConnectionHandler implements Runnable{

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        
        public ConnectionHandler(Socket client){
           this.client = client;
        }
        
        @Override
        public void run() {
            try{
                // send message to client/user
                out = new PrintWriter(client.getOutputStream(), true);
                
                //get message from client/user
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                
                String action;
                
                //For testing only
//                System.out.println(in.readLine());
//                out.println("Hii user");
                
                while((action = in.readLine()) != null){
                    if(action.startsWith("/login")){
                        
                    }else if(action.startsWith("/logout")){
                        
                    }else if(action.startsWith("/register")){
                        
                    }else if(action.startsWith("/changePassword")){
                        
                    }else if(action.startsWith("resetPassword")){
                        
                    }else if(action.startsWith("/sendMessage")){
                        
                    }else if(action.startsWith("/getOnlUser")){
                        
                    }else if(action.startsWith("/getFriendList")){
                        
                    }else if(action.startsWith("/getChatData")){
                        
                    }else if(action.startsWith("/unfriend")){
                        
                    }else if(action.startsWith("/deleteChatRoom")){
                        
                    }else if(action.startsWith("/searchWordInChatRoom")){
                        
                    }
                }
            }catch(IOException e){
                
            }
        }
        
        public void sendMessage(String message){
            out.println(message);
        }
        
        public void shutDown(){
            try {
                in.close();
                out.close();
                if(!client.isClosed()){
                    client.close();
                }
             } catch (IOException ex) {
                shutdown();
            }
        }
    }
    
    public static void main(String[] args) {
        Service.getInstance().run();
    }
}
