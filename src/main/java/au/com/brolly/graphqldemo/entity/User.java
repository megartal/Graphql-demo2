package au.com.brolly.graphqldemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "postedBy", fetch = FetchType.EAGER)
    private List<Link> links;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Vote> votes;

    public User(String email, String password, String name) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}
