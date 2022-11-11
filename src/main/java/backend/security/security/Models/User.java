package backend.security.security.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document()
public class User {
    @Id
    private String _id;
    private String username;
    private String email;
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private  String id_role;

    @DBRef
    private Role role;

    public User() {
    }

    public User(String username, String email, String password, String id_role) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId_role() {
        return id_role;
    }

    public void setId_role(String id_role) {
        this.id_role = id_role;
    }
}