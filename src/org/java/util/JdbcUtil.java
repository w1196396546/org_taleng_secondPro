package org.java.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class JdbcUtil {

    private static DataSource ds;
    static {

        try {
            //加载属性文件
            InputStream in= JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            //创建属性类
            Properties prop=new Properties();
            //用属性类加载流中的信息
            prop.load(in);
            //产生数据源头
            ds= DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDs(){
        return ds;
    }
}
