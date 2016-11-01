package dbService.dao;

import java.sql.SQLException;

public interface DAO<T> {
    T create(T t) throws SQLException;

    T read(Long id) throws SQLException;

    T update(T t) throws SQLException;

    boolean delete(Long id) throws SQLException;
}
