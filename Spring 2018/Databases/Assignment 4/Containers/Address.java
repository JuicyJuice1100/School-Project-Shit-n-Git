public class Address{
    private int id, zip, aptNum, houseNum;
    private String state, city, street;

    public Address(int id, int zip, int aptNum, int houseNum, String state, String city, String street){
        this.id = id;
        this.zip = zip;
        this.aptNum = aptNum;
        this.houseNum = houseNum;
        this.state = state;
        this.city = city;
        this.street = street;
    }
}