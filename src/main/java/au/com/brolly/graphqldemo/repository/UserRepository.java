package au.com.brolly.graphqldemo.repository;

import au.com.brolly.graphqldemo.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

    User findByEmail(String user1);
}
