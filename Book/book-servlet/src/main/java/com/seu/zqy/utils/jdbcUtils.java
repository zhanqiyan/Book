package com.seu.zqy.utils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;
public class jdbcUtils {
    private static SqlSessionFactory factory = null;
    static {
        String resource = "mybatis.xml";
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static SqlSession getSession(){
        SqlSession session = null;
        if (factory != null) {
            try {session = factory.openSession();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return session;
    }

    public static void closeConnection(SqlSession session){
        if (session != null) {
            try {
                session.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}


//    /*
//    在工具类中，将数据库连接池设为全局静态变量，且其创建是通过静态代码实现。
//        这样可以保证类加载完成后数据库连接池就创建完成
//        同时保证一个web工厂复用一个数据库连接池
//     */
//    private static DruidDataSource dataSource = null;
//    static {
//        Properties properties = new Properties();
//        InputStream resourceAsStream = jdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
//        try {
//            properties.load(resourceAsStream);
//            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }