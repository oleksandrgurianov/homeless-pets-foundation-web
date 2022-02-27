package fontys.sem3.hpf.repository;
import fontys.sem3.hpf.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeDataStore {
    private final List<User> userList = new ArrayList<>();

    public FakeDataStore() {
        userList.add(new User(1, "Albert Mason", "a.mason@gmail.com", "0663899230", "password", true));
        userList.add(new User(2, "Maria Grossman", "m.grossman@gmail.com", "0644111033", "password", false));
        userList.add(new User(3, "Elaine Spearman", "e.spearman@gmail.com", "0645508538", "password", true));
        userList.add(new User(4, "Jeanie Markham", "j.markham@gmail.com", "0694551729", "password", false));
        userList.add(new User(5, "Stella Jenkins", "s.jenkins@netease.com", "0690925070", "password", true));
        userList.add(new User(6, "Joanne Hart", "j.hart@netease.com", "0664013878", "password", false));
        userList.add(new User(7, "James Coffin", "j.coffin@netease.com", "0615713110", "password", true));
        userList.add(new User(8, "Robert Cobb", "r.cobb@outlook.com", "0687015388", "password", false));
        userList.add(new User(9, "Ramona Gallegos", "r.gallegos@outlook.com", "0641382683", "password", true));
        userList.add(new User(10, "Charles Fuller", "c.fuller@tencent.com", "0644044761", "password", false));
    }

    public List<User> getUsers() { return userList; }

    public List<User> getUsers(Optional<Boolean> approved) {
        List<User> filtered = new ArrayList<>();
        for (User user : userList) {
            if (user.getApproved().equals(approved))
                filtered.add(user);
        }
        return filtered;
    }

    public User getUser(int id) {
        for (User user : userList) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    public boolean addUser(User user) {
        if (this.getUser(user.getId()) != null) return false;
        userList.add(user);
        return true;
    }

    public boolean updateUser(User user) {
        User old = this.getUser(user.getId());
        if (old == null) return false;
        old.setFullName(user.getFullName());
        old.setEmail(user.getFullName());
        old.setPhoneNumber(user.getPhoneNumber());
        old.setPassword(user.getPassword());
        old.setApproved(user.getApproved());
        return true;
    }

    public boolean deleteUser(int id) {
        User user = getUser(id);
        if (user == null) return false;
        return userList.remove(user);
    }
}
