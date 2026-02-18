package com.Oran.Monitoring_Tool.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMainn {
    public static void main(String[] args) throws IOException {
        String IPAddress = Inet4Address.getLocalHost().getHostAddress();
        Socket socket = new Socket(IPAddress, 3000);
        var in = new DataInputStream(System.in);
        var out = new DataOutputStream(socket.getOutputStream());

        String message = "";

        while(!message.equals("Quit!")){
            try{
                message = in.readLine();
                out.writeUTF(message);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
