package com.pry20220262.augmentedanatomy.service.HumanAnatomy;

import com.pry20220262.augmentedanatomy.exception.Error;
import com.pry20220262.augmentedanatomy.exception.ServiceException;
import com.pry20220262.augmentedanatomy.model.Characteristic;
import com.pry20220262.augmentedanatomy.model.HumanAnatomy;
import com.pry20220262.augmentedanatomy.model.Image;
import com.pry20220262.augmentedanatomy.model.User;
import com.pry20220262.augmentedanatomy.repository.CharacteristicRepository;
import com.pry20220262.augmentedanatomy.repository.HumanAnatomyRepository;
import com.pry20220262.augmentedanatomy.repository.ImageRepository;
import com.pry20220262.augmentedanatomy.resource.HumanAnatomy.*;
import com.pry20220262.augmentedanatomy.resource.Image.ImageType;
import com.pry20220262.augmentedanatomy.resource.Menu.MenuResource;
import com.pry20220262.augmentedanatomy.service.Note.NoteService;
import com.pry20220262.augmentedanatomy.service.QuizAttempt.QuizAttemptService;
import com.pry20220262.augmentedanatomy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HumanAnatomyServiceImpl implements HumanAnatomyService {

    @Autowired
    private HumanAnatomyRepository humanAnatomyRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private QuizAttemptService quizAttemptService;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    private HumanAnatomy findById(Long id) {
        return humanAnatomyRepository.findById(id).orElseThrow(() -> new ServiceException(Error.ELEMENT_DOES_NOT_EXIST));
    }


    @Override
    public List<OrganResource> organQuery(OrganQuery query) {

        List<HumanAnatomy> organs = humanAnatomyRepository.queryOrgan(query.getId(), query.getName(), query.getSystemName());
        List<OrganResource> response = new ArrayList<>();

        for (HumanAnatomy o : organs) {
            OrganResource dto = new OrganResource();
            dto.setId(o.getId());
            dto.setName(o.getName());
            dto.setShortDetail(o.getShortDetail());

            Optional<Image> shortImage = imageRepository.getByTypeAndHumanAnatomyId(ImageType.SHORT_IMAGE, o.getId());
            if (shortImage.isEmpty()) throw new ServiceException(Error.ELEMENT_DOES_NOT_EXIST);

            dto.setImage(shortImage.get().getUrl());

            dto.setSystem(o.getParent().getName());

            response.add(dto);
        }


        if (response.isEmpty()) throw new ServiceException(Error.LIST_IS_EMPTY);

        if (Objects.equals(query.getOrder(), "ASC")){
            response.sort(Comparator.comparing(OrganResource::getName));
        } else if (Objects.equals(query.getOrder(), "DESC")) {
            response.sort(Comparator.comparing(OrganResource::getName).reversed());
        }
        return response;

    }

    @Override
    public List<SystemResource> findSystems() {
        List<HumanAnatomy> systems = humanAnatomyRepository.findAllSystems();
        List<SystemResource> systemResponse = new ArrayList<>();
        for (HumanAnatomy humanAnatomy : systems) {
            systemResponse.add(toSystemListResource(humanAnatomy));
        }
        if (systemResponse.isEmpty()) throw new ServiceException(Error.LIST_IS_EMPTY);
        return systemResponse;
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
        return humanAnatomyRepository.save(organ);
    }

    @Override
    public MenuResource mainMenu(String email) {


        MenuResource mainMenu = new MenuResource();
        User user = userService.findByEmail(email);

        mainMenu.setRecommendation(toSystemListResource(findById(1L)));
        mainMenu.setUserId(user.getId());
        mainMenu.setRecentActivity(findSystems());
        mainMenu.setNoteCount(noteService.getAllNotesByUserId(user.getId()).size());
        mainMenu.setQuizCount(quizAttemptService.getAllQuizAttemptByUserId(user.getId()).size());

        return mainMenu;
    }


    private SystemResource toSystemListResource(HumanAnatomy humanAnatomy) {

        SystemResource systemListResource = new SystemResource();

        systemListResource.setId(humanAnatomy.getId());
        systemListResource.setName(humanAnatomy.getName());
        systemListResource.setShortDetail(humanAnatomy.getShortDetail());
        systemListResource.setOrgansNumber(humanAnatomy.getOrgansNumber());
        Optional<Image> shortImage = imageRepository.getByTypeAndHumanAnatomyId(ImageType.SHORT_IMAGE, humanAnatomy.getId());
        if (shortImage.isEmpty()) throw new ServiceException(Error.ELEMENT_DOES_NOT_EXIST);
        systemListResource.setImage(shortImage.get().getUrl());

        return systemListResource;

    }
}
