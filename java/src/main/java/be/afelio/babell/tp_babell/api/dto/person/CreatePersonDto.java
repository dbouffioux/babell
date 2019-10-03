package be.afelio.babell.tp_babell.api.dto.person;

import java.util.Objects;

public class CreatePersonDto extends AbstractPerson {


    public CreatePersonDto() {
    }

    public CreatePersonDto(String firstname, String lastname, String email, String password) {
        super(firstname, lastname, email, password);
    }

    @Override
    public String toString() {
        return "CreatePersonDto{}"+ super.toString();
    }
}
