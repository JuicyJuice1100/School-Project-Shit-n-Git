public class User{
    private String username, password;
    private int permissionId;

    public User(String username, String password, int permissionId){
        this.username = username;
        this.password = password;
        this.permissionId = permissionId;
    }
}