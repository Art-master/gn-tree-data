package com.history.tree.config;

import io.r2dbc.pool.PoolingConnectionFactoryProvider;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.transaction.ReactiveTransactionManager;

import java.sql.DriverManager;


@Configuration
@Slf4j
@EnableR2dbcRepositories(value = "com.core.app.repository")
public class R2DBCConfig extends AbstractR2dbcConfiguration {

    @Value("${spring.r2dbc.properties.host}")
    private String host;
    @Value("${spring.r2dbc.properties.port}")
    private int port;
    @Value("${spring.r2dbc.name}")
    private String database;
    @Value("${spring.r2dbc.username}")
    private String username;
    @Value("${spring.r2dbc.password}")
    private String password;
    @Value("${spring.r2dbc.pool.max-size}")
    private String maxPoolSize;
    @Value("${spring.jpa.properties.hibernate.default_schema}")
    private String schema;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.liquibase.change-log}")
    private String changeLog;

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(ConnectionFactoryOptions.DRIVER, "postgresql")
                        .option(ConnectionFactoryOptions.HOST, host)
                        .option(ConnectionFactoryOptions.PORT, port)
                        .option(ConnectionFactoryOptions.USER, username)
                        .option(ConnectionFactoryOptions.PASSWORD, password)
                        .option(ConnectionFactoryOptions.DATABASE, database)
                        .option(PoolingConnectionFactoryProvider.MAX_SIZE, Integer.parseInt(maxPoolSize))
                        .option(Option.valueOf("schema"), schema)
                        .build()
        );
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        var initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);

        updateDatabaseStructure();

        return initializer;
    }

    private void updateDatabaseStructure() {
        try {
            var connection = DriverManager.getConnection(jdbcUrl, username, password);
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            final Liquibase liquibase = new Liquibase(changeLog, new ClassLoaderResourceAccessor(), database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
