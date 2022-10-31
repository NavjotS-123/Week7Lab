package services;

import dataaccess.UserDB;
import java.util.ArrayList;
import models.Role;
import models.User;

/**
 *
 * @author navjo
 */
public class UserService {
    
    public User getUser(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        return user;
    }

    public ArrayList<User> getAllUsers() throws Exception {
        UserDB userDB = new UserDB();
        ArrayList<User> users = userDB.getAllUsers();
        return users;
    }

    public void insertUser(String email, String firstName, String lastName, String password, int roleId) throws Exception {
        RoleService rs = new RoleService();
        Role role = rs.getRole(roleId);
        User user = new User(email, firstName, lastName, password, role);
        UserDB userDB = new UserDB();
        userDB.insertUser(user);
    }

    public void updateUser(String email, String firstName, String lastName, String password, int roleId) throws Exception {
        RoleService rs = new RoleService();
        Role role = rs.getRole(roleId);
        User user = new User(email, firstName, lastName, password, role);
        UserDB userDB = new UserDB();
        userDB.updateUser(user);
    }

    public void deleteUser(String email) throws Exception {
        UserDB userDB = new UserDB();
        userDB.delete(email);
    }
}
