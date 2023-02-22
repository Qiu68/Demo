package com.qiu.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket client = new Socket("127.0.0.1",8888);

        System.out.println("请输入数据:");
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();

        //输出留
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        bw.write(data);
        bw.flush();

        //接受服务器发送的数据
        InputStream is = client.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String data1 = new String(bys,0,len);
        System.out.println("服务器发送过来的数据:"+data1);


        client.close();
        bw.close();

    }
}
