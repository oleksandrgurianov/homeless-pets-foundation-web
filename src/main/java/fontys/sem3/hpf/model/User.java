package fontys.sem3.hpf.model;
import java.util.Objects;

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

    public User() { }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Boolean getApproved() { return approved; }

    public void setApproved(Boolean approved) { this.approved = approved; };

    //Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email=" + email +
                ", phoneNumber=" + phoneNumber +
                ", password=" + password +
                ", approved=" + approved +
                '}';
    }
}
