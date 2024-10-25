package com.cjc.movers_and_packers.movers_and_packers.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cjc.movers_and_packers.movers_and_packers.DTO.UserDTO;
import com.cjc.movers_and_packers.movers_and_packers.DTO.UserExistsDTO;
import com.cjc.movers_and_packers.movers_and_packers.entities.User;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.PasswordRequiredException;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.UserAlreadyExistsException;
import com.cjc.movers_and_packers.movers_and_packers.repositories.UserRepository;

@Service
public class UserServices{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    

    public User registerUser(UserDTO userDTO) {

        // check whether email is present or mobile number is present
        Boolean isEmailInBody = userDTO.getEmail() != null && !userDTO.getEmail().isEmpty();
        Boolean isMobileInBody = userDTO.getContactNumber() != null && !userDTO.getContactNumber().toString().isEmpty();

         // check if user already exists
         Boolean isUserExists = doesUserExist(new UserExistsDTO(userDTO.getEmail(), userDTO.getContactNumber()));
         if(isUserExists){
            throw new UserAlreadyExistsException("User Already Exists");
         }

         User user = new User();

         if (isEmailInBody) {
            user.setEmail(userDTO.getEmail());
        }
        if (isMobileInBody) {
            user.setContactNumber(userDTO.getContactNumber());; 
        }
        user.setName(userDTO.getName());
       // user.setEmail(userDTO.getEmail());
       // user.setContactNumber(userDTO.getContactNumber());
        user.setAddress(userDTO.getAddress());
        
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        } else {
            throw new PasswordRequiredException("Please Enter Password");
        }

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

     public Boolean doesUserExist(UserExistsDTO userExistsDTO) {
        Boolean isEmailInBody = userExistsDTO.getEmail() != null && !userExistsDTO.getEmail().isEmpty();
        Boolean isMobileInBody = userExistsDTO.getContactNumber() != null && !userExistsDTO.getContactNumber().toString().isEmpty();
        Boolean doesUserExistByEmail = isEmailInBody
                && userRepository.findByEmail(userExistsDTO.getEmail()).isPresent();
        Boolean doesUserExistByMobile = isMobileInBody
                && userRepository.findByContactNumber(userExistsDTO.getContactNumber()).isPresent();
        return doesUserExistByEmail || doesUserExistByMobile;
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
