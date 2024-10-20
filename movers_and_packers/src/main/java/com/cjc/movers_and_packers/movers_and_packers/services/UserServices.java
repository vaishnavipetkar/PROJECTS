package com.cjc.movers_and_packers.movers_and_packers.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cjc.movers_and_packers.movers_and_packers.entities.User;
import com.cjc.movers_and_packers.movers_and_packers.repositories.UserRepository;

@Service
public class UserServices{

    @Autowired UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public String loginUser(String email, String password) {

        Optional<User> user = userRepository.findUserByEmailAndContactNumber(email, password);
        if(user.isPresent()){
            return "User logged in";
        }
        else{
            return "User Not Present";
        }
        
    }

    public User updateUser(User user, Long id) {
    List<User> users = userRepository.findAll();
    User u = null;

    for(User userdb : users){

        if(userdb.getUserId() == id){
            userdb.setName(user.getName());
            userdb.setEmail(user.getEmail());
            user.setAddress(user.getAddress());
            userdb.setContactNumber(user.getContactNumber());
            userdb.setPassword(user.getPassword());
            u = userRepository.save(userdb);
         }
    }
    return u;
    }

    public String deleteUser(Long id) {
      Optional<User> users = userRepository.findById(id);
      if(users.isPresent()){
        userRepository.deleteById(id);
      }
        return "User Deleted";
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User getSingleUser(Long id) {
       Optional<User> user = userRepository.findById(id);
       User userData = null;
       if(user.isPresent()){
        userData = user.get();
       }
    return userData;
    }
}
