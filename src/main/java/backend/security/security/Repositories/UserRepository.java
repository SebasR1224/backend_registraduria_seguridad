package backend.security.security.Repositories;

import backend.security.security.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String>
{
    @Query("{'email': ?0}")
    public User getUserByEmail(String email);
}
