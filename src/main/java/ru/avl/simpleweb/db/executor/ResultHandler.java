package ru.avl.simpleweb.db.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrey on 09.04.2017.
 */
public interface ResultHandler<T> {
    T handle(ResultSet resultSet) throws SQLException;
}
