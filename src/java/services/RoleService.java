package services;

import java.util.ArrayList;
import models.Role;
import dataaccess.RoleDB;



/**
 *
 * @author navjo
 */
public class RoleService {
    public Role getRole(int roleId) throws Exception {
        RoleDB rdb = new RoleDB();
        Role role = rdb.getRole(roleId);
        return role;
    }
    
    public ArrayList<Role> getAllRoles(String roleName) throws Exception {
        RoleDB rdb = new RoleDB();
        ArrayList<Role> roles = rdb.getAllRoles(roleName);
        return roles;
    }
}
