package com.pjp.business.scraping;

import com.pjp.business.core.Link;
import com.pjp.data.config.dao.LinksDAO;
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
    private final UrlValidator urlValidator;
    private final LinksDAO linksDAO;
    public Crawler(LinksDAO linksDAO){
        this.linksDAO = linksDAO;
        urlValidator = new UrlValidator();
    }
    public void crawl(String startingUrl) throws IOException {

        Queue<String> pendingPages = new LinkedList<>();
        pendingPages.add(startingUrl);

        while(!pendingPages.isEmpty()){
            String url = pendingPages.poll();
            if(!linksDAO.existsByUrl(url)){
                System.out.println("Visiting url: " + url);
                Document doc = Jsoup.connect(url)
                        .timeout(10*1000)
                        .userAgent("Mozilla/17.0")
                        .get();
                Link link = new Link();
                link.setUrl(url);
                linksDAO.save(link);
                pendingPages.addAll(extractLinks(doc));
            }
        }
    }

    private Set<String> extractLinks(Document doc){
        Set<String> links = new HashSet<>();
        Elements cssLinks  = doc.select("a");
        cssLinks.stream()
                .map(a -> a.absUrl("href"))
                .filter(urlValidator::isValid)
                .forEach(links::add);
        return links;
    }

}
