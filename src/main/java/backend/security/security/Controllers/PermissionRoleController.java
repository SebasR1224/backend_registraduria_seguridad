package backend.security.security.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import backend.security.security.Models.Permission;
import backend.security.security.Models.PermissionRole;
import backend.security.security.Models.Role;
import backend.security.security.Repositories.PermissionRepository;
import backend.security.security.Repositories.PermissionRoleRepository;
import backend.security.security.Repositories.RoleRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permissions-roles")
public class PermissionRoleController {

    @Autowired
    private PermissionRoleRepository permissionRoleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public List<PermissionRole> index(){
        return this.permissionRoleRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("permission/{id_permission}/role/{id_role}")
    public PermissionRole create(@PathVariable String id_permission, @PathVariable String id_role){
        Permission permission = this.permissionRepository.findById(id_permission).orElse(null);
        Role role = this.roleRepository.findById(id_role).orElse(null);

        PermissionRole permissionRole = new PermissionRole();

        if (permission != null && role != null){
            permissionRole.setPermission(permission);
            permissionRole.setRole(role);
            return this.permissionRoleRepository.save(permissionRole);
        }else{
            return null;
        }
    }

    @PutMapping("{id}/permission/{id_permission}/role/{id_role}")
    public PermissionRole update(@PathVariable String id, @PathVariable String id_permission, @PathVariable String id_role){

        PermissionRole permissionRole = this.permissionRoleRepository.findById(id).orElse(null);
        Permission permission = this.permissionRepository.findById(id_permission).orElse(null);
        Role role = this.roleRepository.findById(id_role).orElse(null);

        if(permissionRole != null && permission != null && role != null){
            permissionRole.setPermission(permission);
            permissionRole.setRole(role);
            return this.permissionRoleRepository.save(permissionRole);
        }else{
            return null;
        }

    }

    @GetMapping("{id}")
    public PermissionRole show(@PathVariable String id){
        PermissionRole permissionRole = this.permissionRoleRepository.findById(id).orElse(null);
        return permissionRole;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        PermissionRole permissionRole = this.permissionRoleRepository.findById(id).orElse(null);
        if(permissionRole != null){
            this.permissionRoleRepository.delete(permissionRole);
        }
    }

    @GetMapping("validate-permission/role/{id_role}")
    public PermissionRole getPermission(@PathVariable String id_role, @RequestBody Permission infoPermission){
        Permission permission = this.permissionRepository.getPermission(infoPermission.getUrl(), infoPermission.getMethod());
        Role role = this.roleRepository.findById(id_role).get();
        if(permission != null && role != null){
            return this.permissionRoleRepository.getPermissionRole(role.get_id(), permission.get_id());
        }else {
            return null;
        }
    }

}
