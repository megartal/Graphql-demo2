package au.com.brolly.graphqldemo.bootstrap;

import au.com.brolly.graphqldemo.entity.Link;
import au.com.brolly.graphqldemo.entity.User;
import au.com.brolly.graphqldemo.entity.Vote;
import au.com.brolly.graphqldemo.repository.LinkRepository;
import au.com.brolly.graphqldemo.repository.UserRepository;
import au.com.brolly.graphqldemo.repository.VoteRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;
    private LinkRepository linkRepository;
    private VoteRepository voteRepository;

    public DevBootstrap(UserRepository userRepository, LinkRepository linkRepository, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.linkRepository = linkRepository;
        this.voteRepository = voteRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        init();
    }

    private void init() {
        User user1 = new User("user1", "user1@ladoo.com.au", "USER1-123");

        Link link1 = new Link("link1", "link1.com", user1);
        Link link2 = new Link("link2", "link2.com", user1);

        List<Link> links = new ArrayList<>(Arrays.asList(link1, link2));
        user1.setLinks(links);

        List<Vote> link1votes = new ArrayList<>(Arrays.asList(
                new Vote(user1, link1),
                new Vote(user1, link1)

        ));

        List<Vote> link2votes = new ArrayList<>(Arrays.asList(
                new Vote(user1, link2),
                new Vote(user1, link2)

        ));

        List<Vote> allVotes = new ArrayList<>();
        allVotes.addAll(link1votes);
        allVotes.addAll(link2votes);

        user1.setVotes(allVotes);

        userRepository.save(user1);
        linkRepository.saveAll(links);
        voteRepository.saveAll(allVotes);

    }
}
