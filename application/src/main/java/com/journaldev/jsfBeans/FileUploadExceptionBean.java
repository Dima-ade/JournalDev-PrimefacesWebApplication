package com.journaldev.jsfBeans;
import com.journaldev.exception.ItemNotFoundException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class FileUploadExceptionBean {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadExceptionBean.class);
    private String fileContent;
    private String fileName;

    public FileUploadExceptionBean() {
        logger.info("=====================FileUploadExceptionBean===========================");
    }

    public void handleUpload(FileUploadEvent event) throws ItemNotFoundException {
        logger.info("\n\n----------------- FileUploadExceptionBean.handleUpload");

        UploadedFile file = null;
        throw new ItemNotFoundException("The file is null");
    }

    public String getFileContent() {
        return fileContent;
    }

    public String getFileName() {
        return fileName;
    }
}