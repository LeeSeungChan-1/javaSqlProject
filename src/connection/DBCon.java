package connection;

import java.sql.*;

public class DBCon {
    private String url;
    private String user;
    private String password;

    private Connection conn;
    private Statement stmt;

    public DBCon() throws SQLException {
        System.out.println("Connecting to database...");
        this.url = "jdbc:mysql://localhost:3306/db_javaSqlProject";
        this.user = "user_javaSqlProject";
        this.password = "1234";

        this.conn = DriverManager.getConnection(this.url, this.user, this.password);
        this.stmt = this.conn.createStatement();
    }

    public void dbClose() throws SQLException {
        this.stmt.close();
        this.conn.close();
        System.out.println("Connection closed.");
    }

    public Statement getStmt() {
        return this.stmt;
    }
    public Connection getConn() {
        return this.conn;
    }

}
