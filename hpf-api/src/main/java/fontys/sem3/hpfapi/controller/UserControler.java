package fontys.sem3.hpfapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class UserControler {
    private PetsService petsService;

    public UsersController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping
    //GET at http://localhost:XXXX/users
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(value = "approved") Optional<Boolean> approved) {
        List<User> users = null;
        if (approved.isPresent()) {
            users = fakeDataStore.getUsers(approved);
        }
        else users = fakeDataStore.getUsers();
        if(users != null) return ResponseEntity.ok().body(users);
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("{id}")
    //GET at http://localhost:XXXX/users/{id}
    public ResponseEntity<User> getUserPath(@PathVariable(value = "id") int id) {
        User user = fakeDataStore.getUser(id);
        if(user != null) return ResponseEntity.ok().body(user);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping()
    //POST at http://localhost:XXXX/users
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (!fakeDataStore.addUser(user)) {
            String entity =  "User with id " + user.getId() + " already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "user" + "/" + user.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @PutMapping()
    //PUT at http://localhost:XXXX/users
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (fakeDataStore.updateUser(user)) return ResponseEntity.noContent().build();
        else return new ResponseEntity("Please provide a valid id.", HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    //PUT at http://localhost:XXXX/users/{id}
    public ResponseEntity<User> updateUser(@PathVariable("id") int id,  @RequestParam("fullName") String fullName, @RequestParam("email") String email, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password, @RequestParam("approved") Boolean approved) {
        User user = fakeDataStore.getUser(id);
        if (user == null) return new ResponseEntity("Please provide a valid id.", HttpStatus.NOT_FOUND);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        user.setApproved(approved);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    //DELETE at http://localhost:XXXX/users/{id}
    public ResponseEntity deleteUser(@PathVariable int id) {
        fakeDataStore.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}