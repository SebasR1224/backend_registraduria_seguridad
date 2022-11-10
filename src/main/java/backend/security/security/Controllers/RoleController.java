package backend.security.security.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


import backend.security.security.Models.Role;
import backend.security.security.Repositories.RoleRepository;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RoleController {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @GetMapping("")
    public List<Role> index(){
        return this.roleRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Role create(@RequestBody Role infoRole){
        return this.roleRepository.save(infoRole);
    }

    @GetMapping("{id}")
    public Role show(@PathVariable String id){
        Role role = this.roleRepository.findById(id).orElse(null);
        return role;
    }

    @PutMapping("{id}")
    public Role update(@PathVariable String id, @RequestBody Role infoRole){
        Role role = this.roleRepository.findById(id).orElse(null);
        if(role != null){
            role.setName(infoRole.getName());
            return this.roleRepository.save(role);
        }else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Role role = this.roleRepository.findById(id).orElse(null);
        if(role != null){
            this.roleRepository.delete(role);
        }
    }


}
