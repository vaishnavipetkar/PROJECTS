package com.cjc.movers_and_packers.movers_and_packers.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cjc.movers_and_packers.movers_and_packers.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    Optional<User> findUserByEmailAndContactNumber(String email, String password);
    
}
