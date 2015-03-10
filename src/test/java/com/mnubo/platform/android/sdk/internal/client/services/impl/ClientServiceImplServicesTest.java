package com.mnubo.platform.android.sdk.internal.client.services.impl;

import com.mnubo.platform.android.sdk.internal.client.services.ClientService;
import com.mnubo.platform.android.sdk.internal.AbstractServicesTest;
import com.mnubo.platform.android.sdk.models.security.ResetPassword;
import com.mnubo.platform.android.sdk.models.security.UserConfirmation;

import org.junit.Before;
import org.junit.Test;

import java.net.URI;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ClientServiceImplServicesTest extends AbstractServicesTest {

    private ClientService clientService;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        clientService = new ClientServiceImpl(PLATFORM_BASE_URL, mockedRestTemplate);
    }

    @Test
    public void testCreateUser() throws Exception {

        String calledUrl = buildPath("/users");

        clientService.createUser(expectedUser);
        verify(mockedRestTemplate, only()).postForLocation(calledUrl, expectedUser);

    }

    @Test
    public void testConfirmCreation() throws Exception {

        UserConfirmation userConfirmation = new UserConfirmation("password", "token");

        String calledUrl = buildPath("/users/test/confirmation");
        when(mockedRestTemplate.postForLocation(calledUrl, userConfirmation)).thenReturn(new URI(PLATFORM_BASE_URL));

        clientService.confirmUserCreation("test", userConfirmation);
        verify(mockedRestTemplate, only()).postForLocation(calledUrl, userConfirmation);

    }

    @Test
    public void testResetPassword() throws Exception {
        String calledUrl = buildPath("/users/test/password");

        clientService.resetPassword("test");

        verify(mockedRestTemplate, only()).delete(calledUrl);
    }

    @Test
    public void testConfirmPasswordUpdate() throws Exception {
        ResetPassword resetPassword = new ResetPassword("token", "password", "password");

        String calledUrl = buildPath("/users/test/password");
        when(mockedRestTemplate.postForLocation(calledUrl, resetPassword)).thenReturn(new URI(PLATFORM_BASE_URL));

        clientService.confirmPasswordReset("test", resetPassword);

        verify(mockedRestTemplate, only()).postForLocation(calledUrl, resetPassword);

    }
}