package backend.security.security.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import backend.security.security.Models.Permission;
import backend.security.security.Repositories.PermissionRepository;




@CrossOrigin
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    @GetMapping("")
    public List<Permission> index(){
        return this.permissionRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Permission create(@RequestBody Permission infoPermission){
        return this.permissionRepository.save(infoPermission);
    }

    @GetMapping("{id}")
    public Permission show(@PathVariable String id){
        Permission permission = this.permissionRepository.findById(id).orElse(null);
        return permission;
    }

    @PutMapping("{id}")
    public Permission update(@PathVariable String id, @RequestBody Permission infoPermission){
        Permission permission = this.permissionRepository.findById(id).orElse(null);
        if(permission != null){
            permission.setUrl(infoPermission.getUrl()); 
            permission.setMethod(infoPermission.getMethod()); 
            return this.permissionRepository.save(permission);
        }else {
            return null;
        }    
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Permission permission = this.permissionRepository.findById(id).orElse(null);
        if(permission != null){
            this.permissionRepository.delete(permission);
        }
    }
}
