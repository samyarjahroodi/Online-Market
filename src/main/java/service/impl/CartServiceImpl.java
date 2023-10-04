package service.impl;

import base.baseRepository.baseRepositoryImpl.BaseEntityRepositoryImpl;
import base.baseService.baseServiceImpl.BaseEntityServiceImpl;
import entity.Cart;
import repository.CartRepository;
import repository.impl.CartRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.CartService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static until.ApplicationContext.cartRepository;

public class CartServiceImpl
        extends BaseEntityServiceImpl<Integer, Cart, CartRepository>
        implements CartService {
    Scanner scanner = new Scanner(System.in);

    public CartServiceImpl(CartRepository repository) {
        super(repository);
    }


    @Override
    public int sum() {
        try {
            System.out.println("Enter your id to check the sum");
            int id = scanner.nextInt();
            return repository.sum(id);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    @Override
    public void load() {
        try {
            System.out.println("Enter your id ");
            int id = scanner.nextInt();
            repository.loadAndDisplayProducts(id);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }


    @Override
    public ResultSet loadAllShoes() throws SQLException {
        return repository.loadAllShoes();
    }

    @Override
    public ResultSet loadAllElectronicAppliances() throws SQLException {
        return repository.loadAllElectronicAppliances();
    }


    @Override
    public int delete() throws SQLException {
        try {
            System.out.println("Enter your the id of your order to remove ");
            int id = scanner.nextInt();
            return repository.delete(id);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    @Override
    public int update() throws SQLException {
        try {
            System.out.println("Enter the id of your cart that you want to update ");
            int id = scanner.nextInt();
            return repository.update(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        } catch (InputMismatchException mismatchException) {
            System.out.println(mismatchException.getMessage());
            return -1;
        }
    }

    @Override
    public int add() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter your user id:");
            int userId = scanner.nextInt();

            System.out.println("Enter the electronic appliance id:");
            int electronicAppliancesId = scanner.nextInt();

            System.out.println("Enter the shoe id:");
            int shoeId = scanner.nextInt();

            Cart cart = new Cart(null, userId, electronicAppliancesId, shoeId);

            int generatedId = cartRepository.add(cart);
            System.out.println("Item added to cart with ID: " + generatedId);
            return generatedId;
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a valid integer.");
            return -1;
        } catch (SQLException e) {
            System.err.println("Error adding item to cart: " + e.getMessage());
            return -1;
        }

    }
}



