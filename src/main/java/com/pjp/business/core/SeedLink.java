package com.pjp.business.core;

/**
 * Created by Elias on 11/12/2016.
 */
public class SeedLink extends Link{
    public SeedLink (){
        this.type = Link.SEED_TYPE;
    }
    public SeedLink(String url){
        this.url = url;
        this.type = Link.SEED_TYPE;
    }
}
