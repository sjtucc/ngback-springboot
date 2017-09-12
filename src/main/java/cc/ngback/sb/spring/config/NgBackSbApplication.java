package cc.ngback.sb.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@PropertySource(value = { "classpath:jdbc.properties"})
@ComponentScan(basePackages = "cc.ngback.sb")
@SpringBootApplication
public class NgBackSbApplication extends SpringBootServletInitializer{

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driverClassName}")
    private String jdbcDriverClassName;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
    	DruidDataSource datasource = new DruidDataSource ();
        // 数据库驱动
    	datasource.setDriverClassName(jdbcDriverClassName);
        // 相应驱动的jdbcUrl
    	datasource.setUrl(jdbcUrl);
        // 数据库的用户名
    	datasource.setUsername(jdbcUsername);
        // 数据库的密码
    	datasource.setPassword(jdbcUsername);
        // 检查数据库连接池中空闲连接的间隔时间，单位是分，默认值：240，如果要取消则设置为0
    	datasource.setTimeBetweenEvictionRunsMillis(60);
        // 每个分区最大的连接数
    	datasource.setMaxActive(100);
        return datasource;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(NgBackSbApplication.class);
    }

}
