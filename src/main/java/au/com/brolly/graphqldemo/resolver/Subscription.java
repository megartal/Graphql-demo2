package au.com.brolly.graphqldemo.resolver;

import au.com.brolly.graphqldemo.entity.Link;
import au.com.brolly.graphqldemo.entity.Vote;
import au.com.brolly.graphqldemo.model.StockPriceUpdate;
import au.com.brolly.graphqldemo.publisher.LinkPublisher;
import au.com.brolly.graphqldemo.publisher.StockTickerPublisher;
import au.com.brolly.graphqldemo.publisher.VotePublisher;
import com.coxautodev.graphql.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Subscription implements GraphQLSubscriptionResolver {

    private LinkPublisher linkPublisher;
    private VotePublisher votePublisher;
    private StockTickerPublisher stockTickerPublisher;



    public Subscription(LinkPublisher linkPublisher, VotePublisher votePublisher) {
        this.linkPublisher = linkPublisher;
        this.votePublisher = votePublisher;
    }

    public Publisher<Link> newLink() {
        return linkPublisher.getPublisher();
    }


    public Publisher<Vote> newVote() {
        return votePublisher.getPublisher();
    }

    Publisher<StockPriceUpdate> stockQuotes(List<String> stockCodes) {
        return stockTickerPublisher.getPublisher(stockCodes);
    }
}
