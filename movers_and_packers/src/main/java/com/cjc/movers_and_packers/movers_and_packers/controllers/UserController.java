package com.cjc.movers_and_packers.movers_and_packers.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cjc.movers_and_packers.movers_and_packers.entities.User;
import com.cjc.movers_and_packers.movers_and_packers.services.UserServices;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired UserServices userService;

    @PostMapping("/register" )
    public ResponseEntity<User> registerUser(@RequestBody User user){
       User users = userService.registerUser(user);
       return ResponseEntity.ok(users);
    }

    // @PostMapping("/login")
    // public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password){
    //     String user = userService.loginUser(email, password);
    //     return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    // }
  
    @PostMapping("/login/{email}/{password}")
    public ResponseEntity<String> loginUser(@PathVariable String email, @PathVariable String password){
        String user = userService.loginUser(email, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        User userUpdate = userService.updateUser(user,id);
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        String deleteUser = userService.deleteUser(id);
        return new ResponseEntity<>(deleteUser, HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getSingleUser/{id}")
    public ResponseEntity<User> getSingleUser(@PathVariable Long id){
        User user = userService.getSingleUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
