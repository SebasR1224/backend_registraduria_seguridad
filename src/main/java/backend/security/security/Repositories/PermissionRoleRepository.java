package backend.security.security.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import backend.security.security.Models.PermissionRole;

public interface PermissionRoleRepository extends MongoRepository<PermissionRole, String>
{
    
}
