package com.core.app.config

import io.r2dbc.pool.PoolingConnectionFactoryProvider
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.Option
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator
import org.springframework.transaction.ReactiveTransactionManager


@Configuration
@EnableR2dbcRepositories("com.core.app.repository")
class R2DBCConfig(
    @Value("\${spring.r2dbc.properties.host}") private val host: String,
    @Value("\${spring.r2dbc.properties.port}") private val port: Int,
    @Value("\${spring.r2dbc.name}") private val database: String,
    @Value("\${spring.r2dbc.username}") private val username: String,
    @Value("\${spring.r2dbc.password}") private val password: String,
    @Value("\${spring.r2dbc.pool.max-size}") private val maxPoolSize: String,
    @Value("\${spring.jpa.properties.hibernate.default_schema}") private val schema: String
): AbstractR2dbcConfiguration()  {

    @Bean
    override fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "postgresql")
                .option(ConnectionFactoryOptions.HOST, host)
                .option(ConnectionFactoryOptions.PORT, port)
                .option(ConnectionFactoryOptions.USER, username)
                .option(ConnectionFactoryOptions.PASSWORD, password)
                .option(ConnectionFactoryOptions.DATABASE, database)
                .option(PoolingConnectionFactoryProvider.MAX_SIZE, maxPoolSize.toInt())
                .option(Option.valueOf("schema"), schema)
                .build()
        )
    }

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager {
        return R2dbcTransactionManager(connectionFactory)
    }

    @Bean
    fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory)
        val populator = CompositeDatabasePopulator()
        populator.addPopulators(ResourceDatabasePopulator(ClassPathResource("/sql/initial_schema.sql")))
        initializer.setDatabasePopulator(populator)
        return initializer
    }
}
