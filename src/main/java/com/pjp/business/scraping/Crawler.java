package com.pjp.business.scraping;

import com.pjp.business.core.Link;
import com.pjp.business.core.NormalLink;
import com.pjp.business.core.SeedLink;
import com.pjp.data.config.dao.LinksDAO;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Elias on 07/12/2016.
 */
public class Crawler {
    private Logger vertxLogger = LoggerFactory.getLogger(Crawler.class.getName());
    private final UrlValidator urlValidator;
    private final LinksDAO linksDAO;
    public Crawler(LinksDAO linksDAO){
        this.linksDAO = linksDAO;
        urlValidator = new UrlValidator();
    }
    public void crawl() {
        System.out.println("Crawling!");
        Link seedLink = linksDAO.getRandomSeed();
        assert(seedLink != null);
        System.out.println("Seed link: " + seedLink.getUrl());
        Queue<Link> pendingLinks = new LinkedList<>();
        pendingLinks.add(seedLink);

        while(!pendingLinks.isEmpty()){
            Link link = pendingLinks.poll();
            if(!linksDAO.existsByUrl(link)|| link instanceof SeedLink){
                System.out.println("Visiting link: " + link.getUrl());
                if(link instanceof NormalLink){
                    linksDAO.save(link);
                }
                pendingLinks.addAll(extractLinks(link));
            }
        }
    }

    private Set<Link> extractLinks(Link link){
        Set<Link> extractedLinks = new HashSet<>();
        Document doc;
        try {
            doc = Jsoup.connect(link.getUrl())
                    .timeout(10*1000)
                    .userAgent("Mozilla/17.0")
                    .get();
            Elements cssLinks  = doc.select("a");
            cssLinks.stream()
                    .map(a -> a.absUrl("href"))
                    .filter(urlValidator::isValid)
                    .map(NormalLink::new)
                    .forEach(extractedLinks::add);
        } catch (IOException e) {
            vertxLogger.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return extractedLinks;
    }

}
