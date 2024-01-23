package sorcer.flatfinder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OwnerStore {
    private List<Owner> ownersList;

    public OwnerStore() {
        this.ownersList = new ArrayList<>();

        ownersList.add(new Owner("Michael", "Jordan", new Date("1963-02-17")));
        ownersList.add(new Owner("Jennifer", "Lopez", new Date("1969-07-24")));
        ownersList.add(new Owner("Elon", "Musk", new Date("1971-06-28")));
        ownersList.add(new Owner("Oprah", "Winfrey", new Date("1954-01-29")));
        ownersList.add(new Owner("Bill", "Gates", new Date("1955-10-28")));
    }

    public List<Owner> getOwners() {
        return ownersList;
    }
}
