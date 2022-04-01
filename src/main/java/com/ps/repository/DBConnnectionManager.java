package com.ps.repository;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnnectionManager {
    public static Connection getDBconnection;

    public static Connection getconnection() throws NamingException, SQLException {

        InitialContext context = new InitialContext();
        DataSource dataSource =  (DataSource) context.lookup("java:comp/env/jdbc/contact");
        return dataSource.getConnection();
    }

}
