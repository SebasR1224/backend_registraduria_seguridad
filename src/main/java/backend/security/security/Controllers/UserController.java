package backend.security.security.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import backend.security.security.Models.Role;
import backend.security.security.Models.User;
import backend.security.security.Repositories.RoleRepository;
import backend.security.security.Repositories.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("")
    public List<User> index(){
        return this.userRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User create(@RequestBody User infoUser){
        infoUser.setPassword(hashPassworSHA256(infoUser.getPassword()));        
        return this.userRepository.save(infoUser);
    }

    @GetMapping("{id}")
    public User show(@PathVariable String id){
        User user = this.userRepository.findById(id).orElse(null);
        return user;
    }

    @PutMapping("{id}")
    public User update(@PathVariable String id, @RequestBody User infoUser){
        User user = this.userRepository.findById(id).orElse(null);
        if(user != null){
            user.setUsername(infoUser.getUsername());
            user.setEmail(infoUser.getEmail());
            user.setPassword(hashPassworSHA256(infoUser.getPassword()));
            return this.userRepository.save(user);
        }else {
            return null;
        }
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        User user = this.userRepository.findById(id).orElse(null);
        if(user != null){
            this.userRepository.delete(user);
        }
    }

    @PutMapping("assign-role/{id}/role/{id_role}")
    public User assignRole(@PathVariable String id, @PathVariable String id_role){
        User user = this.userRepository.findById(id).orElse(null);
        Role role = this.roleRepository.findById(id_role).orElse(null);

        if(user != null && role != null ){
            user.setRole(role);
            return this.userRepository.save(user);
        }else{
            return null;
        }
    }

    @PostMapping("/login")
    public User login(@RequestBody User inforUser, final HttpServletResponse response) throws IOException {
        User user = this.userRepository.getUserByEmail(inforUser.getEmail());
        
        if(user != null && user.getPassword().equals(hashPassworSHA256(inforUser.getPassword()))){
            return user;
        }else{
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    public String hashPassworSHA256(String password){

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

}
