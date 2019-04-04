package au.com.brolly.graphqldemo.service;

import au.com.brolly.graphqldemo.entity.User;
import au.com.brolly.graphqldemo.model.Feed;

public interface FeedService {
    Feed feed();

    Iterable<User> getUsers();
}
