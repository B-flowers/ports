package com.udp;


import java.io.IOException;
import java.net.*;
import java.util.Date;

public class UdpServer {

    private final static int PORT = 5002;

    public static void main(String args[]) {
        DatagramSocket socket = null;
        DatagramPacket datapacket = null;
        InetSocketAddress address = null;

        try {
            //address = new InetSocketAddress(InetAddress.getLocalHost(), PORT);
            address = new InetSocketAddress(PORT);
            socket = new DatagramSocket(address);
            // socket.bind(address);
            for (; ; ) {

                byte buf[] = new byte[1024];
                datapacket = new DatagramPacket(buf, buf.length);
                System.out.println("server start ...");
                socket.receive(datapacket);
                /****** 解析数据报****/
                String receStr = new String(datapacket.getData(), 0, datapacket.getLength(), "UTF-8");
                System.out.println("client data: " + receStr);

                InetAddress addr = datapacket.getAddress();
                int port = datapacket.getPort();
                System.out.println("数据来源 " + addr + ":" + port);

                SocketAddress toAddress = datapacket.getSocketAddress();
                String sendStr = "server return ok 中文";
                buf = sendStr.getBytes("UTF-8");
                datapacket = new DatagramPacket(buf, buf.length);
                datapacket.setSocketAddress(toAddress);
                socket.send(datapacket);
                System.out.println("end ...");
            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}