package daoimpl;

import Entity.Run;
import database.jdbc.ultils.JdbcUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunDaoImplTest {
    private RunDaoImpl runDao = new RunDaoImpl();

    @BeforeEach
    void clearTable() throws SQLException {
        Statement statement = JdbcUtils
                .GetInstance()
                .getConnection()
                .createStatement();
        statement.executeUpdate("DELETE FROM runs");
        statement.close();
    }

    @Test
    void save() {
        Run run = new Run(1, "Testowy bieg", 99);
        try {
            runDao.save(run);
            Run saved = runDao.findById(run.getId());

            assertNotNull(saved);
            assertEquals(run.getId(), saved.getId());
            assertEquals(run.getName(), saved.getName());
            assertEquals(run.getMembersLimit(), saved.getMembersLimit());
        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void findAll() {
        try {
            Run run1 = new Run(100, "Bieg numer 100", 99);
            Run run2 = new Run(105, "Inny bieg", 20);

            runDao.save(run1);
            runDao.save(run2);

            List<Run> runList = runDao.findAll();

            assertNotNull(runList);
            assertEquals(2, runList.size());
        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void update() {
        Run run = new Run(1, "Bieg testowy przed zmiana", 50);
        try {
            runDao.save(run);

            run.setMembersLimit(20);
            run.setName("Inna nazwa");

            runDao.update(run);

            Run updated = runDao.findById(run.getId());

            assertNotNull(updated);
            assertEquals(run.getMembersLimit(), updated.getMembersLimit());
            assertEquals(run.getName(), updated.getName());

        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void deleteByid() {
        Run run = new Run(100, "Bieg do usuniecia", 100);
        try {
            runDao.save(run);
            runDao.deleteByid(run.getId());
            Run deleted = runDao.findById(run.getId());

            assertNull(deleted);
        }catch (SQLException e) {
            fail(e);
        }
    }
}
