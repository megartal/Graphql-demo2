package au.com.brolly.graphqldemo.publisher;

import au.com.brolly.graphqldemo.entity.Link;
import au.com.brolly.graphqldemo.entity.User;
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

@Service
@Slf4j
public class LinkPublisher {

    private final Flowable<Link> publisher;

    public LinkPublisher() {
        Observable<Link> stockPriceUpdateObservable = Observable.create(emitter -> {

            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(newLink(emitter), 0, 2, TimeUnit.SECONDS);

        });

        ConnectableObservable<Link> connectableObservable = stockPriceUpdateObservable.share().publish();
        connectableObservable.connect();

        publisher = connectableObservable.toFlowable(BackpressureStrategy.BUFFER);
    }


    private Runnable newLink(ObservableEmitter<Link> emitter) {
        return () -> {
            List<Link> linkUpdates = getUpdates();
            if (linkUpdates != null) {
                emitLinks(emitter, linkUpdates);
            }
        };
    }

    private void emitLinks(ObservableEmitter<Link> emitter, List<Link> linkUpdates) {
        for (Link stockPriceUpdate : linkUpdates) {
            try {
                emitter.onNext(stockPriceUpdate);
            } catch (RuntimeException e) {
                log.error("Cannot send LinkUpdate", e);
            }
        }
    }

    private List<Link> getUpdates() {
        List<Link> updates = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            updates.add(new Link("newLink" + i, "newURL" + i, new User()));
        }
        return updates;
    }

    public Flowable<Link> getPublisher() {
        return publisher;
    }
}
