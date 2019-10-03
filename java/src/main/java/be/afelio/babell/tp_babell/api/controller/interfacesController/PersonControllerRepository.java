package be.afelio.babell.tp_babell.api.controller.interfacesController;

import be.afelio.babell.tp_babell.api.dto.person.CreatePersonDto;
import be.afelio.babell.tp_babell.api.dto.person.PersonDto;
import be.afelio.babell.tp_babell.api.dto.person.UpdatePersonDto;

public interface PersonControllerRepository {
    void createPerson(CreatePersonDto createPersonDto);

    PersonDto findOnePersonByEmail(String email);

    void updatePersonDto(UpdatePersonDto updatePersonDto);

    void deletePerson(String email);

    PersonDto findOneByFirstnameAndLastname(String firstname, String lastname);
}
