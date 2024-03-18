package com.test.db2any.dao;

import com.test.db2any.mapper.DataMapper;
import com.test.db2any.model.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@PropertySource("classpath:queries.properties")
public class DataDAOImpl implements DataDAO {

    JdbcTemplate jdbcTemplate;
    @Value("${sqlQuery.findDataById}")
    private String sqlFindData;

    @Autowired
    public DataDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Data getDataById(Long id) {
        return jdbcTemplate.
                queryForObject(sqlFindData, new Object[]{id}, new DataMapper());
    }
}
