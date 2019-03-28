package au.com.brolly.graphqldemo.repository;

import au.com.brolly.graphqldemo.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
