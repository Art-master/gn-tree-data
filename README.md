**THREE DATA SERVICE**

***Environment variables to run the application*** (replace values if need)
```env
APP_PORT=8081;
DB_USER=postgres;
DB_PASSWORD=admin;
DB_NAME=tree;
DB_HOST=localhost;
DB_PORT=5432;
DB_SCHEMA=public;
KEYCLOAK_ADDR=http://localhost:8484
```


**Liquibase**  
For launching from mvn command line
```shell
mvn liquibase:update -Denv=dev -Dliquibase.url=jdbc:postgresql://{HOST}:{PORT}/{DATABASE} -Dliquibase.username={NAME} -Dliquibase.password={PASSWORD}
```

