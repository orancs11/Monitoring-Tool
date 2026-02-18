package com.Oran.Monitoring_Tool.Main;

import com.Oran.Monitoring_Tool.Server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = "com.Oran.Monitoring_Tool")
@EnableConfigurationProperties
public class MonitoringToolApplication {

	public static void main(String[] args) {
		var applicationContext = SpringApplication.run(MonitoringToolApplication.class, args);
		Server server = applicationContext.getBeanFactory().getBean(Server.class);
		try{
			System.out.println("Server waits!");
			server.openConnection();

		}
		catch (IOException e){
			e.printStackTrace();
		}
	}

}
