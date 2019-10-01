package be.afelio.babell.tp_babell.api.controller;

import be.afelio.babell.tp_babell.api.dto.person.CreatePersonDto;
import be.afelio.babell.tp_babell.api.dto.person.PersonDto;

public interface PersonControllerRepository {
    void createPerson(CreatePersonDto createPersonDto);

    PersonDto findOneTodoByEmail(String email);
}
