package com.ws.java;

import com.appliance.Application;

import java.net.*;

/***
 * UDPclientClient端
 ***/
public class UdpClient {

    private static String netAddress = Application.getProperty("application.udp.address", "192.168.5.16");
    private static int PORT_NUM = Application.getProperty("application.udp.port", 9999);

    private static DatagramSocket datagramSocket = null;

    public static String start(String sendStr) {
        try {

            /*** 发送数据***/
            // 初始化datagramSocket,注意与前面Server端实现的差别

            datagramSocket = new DatagramSocket();

            // 使用DatagramPacket(byte buf[], int length, InetAddress address, int port)函数组装发送UDP数据报
            byte[] buf = sendStr.getBytes();
            InetAddress address = InetAddress.getByName(netAddress);
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length, address, PORT_NUM);
            // 发送数据
            datagramSocket.send(datagramPacket);

            /*** 接收数据***/
            byte[] receBuf = new byte[1024];
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);

            String receStr = new String(recePacket.getData(), 0, recePacket.getLength());
            System.out.println("Client Rece Ack:" + receStr);
            return receStr;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }


    public static void main(String[] args) {
        UdpClient.start("all_interface");
    }
}