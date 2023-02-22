import java.io.*;
import java.math.BigInteger;

public class FileTest1 {
    public static void main(String[] args) throws IOException {
        byte[] data = new byte[48*1024];

        int k = 1;
        InputStream in = new FileInputStream("d:/doctor-2022.h264");
        OutputStream out = new FileOutputStream("d:/doctor-2023.h264",true);
        while (true){
            k = in.read(data);
            if (k == -1){
                break;
            }
            byte[] temp = new byte[k];
            System.arraycopy(data,0,temp,0,k);
            out.write(temp);
            out.flush();
        }
        in.close();

        out.flush();
        out.close();
        //BigInteger bigInteger = new BigInteger(1,data);
        //System.out.println(bigInteger.toString(16));
        System.out.println("写入完成");
    }
}
