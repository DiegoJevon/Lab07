package models;

/**
 *
 * @author Diego Maia
 */
public class Role {
    
    private int roleId;
    private String roleName;
    
    
    public Role(){
        
    }
    
    public Role (int roleId, String roleName){
        setRoleId(roleId);
        setRoleName(roleName);
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    

    public int getRoleId() {
        return this.roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }
    
    
    
}
