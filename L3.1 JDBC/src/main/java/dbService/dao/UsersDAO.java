package dbService.dao;

import java.sql.Connection;
import java.sql.SQLException;

import dbService.dataSets.UsersDataSet;
import dbService.executor.Executor;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersDAO implements DAO<UsersDataSet> {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public long getUserId(String name) throws SQLException {
        return executor.execQuery("select * from users where user_name='" + name + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }

    public void insertUser(String name) throws SQLException {
        executor.execStatement("insert into users (user_name) values ('" + name + "')");
    }

    public void createTable() throws SQLException {
        executor.execStatement("create table if not exists " +
                                       "users (id bigint auto_increment, " +
                                       "login varchar(256), " +
                                       "password varchar(256), " +
                                       "primary key (id));");
    }

    public void dropTable() throws SQLException {
        executor.execStatement("drop table users");
    }

    @Override
    public UsersDataSet create(UsersDataSet usersDataSet) throws SQLException {
        return executor.execQuery(
                String.format("INSERT INTO users (login, password) VALUE ('%s', '%s');" +
                                      "SELECT last_insert_id();",
                              usersDataSet.getLogin(),
                              usersDataSet.getPassword()),
                result -> {
                    result.next();
                    usersDataSet.setId(result.getLong(1));
                    return usersDataSet;
                }
        );
    }

    @Override
    public UsersDataSet read(Long id) throws SQLException {
        return executor.execQuery("select * from users where id=" + id, result -> {
            result.next();
            return new UsersDataSet(id, result.getString(1), result.getString(2));
        });
    }

    public UsersDataSet read(String name) throws SQLException {
        return executor.execQuery("select * from users where login=" + name, result -> {
            result.next();
            return new UsersDataSet(result.getLong(1), result.getString(2), result.getString(3));
        });
    }

    @Override
    public UsersDataSet update(UsersDataSet usersDataSet) throws SQLException {
        executor.execStatement("UPDATE users" +
                                       "SET login = '" + usersDataSet.getLogin() + "', " +
                                       "password = '" + usersDataSet.getPassword() + "'" +
                                       "WHERE id = " + usersDataSet.getId() + ";");
        return usersDataSet;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        executor.execStatement("DELETE FROM users where id = " + id);
        return true;
    }
}
