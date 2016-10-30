/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.thesam.myapplication.backend;

import com.example.TellJock;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.thesam.example.com",
                ownerName = "backend.myapplication.thesam.example.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "sayJock")
    public MyBean sayJock() {
        MyBean response = new MyBean();
        TellJock tellJock = new TellJock();
        response.setData(tellJock.getJokes());

        return response;
    }

}
