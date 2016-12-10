package com.pjp.services;

import com.pjp.business.scraping.Crawler;
import io.vertx.core.AbstractVerticle;

/**
 * Created by Elias on 07/12/2016.
 */
public class CrawlingVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception{
        System.out.println("Deploying crawling verticle..");
        Crawler crawler = new Crawler();
        crawler.crawl("http://colombia.as.com/colombia/");
    }
}
