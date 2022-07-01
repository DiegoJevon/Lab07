
package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author Diego Maia
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService us = new UserService();
        RoleService rs = new RoleService();
        List <User> users = null;
        List <Role> roles = null;
         
        try{
           users = us.getAll();
           request.setAttribute("users", users);
           roles = rs.getAll();
           request.setAttribute("roles", roles);            
        }catch (Exception e){
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
            
        }          
        try{
            String action = request.getParameter("action");
            if(action != null){  
                switch (action){
                    case "edit":
                        String email = request.getParameter("email");
                        User user = us.get(email.replaceAll(" ","+"));
                        int roleId = user.getRole();
                        Role role = rs.get(roleId);
                        request.setAttribute("edit_email", user.getEmail());
                        request.setAttribute("edit_active", user.isActive());
                        request.setAttribute("edit_fname", user.getFirstName() );
                        request.setAttribute("edit_lname", user.getLastName() );
                        request.setAttribute("edit_password", user.getPassword() );
                        request.setAttribute("edit_role", role.getRoleName());
                        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
                        return;

                    case "delete":
                            String emailDelete = request.getParameter("delete_email");
                            us.delete(emailDelete);
                            response.sendRedirect("users");
                            return;

                }
            }
        }catch (Exception e){
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
        }      
       
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
       return;             
    }
   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService us = new UserService();
        RoleService rs = new RoleService();
        HttpSession session = request.getSession();
        
       
        String action = request.getParameter("action");
                
            try {
                switch(action){
                case "add":
                    String email = request.getParameter("email");
                    boolean active = request.getParameter("active") != null;
                    String firstName = request.getParameter("fname");
                    String lastName = request.getParameter("lname");
                    String password = request.getParameter("password");
                    String roleString = request.getParameter("role");
                    int roleInt = 0;
                    List <Role> roles=null;
                    roles = rs.getAll();
                    for (Role role: roles){
                        if (role.getRoleName().equals(roleString)){
                            roleInt = role.getRoleId();
                        }
                    }
                    us.insert(email, active, firstName, lastName, password, roleInt);
                    break;
                    
                case "update":     
                    String emailEdit = request.getParameter("edit_email");
                    boolean activeEdit = request.getParameter("edit_active") != null;
                    String firstNameEdit = request.getParameter("edit_fname");
                    String lastNameEdit = request.getParameter("edit_lname");
                    String passwordEdit = request.getParameter("edit_password");
                    String roleStringEdit = request.getParameter("edit_role").replaceAll("_"," ");
                    int roleIntEdit=0;

                    List <Role> rolesEdit = rs.getAll();
                        for (Role roleEdit: rolesEdit){
                            if (roleEdit.getRoleName().equals(roleStringEdit)){
                                roleIntEdit = roleEdit.getRoleId();
                                
                            }
                        }                        
                    us.update(emailEdit, activeEdit, firstNameEdit, lastNameEdit, passwordEdit, roleIntEdit);
                    break;
                    
                case "cancel":
                    session.invalidate();
                    return;             
                    
                }    
                
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
           response.sendRedirect("users");
        }
}
