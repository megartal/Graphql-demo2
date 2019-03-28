package au.com.brolly.graphqldemo.repository;

import au.com.brolly.graphqldemo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
