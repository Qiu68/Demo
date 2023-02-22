import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        FileInputStream in = new FileInputStream("d:/1.txt");
        FileChannel fileChannel = in.getChannel();

        BufferedInputStream out = new BufferedInputStream(in);


        byte[] data = new byte[48*1024];

        System.out.println(fileChannel.size());

        ByteBuffer date = ByteBuffer.allocate(1024*1024);
        int read=0;

        long size = 0;
       while (true)
       {
           long t = System.currentTimeMillis();
             read = out.read(data);
           System.out.println("---"+out.available());


            if (read == -1){
                System.out.println("读取结束");
               break;
            }
            size = size + read;
           System.out.println("11111");
           System.out.println(System.currentTimeMillis() - t);
           Thread.sleep(1);
        }

        System.out.println(size+"--");
        read = fileChannel.read(date);
        System.out.println("读取的字节数"+read);

//        System.out.println(new String(date.array()));

        System.out.println(fileChannel.size());
    }
}
