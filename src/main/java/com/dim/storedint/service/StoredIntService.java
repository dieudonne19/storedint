package com.dim.storedint.service;


import com.dim.storedint.file.bucket.BucketComponent;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import static java.io.File.createTempFile;

@Service
@AllArgsConstructor
public class StoredIntService {
    private final BucketComponent bucketComponent;

    @SneakyThrows
    public String storeInt() {
        var fileSuffix = ".txt";
        var filePrefix = "stored-int";
        var bucketKey = filePrefix + fileSuffix;

        var isFileExists = bucketComponent.download(bucketKey);
        if (isFileExists.isFile()) {
            return Files.readString(isFileExists.toPath());
        }


        var fileToUpload = createTempFile(filePrefix, fileSuffix);

        long randomNumber = ThreadLocalRandom.current().nextLong(10, 20);
        writeMessageIntoFile(randomNumber, fileToUpload);

        bucketComponent.upload(fileToUpload, bucketKey);
        return bucketComponent.presign(bucketKey, Duration.ofSeconds(30)).toString();
    }

    private void writeMessageIntoFile(long message, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(message + "");
        writer.close();
    }
}

