package backend.security.security.Repositories;

import backend.security.security.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>
{

}
