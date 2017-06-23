package Training.Spring.config;

import Training.Dao.AdvDAO;
import Training.Dao.Impl.AdvDaoJdbcImpl;
import Training.Dao.Impl.UserDaoJdbcImpl;
import Training.Dao.UserDao;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Администратор on 25.04.2017.
 */
@Configuration
@ComponentScan("Training")
@EnableWebMvc
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurerAdapter{

    @Bean
    public DataSource dataSource() {
        Properties properties = dbConnectionProperties();
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(properties.getProperty("url"));
        mysqlDataSource.setUser(properties.getProperty("user"));
        mysqlDataSource.setPassword(properties.getProperty("password"));
        return mysqlDataSource;
    }

    @Bean
    public Properties dbConnectionProperties(){
        InputStream fis;
        Properties properties = new Properties();
        try {
            fis = (new ClassPathResource("dbConnection.properties")).getInputStream();
            properties.load(fis);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public Properties hibernateProperties(){
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.MySQLDialect" );
        properties.put( "hibernate.connection.driver_class", "com.mysql.jdbc.Driver" );
        properties.put( "hibernate.hbm2ddl.auto", "update" );
        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource( dataSource() );
        em.setPackagesToScan("Training");
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties() );
        em.setPersistenceUnitName( "mytestdomain" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();;
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject() );
        return transactionManager;
    }

    @Bean
    public AdvDAO advDAO(){
        return new AdvDaoJdbcImpl();
    }

    @Bean
    public UserDao userDao(){
        return new UserDaoJdbcImpl();
    }

    @Bean
    public CommonsMultipartResolver filterMultipartResolver(){
        CommonsMultipartResolver cmr =  new CommonsMultipartResolver();
        cmr.setDefaultEncoding("utf-8");
        return cmr;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver(){
        UrlBasedViewResolver resolver =  new UrlBasedViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);
        return resolver;
    }

    @Override
    public void addResourceHandlers( final ResourceHandlerRegistry registry ) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/administration/images/**").addResourceLocations("/resources/images/");
        registry.addResourceHandler("/auth/images/**").addResourceLocations("/resources/images/");
    }
}
