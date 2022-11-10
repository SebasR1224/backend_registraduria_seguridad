package backend.security.security.Repositories;

import backend.security.security.Models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String>
{

}