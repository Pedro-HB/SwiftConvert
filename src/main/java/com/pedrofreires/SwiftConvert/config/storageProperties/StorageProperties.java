package com.pedrofreires.SwiftConvert.config.storageProperties;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class StorageProperties {

    private final String location = "/DataSaved";
}
