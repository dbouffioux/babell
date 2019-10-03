package be.afelio.babell.tp_babell.api.dto.person;

import be.afelio.babell.tp_babell.persistence.entities.PersonEntity;

public class PersonDto {

    private String firstname;
    private String lastname;
    private String email;

    public PersonDto() {
    }

    public PersonDto(String firstname, String lastname, String email) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static PersonDto from(PersonEntity personEntity) {
        return new PersonDto(
                personEntity.getFirstname(),
                personEntity.getLastname(),
                personEntity.getEmail());
    }

    @Override
    public String toString() {
        return "PersonDto [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
        result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonDto other = (PersonDto) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstname == null) {
            if (other.firstname != null)
                return false;
        } else if (!firstname.equals(other.firstname))
            return false;
        if (lastname == null) {
            return other.lastname == null;
        } else return lastname.equals(other.lastname);
    }


}
