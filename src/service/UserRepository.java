package service;

import config.CrudRepository;
import model.User;

public class UserRepository extends CrudRepository<User> {

    private static UserRepository userRepository;

    private UserRepository(){
    }

    public static UserRepository getInstance(){
        if (userRepository == null)
            userRepository = new UserRepository();
        return userRepository;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}


