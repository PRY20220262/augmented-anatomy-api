package com.pry20220262.augmentedanatomy.service.HumanAnatomy;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.Model;
import com.pry20220262.augmentedanatomy.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService{

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<Model> findModels(Long humanAnatomyId) {
        List<Model> models = modelRepository.findByHumanAnatomyId(humanAnatomyId).orElseThrow(() -> new ServiceException(Error.DATA_NOT_FOUND));
        if (models.isEmpty()) throw new ServiceException(Error.LIST_IS_EMPTY);
        return models;
    }
}
