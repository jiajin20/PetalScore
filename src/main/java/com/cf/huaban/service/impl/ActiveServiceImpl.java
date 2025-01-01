package com.cf.huaban.service.impl;

import com.cf.huaban.entity.Active;
import com.cf.huaban.service.ActiveService;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service("ActiveService")
@Transactional
public class ActiveServiceImpl implements ActiveService {
    private final DataSource dataSource;

    // 使用构造方法注入 DataSource
    @Autowired
    public ActiveServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Active active() throws SQLException {
        QueryRunner qr = new QueryRunner(dataSource);
        String sql = "SELECT * FROM activeimformation ";
        try (Connection conn = dataSource.getConnection()) {
            return qr.query(conn, sql, new BeanHandler<>(Active.class));
        }
    }
}
