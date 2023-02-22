import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class DiscardServerHandler  {



    public static void main(String[] args) throws IOException {

        //创建一个文件,以追加的形式写入内容
        FileOutputStream in = new FileOutputStream("d:/data.txt",true);
        FileChannel channel = in.getChannel();
        //在内存中创建一块缓存区域
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        /**
         * buffer读数据有两种方法
         *  1. buffer.get
         *  2. channel.read
         *buffer写数据有两种方法
         * 1.buffer.put
         * 2.channel.write
         */
        for (int i=1;i<=10;i++) {
            buffer.clear();
            buffer.put("从入门到放弃".getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            channel.write(buffer);
        }
        channel.close();
        in.close();
    }

}
