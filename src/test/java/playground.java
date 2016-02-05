import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by bharel on 2/5/2016.
 */
public class playground {

    @Test
    public void testRunningExe()
                throws Exception {
//        URL resource = this.getClass().getClassLoader().getResource("generator-windows-amd64.exe");
//        ProcessBuilder processBuilder = new ProcessBuilder(resource.toString());
//        Process process = processBuilder.start();
//
//        InputStream inputStream = process.getInputStream();
//        PipedOutputStream pipedOutputStream = new PipedOutputStream();
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(pipedOutputStream));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        //        Thread thread = getThread(pipedOutputStream);
        //
        //        thread.start();
        //
        //        while (true) {
        //            String line = reader.readLine();
        //            if (line == null) {
        //                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        //                continue;
        //            }
        //
        //            bufferedWriter.write(line);
        //            bufferedWriter.newLine();
        //        }
        //    }
    }
}
