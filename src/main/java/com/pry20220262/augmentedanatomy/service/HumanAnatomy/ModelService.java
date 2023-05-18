package com.pry20220262.augmentedanatomy.service.HumanAnatomy;

import com.pry20220262.augmentedanatomy.model.Model;
import java.util.List;

public interface ModelService {
    List<Model> findModels(Long humanAnatomyId);
}
