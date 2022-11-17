package backend.security.security.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import backend.security.security.Models.PermissionRole;
import org.springframework.data.mongodb.repository.Query;

public interface PermissionRoleRepository extends MongoRepository<PermissionRole, String>
{
    @Query("{'permission.$id': ObjectId(?1)}, 'role.$id': ObjectId(?0),")
    PermissionRole getPermissionRole(String id_permission, String id_role);
}
