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
        Object obj = new Object();

        String IPAddress = Inet4Address.getLocalHost().getHostAddress();
        Socket socket = new Socket(IPAddress, 3000); // Request Connection

        //var in = new DataInputStream(System.in);
        var out = new DataOutputStream(socket.getOutputStream());


        while(true){
            try{
                Thread.sleep(1000);
                double currCpuUsage = bean.getCpuLoad();
                if(bean.getCommittedVirtualMemorySize() == -1) break;
                double currMemoryUsage = bean.getTotalMemorySize() - bean.getFreeMemorySize();
                out.writeUTF("Current CPU usage: " + currCpuUsage * 100 + "%");
                out.writeUTF("Current memory usage: " + currMemoryUsage + "bytes");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
