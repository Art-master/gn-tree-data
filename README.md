# TREE DATA SERVICE

## Dev guid

### First start

1. Install PostgreSQL and PgAdmin if you haven't already installed them
2. Create database with name `tree_data`
3. Java `version 17` is needed for the service to work. You can install it from IDEA **Project Structure** settings
4. Add correct charset to IDEA:
    - move to  *Help* -> *Edit custom VM option*
    - add line `-Dfile.encoding=UTF-8`
    - add line `-Dconsole.encoding=UTF-8`
    - restart IDEA
5. Create kotlin Run/Debug configuration:
    - `Add Configuration` -> `Add New Configuration` -> `Kotlin`
    - Set `Use classpath of module` to the value `gn-tree-data.main`
    - Select `ApplicationKt` in the `Main class` field
    - Copy and paste env variables (replace values if needed):
      ```shell
        APP_PORT=8081
        DB_USER=postgres
        DB_PASSWORD=postgres
        DB_NAME=tree_data
        DB_HOST=localhost
        DB_PORT=5432
        DB_SCHEMA=public
        KEYCLOAK_ADDR=http://localhost:8484
      ```
6. start liquibase database initialisation by gradle command (replace values if needed): 
   `gradle :db:update -DDB_USER=postgres -DDB_PASSWORD=postgres -DDB_NAME=tree_data -DDB_HOST=localhost -DDB_PORT=5432`
7. Also, you can add gradle `update` command in the run configuration ("before launch" section).
   After this manipulation liquibase will start before launch every time after you click the run button.
   Copy and paste in the "Arguments" field:
   ```shell
    -DDB_USER=postgres
    -DDB_PASSWORD=postgres
    -DDB_NAME=tree_data
    -DDB_HOST=localhost
    -DDB_PORT=5432
   ```

