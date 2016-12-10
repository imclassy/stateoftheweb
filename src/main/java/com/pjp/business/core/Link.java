package com.pjp.business.core;

import java.sql.Timestamp;

/**
 * Created by Elias on 08/12/2016.
 */
public class Link {
    public static final String SEED_TYPE = "seed";
    public static final String NORMAL_TYPE = "normal";
    private int id;
    private String url;
    private Timestamp lastVisited;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(Timestamp lastVisited) {
        this.lastVisited = lastVisited;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
