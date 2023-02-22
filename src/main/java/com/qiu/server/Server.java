package com.qiu.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);

        System.out.println("-----服务器监听中！-----");

        Socket s = server.accept();

        InputStream is = s.getInputStream();
        byte[] bys = new byte[1024];
        int len = is.read(bys);
        String data = new String(bys,0,len);

        System.out.println("接受到客户端信息："+data);

        OutputStream os = s.getOutputStream();
        os.write(data.getBytes(StandardCharsets.UTF_8));

        //释放资源
        s.close();
        server.close();

    }
}
