package com.seu.zqy.utils;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;
import javax.sql.DataSource;
import java.util.Properties;


public class dataSourceFactory implements DataSourceFactory {
    private Properties properties;
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public DataSource getDataSource() {
        DataSource dss = null;
        try {
              dss = DruidDataSourceFactory.createDataSource(this.properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dss;
    }
}

//    private Properties properties;
//
//    @Override
//    public void setProperties(Properties properties) {
//        this.properties = properties;
//    }
//    @Override
//    public DataSource getDataSource() {
//        DataSource dss = null;
//        try {
//              dss = DruidDataSourceFactory.createDataSource(this.properties);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dss;
//    }

