package com.pjp.data.config.dao;

import com.pjp.business.core.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by Elias on 10/12/2016.
 */
@Repository
public class LinksDAO extends JdbcDaoSupport {

    @Autowired
    public LinksDAO(DataSource dataSource){
        this.setDataSource(dataSource);
    }

    public boolean existsByUrl(String url){
        String sql = "select exists_link(?)";
        int res = this.getJdbcTemplate().queryForObject(sql, new Object[]{url}, Integer.class);
        return res == 1;
    }

    public void save(Link link){
        String sql = "insert into links (url) values (?)";
        this.getJdbcTemplate().update(sql, link.getUrl());
    }

}
