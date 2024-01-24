package sorcer.flatfinder;

public class Flat {
    public String city;
    public String street;
    public String buildingNumber;
    public Tenant tenant;

    public Flat(String city, String street, String buildingNumber){
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
    }
}
