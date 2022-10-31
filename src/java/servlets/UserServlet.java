package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author navjo
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoleService rs = new RoleService();
        UserService us = new UserService();
        String email = request.getParameter("email");

        try {
            ArrayList<User> users = us.getAllUsers();
            request.setAttribute("users", users);
            {
            }
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String action = request.getParameter("action");

        if (action != null && action.equals("edit")) {
            try {
                User user = us.getUser(email);
                request.setAttribute("email", email);
                request.setAttribute("selectedUser", user);
                request.setAttribute("message", "User has been edited.");
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (action != null && action.equals("delete")) {
            try {
                us.deleteUser(email);
                request.setAttribute("message", "User has been deleted.");
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoleService rs = new RoleService();
        UserService us = new UserService();
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        int role = Integer.parseInt(request.getParameter("role"));

        if (action != null && action.equals("add")) {
            try {
                us.insertUser(email, firstName, lastName, password, role);
                request.setAttribute("message", "User has been added.");
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (action != null && action.equals("edit")) {
            try {
                us.updateUser(email, firstName, lastName, password, role);
                request.setAttribute("message", "User has been edited.");
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            ArrayList<User> users = us.getAllUsers();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}
