package au.com.brolly.graphqldemo.resolver;

import au.com.brolly.graphqldemo.entity.Link;
import au.com.brolly.graphqldemo.entity.User;
import au.com.brolly.graphqldemo.model.Feed;
import au.com.brolly.graphqldemo.repository.LinkRepository;
import au.com.brolly.graphqldemo.repository.UserRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private UserRepository userRepository;
    private LinkRepository linkRepository;

    public Query(UserRepository userRepository, LinkRepository linkRepository) {
        this.userRepository = userRepository;
        this.linkRepository = linkRepository;
    }

    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Feed feed(){
        List<Link> links = linkRepository.findAll();
        return new Feed(links.size(), links);
    }
}
