Run db container:
`docker run --rm --name users-db -e POSTGRES_USER=test -e POSTGRES_PASSWORD=test -e POSTGRES_USER=users -p 15452:5432 -d postgres:13.3-alpine`



Run db migration:
`docker run --rm --name users-migration --link users-db:db_host -v pathToMigration:flyway/sql flyway/flyway:7-alpine -url=jdbc:postgresql://db/users -schemas=keyauto -user=test -password=test -connectRetries=30 migrate`

path to migration files:
`<full_path>/withmq/testcase/src/main/resources/db/migration`
