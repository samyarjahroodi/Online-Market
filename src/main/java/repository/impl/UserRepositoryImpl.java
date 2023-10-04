package repository.impl;

import base.baseRepository.baseRepositoryImpl.BaseEntityRepositoryImpl;
import entity.User;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl
        extends BaseEntityRepositoryImpl<Integer, User>
        implements UserRepository {

    public UserRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getUpdateQueryParams() {
        return "nameOfUsers,username=?,passwordOfUsers=?";
    }

    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    protected String getColumnsName() {
        return "(nameOfUsers,username,passwordOfUsers)";
    }

    @Override
    protected String getCountQuestionMarkForParams() {
        return "(?,?,?)";
    }

    @Override
    protected void fillParamForStatement(PreparedStatement preparedStatement, User user, boolean b) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getPassword());
    }

    @Override
    protected User mapResultSetEntity(ResultSet resultSet) throws SQLException {
        return new User(null,
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("password")
        );
    }


    @Override
    public boolean login(User user) {
        try {
            String checkUsers = "SELECT * FROM users WHERE username=? AND passwordOfUsers=? ";
            PreparedStatement preparedStatement = connection.prepareStatement(checkUsers);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean doesUserExist(String username) {
        try {
            String checkUsername = "SELECT * from users where username = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(checkUsername);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int register(User user) {
        try {
            String sql = "INSERT INTO " + getTableName() + " " + getColumnsName() + " VALUES " + getCountQuestionMarkForParams();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            fillParamForStatement(preparedStatement, user, true);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


}
