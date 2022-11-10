package backend.security.security.Repositories;

import backend.security.security.Models.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String>
{

}