package com.emanueleivaldi.jooql;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.emanueleivaldi.jooq.generated.tables.Users.*;

public class App {


    public static void main(String[] args) {
        new App().start();
    }

    public void start() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/postgres",
                    "postgres",
                    "root");
            System.out.println("connection: " + connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DSLContext create = DSL.using(connection, SQLDialect.POSTGRES);

        Result<Record> result = create.select().from(USERS).fetch();

        for (Record r : result) {
            String firstName = r.getValue(USERS.FIRSTNAME);
            String lastName = r.getValue(USERS.LASTNAME);

            System.out.println("first name: " + firstName + ", last name: " + lastName);
        }

    }

}
