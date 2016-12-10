package com.pjp.business.scraping;

/**
 * Created by Elias on 09/12/2016.
 */
public class UrlValidator {

    public boolean isValid(String url){
        if(!url.trim().equals("") && !url.isEmpty()){
            return url.matches("(.*).(html|xhtml)");
        }
        return false;
    }
}
