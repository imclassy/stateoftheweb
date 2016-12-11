package com.pjp.services;

import com.pjp.spring.config.AppConfiguration;
import com.pjp.spring.context.ApplicationContextProvider;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Elias on 07/12/2016.
 */
public class Main {
    public static void main(String[] args) {

        Vertx.vertx().deployVerticle("com.pjp.services.StateOfTheWebVerticle", res -> {
            if(res.succeeded()){
                System.out.println("Successfully deployed StateOfTheWeb Verticle!");
            }
            else{
                System.out.println("Failed to deploy StateOfTheWeb Verticle Cause: " + res.cause());
            }
        });
    }
}
