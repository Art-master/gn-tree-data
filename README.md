# THREE DATA SERVICE

## Dev guid

### First start

1. Install PostgreSQL and PgAdmin if you haven't already installed its
2. Create database with name `tree_data`
3. Add correct charset tot IDEA:
    - move to  *Help* -> *Edit custom VM option*
    - add line `-Dfile.encoding=UTF-8`
    - add line `-Dconsole.encoding=UTF-8`
    - restart IDEA
4. Open `Application.Kt` class in IDEA and press `Run` button snd stop at once
5. Open `edit Configurations` in upper menu
6. Copy and paste env variables (replace values if need):
      ```shell
    APP_PORT=8081;
    DB_USER=postgres;
    DB_PASSWORD=postgres;
    DB_NAME=tree_data;
    DB_HOST=localhost;
    DB_PORT=5432;
    DB_SCHEMA=public;
    KEYCLOAK_ADDR=http://localhost:8484
    ```
7. start liquibase database initialisation by gradle command (replace values if need):
   `gradle update -DDB_USER=postgres -DDB_PASSWORD=postgres -DDB_NAME=tree_data -DDB_HOST=localhost -DDB_PORT=5432`
8. Also, you can add gradle command in run configuration ("before launch" section).
   And after this manipulation liquibase will start before launch every time after you press run button.
   Copy and paste in "Arguments" field:
   ```shell
    -DDB_USER=postgres
    -DDB_PASSWORD=postgres
    -DDB_NAME=tree_data
    -DDB_HOST=localhost
    -DDB_PORT=5432
   ```

