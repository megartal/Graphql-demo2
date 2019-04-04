package au.com.brolly.graphqldemo.service;

import au.com.brolly.graphqldemo.entity.Link;
import au.com.brolly.graphqldemo.entity.User;
import au.com.brolly.graphqldemo.model.Feed;
import au.com.brolly.graphqldemo.repository.LinkRepository;
import au.com.brolly.graphqldemo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {
    private UserRepository userRepository;
    private LinkRepository linkRepository;

    public FeedServiceImpl(UserRepository userRepository, LinkRepository linkRepository) {
        this.userRepository = userRepository;
        this.linkRepository = linkRepository;
    }

    public Feed feed() {
        List<Link> links = linkRepository.findAll();
        return new Feed(links.size(), links);
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

}
