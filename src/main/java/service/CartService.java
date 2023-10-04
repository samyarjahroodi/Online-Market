package service;

import base.baseService.BaseServiceEntity;
import entity.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CartService extends BaseServiceEntity<Integer, Cart> {

    int sum() throws SQLException;

    void load() throws SQLException;

    ResultSet loadAllShoes() throws SQLException;

    ResultSet loadAllElectronicAppliances() throws SQLException;

    int delete() throws SQLException;

    int update() throws SQLException;

    int add() throws SQLException;
}
