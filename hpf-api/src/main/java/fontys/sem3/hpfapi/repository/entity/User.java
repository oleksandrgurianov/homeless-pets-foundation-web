package fontys.sem3.hpfapi.repository.entity;

public class User {
    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean approved;

    public User(int id, String fullName, String email, String phoneNumber, String password, Boolean approved) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.approved = approved;
    }
}
