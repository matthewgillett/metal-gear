package org.finra.metal.gear.Infrastructure;

import org.finra.metal.gear.Sentiment.SentimentData;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@PropertySource(value = { "classpath:database.properties" })
@EnableTransactionManagement
public class InfrastructureConfiguration {

    @Bean
    public AwsClientFactory awsClientFactory() {
        return new AwsClientFactory();
    }

    @Bean
    @Autowired
    public SentimentData sentimentData(AwsClientFactory awsClientFactory) {
        return new SentimentData(awsClientFactory);
    }

}
