package sorcer.flatfinder;

import java.util.HashMap;
import java.util.Map;

public class FlatStore {
    public static Map<String, Flat> flats;
    static {
        flats = new HashMap<>();
        flats.put("Potulica", new Flat("Warsaw", "Potulica", "47"));
        flats.put("Wawelska", new Flat("Warsaw", "Wawelska", "99"));
    }
}