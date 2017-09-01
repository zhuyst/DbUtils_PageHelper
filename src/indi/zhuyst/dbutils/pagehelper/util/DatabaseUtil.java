package indi.zhuyst.dbutils.pagehelper.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseUtil {
    private static BasicDataSource dataSource;

    private static QueryRunner queryRunner;

    static {
        dataSource = new BasicDataSource();

        Properties properties = new Properties();
        InputStream inputStream = DatabaseUtil.class.getResourceAsStream("/jdbc.properties");
        try {
            properties.load(inputStream);
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setInitialSize(Integer.parseInt(properties.getProperty("initialSize")));
            dataSource.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
            dataSource.setMinIdle(Integer.parseInt(properties.getProperty("minIdle")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        queryRunner = new QueryRunner(dataSource);
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }

    public static QueryRunner getQueryRunner() {
        return queryRunner;
    }
}
