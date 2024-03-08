package com.pedrofreires.SwiftConvert.services;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import com.pedrofreires.SwiftConvert.domain.storage.StorageService;
import com.pedrofreires.SwiftConvert.config.storageProperties.StorageProperties;
import com.pedrofreires.SwiftConvert.domain.storage.exceptions.StorageException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FilesystemStorageService implements StorageService {

    private final Path destinationFile;

    @Autowired
    public FilesystemStorageService(StorageProperties properties){
        this.destinationFile = Paths.get(properties.getLocation());
        this.init();
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(destinationFile);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public String store(@NonNull MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            // normalize to remove duplicate name
            Path destinationFile = this.destinationFile
                    .resolve( Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }

        return destinationFile.toAbsolutePath().toString();
    }
}
