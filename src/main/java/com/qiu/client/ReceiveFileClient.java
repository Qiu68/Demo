package com.qiu.client;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class ReceiveFileClient {
    static DatagramSocket ds = null;
    public static void main(String[] args) throws IOException {

        /**
         * t1线程完成udp数据包接收
         * t2线程接收tcp确认信息，并通知t1文件传输完毕
         */

        Thread t1 = new Thread(()->{
            int count = 1;
            FileOutputStream out = null;
            try {
                out = new FileOutputStream("d:/doctor-2023.h264",true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //BufferedOutputStream bops = new BufferedOutputStream(out);
           byte[] data = new byte[48*1024];
            try {
                ds = new DatagramSocket(9999);
            } catch (SocketException e) {
                e.printStackTrace();
            }
            while(true) {

               DatagramPacket dp = new DatagramPacket(data, data.length);

               try {
                   try{
                   ds.receive(dp);
                   }
                   catch (SocketException e1){
                       System.out.println("没有连接啦");
                       out.flush();
                       return;
                   }
               } catch (IOException e) {
                   System.out.println("没有连接哦！");
                   e.printStackTrace();
               }
               //int length = trim(dp.getData());
                byte[] temp = new byte[dp.getLength()];
                System.arraycopy(dp.getData(),0,temp,0,dp.getLength());
                try {

                   out.write(temp);
                   out.flush();
                   System.out.println("接收的大小:"+temp.length+"循环次数:"+count++);
               } catch (IOException e) {
                   e.printStackTrace();
               }

           }

        });

        Thread t2 = new Thread(()->{
            try {
                ServerSocket socket = new ServerSocket(8888);
                Socket accept = socket.accept();
                InputStream inputStream = accept.getInputStream();
                if (inputStream.read() == 200){
                    ds.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        t1.start();
        t2.start();

    }

    public static int trim(byte[] data){
        int count = 0;
        int length = 0;
        for (int i=0; i<data.length;i++){
            //System.out.println(new String(String.valueOf(data[i])));
            if (data[i] != 0){
                count++;
                System.out.println("count:"+count);
            }
        }
        // 减去空白数，返回实际大小
        length = data.length - count;
        System.out.println("data.length:"+data.length+"ss"+length);
        return data.length - count;
    }
}
