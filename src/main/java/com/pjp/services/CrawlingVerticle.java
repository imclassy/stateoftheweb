package com.pjp.services;

import com.pjp.business.scraping.Crawler;
import com.pjp.spring.config.AppConfiguration;
import com.pjp.data.config.dao.LinksDAO;
import com.pjp.spring.context.ApplicationContextProvider;
import io.vertx.core.AbstractVerticle;
import org.jacpfx.vertx.spring.SpringVerticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Elias on 07/12/2016.
 */
public class CrawlingVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception{
        System.out.println("Deploying crawling verticle..");
        LinksDAO linksDAO = ApplicationContextProvider.getApplicationContext().getBean(LinksDAO.class);
        if(linksDAO == null){
            throw  new  Exception("dao nulo");
        }
        Crawler crawler = new Crawler(linksDAO);
        crawler.crawl();
    }
}
