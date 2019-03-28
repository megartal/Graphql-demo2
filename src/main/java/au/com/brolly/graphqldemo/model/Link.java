package au.com.brolly.graphqldemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date createdAt = new Date();
    private String description;
    private String url;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User postedBy;
    @OneToMany(mappedBy = "link", fetch = FetchType.EAGER)
    private List<Vote> votes;

    public Link(String description, String url, User postedBy) {
        this.description = description;
        this.url = url;
        this.postedBy = postedBy;
    }
}
