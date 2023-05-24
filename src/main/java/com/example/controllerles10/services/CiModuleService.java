package com.example.controllerles10.services;


import com.example.controllerles10.dtos.TelevisionDto;
import com.example.controllerles10.exceptions.RecordNotFoundException;
import com.example.controllerles10.model.CiModule;
import com.example.controllerles10.model.Television;
import com.example.controllerles10.repository.CiModuleRepository;
import com.example.controllerles10.repository.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CiModuleService {

    private final TelevisionRepository televisionRepo;
    private final CiModuleRepository ciModuleRepository;

    private TelevisionDto televisionDto;

    public CiModuleService(TelevisionRepository televisionRepo, CiModuleRepository ciModuleRepository) {
        this.televisionRepo = televisionRepo;
        this.ciModuleRepository = ciModuleRepository;
    }

    public String assignTelevisionToCiModule(Long id, Long Television_id) throws RecordNotFoundException {
        Optional<CiModule> optionalCiModule = ciModuleRepository.findById(id);
        Optional<Television> optionalTelevision = televisionRepo.findById(Television_id);
        if (optionalCiModule.isEmpty() && optionalTelevision.isEmpty()) {
            throw new RecordNotFoundException("Cannot find a module or television with id: " + id);
        }
            CiModule ciModule = optionalCiModule.get();
            Television television = optionalTelevision.get();
            ciModule.setTelevision(television);
            ciModuleRepository.save(ciModule);
            return "Ci module " + id + " is assigned to television " + Television_id;


    }


}
