package com.Oran.Monitoring_Tool.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.Socket;
import com.sun.management.OperatingSystemMXBean;

public class ClientMainn {
    public static void main(String[] args) throws IOException {
        OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean)  ManagementFactory.getOperatingSystemMXBean();

        String IPAddress = Inet4Address.getLocalHost().getHostAddress();
        Socket socket = new Socket(IPAddress, 3000); // Request Connection

        //var in = new DataInputStream(System.in);
        var out = new DataOutputStream(socket.getOutputStream());


        while(true){
            try{
                Thread.sleep(1000);
                double currCpuUsage = bean.getCpuLoad();
                System.out.printf("Current CPU usage: %.2f \37 \n", currCpuUsage * 100);
                double currMemoryUsage = bean.getTotalMemorySize() - bean.getFreeMemorySize();
                System.out.printf("Current memory usage: %.2f \n", currMemoryUsage);
                System.out.println("---------------------------------------------------------------------");
                out.writeUTF(String.format("Current CPU usage: %.2f \37", currCpuUsage * 100));
                out.writeUTF(String.format("Current memory usage: %.2f bytes", currMemoryUsage));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
