package SDA.JDBC;

import database.jdbc.ultils.JdbcUtils;


import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

      printAlldata();
    }

    private static void insertTest() throws SQLException {
        Statement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .createStatement();

        statement.executeUpdate("INSERT INTO runs (id, name, members_limit) VALUES (4, 'Nowy bieg 1', 100)");
        statement.executeUpdate("INSERT INTO runs (id, name, members_limit) VALUES (2, 'Nowy bieg 2', 100)");
        statement.executeUpdate("INSERT INTO runs (id, name, members_limit) VALUES (3, 'Nowy bieg 3', 100)");

        statement.close();
    }

    private static void deleteById(int id) throws SQLException {
        Statement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .createStatement();
        statement.executeUpdate("DELETE FROM runs WHERE id= " + id);
        statement.close();
    }

    private static void updateRun(int id, String name, int membersLimit) throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        PreparedStatement statement = connection
                .prepareStatement("UPDATE runs SET name=?, members_limit=? WHERE id=?");

        statement.setString(1, name);
        statement.setInt(2, membersLimit);
        statement.setInt(3, id);
        statement.executeUpdate();
        statement.close();
    }

    private static void printAlldata() throws SQLException {
        Connection connection = JdbcUtils.getInstance().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM runs");

        while (resultSet.next()) {
            System.out.printf("id=%d, name=%s, members limit=%d \n",
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getInt("members_limit"));
        }
        statement.close();
    }

}
