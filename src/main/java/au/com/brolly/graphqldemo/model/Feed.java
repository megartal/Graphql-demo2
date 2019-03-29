package au.com.brolly.graphqldemo.model;


import au.com.brolly.graphqldemo.entity.Link;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feed {
    private long count;
    private List<Link> links;
}
