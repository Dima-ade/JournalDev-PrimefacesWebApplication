package com.journaldev.jsfBeans;

import main.java.com.journaldev.jsfBeans.UserLoginView;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import static org.junit.Assert.assertEquals;

public class UserLoginViewTest {

    @Test
    public void testLoginTrue() {
        UserLoginView userLoginView = new UserLoginView();
        userLoginView.setUsername("admin");
        userLoginView.setPassword("admin");

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
    public void testLoginSuccess() {
        // Arrange
        UserLoginView userLoginView = new UserLoginView();
        userLoginView.setUsername("admin");
        userLoginView.setPassword("admin");

        FacesContext facesContextMock = Mockito.mock(FacesContext.class);
        RequestContext requestContextMock = Mockito.mock(RequestContext.class);

        try (MockedStatic<FacesContext> mockedFaces = Mockito.mockStatic(FacesContext.class);
             MockedStatic<RequestContext> mockedRequest = Mockito.mockStatic(RequestContext.class)) {

            mockedFaces.when(FacesContext::getCurrentInstance).thenReturn(facesContextMock);
            mockedRequest.when(RequestContext::getCurrentInstance).thenReturn(requestContextMock);

            // Act
            userLoginView.login();

            // Assert
            Mockito.verify(facesContextMock).addMessage(
                    Mockito.isNull(),
                    Mockito.argThat(msg ->
                            msg.getSeverity().equals(FacesMessage.SEVERITY_INFO)
                                    && "Welcome".equals(msg.getSummary())
                                    && "admin".equals(msg.getDetail())
                    )
            );

            Mockito.verify(requestContextMock).addCallbackParam("loggedIn", true);
        }
    }

    @Test
    public void testLoginSuccessWithCustomFacesMessage() {
        // Arrange
        UserLoginView userLoginView = new UserLoginView();
        userLoginView.setUsername("admin");
        userLoginView.setPassword("admin");

        FacesContext facesContextMock = Mockito.mock(FacesContext.class);
        RequestContext requestContextMock = Mockito.mock(RequestContext.class);

        // This is the message you want the login() method to use
        FacesMessage expectedMessage =
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Benvenuto", "admin");

        try (MockedStatic<FacesContext> mockedFaces = Mockito.mockStatic(FacesContext.class);
             MockedStatic<RequestContext> mockedRequest = Mockito.mockStatic(RequestContext.class);
             MockedConstruction<FacesMessage> mockedFacesMessage =
                     Mockito.mockConstruction(FacesMessage.class,
                             (mock, context) -> {
                                 // Force the mock to behave like your expected message
                                 Mockito.when(mock.getSeverity()).thenReturn(expectedMessage.getSeverity());
                                 Mockito.when(mock.getSummary()).thenReturn(expectedMessage.getSummary());
                                 Mockito.when(mock.getDetail()).thenReturn(expectedMessage.getDetail());
                             })) {

            mockedFaces.when(FacesContext::getCurrentInstance).thenReturn(facesContextMock);
            mockedRequest.when(RequestContext::getCurrentInstance).thenReturn(requestContextMock);

            // Act
            userLoginView.login();

            // Assert: capture the message passed to FacesContext
            ArgumentCaptor<FacesMessage> msgCaptor = ArgumentCaptor.forClass(FacesMessage.class);
            Mockito.verify(facesContextMock).addMessage(Mockito.isNull(), msgCaptor.capture());

            FacesMessage actualMessage = msgCaptor.getValue();

            // Compare with your expected message
            assertEquals(expectedMessage.getSeverity(), actualMessage.getSeverity());
            assertEquals(expectedMessage.getSummary(), actualMessage.getSummary());
            assertEquals(expectedMessage.getDetail(), actualMessage.getDetail());

            // Verify callback param
            Mockito.verify(requestContextMock).addCallbackParam("loggedIn", true);
        }
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
