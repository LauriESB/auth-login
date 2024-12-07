package com.porkin.porkin.service;

import com.porkin.porkin.dto.PersonDTO;
import com.porkin.porkin.entity.PersonEntity;
import com.porkin.porkin.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

  @Autowired
  public PersonRepository personRepository;

  public List<PersonDTO> listAll() {
    List<PersonEntity> personEntities = personRepository.findAll();
    return personEntities.stream().map(PersonDTO::new).toList();
  }

  public PersonDTO findByUsername(String username) {
    PersonEntity person = (PersonEntity) personRepository.findByUsername(username);
    return new PersonDTO(person);
  }

  /*
  public void insert(PersonDTO personDTO) {
    PersonEntity personEntity = new PersonEntity(personDTO);
    personRepository.save(personEntity);
  }

   */

  public PersonDTO update(String username, PersonDTO personDTO) {
    PersonEntity personEntity = (PersonEntity) personRepository.findByUsername(username);

    if(personDTO.getName() != null) {
      personEntity.setName(personDTO.getName());
    }

    if(personDTO.getUsername() != null) {
      personEntity.setPassword(personDTO.getUsername());
    }

    if(personDTO.getEmail() != null) {
      personEntity.setEmail(personDTO.getEmail());
    }

    if(personDTO.getPassword() != null) {
      personEntity.setPassword(personDTO.getPassword());
    }

    return new PersonDTO(personRepository.save(personEntity));

  }

  public void delete(String username) {
    PersonEntity personEntity = (PersonEntity) personRepository.findByUsername(username);
    personRepository.delete(personEntity);
  }

  public PersonDTO findById(Long id) {
    return new PersonDTO(personRepository.findById(id).get());
  }

}
