package es.jlarriba.uss;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.util.Headers;

/**
 *
 * @author jlarriba
 */
public class App {

    public static void main(String args[]) throws Exception {

        Undertow server = Undertow.builder()
                .addHttpListener(8080, "0.0.0.0")
                .setHandler(Handlers.path()
                        .addPrefixPath("/healthz", new HttpHandler() {
                            @Override
                            public void handleRequest(HttpServerExchange hse) throws Exception {
                                hse.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                                hse.getResponseSender().send("OK");
                            }
                        })
                        .addPrefixPath("/", Handlers.resource(new ClassPathResourceManager(App.class.getClassLoader(), "webapp"))
                                .addWelcomeFiles("index.html")
                                .setDirectoryListingEnabled(true)))
                .build();
        server.start();
    }
}
