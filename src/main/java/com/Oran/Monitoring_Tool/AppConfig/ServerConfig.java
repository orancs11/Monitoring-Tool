package com.Oran.Monitoring_Tool.AppConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class ServerConfig {

    // Example purpose
    //We don't need it
    @Bean
    public List<Socket> socketConfig(){
        List<Socket> listSockets = new ArrayList<>();
        // Do configurations
        return listSockets;
    }
}
