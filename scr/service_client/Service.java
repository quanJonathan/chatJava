/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Service implements Runnable{
    private Socket client;
    private static Service instance;
    static final int PORT_NUMBER = 9999;
    static final String HOST = "127.0.0.1";
    private BufferedReader in;
    private PrintWriter out;
    private ExecutorService pool;
    
    public static Service getInstance(){
        if(instance == null){
            instance = new Service();
        }
        return instance;
    }
   
    
    @Override
    public void run() {
        try {
            client = new Socket(HOST, PORT_NUMBER);
            pool = Executors.newCachedThreadPool();
            
            ActionListener al = new ActionListener(client);
            pool.execute(al);
           
        } catch (IOException ex) {
           shutDown();
        }
        
    }
    
    public void shutDown(){
        try{
            in.close();
            out.close();
            if(!client.isClosed()){
                client.close();
            }
        }catch(IOException e){
            
        }
    }
    
    public class ActionListener implements Runnable{

        private BufferedReader in;
        private PrintWriter out;
        private Socket client;
        
        public ActionListener(Socket client){
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
                
            } catch (IOException ex) {
                Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
