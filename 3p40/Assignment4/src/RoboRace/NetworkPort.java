/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RoboRace;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author tm10vy
 */
public class NetworkPort implements Port {
    
    private Socket socks;
    private BufferedReader in;
    
    private PrintWriter out;
    
    
    public NetworkPort(Socket s){
        socks = s;
        try {
            in = new BufferedReader( new InputStreamReader(socks.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socks.getOutputStream()));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    } 
    
      public NetworkPort(int port){
        
      
        try {
            socks = new Socket(InetAddress.getByName("139.57.242.24"),port);
            in = new BufferedReader( new InputStreamReader(socks.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socks.getOutputStream()));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    } 
    
    public void send(String message){
       if (message != null){
           out.println(message);
           out.flush();
       } 
    }
    
    public String recieve(){
        String open = "";
        try{
            open = in.readLine();
        }
        catch (Exception e){}
        
        if(open.charAt(0)!='<'){
            return open;
        }
        
        String message = "" + open;
        String temp = "";
        
        while(!temp.contains(open.substring(2))){
            try{
               temp = in.readLine(); 
               message += temp;
               System.out.println(temp);
            }
            catch(Exception e){}
        }
        return message;
    }
    
    public void close(){
        
    }
    
}
