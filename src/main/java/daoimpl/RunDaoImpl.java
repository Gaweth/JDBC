package daoimpl;

import Entity.Run;
import dao.RunDao;
import database.jdbc.ultils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RunDaoImpl implements RunDao {

    public void save(Run run) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("INSERT INTO runs (id, name, members_limit) VALUES (?, ?, ?)");

        statement.setInt(1, run.getId());
        statement.setString(2, run.getName());
        statement.setInt(3, run.getMembersLimit());

        statement.executeUpdate();
        statement.close();
    }

    public List<Run> findAll() throws SQLException {
        Statement statement = JdbcUtils.getInstance().getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM runs");
        List<Run> runList = new ArrayList<>();
        while (resultSet.next()) {
            runList.add(new Run(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("members_limit")));
        }
        statement.close();
        return runList;
    }

    public Run findById(int id) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("SELECT * FROM runs WHERE id=?");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        Run run = null;
        if (resultSet.next()) {
            run = new Run();
            run.setId(resultSet.getInt("id"));
            run.setName(resultSet.getString("name"));
            run.setMembersLimit(resultSet.getInt("members_limit"));
        }

        return run;
    }

    public void update(Run run) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("UPDATE runs SET name=?, members_limit=?, id=?");
        statement.setString(1, run.getName());
        statement.setInt(2, run.getMembersLimit());
        statement.setInt(3,run.getId());

        statement.executeUpdate();
        statement.close();
    }

    public void deleteById(int id) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("DELETE FROM runs WHERE id = ? ");
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }




  public List<Run> findByNameFragment(String fragment) throws SQLException {
//        PreparedStatement statement = JdbcUtils
//                .getInstance()
//                .getConnection()
//                .prepareStatement("SELECT * FROM runs WHERE name LIKE (%?%)");
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM runs WHERE name=%?%");
//
//        while (resultSet.next()) {
//            System.out.printf("name=%?",
//                    resultSet.getString("name"));
//            statement.close();
//
//        }
//       return null;

      PreparedStatement statement = JdbcUtils
              .getInstance()
              .getConnection()
              .prepareStatement("SELECT * FROM runs WHERE name LIKE CONCAT('%',?,'%')");
      statement.setString(1, fragment);
      ResultSet resultSet = statement.executeQuery();
      List<Run> runList = new ArrayList<>();
      while (resultSet.next()) {
          runList.add(new Run(resultSet.getInt("id"),
                  resultSet.getString("name"),
                  resultSet.getInt("members_limit")));
      }
      statement.close();
      return runList;
  }


    public List<Run> findByMemberLimitRange(int min, int max) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("SELECT * FROM runs WHERE members_limit BETWEEN ? AND ?");
        statement.setInt(1, min);
        statement.setInt(2, max);
        ResultSet resultSet = statement.executeQuery();
        List<Run> runList = new ArrayList<>();
        while (resultSet.next()) {
            runList.add(new Run(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("members_limit")));
        }
        statement.close();
        return runList;
    }


}


