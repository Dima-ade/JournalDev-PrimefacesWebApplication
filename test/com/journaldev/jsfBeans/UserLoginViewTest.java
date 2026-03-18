package com.journaldev.jsfBeans;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import static net.bytebuddy.matcher.ElementMatchers.any;

public class UserLoginViewTest {

    @Test
    public void testLoginTrue() {
        UserLoginView userLoginView = new UserLoginView();
        userLoginView.setUsername("admin");
        userLoginView.setPassword("admin");

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", "admin");

//        Mockito.when(FacesContext.getCurrentInstance().addMessage(null, facesMessage)).thenReturn(null);

        FacesContext facesContextMock = Mockito.mock(FacesContext.class);
        Mockito.doNothing().when(facesContextMock).addMessage(Mockito.anyString(), Mockito.any(FacesMessage.class));

        RequestContext contextMock = Mockito.mock(RequestContext.class);

        try (MockedStatic<FacesContext> mockFacesContext = Mockito.mockStatic(FacesContext.class)) {
            mockFacesContext.when(FacesContext::getCurrentInstance).thenReturn(facesContextMock);

            try (MockedStatic<RequestContext> mockRequestContext = Mockito.mockStatic(RequestContext.class)) {
                mockRequestContext.when(RequestContext::getCurrentInstance).thenReturn(contextMock);
                userLoginView.login();
            }

        }
        Mockito.verify(contextMock, Mockito.times(1)).addCallbackParam(Mockito.eq("loggedIn"), Mockito.eq(true));

        Assert.assertTrue(true);
    }

    @Test
    public void testLoginFalse() {
        UserLoginView userLoginView = new UserLoginView();
        userLoginView.setUsername("qqq");
        userLoginView.setPassword("qqq");

        FacesContext facesContextMock = Mockito.mock(FacesContext.class);
        Mockito.doNothing().when(facesContextMock).addMessage(Mockito.anyString(), Mockito.any(FacesMessage.class));

        RequestContext contextMock = Mockito.mock(RequestContext.class);

        try (MockedStatic<FacesContext> mockFacesContext = Mockito.mockStatic(FacesContext.class)) {
            mockFacesContext.when(FacesContext::getCurrentInstance).thenReturn(facesContextMock);

            try (MockedStatic<RequestContext> mockRequestContext = Mockito.mockStatic(RequestContext.class)) {
                mockRequestContext.when(RequestContext::getCurrentInstance).thenReturn(contextMock);
                userLoginView.login();
            }

        }
        Mockito.verify(contextMock, Mockito.times(1)).addCallbackParam(Mockito.eq("loggedIn"), Mockito.eq(false));

        Assert.assertTrue(true);
    }


}
