package com.cjc.movers_and_packers.movers_and_packers.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.movers_and_packers.movers_and_packers.DTO.ErrorResponseDTO;
import com.cjc.movers_and_packers.movers_and_packers.DTO.LoginDTO;
import com.cjc.movers_and_packers.movers_and_packers.DTO.SuccessResponseDTO;
import com.cjc.movers_and_packers.movers_and_packers.DTO.UserDTO;
import com.cjc.movers_and_packers.movers_and_packers.DTO.UserExistsDTO;
import com.cjc.movers_and_packers.movers_and_packers.DTO.UserUpdateDTO;
import com.cjc.movers_and_packers.movers_and_packers.entities.User;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.InvalidCredentialsException;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.PasswordRequiredException;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.UserAlreadyExistsException;
import com.cjc.movers_and_packers.movers_and_packers.exceptions.UserNotFoundException;
import com.cjc.movers_and_packers.movers_and_packers.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired UserServices userService;

   @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            User user = userService.registerUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (UserAlreadyExistsException | PasswordRequiredException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO("An error occurred during registration."));
        }
    }

    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            User user = userService.loginUser(loginDTO);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException | InvalidCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDTO(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO("An error occurred during login."));
        }
    }

    @PutMapping(value = "/update/{userId}", produces = "application/json")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long userId, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        try {
            User updatedUser = userService.updateUserProfile(userId, userUpdateDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(ex.getMessage()));
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO("An error occurred while updating the profile."));
        }
    }

    @DeleteMapping(value = "/delete/{userId}", produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new SuccessResponseDTO("User deleted successfully."));
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponseDTO("An error occurred while deleting the user."));
        }
    }
    // @ExceptionHandler(Exception.class)
    // public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
    //     ErrorResponseDTO errorResponse = new ErrorResponseDTO(
    //     "An unexpected error occurred",
    //     HttpStatus.INTERNAL_SERVER_ERROR.value(),
    //     HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
    //     );
    //     return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    // @PostMapping("/login")
    // public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password){
    //     String user = userService.loginUser(email, password);
    //     return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    // }
  
    // @PostMapping("/login/{email}/{password}")
    // public ResponseEntity<String> loginUser(@PathVariable String email, @PathVariable String password){
    //     String user = userService.loginUser(email, password);
    //     return new ResponseEntity<>(user, HttpStatus.OK);
    // }

    // @PostMapping(value = "/doesUserExist", produces = "application/json")
    // public ResponseEntity<Boolean> doesUserExist(@RequestBody UserExistsDTO userExistsDTO) {
    //     Boolean user = userService.doesUserExist(userExistsDTO);
    //     return ResponseEntity.status(HttpStatus.OK).body(user);
    // }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
    //     User userUpdate = userService.updateUser(user,id);
    //     return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    // }

    // @DeleteMapping("/Delete/{id}")
    // public ResponseEntity<String> deleteUser(@PathVariable Long id){
    //     String deleteUser = userService.deleteUser(id);
    //     return new ResponseEntity<>(deleteUser, HttpStatus.OK);
    // }

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
