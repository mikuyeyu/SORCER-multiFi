package sorcer.flatfinder;

import java.time.LocalDate;
import java.util.*;

public class OwnerStore {
    public static List<Owner> owners;
    static {
        owners = new ArrayList<>();

        Flat potulickaFlat = FlatStore.flats.get("Potulica");
        Flat wawelskaFlat = FlatStore.flats.get("Wawelska");

        owners.add(new Owner("Michael", "Jordan", LocalDate.parse("1963-02-17"), new Flat[]{potulickaFlat}));
        owners.add(new Owner("Jennifer", "Lopez", LocalDate.parse("1969-07-24"), new Flat[]{wawelskaFlat}));
    }
}
