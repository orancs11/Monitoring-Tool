package com.Oran.Monitoring_Tool.Client;


import com.Oran.Monitoring_Tool.ConfigurationProperties.ServerProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;

@Component
public class LocalPC implements Client{
    private ServerProperties clientProperties;
    private String hostName;
    private String IPAddress;
    private Socket socket;

    @Autowired
    public LocalPC(ServerProperties properties){
        this.clientProperties = properties;
    }

    @PostConstruct
    public void init() throws IOException {
        this.hostName = Inet4Address.getLocalHost().getHostName();
        this.IPAddress = Inet4Address.getLocalHost().getHostAddress();
    }

    @PreDestroy
    public void close() throws IOException{
        this.socket.close();
    }

    public void sendMessage() throws IOException {
        this.socket = new Socket(IPAddress, clientProperties.getPort());
        var in = new DataInputStream(System.in);
        var out = new DataOutputStream(this.socket.getOutputStream());
        String message = "";

        while(!message.equals("Quit!")){
            try{
                message = in.readLine();
                out.writeUTF(message);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        in.close();
        out.close();
        this.socket.close();
    }




}
