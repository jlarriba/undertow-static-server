package com.jlarriba.uss;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.resource.ClassPathResourceManager;

/**
 *
 * @author jlarriba
 */
public class App {
  
    public static void main(String args[]) throws Exception {
        
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "0.0.0.0")
                .setHandler(Handlers.path()
                        .addPrefixPath("/", Handlers.resource(new ClassPathResourceManager(App.class.getClassLoader(), "webapp"))
                                .addWelcomeFiles("index.html")
                                .setDirectoryListingEnabled(true)))
                .build();
        server.start();
    }
}