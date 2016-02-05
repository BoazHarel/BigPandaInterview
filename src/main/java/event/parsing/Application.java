package event.parsing;

import event.parsing.backend.EventProducer;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by bharel on 2/4/2016.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args)
                throws IOException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        EventProducer eventProducer = applicationContext.getBean(EventProducer.class);

        String pathToExecutable = copyExecutableToTemp();
        ProcessBuilder processBuilder = new ProcessBuilder(pathToExecutable);

        try {
            Process process = processBuilder.start();
            InputStream processInputStream = process.getInputStream();
            eventProducer.setStream(processInputStream);
            eventProducer.startProducing();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String copyExecutableToTemp()
                throws IOException {
        InputStream resourceAsStream = Application.class.getResourceAsStream("/generator-windows-amd64.exe");
        Path tempFilePath = Files.createTempFile("jsonCreator", "tmp");
        FileUtils.copyInputStreamToFile(resourceAsStream, tempFilePath.toFile());
        return tempFilePath.toString();
    }
}
