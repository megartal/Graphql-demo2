package au.com.brolly.graphqldemo.model;

import au.com.brolly.graphqldemo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Auth {
    private String token;
    private User user;
}
