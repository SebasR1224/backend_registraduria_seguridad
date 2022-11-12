package backend.security.security.Models;

public class PermissionRole {

    private String _id;
    private Role role;
    private Permission permission;

    public PermissionRole() {
    }

    public String get_id() {
        return _id;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
