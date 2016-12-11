package com.pjp.data.config.dao;

import com.pjp.business.core.Link;
import com.pjp.business.core.SeedLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    public boolean existsByUrl(Link link){
        String sql = "select exists_link(?)";
        int res = this.getJdbcTemplate().queryForObject(sql, new Object[]{link.getUrl()}, Integer.class);
        assert(res == 1 || res == 0);
        return res == 1;
    }

    public void save(Link link){
        String sql = "insert into links (url, type) values (?,?)";
        this.getJdbcTemplate().update(sql, link.getUrl(),link.getType());
    }

    public Link getRandomSeed(){
        String sql = "select id, url, last_visited as lastVisited, type from get_random_seed_link()";
        return this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<>(SeedLink.class));
    }

}
