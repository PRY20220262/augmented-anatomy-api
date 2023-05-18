package com.pry20220262.augmentedanatomy.resource.Menu;

import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.SystemResource;
import lombok.Data;
import java.util.List;

@Data
public class MenuResource {

    private Long userId;

    private List<SystemResource> recentActivity;

    private SystemResource recommendation;

    private int noteCount;

    private int quizCount;
}
