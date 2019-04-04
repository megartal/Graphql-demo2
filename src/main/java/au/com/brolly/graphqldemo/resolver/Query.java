package au.com.brolly.graphqldemo.resolver;

import au.com.brolly.graphqldemo.entity.User;
import au.com.brolly.graphqldemo.model.Feed;
import au.com.brolly.graphqldemo.service.FeedService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    public FeedService feedService;

    public Query(FeedService feedService) {
        this.feedService = feedService;
    }

    public Iterable<User> getUsers() {
        return feedService.getUsers();
    }

    public Feed feed(){
        return feedService.feed();
    }
}
