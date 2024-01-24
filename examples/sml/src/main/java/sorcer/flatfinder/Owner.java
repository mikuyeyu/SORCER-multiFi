package sorcer.flatfinder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Owner extends User {
    private final ArrayList<Flat> _flats;

    public Owner(String firstName, String lastName, LocalDate birthDate, Flat[] flats) {
        super(firstName, lastName, birthDate);
        this._flats = new ArrayList<>();
        this._flats.addAll(Arrays.asList(flats));
    }

    public Flat[] getFlats(){
        Flat[] flats = new Flat[this._flats.size()];
        flats = this._flats.toArray(flats);

        return flats;
    }
}
