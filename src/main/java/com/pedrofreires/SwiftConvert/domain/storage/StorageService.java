package com.pedrofreires.SwiftConvert.domain.storage;

import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String store(@NonNull MultipartFile file, String fileName);
}
