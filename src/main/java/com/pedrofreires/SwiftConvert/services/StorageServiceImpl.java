package com.pedrofreires.SwiftConvert.services;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import com.pedrofreires.SwiftConvert.domain.storage.StorageService;
import com.pedrofreires.SwiftConvert.config.storageProperties.StorageProperties;
import com.pedrofreires.SwiftConvert.domain.storage.exceptions.StorageException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;


@Service
public class StorageServiceImpl implements StorageService {

    private final Path destinationFile;

    @Autowired
    public StorageServiceImpl(StorageProperties properties) throws IOException {
        this.destinationFile = Path.of(properties.getLocation());

        if( !Files.exists(this.destinationFile.toAbsolutePath()) ) {
            Files.createDirectory(this.destinationFile.toAbsolutePath());
        }
    }

    @Override
    public String store(@NonNull MultipartFile file, String fileName){

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            Path destinationFile = this.destinationFile
                    .resolve( Paths.get(fileName)).normalize().toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
        // location final of the file.
        return destinationFile.toAbsolutePath().toString() + "/" + fileName;
    }
}
