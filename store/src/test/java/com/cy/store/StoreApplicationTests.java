package com.cy.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class StoreApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private DataSource dataSource;

    @Test
    public void getConnection() throws Exception {
        System.out.println(dataSource.getConnection());
    }

}
