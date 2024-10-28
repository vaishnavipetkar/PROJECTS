package com.cjc.movers_and_packers.movers_and_packers.services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cjc.movers_and_packers.movers_and_packers.DTO.LoginDTO;
import com.cjc.movers_and_packers.movers_and_packers.DTO.UserDTO;
import com.cjc.movers_and_packers.movers_and_packers.DTO.UserExistsDTO;
import com.cjc.movers_and_packers.movers_and_packers.DTO.UserUpdateDTO;
import com.cjc.movers_and_packers.movers_and_packers.entities.User;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.InvalidCredentialsException;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.InvalidUserInputException;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.PasswordRequiredException;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.UserAlreadyExistsException;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.UserNotFoundException;
import com.cjc.movers_and_packers.movers_and_packers.repositories.UserRepository;

@Service
public class UserServices{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserDTO userDTO) {
        if (!isValidEmail(userDTO.getEmail()) || !isValidPhone(userDTO.getContactNumber())) {
            throw new InvalidUserInputException("Invalid email or phone number.");
        }

        if (doesUserExist(userDTO.getEmail(), userDTO.getContactNumber())) {
            throw new UserAlreadyExistsException("User with the same email or phone number already exists.");
        }

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setContactNumber(userDTO.getContactNumber());
        user.setAddress(userDTO.getAddress());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        } else {
            throw new PasswordRequiredException("Please enter a password.");
        }

        return userRepository.save(user);
    }

    private boolean doesUserExist(String email, Long contactNumber) {
        return userRepository.findByEmail(email).isPresent() || userRepository.findByContactNumber(contactNumber).isPresent();
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    private boolean isValidPhone(Long contactNumber) {
        return contactNumber != null && contactNumber.toString().length() == 10;
    }

    public User loginUser(LoginDTO loginDTO) {
        Optional<User> userOptional;
        
        if (loginDTO.getEmail() != null && !loginDTO.getEmail().isEmpty()) {
            userOptional = userRepository.findByEmail(loginDTO.getEmail());
        } else if (loginDTO.getContactNumber() != null) {
            userOptional = userRepository.findByContactNumber(loginDTO.getContactNumber());
        } else {
            throw new InvalidCredentialsException("Email or contact number must be provided for login.");
        }

        User user = userOptional.orElseThrow(() -> new UserNotFoundException("User not found."));
        
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password.");
        }   
        return user;
    }

    public User updateUserProfile(Long userId, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // Check if email is being updated and if it already exists
        if (userUpdateDTO.getEmail() != null && !userUpdateDTO.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(userUpdateDTO.getEmail()).isPresent()) {
                throw new UserAlreadyExistsException("Email already in use.");
            }
            user.setEmail(userUpdateDTO.getEmail());
        }

        // Check if contact number is being updated and if it already exists
        if (userUpdateDTO.getContactNumber() != null && !userUpdateDTO.getContactNumber().equals(user.getContactNumber())) {
            if (userRepository.findByContactNumber(userUpdateDTO.getContactNumber()).isPresent()) {
                throw new UserAlreadyExistsException("Contact number already in use.");
            }
            user.setContactNumber(userUpdateDTO.getContactNumber());
        }

        // Update other fields if provided
        if (userUpdateDTO.getName() != null) {
            user.setName(userUpdateDTO.getName());
        }
        if (userUpdateDTO.getAddress() != null) {
            user.setAddress(userUpdateDTO.getAddress());
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        userRepository.delete(user);
    }


    // public String loginUser(String email, String password) {

    //     Optional<User> user = userRepository.findUserByEmailAndContactNumber(email, password);
    //     if(user.isPresent()){
    //         return "User logged in";
    //     }
    //     else{
    //         return "User Not Present";
    //     }
        
    // }

    //  public Boolean doesUserExist(UserExistsDTO userExistsDTO) {
    //     Boolean isEmailInBody = userExistsDTO.getEmail() != null && !userExistsDTO.getEmail().isEmpty();
    //     Boolean isMobileInBody = userExistsDTO.getContactNumber() != null && !userExistsDTO.getContactNumber().toString().isEmpty();
    //     Boolean doesUserExistByEmail = isEmailInBody
    //             && userRepository.findByEmail(userExistsDTO.getEmail()).isPresent();
    //     Boolean doesUserExistByMobile = isMobileInBody
    //             && userRepository.findByContactNumber(userExistsDTO.getContactNumber()).isPresent();
    //     return doesUserExistByEmail || doesUserExistByMobile;
    // }


    // public User updateUser(User user, Long id) {
    // List<User> users = userRepository.findAll();
    // User u = null;

    // for(User userdb : users){

    //     if(userdb.getUserId() == id){
    //         userdb.setName(user.getName());
    //         userdb.setEmail(user.getEmail());
    //         user.setAddress(user.getAddress());
    //         userdb.setContactNumber(user.getContactNumber());
    //         userdb.setPassword(user.getPassword());
    //         u = userRepository.save(userdb);
    //      }
    // }
    // return u;
    // }

    // public String deleteUser(Long id) {
    //   Optional<User> users = userRepository.findById(id);
    //   if(users.isPresent()){
    //     userRepository.deleteById(id);
    //   }
    //     return "User Deleted";
    // }

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
