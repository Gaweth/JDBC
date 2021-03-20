package daoimpl;

import Entity.Run;
import dao.RunDao;
import database.jdbc.ultils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RunDaoImpl implements RunDao {


    @Override
    public void save(Run run) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .GetInstance()
                .getConnection()
                .prepareStatement("INSERT INTO runs (id, name, members_limit) VALUES (?,?,?)");

        statement.setInt(1, run.getId());
        statement.setString(2, run.getName());
        statement.setInt(3, run.getMembersLimit());

        statement.executeUpdate();
        statement.close();
    }

    @Override
    public List<Run> findAll() throws SQLException {
        return null;
    }

    @Override
    public Run findById(int id) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .GetInstance()
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

    @Override
    public void update(Run run) throws SQLException {


    }

    @Override
    public void deleteByid(int id) throws SQLException {
//        PreparedStatement statement = JdbcUtils
//                .GetInstance()
//                .getConnection()
//                .prepareStatement("DELETE * FROM runs WHERE id=?");
//        statement.setInt(10, id);
//        ResultSet resultSet= statement.executeQuery();
//

    }


}