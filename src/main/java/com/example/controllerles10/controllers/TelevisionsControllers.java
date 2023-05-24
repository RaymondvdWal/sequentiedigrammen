package com.example.controllerles10.controllers;

import com.example.controllerles10.dtos.TelevisionDto;
import com.example.controllerles10.exceptions.RecordNotFoundException;
import com.example.controllerles10.exceptions.ToManyCharException;
import com.example.controllerles10.services.TelevisionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
public class TelevisionsControllers {

   private final TelevisionService televisionService;

   public TelevisionsControllers(TelevisionService televisionService) {
       this.televisionService = televisionService;
   }

    @GetMapping("/televisions")
    public List<TelevisionDto> getAllTelevisions() {
        return televisionService.getAllTelevision();
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable Long id) throws RecordNotFoundException {
        return  ResponseEntity.ok().body(televisionService.getTelevision(id));
    }

    @PostMapping("/addTelevision")
    public ResponseEntity<Object> addTelevision(@Valid @RequestBody TelevisionDto television, BindingResult br) throws ToManyCharException {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField()).append(": ");
                sb.append(fe.getDefaultMessage()).append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        }
        Long newId = televisionService.createTelevision(television);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).body(newId);
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@PathVariable(value = "id") Long televisionId,
        @Valid @RequestBody TelevisionDto televisionDto) throws RecordNotFoundException {
        televisionService.updateTelevision(televisionId, televisionDto);
        return ResponseEntity.ok().body(televisionDto);

    }

    @PutMapping("/televisions/{id}/remote/{remote_controller_id}")
    public ResponseEntity<TelevisionDto> assignRemoteControllerToTelevision(@PathVariable Long id, @PathVariable Long remote_controller_id) {
        return ResponseEntity.ok(televisionService.assignRemoteControllerToTelevision(id, remote_controller_id));
    }

    @PutMapping("/televisions/{id}/wallbracket/{wallbracket_id}")
    public ResponseEntity<String>assignWallBracketToTelevision(@PathVariable Long id, @PathVariable Long wallbracket_id) {
        return ResponseEntity.ok(televisionService.assignWallBracketToTelevision(id, wallbracket_id));
    }
    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<TelevisionDto> deleteTelevision(@PathVariable(value = "id") Long televisionId)
        throws RecordNotFoundException{
        televisionService.deleteTelevision(televisionId);
        return ResponseEntity.ok().build();
    }
}
