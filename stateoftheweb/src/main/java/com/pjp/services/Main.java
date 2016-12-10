package com.pjp.services;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

/**
 * Created by Elias on 07/12/2016.
 */
public class Main {
    public static void main(String[] args) {
        Vertx.vertx().deployVerticle("com.pjp.services.CrawlingVerticle",new DeploymentOptions().setWorker(true));
    }
}
