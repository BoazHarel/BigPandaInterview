package event.parsing;

import event.parsing.backend.EventProducer;
import event.parsing.backend.EventProducerImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
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
        EventProducer eventProducer = applicationContext.getBean(EventProducerImpl.class);

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
        InputStream resourceAsStream = Application.class.getResourceAsStream(geOsSpecificJsonGenerator());
        Path tempFilePath = Files.createTempFile("jsonCreator", "tmp");
        FileUtils.copyInputStreamToFile(resourceAsStream, tempFilePath.toFile());
        return tempFilePath.toString();
    }

    private static String geOsSpecificJsonGenerator() {
        String directory = "/jsonGenerators/";
        if (SystemUtils.IS_OS_WINDOWS) {
            return directory + "generator-windows-amd64.exe";
        }
        if (SystemUtils.IS_OS_LINUX) {
            return directory + "generator-linux-amd64";
        }
        if (SystemUtils.IS_OS_MAC) {
            return directory + "generator-macosx-amd64";
        }

        throw new IllegalStateException("Failed to get OS type");
    }
}
