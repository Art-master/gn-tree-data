package com.history.tree.config

import io.r2dbc.pool.PoolingConnectionFactoryProvider
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.Option
import liquibase.Contexts
import liquibase.LabelExpression
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.transaction.ReactiveTransactionManager
import java.sql.DriverManager

@Configuration
@EnableR2dbcRepositories(value = ["com.core.app.repository"])
class
R2DBCConfig : AbstractR2dbcConfiguration() {

    private val log: Logger = LoggerFactory.getLogger(R2DBCConfig::class.java.name)

    @Value("\${spring.r2dbc.properties.host}")
    private lateinit var host: String

    @Value("\${spring.r2dbc.properties.port}")
    private lateinit var port: String

    @Value("\${spring.r2dbc.name}")
    private lateinit var database: String

    @Value("\${spring.r2dbc.username}")
    private lateinit var username: String

    @Value("\${spring.r2dbc.password}")
    private lateinit var password: String

    @Value("\${spring.r2dbc.pool.max-size}")
    private lateinit var maxPoolSize: String

    @Value("\${spring.jpa.properties.hibernate.default_schema}")
    private lateinit var schema: String

    @Value("\${spring.datasource.url}")
    private lateinit var jdbcUrl: String

    @Value("\${spring.liquibase.change-log}")
    private lateinit var changeLog: String

    @Bean
    override fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get(
            ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "postgresql")
                .option(ConnectionFactoryOptions.HOST, host)
                .option(ConnectionFactoryOptions.PORT, port.toInt())
                .option(ConnectionFactoryOptions.USER, username)
                .option(ConnectionFactoryOptions.PASSWORD, password)
                .option(ConnectionFactoryOptions.DATABASE, database)
                .option(PoolingConnectionFactoryProvider.MAX_SIZE, maxPoolSize.toInt())
                .option(Option.valueOf("schema"), schema)
                .build()
        )
    }

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory?): ReactiveTransactionManager {
        return R2dbcTransactionManager(connectionFactory!!)
    }

    @Bean
    fun initializer(connectionFactory: ConnectionFactory?): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory!!)
        updateDatabaseStructure()
        return initializer
    }

    private fun updateDatabaseStructure() {
        try {
            val connection = DriverManager.getConnection(jdbcUrl, username, password)
            val database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(connection))
            val liquibase = Liquibase(changeLog, ClassLoaderResourceAccessor(), database)
            liquibase.update(Contexts(), LabelExpression())
        } catch (e: Exception) {
            log.error(e.message, e)
        }
    }
}