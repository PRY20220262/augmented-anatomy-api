package com.pry20220262.augmentedanatomy.service.HumanAnatomy;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.Characteristic;
import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.model.Image;
import com.pry20220262.augmentedanatomy.repository.CharacteristicRepository;
import com.pry20220262.augmentedanatomy.repository.HumanAnatomyRepository;
import com.pry20220262.augmentedanatomy.repository.ImageRepository;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.HumanAnatomyDetailResource;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.OrganSaveResource;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.OrganListResource;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.SystemSaveResource;
import com.pry20220262.augmentedanatomy.resource.Image.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HumanAnatomyServiceImpl implements HumanAnatomyService {

    @Autowired
    private HumanAnatomyRepository humanAnatomyRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    private HumanAnatomy findById(Long id) {
        return humanAnatomyRepository.findById(id).orElseThrow(() -> new ServiceException(Error.ELEMENT_DOES_NOT_EXIST));
    }

    @Override
    public List<OrganListResource> findOrgans() {
        List<HumanAnatomy> organs = humanAnatomyRepository.findAllOrgans();
        List<OrganListResource> response = new ArrayList<>();

        for (HumanAnatomy o : organs) {
            OrganListResource dto = new OrganListResource();
            dto.setId(o.getId());
            dto.setName(o.getName());
            dto.setShortDetail(o.getShortDetail());

            Optional<Image> shortImage = imageRepository.getByTypeAndHumanAnatomyId(ImageType.SHORT_IMAGE, o.getId());
            if (shortImage.isEmpty()) throw new ServiceException(Error.ELEMENT_DOES_NOT_EXIST);

            dto.setImage(shortImage.get().getUrl());

            response.add(dto);
        }


        if (response.isEmpty()) throw new ServiceException(Error.LIST_IS_EMPTY);
        return response;
    }

    @Override
    public HumanAnatomyDetailResource getById(Long id) {

        HumanAnatomy organ = findById(id);
        HumanAnatomyDetailResource response = new HumanAnatomyDetailResource();

        response.setId(organ.getId());
        response.setDetail(organ.getDetail());
        response.setName(organ.getName());
        response.setHasGender(organ.isHasGender());

        Optional<Image> image = imageRepository.getByTypeAndHumanAnatomyId(ImageType.LONG_IMAGE, id);
        if (image.isEmpty()) throw new ServiceException(Error.IMAGE_DOES_NOT_EXIST);
        response.setImage(image.get().getUrl());

        List<Characteristic> characteristics = characteristicRepository.findByHumanAnatomyId(id);
        response.setCharacteristics(characteristics);

        return response;
    }

    @Override
    public HumanAnatomy createSystem(SystemSaveResource resource) {
        HumanAnatomy system = new HumanAnatomy();
        system.setName(resource.getName());
        system.setDetail(resource.getDetail());
        system.setShortDetail(resource.getShortDetail());
        system.setOrgansNumber(resource.getOrgansNumber());
        return humanAnatomyRepository.save(system);
    }

    @Override
    public HumanAnatomy createOrgan(OrganSaveResource resource) {
        HumanAnatomy organ = new HumanAnatomy();
        organ.setName(resource.getName());
        organ.setDetail(resource.getDetail());
        organ.setShortDetail(resource.getShortDetail());
        organ.setHasGender(resource.isHasGender());

        HumanAnatomy parent = findById(resource.getParentId());
        organ.setParent(parent);
        return humanAnatomyRepository.save(organ);    }
}
