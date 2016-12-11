package com.pjp.services;

import com.pjp.spring.config.AppConfiguration;
import com.pjp.spring.context.ApplicationContextProvider;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Elias on 11/12/2016.
 */
public class StateOfTheWebVerticle extends AbstractVerticle {

    @Override
    public void start(){
        saveAppContext();
        Vertx.vertx().deployVerticle("com.pjp.services.CrawlingVerticle", new DeploymentOptions().setWorker(true),
                res -> {
                    if (res.succeeded()) {
                        System.out.println("Crawling Verticle Deployment id: " + res.result());
                    } else {
                        System.out.println("Crawling Verticle failed to deploy Cause: " + res.cause());
                    }
                });
    }
    private void saveAppContext(){
        ApplicationContextProvider provider = new ApplicationContextProvider();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        provider.setApplicationContext(context);
    }

}
