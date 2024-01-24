package sorcer.flatfinder;

import java.time.LocalDate;
import java.util.Date;

public abstract class User {
    private String _firstName;
    private String _lastName;
    private LocalDate _birthDate;

    protected User(String firstName, String lastName, LocalDate birthDate) {
        this._firstName = firstName;
        this._lastName = lastName;
        this._birthDate = birthDate;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public LocalDate getBirthDate() {
        return _birthDate;
    }

    public void setFirstName(String firstName) {
        this._firstName = firstName;
    }

    public void setLastName(String lastName) {
        this._lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this._birthDate = birthDate;
    }
}
