package com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient {

    private final static int PORT = 5002;
    private static final String HOSTNAME = "127.0.0.1";


    public static void main(String[] args) {
        //����0��ʾ�ò���ϵͳ����һ���˿ں�
        try (DatagramSocket socket = new DatagramSocket(0)) {
            socket.setSoTimeout(10000);
            InetAddress host = InetAddress.getByName(HOSTNAME);
            String cliStr="my client";
            //ָ����Ҫ���͵�Ŀ�ĵ�
            DatagramPacket request = new DatagramPacket(cliStr.getBytes(), cliStr.length(), host, PORT);
            //Ϊ���ܵ����ݰ������ռ�
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            socket.send(request);
            socket.receive(response);
            String result = new String(response.getData(), 0, response.getLength(), "UTF-8");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
