package models;
import java.io.Serializable;
/**
 *
 * @author navjo
 */
public class Role implements Serializable{
    int roleID;
    String roleName;

    public Role() {
    }

    public Role(int roleId, String roleName) {
        this.roleID = roleID;
        this.roleName = roleName;
    }
    
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }   
}
