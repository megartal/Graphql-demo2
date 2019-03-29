package au.com.brolly.graphqldemo.resolver;

import au.com.brolly.graphqldemo.entity.Link;
import au.com.brolly.graphqldemo.entity.User;
import au.com.brolly.graphqldemo.entity.Vote;
import au.com.brolly.graphqldemo.model.Auth;
import au.com.brolly.graphqldemo.repository.LinkRepository;
import au.com.brolly.graphqldemo.repository.UserRepository;
import au.com.brolly.graphqldemo.repository.VoteRepository;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class Mutation implements GraphQLMutationResolver {


    private EntityManager em;
    private UserRepository userRepository;
    private LinkRepository linkRepository;
    private VoteRepository voteRepository;

    public Mutation(EntityManager em, UserRepository userRepository, LinkRepository linkRepository, VoteRepository voteRepository) {
        this.em = em;
        this.userRepository = userRepository;
        this.linkRepository = linkRepository;
        this.voteRepository = voteRepository;
    }

    public Auth signup(String email, String password, String name) {
        User user = new User(email, password, name);
        User savedUser = userRepository.save(user);
        return new Auth("token", savedUser);
    }

    public Auth login(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        return new Auth("token", user);
    }

    public Link post(String url, String description) {
        User user = userRepository.findByEmail("user1");
        Link link = new Link(description, url, user);
        return linkRepository.save(link);
    }

    public Vote vote(long linkId) {
        Link linkRef = em.getReference(Link.class, linkId);
        User user = userRepository.findByEmail("user1");
        Vote vote = new Vote(user, linkRef);
        return voteRepository.save(vote);
    }
}
