/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server_utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author ADMIN
 */
public class Server implements Runnable{

    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;
    
    private ExecutorService pool;
    
    public Server(){
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
    
    public void broadCast(String message){
        for(ConnectionHandler ch: connections){
            if(ch!=null){
               ch.sendMessage(message);
            }
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
            
        }
        
        @Override
        public void run() {
            try{
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("Hello");
            }catch(IOException e){
                
            }
        }
        
        public void sendMessage(String message){
            out.println(message);
        }
        
        public void shutDown(){
            try {in.close();
                out.close();
                if(!client.isClosed()){
                    client.close();
                }
             } catch (IOException ex) {
                shutdown();
            }
        }
    }
    
    public static void main(String args[]){
        Server server = new Server();
        server.run();
    }
}
