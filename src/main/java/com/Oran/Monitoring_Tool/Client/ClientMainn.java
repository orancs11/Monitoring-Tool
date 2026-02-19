package com.Oran.Monitoring_Tool.Client;

import com.Oran.Monitoring_Tool.Utilities.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.Socket;

import com.Oran.Monitoring_Tool.DTOs.ClientInfo;
import com.sun.management.OperatingSystemMXBean;

public class ClientMainn {
    public static void main(String[] args) throws IOException {
        OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean)  ManagementFactory.getOperatingSystemMXBean();

        String IPAddress = Inet4Address.getLocalHost().getHostAddress();
        Socket socket = new Socket(IPAddress, 3000); // Request Connection

        var out = new DataOutputStream(socket.getOutputStream());
        while(true){
            try{
                Thread.sleep(1000);
                String hostName = Inet4Address.getLocalHost().getHostName();
                System.out.printf("Host Name: %s%n ", hostName);
                String OS = System.getProperty("os.name");
                System.out.printf("Operating System: %s%n ", OS);
                double currCpuUsage = bean.getCpuLoad();
                System.out.printf("Current CPU usage: %.2f \37 \n", currCpuUsage * 100);
                double currMemoryUsage = bean.getTotalMemorySize() - bean.getFreeMemorySize();
                System.out.printf("Current memory usage: %.2f \n", currMemoryUsage);
                System.out.println("---------------------------------------------------------------------");
                ClientInfo dataPack = new ClientInfo(hostName, OS, currCpuUsage, currMemoryUsage);
                byte[] pack = Serializer.serialize(dataPack);
                out.writeInt(pack.length);
                out.write(pack);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
