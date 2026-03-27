package com.journaldev.handler;

import com.journaldev.exception.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.context.FacesContext;
import javax.faces.context.ExternalContext;
import java.io.IOException;
import java.util.Iterator;

public class CustomExceptionHandler extends ExceptionHandlerWrapper {

    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);


    private final ExceptionHandler wrapped;

    public CustomExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

    @Override
    public void handle() throws FacesException {
        for (Iterator<ExceptionQueuedEvent> i = getUnhandledExceptionQueuedEvents().iterator(); i.hasNext();) {
            Throwable t = i.next().getContext().getException();

            if (t.getCause().getCause() instanceof ItemNotFoundException) {
                logger.warn("ITEM NOT FOUND EXCEPTION");
                FacesContext fc = FacesContext.getCurrentInstance();
                ExternalContext ec = fc.getExternalContext();

                try {
                    ec.redirect(ec.getRequestContextPath() + "/faces/errorPage.xhtml");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    i.remove();
                }
            }
        }

        wrapped.handle();
    }
}