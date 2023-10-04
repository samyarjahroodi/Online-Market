package repository.impl;

import base.baseRepository.baseRepositoryImpl.BaseEntityRepositoryImpl;
import entity.Cart;
import repository.CartRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRepositoryImpl
        extends BaseEntityRepositoryImpl<Integer, Cart>
        implements CartRepository {
    public CartRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public int sum(int id) {
        try {
            String sql = "SELECT SUM(s.price + e.price) AS total_price " +
                    "FROM cart c " +
                    "LEFT JOIN shoes s ON c.idOfShoe = s.id " +
                    "LEFT JOIN ElectronicAppliances e ON c.idOfElectronicAppliance = e.id " +
                    "WHERE c.idOfUser = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("total_price");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }


    @Override
    public void loadAndDisplayProducts(int userId) {
        try {
            String sql = "SELECT EA.nameOfBrand AS ea_brand, EA.price AS ea_price, s.nameOfBrand AS shoe_brand, s.price AS shoe_price " +
                    "FROM cart c " +
                    "LEFT JOIN ElectronicAppliances EA ON EA.id = c.idOfElectronicAppliance " +
                    "LEFT JOIN shoes s ON s.id = c.idOfShoe " +
                    "WHERE c.idOfUser = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String eaBrand = resultSet.getString("ea_brand");
                int eaPrice = resultSet.getInt("ea_price");
                String shoeBrand = resultSet.getString("shoe_brand");
                int shoePrice = resultSet.getInt("shoe_price");

                System.out.println("Electronic Appliance Brand: " + eaBrand);
                System.out.println("Electronic Appliance Price: $" + eaPrice);
                System.out.println("Shoe Brand: " + shoeBrand);
                System.out.println("Shoe Price: $" + shoePrice);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ResultSet loadAllShoes() throws SQLException {
        try {
            String sql = "SELECT * FROM shoes";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameOfBrand = resultSet.getString("nameOfBrand");
                String usageOfShoes = resultSet.getString("usageOfShoes");
                int price = resultSet.getInt("price");

                System.out.println("ID: " + id);
                System.out.println("Brand: " + nameOfBrand);
                System.out.println("Usage: " + usageOfShoes);
                System.out.println("Price: " + price);
                System.out.println();
            }
            return resultSet;
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet loadAllElectronicAppliances() throws SQLException {
        try {
            String sql = "SELECT * FROM ElectronicAppliances";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameOfBrand = resultSet.getString("nameOfBrand");
                String usageForAppliance = resultSet.getString("usageForAppliance");
                int price = resultSet.getInt("price");


                System.out.println("ID: " + id);
                System.out.println("Brand: " + nameOfBrand);
                System.out.println("Usage: " + usageForAppliance);
                System.out.println("Price: " + price);
                System.out.println();

            }
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int add(Cart cart) throws SQLException {
        String sql = "INSERT INTO " + getTableName() + " " + getColumnsName() + " VALUES " + getCountQuestionMarkForParams();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, cart.getUserId());
        preparedStatement.setInt(2, cart.getElectronicAppliancesId());
        preparedStatement.setInt(3, cart.getShoeId());
        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(int id) throws SQLException {
        try {
            String sql = "DELETE FROM cart where idCart =? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(int id) throws SQLException {
        try {
            Cart cart = new Cart(null);
            String sql = "UPDATE " + getTableName() + " SET " + getUpdateQueryParams() + " WHERE idCart = " + id;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                fillParamForStatement(statement, cart, true);
                return statement.executeUpdate();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return -1;
    }

    @Override
    protected String getUpdateQueryParams() {
        return "idOfUser=?,idOfElectronicAppliance=?,idOfShoe=? ";
    }


    @Override
    protected String getTableName() {
        return "cart";
    }

    @Override
    protected String getColumnsName() {
        return "( idOfUser,idOfElectronicAppliance,idOfShoe)";
    }

    @Override
    protected String getCountQuestionMarkForParams() {
        return "(?,?,?)";
    }

    @Override
    protected void fillParamForStatement(PreparedStatement preparedStatement, Cart entity, boolean b) throws SQLException {
        preparedStatement.setInt(1, entity.getUserId());
        preparedStatement.setInt(2, entity.getElectronicAppliancesId());
        preparedStatement.setInt(3, entity.getShoeId());
    }

    @Override
    protected Cart mapResultSetEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("idCart");
        int userId = resultSet.getInt("idOfUser");
        int electronicAppliancesId = resultSet.getInt("idOfElectronicAppliance");
        int shoeId = resultSet.getInt("idOfShoe");
        return new Cart(id, userId, shoeId, electronicAppliancesId);
    }
}
