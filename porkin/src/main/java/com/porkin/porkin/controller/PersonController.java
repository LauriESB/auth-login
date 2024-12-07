package com.porkin.porkin.controller;

import com.porkin.porkin.dto.PersonDTO;
import com.porkin.porkin.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

  @Autowired
  private PersonService personService;

  @Autowired
  private AuthControler authControler;

  @GetMapping
  public List<PersonDTO> listAll() {
    return personService.listAll();
  }

  @GetMapping("/{username}")
  public PersonDTO listAll(@PathVariable("username") String username) {
    return personService.findByUsername(username);
  }

  @PostMapping
  public void insert(@RequestBody PersonDTO personDTO) {
    authControler.register(personDTO);
  }

  @PutMapping("/{username}")
  public PersonDTO update(@PathVariable("username") String username, @RequestBody PersonDTO personDTO) {
    return personService.update(username, personDTO);
  }

  @DeleteMapping("/{username}")
  public ResponseEntity<Void> delete(@PathVariable("username") String username) {
    personService.delete(username);
    return ResponseEntity.ok().build();
  }

}