package com.shawn.test.spring.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shawn on 2017/7/31.
 */
public class DemoSpringDao extends NamedParameterJdbcDaoSupport {
    private String var1;

    private String var2;

    private final static String INSERT_SQL = "insert into Table1 (c1, c2) values (:c1, :c2)";

    private DataSourceTransactionManager dataSourceTransactionManager;

    private TransactionDefinition transactionDefinition;

    private TransactionTemplate transactionTemplate;

    public DemoSpringDao() {}

    public void insert(String c1, String c2) throws RuntimeException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("c1", c1);
        params.put("c2", c2);

        getNamedParameterJdbcTemplate().update(INSERT_SQL, params);

//        throw new RuntimeException();
    }

    public void simpleTransactionWithAPI() {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);

        try {
            insert("c11", "c12");
        } catch (RuntimeException e) {
            dataSourceTransactionManager.rollback(transactionStatus);
        }

        dataSourceTransactionManager.commit(transactionStatus);
    }

    public void simpleTransactionWithTemplate()  {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            public Object doInTransaction(TransactionStatus status) throws RuntimeException {
                try {
                    insert("c11", "c12");
                } catch (RuntimeException e) {
                    System.out.println(e);
                    status.setRollbackOnly();
                }

                return null;
            }
        });
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void simpleTransaction() throws RuntimeException{
        insert("c11", "c12");
//        throw new RuntimeException();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(new String[]{"config/spring/appcontext-db.xml", "config/spring/appcontext-transaction.xml"});
        DemoSpringDao demoSpringDao = (DemoSpringDao) applicationContext.getBean("demoSpringDao");

        try {
//        demoSpringDao.insert("c1value", "c2value");
//        demoSpringDao.simpleTransactionWithAPI();
//        demoSpringDao.simpleTransactionWithTemplate();
            demoSpringDao.simpleTransaction();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setDataSourceTransactionManager(DataSourceTransactionManager dataSourceTransactionManager) {
        this.dataSourceTransactionManager = dataSourceTransactionManager;
    }

    public void setTransactionDefinition(TransactionDefinition transactionDefinition) {
        this.transactionDefinition = transactionDefinition;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
