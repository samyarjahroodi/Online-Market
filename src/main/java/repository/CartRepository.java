package repository;

import base.baseRepository.BaseEntityRepository;
import base.domain.BaseEntity;
import entity.Cart;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CartRepository
        extends BaseEntityRepository<Integer, Cart> {

    int sum(int id);

    void loadAndDisplayProducts(int userId);

    ResultSet loadAllShoes() throws SQLException;

    ResultSet loadAllElectronicAppliances() throws SQLException;

    int delete(int id) throws SQLException;

    int update(int id) throws SQLException;

    int add(Cart cart) throws SQLException;
}
