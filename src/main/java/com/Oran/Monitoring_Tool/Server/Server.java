package com.Oran.Monitoring_Tool.Server;

import com.Oran.Monitoring_Tool.ConfigurationProperties.ServerProperties;
import com.Oran.Monitoring_Tool.DTOs.ClientInfo;
import com.Oran.Monitoring_Tool.Utilities.Serializer;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


@Component
public class Server{
    private ServerSocket serverSocket;
    private ServerProperties serverProperties;
    private List<Socket> connectedSockets;
    private Socket clientSocket; // For one time use

    @Autowired
    public Server(ServerProperties properties){
        this.serverProperties = properties;
    }

    @PostConstruct
    public void init() throws IOException{
        this.serverSocket = new ServerSocket(this.serverProperties.getPort());
    }

    @PreDestroy
    public void close() throws IOException{
        this.serverSocket.close();
        this.serverSocket = null;
    }


    public void openConnection() throws IOException, ClassNotFoundException{
        System.out.println("Waiting for Client Side!");
        clientSocket = this.serverSocket.accept();
        System.out.println("Client side has accepted");
        var in = new DataInputStream(this.clientSocket.getInputStream());

        while(!clientSocket.isClosed()){
            try{
                int length = in.readInt();
                byte[] bytePack = in.readNBytes(length);
                ClientInfo dataPack = (ClientInfo) Serializer.deserialize(bytePack);
                System.out.println(dataPack);
            }
            catch(Exception e){
                e.printStackTrace();
                clientSocket.close();
            }
        }

        clientSocket.close();
        in.close();
    }

}


