package be.afelio.babell.tp_babell.api.dto.person;

import java.util.Objects;

public class UpdatePersonDto extends AbstractPerson {
    private Integer id;

    public UpdatePersonDto() {
    }

    public UpdatePersonDto(String firstname, String lastname, String email, String password) {
        super(firstname, lastname, email, password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UpdatePersonDto{" +
                "id=" + id + super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UpdatePersonDto that = (UpdatePersonDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
