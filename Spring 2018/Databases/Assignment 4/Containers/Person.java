public class Person{
    private int id, genderId;
    private String bio, firstName, middleName, lastName, lastKnownPhoneNumber;

    public Person(int id, int genderId, String bio, String firstName, String middleName, String lastName, String lastKnownPhoneNumber){
        this.id = id;
        this.genderId = genderId;
        this.bio = bio;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.lastKnownPhoneNumber = lastKnownPhoneNumber;
    }
}