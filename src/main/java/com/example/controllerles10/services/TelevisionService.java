package com.example.controllerles10.services;

import com.example.controllerles10.dtos.TelevisionDto;
import com.example.controllerles10.exceptions.RecordNotFoundException;
import com.example.controllerles10.model.RemoteController;
import com.example.controllerles10.model.Television;
import com.example.controllerles10.model.WallBracket;
import com.example.controllerles10.repository.RemoteControllerRepository;
import com.example.controllerles10.repository.TelevisionRepository;
import com.example.controllerles10.repository.WallBracketRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepo;
    private final RemoteControllerRepository remoteControllerRepository;
    private TelevisionDto televisionDto;
    private final WallBracketRepository wallBracketRepository;

    public TelevisionService(TelevisionRepository televisionRepo, RemoteControllerRepository remoteControllerRepository,
                             WallBracketRepository wallBracketRepository) {
        this.televisionRepo = televisionRepo;
        this.remoteControllerRepository = remoteControllerRepository;
        this.wallBracketRepository = wallBracketRepository;
    }

    public TelevisionDto transferModelToDTO(Television television) {
        TelevisionDto televisionDto = new TelevisionDto();
        BeanUtils.copyProperties(television, televisionDto);
        return televisionDto;
    }

    List<TelevisionDto> televisionDtos = new ArrayList<>();


    public List<TelevisionDto> getAllTelevision() {

        List<Television> televisions = televisionRepo.findAll();

        for (Television television : televisions) {

            televisionDto = new TelevisionDto();

            televisionDto.setId(television.getId());
            televisionDto.setBrand(television.getBrand());
            televisionDto.setPrice(television.getPrice());
            televisionDto.setType(television.getType());
            televisionDto.setScreenSize(television.getScreenSize());

            televisionDtos.add(televisionDto);
        }

        return televisionDtos;

    }

    public TelevisionDto getTelevision(Long id) {

        Television television = televisionRepo.getReferenceById(id);

            televisionDto = new TelevisionDto();

            assert television.id != null;
            televisionDto.setId(television.getId());
            televisionDto.setBrand(television.getBrand());
            televisionDto.setPrice(television.getPrice());
            televisionDto.setType(television.getType());
            televisionDto.setScreenSize(television.getScreenSize());

            return televisionDto;

    }


    public Long createTelevision(TelevisionDto  televisionDto) {

        Television television = new Television();

        television.setBrand(televisionDto.brand);
        television.setPrice(televisionDto.price);
        television.setType(televisionDto.type);
        television.setScreenSize(televisionDto.screenSize);

        televisionRepo.save(television);

        return television.getId();
    }


    public void updateTelevision(Long id, TelevisionDto televisionDto) {

        Television television = televisionRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Television not found with id " + id)
        );

        television.setBrand(televisionDto.getBrand());
        television.setPrice(televisionDto.getPrice());
        television.setType(televisionDto.getType());
        television.setScreenSize(televisionDto.getScreenSize());

        televisionRepo.save(television);
    }

    public String assignWallBracketToTelevision(Long id, Long wallBracketId) throws RecordNotFoundException {
        Optional<Television> optionalTelevision = televisionRepo.findById(id);
        Optional<WallBracket> optionalWallBracket = wallBracketRepository.findById(wallBracketId);

        if (optionalTelevision.isEmpty() && optionalWallBracket.isEmpty()) {
            throw new RecordNotFoundException("Television or wall bracket not found");
        }

        Television television = optionalTelevision.get();
        WallBracket wallBracket = optionalWallBracket.get();
        List<WallBracket> wallBracketList = television.getWallBrackets();
        wallBracketList.add(wallBracket);
        television.setWallBrackets(wallBracketList);

        televisionRepo.save(television);

        return  "Television and wall bracket assigned";
    }

    public TelevisionDto assignRemoteControllerToTelevision(Long id, Long remote_controller_id)
        throws  RuntimeException {

        Optional<Television> optionalTelevision = televisionRepo.findById(id);
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById( remote_controller_id);

        if (optionalTelevision.isEmpty() && optionalRemoteController.isEmpty()) {
            throw new RuntimeException("Television or remote controller not found with id " + id);
        }

        Television television = optionalTelevision.get();
        RemoteController remoteController = optionalRemoteController.get();

        television.setRemoteController(remoteController);
        Television updatedTelevision = televisionRepo.save(television);

        return transferModelToDTO(updatedTelevision);
    }

    public void deleteTelevision(Long id) {
        Television television = televisionRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Television not found with id " + id)
        );

        televisionRepo.delete(television);
    }

}
