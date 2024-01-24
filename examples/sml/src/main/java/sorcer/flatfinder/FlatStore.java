package sorcer.flatfinder;

import java.util.HashMap;
import java.util.Map;

public class FlatStore {
    public static Map<String, Flat> flats;
    static {
        flats = new HashMap<>();
        flats.put("Potulicka", new Flat("Warsaw", "Potulicka", "47"));
        flats.put("Wawelska", new Flat("Warsaw", "Wawelska", "99"));
    }
}