package com.pedrofreires.SwiftConvert.services.converter;

public enum MimeTypeSupport{

    IMAGE_PNG("image/png", ".png"),
    TEXT_HTML("text/html", ".html"),
    TEXT_PLAIN("text/plain", ".txt"),
    IMAGE_JPEG("image/jpeg", ".jpeg"),
    APPLICATION_PDF("application/pdf", ".pdf");

    final String mimeTypeInput;
    final String extension;

    MimeTypeSupport(String mimeType, String extension){
        this.extension = extension;
        this.mimeTypeInput = mimeType;
    }

    static String getExtension(String mimeType){
        MimeTypeSupport[] supports = MimeTypeSupport.values();

        for (MimeTypeSupport support : supports) {
            if (support.mimeTypeInput.equals(mimeType)) {
                return support.extension;
            }
        }
        return null;
    }
}
