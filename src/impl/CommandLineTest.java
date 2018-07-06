package impl;



import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by dyj on 4/16/18.
 */
public class CommandLineTest {

    IOUtils utils = new IOUtils();

    public static void main(String[] args) {
        try {
            String cmd = "ls";
            ProcessBuilder pr = new ProcessBuilder(cmd);
            Process p = pr.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()   ));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            p.waitFor();
            System.out.println("end");
            in.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
