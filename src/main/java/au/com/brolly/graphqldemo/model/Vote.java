package au.com.brolly.graphqldemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "link_id")
    private Link link;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Vote(User user, Link link) {
        this.link = link;
        this.user = user;
    }
}
