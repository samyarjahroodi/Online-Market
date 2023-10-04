package repository;

import base.baseRepository.BaseEntityRepository;
import entity.User;

import java.sql.SQLException;


public interface UserRepository
        extends BaseEntityRepository<Integer, User> {

    boolean login(User user) throws SQLException;

    boolean doesUserExist(String string) throws SQLException;

    int register(User user) throws SQLException;
}
