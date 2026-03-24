package com.journaldev.jsfBeans;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class FileUploadBean {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadBean.class);
    private String fileContent;
    private String fileName;

    public FileUploadBean() {
        logger.info("=====================FileUploadBean===========================");
        System.out.println("\n\n************** constructor FileUploadBean.FileUploadBean");
    }

    public void handleUpload(FileUploadEvent event) {
        System.out.println("\n\n----------------- FileUploadBean.handleUpload");

        UploadedFile file = event.getFile();
        byte[] contents = file.getContents();
        fileContent = new String(contents);
        fileName = file.getFileName();
    }

    public String getFileContent() {
        return fileContent;
    }

    public String getFileName() {
        return fileName;
    }
}