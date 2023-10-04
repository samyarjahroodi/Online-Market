package until;


import repository.CartRepository;
import repository.UserRepository;
import repository.impl.CartRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.CartService;
import service.UserService;
import service.impl.CartServiceImpl;
import service.impl.UserServiceImpl;

import java.sql.Connection;

public class ApplicationContext {
    private final static Connection CONNECTION;
    public static final CartRepository cartRepository;
    public static final CartService cartService;
    public static final UserRepository USER_REPOSITORY;
    public static final UserService USER_SERVICE;


    static {
        CONNECTION = DataSource.getConnection();
        USER_REPOSITORY = new UserRepositoryImpl(CONNECTION);
        USER_SERVICE = new UserServiceImpl(USER_REPOSITORY);
        cartRepository = new CartRepositoryImpl(CONNECTION);
        cartService = new CartServiceImpl(cartRepository);

    }


}
