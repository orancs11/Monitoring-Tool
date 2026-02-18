package com.Oran.Monitoring_Tool.Main;

import org.apache.catalina.Server;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MonitoringToolApplication {

	public static void main(String[] args) {
		var applicationContext = SpringApplication.run(MonitoringToolApplication.class, args);
		Server server = applicationContext.getBean(Server.class);
	}

}
