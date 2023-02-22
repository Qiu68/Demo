package com.qiu.nio.file;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NIO 读取文件demo
 */
public class ReadFileTest {
    public static void main(String[] args) throws IOException {
        //指定一个缓冲区大小
//        ByteBuffer buf = ByteBuffer.allocate(1024);
//        System.out.println("---allocate---");
//        System.out.println(buf.position());
//        System.out.println(buf.limit());
//        System.out.println(buf.capacity());
//
//        System.out.println("----put----");
//        String str = "jjlin";
//        buf.put(str.getBytes(StandardCharsets.UTF_8));
//        System.out.println(buf.position());
//        System.out.println(buf.limit());
//        System.out.println(buf.capacity());
//
//        System.out.println("----flip()----");
//        //切换数据读取模式
//        buf.flip();
//        System.out.println(buf.position());
//        System.out.println(buf.limit());
//        System.out.println(buf.capacity());
//
//        System.out.println("---get()---");
//        byte[] dst = new byte[buf.limit()];
//        buf.get(dst);
//        System.out.println(new String(dst,0, dst.length));
//        System.out.println(buf.position());
//        System.out.println(buf.limit());
//        System.out.println(buf.capacity());

//        FileOutputStream file = new FileOutputStream("d:/data.txt");
//        FileChannel channel = file.getChannel();
//        ByteBuffer buf = ByteBuffer.allocate(1024);
//       for (int i=1;i<=10;i++){
//           buf.clear();
//           buf.put("少林功夫好\n".getBytes(StandardCharsets.UTF_8));
//           buf.flip();
//           channel.write(buf);
//       }
//       channel.close();
//        System.out.println("文件写入完毕！");

        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
        ssChannel.bind(new InetSocketAddress(9999));
        Selector selector = Selector.open();
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

//        while (selector.select() >0){
//            System.out.println("开始事件处理");
//            //获取注册到选择器的channel已准备好的事件
//            Iterator<SelectionKey> iterator = selector.
//                    selectedKeys().iterator();
//        }


        //固定线程池
        ExecutorService fixedThread = Executors.newFixedThreadPool(3);
        Runnable r = ()-> System.out.println("执行任务咯");

        fixedThread.execute(r);

    }
}
