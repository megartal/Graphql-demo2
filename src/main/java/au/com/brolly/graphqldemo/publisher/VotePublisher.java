package au.com.brolly.graphqldemo.publisher;

import au.com.brolly.graphqldemo.entity.Link;
import au.com.brolly.graphqldemo.entity.User;
import au.com.brolly.graphqldemo.entity.Vote;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class VotePublisher {

    private final Flowable<Vote> publisher;

    public VotePublisher() {
        Observable<Vote> voteUpdateObservable = Observable.create(emitter -> {

            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(newVote(emitter), 0, 2, TimeUnit.SECONDS);

        });

        ConnectableObservable<Vote> connectableObservable = voteUpdateObservable.share().publish();
        connectableObservable.connect();

        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }


    private Runnable newVote(ObservableEmitter<Vote> emitter) {
        return () -> {
            List<Vote> voteUpdates = getUpdates();
            if (voteUpdates != null) {
                emitVotes(emitter, voteUpdates);
            }
        };
    }

    private void emitVotes(ObservableEmitter<Vote> emitter, List<Vote> linkUpdates) {
        for (Vote stockPriceUpdate : linkUpdates) {
            try {
                emitter.onNext(stockPriceUpdate);
            } catch (RuntimeException e) {
                log.error("Cannot send LinkUpdate", e);
            }
        }
    }

    private List<Vote> getUpdates() {
        List<Vote> updates = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            updates.add(new Vote(new User(), new Link()));
        }
        return updates;
    }

    public Flowable<Vote> getPublisher() {
        return publisher;
    }
}
