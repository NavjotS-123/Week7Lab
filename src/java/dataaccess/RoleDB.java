package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.Role;

/**
 *
 * @author navjo
 */
public class RoleDB {

    public Role getRole(int roleId) throws Exception {
        Role role = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM role WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int roleID = rs.getInt(1);
                String roleName = rs.getString(2);
                role = new Role (roleID, roleName);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
       return role;
    }
    
    public ArrayList<Role> getAllRoles(String roleName) throws Exception {
        ArrayList<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM role WHERE role_name=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, roleName);
            rs = ps.executeQuery();
            while (rs.next()) {
                int roleId = rs.getInt(1);
                Role role = new Role(roleId, roleName);
                roles.add(role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        return roles;
    } 
}
