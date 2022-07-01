
package models;

/**
 *
 * @author Diego Maia
 */
public class User {
    
    private String email;
    private boolean active;
    private String firstName;
    private String lastName;
    private String password;
    private int role;
    
    public User(){
        
    }
    
    public User(String email, boolean active, String firstName, String lastName, String password, int role){
        setEmail(email);
        setActive(active);
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setRole(role);
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    

    public boolean isActive() {
        return active;
    }
    
     public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
     public void setRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;
    }

}
