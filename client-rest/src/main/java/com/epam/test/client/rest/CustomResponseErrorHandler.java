package com.epam.test.client.rest;

import com.epam.test.client.exception.ServerDataAccessException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by master on 28.2.17.
 */
public class CustomResponseErrorHandler implements ResponseErrorHandler {

    private ResponseErrorHandler errorHandler=new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return errorHandler.hasError(clientHttpResponse);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
       throw new ServerDataAccessException(response.getStatusCode()+ ": "+ response.getStatusText()+": "+response.getBody());
    }
}
