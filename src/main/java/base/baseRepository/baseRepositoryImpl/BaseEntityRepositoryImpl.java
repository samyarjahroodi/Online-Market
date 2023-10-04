package base.baseRepository.baseRepositoryImpl;

import base.baseRepository.BaseEntityRepository;
import base.domain.BaseEntity;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseEntityRepositoryImpl<ID extends Number, TYPE extends BaseEntity<ID>>
        implements BaseEntityRepository<ID, TYPE> {
    protected Connection connection;

    public BaseEntityRepositoryImpl(Connection connection) {
        this.connection = connection;
    }



    protected abstract String getUpdateQueryParams();

    protected abstract String getTableName();

    protected abstract String getColumnsName();

    protected abstract String getCountQuestionMarkForParams();

    protected abstract void fillParamForStatement(PreparedStatement preparedStatement, TYPE entity, boolean b) throws SQLException;

    protected abstract TYPE mapResultSetEntity(ResultSet resultSet) throws SQLException;
}
