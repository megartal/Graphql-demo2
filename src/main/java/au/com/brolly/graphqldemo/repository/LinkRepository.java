package au.com.brolly.graphqldemo.repository;

import au.com.brolly.graphqldemo.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
