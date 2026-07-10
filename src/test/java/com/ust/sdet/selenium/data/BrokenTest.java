package com.ust.sdet.selenium.data;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.ust.sdet.selenium.data.OrderBuilder.anOrder;

@Testcontainers

@Tag("integration")

public class BrokenTest {

    @Container

    static MySQLContainer mysql =

            new MySQLContainer<>("mysql:8.0")

                    .withDatabaseName("GradleTest")

                    .withUsername("athuldev")

                    .withPassword("root");

    static OrderRepository repository;

    static OrderFactory factory;

    @BeforeAll

    static void migrateSchema() {

        Flyway.configure()

                .dataSource(

                        mysql.getJdbcUrl(),

                        mysql.getUsername(),

                        mysql.getPassword()

                )

                .locations("classpath:db/migration")

                .load()

                .migrate();

        repository = new OrderRepository(

                mysql.getJdbcUrl(),

                mysql.getUsername(),

                mysql.getPassword()

        );

        factory = new OrderFactory(repository);

    }

    @BeforeEach

    void resetMutableTables() {

        repository.resetMutableTables();

    }

    @Test

    void thisistruetest() {

        assertEquals(

                4,

                repository.referenceStatusCount(),

                "Reference statuses are seeded by migration"

        );

        assertEquals(

                0,

                repository.count(),

                "Per-test order rows should not come from migrations"

        );

    }
    @Test
    void thsisfailtest()
    {
        {

            assertEquals(

                    4,

                    repository.referenceStatusCount(),

                    "Reference statuses are seeded by migration"

            );

            assertEquals(

                    8,

                    repository.count(),

                    "Per-test order rows should not come from migrations"

            );

        }
    }

    @Test
    void brokenTestExample() {

        String value = null;

        value.length();

    }
    @Test
    void databaseUnavailableExamplebroken() {

        throw new RuntimeException(
                "Database connection failed"
        );

    }
    @Test
    void brokenTestExample2() {

        String value = null;

        value.length();

    }
    @Test
    @Disabled
    void skippedtest1() {

        String value = null;

        value.length();

    }
    @Test
    @Disabled
    void skippedtest2() {

        String value = null;

        value.length();

    }

}
