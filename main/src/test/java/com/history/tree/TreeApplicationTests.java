package com.history.tree;

import com.history.tree.config.R2DBCConfig;
import com.history.tree.repositories.PersonRepository;
import com.history.tree.services.TreeTestDataGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
//@DataR2dbcTest(excludeAutoConfiguration = R2DBCConfig.class)
@SpringBootTest
@TestPropertySource(properties = {
        "DB_USER=postgres",
        "DB_PASSWORD=postgres",
        "DB_NAME=table",
        "DB_PORT=5432",
        "DB_HOST=localhost"})
class TreeApplicationTests {

    //@Autowired
    //private EntityManagerFactory factory;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TreeTestDataGenerator generator;

    @Test
    void contextLoads() {
        //var entityManager = factory.createEntityManager();
/*        generator.generate().subscribe(e1 -> {
            personRepository.findAllByTreeId(47L).doOnNext(e -> {
                System.out.println(e.getId());
            }).subscribe();
        });*/
    }

}
