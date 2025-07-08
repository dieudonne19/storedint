package com.dim.storedint.endpoint.rest.controller.storedint;


import com.dim.storedint.PojaGenerated;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ThreadLocalRandom;

import static java.io.File.createTempFile;

@PojaGenerated
@RestController
@AllArgsConstructor
public class StoredIntController {

    @GetMapping("/stored-int")
    public String store_int() {
        return this.storeInt();
    }

    @SneakyThrows
    private String storeInt() {
        var fileSuffix = ".txt";
        var filePrefix = "stored-int";
        var bucketKey = filePrefix + fileSuffix;


        var isFileExists = new File("/tmp/" + bucketKey);
        if (Files.exists(isFileExists.toPath())) {
            return Files.readString(isFileExists.toPath());
        }

        var fileToUpload = createTempFile(filePrefix, fileSuffix);

        long randomNumber = ThreadLocalRandom.current().nextLong(10, 20);
        writeMessageIntoFile(randomNumber, fileToUpload);
        return Files.readString(fileToUpload.toPath());
    }


    private void writeMessageIntoFile(long message, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(message + "");
        writer.close();
    }
}
