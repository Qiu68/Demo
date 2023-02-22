package com.qiu.readerfile;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.*;

public class StreamFileReader {

    private BufferedInputStream fileIn;

    private long fileLength;
    private int arraySize;
    private byte[] array;
    private static DatagramSocket ds;

    static {
        try {
            ds = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public StreamFileReader(String fileName, int arraySize) throws IOException {
        this.fileIn = new BufferedInputStream(new FileInputStream(fileName), arraySize);
        this.fileLength = fileIn.available();
        this.arraySize = arraySize;
    }

    public int read() throws IOException {
        byte[] tmpArray = new byte[arraySize];
        int bytes = fileIn.read(tmpArray);// 暂存到字节数组中
        if (bytes != -1) {
            array = new byte[bytes];// 字节数组长度为已读取长度
            System.arraycopy(tmpArray, 0, array, 0, bytes);// 复制已读取数据
            send(array);
            return bytes;
        }
        send(new byte[0]);
        return -1;
    }

    public void send(byte[] data) throws IOException {
        DatagramPacket dp = new DatagramPacket(
                data, data.length, InetAddress.getByName("127.0.0.1"),9999);
        ds.send(dp);

    }

    public void close() throws IOException {
        fileIn.close();
        array = null;
    }


    public static void main(String[] args) throws IOException {
        StreamFileReader reader = new StreamFileReader("d:/doctor-2022.h264", 50000);
        long start = System.nanoTime();
        while (reader.read() != -1) ;
        long end = System.nanoTime();
        reader.close();
        System.out.println("StreamFileReader: " + (end - start));
        ds.close();
    }
}