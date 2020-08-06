package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/all")
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping(path = "/add")
    public String addNewUser(@RequestBody User user){
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<User> one(@PathVariable(value = "id") Integer id){
        User user =  userRepository.findById(id)
        .orElseThrow(()-> new UserNotFoundException(id));

        return ResponseEntity.ok().body(user);
    }

    @PutMapping(path = "/user/{id}")
    public User replaceUser(@RequestBody User newUser, @PathVariable("id") int id){
        return userRepository.findById(id).map(user->{
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        })
        .orElseGet(()->{
            newUser.setId(id);
            return userRepository.save(newUser);
        });
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable(value = "id") Integer id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().body("User deleted with id " + id);
    }
    
}