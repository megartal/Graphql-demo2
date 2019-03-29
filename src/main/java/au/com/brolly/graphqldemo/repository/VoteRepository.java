package au.com.brolly.graphqldemo.repository;

import au.com.brolly.graphqldemo.entity.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {
}
