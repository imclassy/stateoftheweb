package com.pjp.business.core;

/**
 * Created by Elias on 11/12/2016.
 */
public class NormalLink extends Link {
    public NormalLink (){
        this.type = Link.NORMAL_TYPE;
    }
    public NormalLink(String url){
        this.url = url;
        this.type = Link.NORMAL_TYPE;
    }
}
