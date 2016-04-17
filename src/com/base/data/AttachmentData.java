package com.base.data;

import java.io.File;
import java.nio.file.Paths;

public class AttachmentData {

    private String attachmentFilePath;

    public AttachmentData(String attachmentFilePath) {
        this.attachmentFilePath = attachmentFilePath;
    }

    public AttachmentData() {
        this.attachmentFilePath = System.getProperty("core.dir") + String.format("%1$sdata%1$stestFile%1$sdata.zip",
                File.separator);
    }

    public String getAttachmentFilePath() {
        return attachmentFilePath;
    }
}
