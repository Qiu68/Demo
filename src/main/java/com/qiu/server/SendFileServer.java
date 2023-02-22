package com.qiu.server;

import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class SendFileServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        //读文件
        FileInputStream file = new FileInputStream("d:/doctor-2022.h264");
        BufferedInputStream in = new BufferedInputStream(file);

        //UDP发送文件内容
        byte[] data = new byte[48*1024];
        byte[] temp = null;
        int count = 1;
        DatagramSocket ds = new DatagramSocket();
        DatagramPacket dp = null;
        while (true){
            int read = in.read(data);
            if (read == -1){
                System.out.println("文件读取完毕");
                break;
            }

            temp = new byte[read];
            System.arraycopy(data,0,temp,0,read);
            dp = new DatagramPacket(temp,temp.length, InetAddress.getByName("127.0.0.1"),9999);
            ds.send(dp);
            System.out.println("文件读取的大小:"+temp.length+"-----"+"循环次数"+count++ +"发送字节数:"+dp.getLength());
            Thread.sleep(1);
        }

        in.close();
        file.close();
        ds.close();

        //TCP发送确认信息
        Socket socket = new Socket("127.0.0.1",8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(200);
        outputStream.flush();
        outputStream.close();
        socket.close();
    }
}
