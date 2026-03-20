package com.journaldev.jsfBeans;

import main.java.com.journaldev.jsfBeans.FileUploadBean;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public class FileUploadBeanTest {

    @Test
    public void testHandleUpload() {
        FileUploadBean fileUploadBean = new FileUploadBean();
        FileUploadEvent fileUploadEventMock = Mockito.mock(FileUploadEvent.class);

        UploadedFile fileMock = Mockito.mock(UploadedFile.class);
        byte[] content = new byte[0];
        String fileName = "abc";
        Mockito.when(fileMock.getContents()).thenReturn(content);
        Mockito.when(fileMock.getFileName()).thenReturn(fileName);

        Mockito.when(fileUploadEventMock.getFile()).thenReturn(fileMock);

        fileUploadBean.handleUpload(fileUploadEventMock);

        Assert.assertNotNull(fileUploadBean.getFileName());
        Assert.assertEquals(fileName, fileUploadBean.getFileName());

        //Mockito.verify(fileMock.getContents()).
    }


}
