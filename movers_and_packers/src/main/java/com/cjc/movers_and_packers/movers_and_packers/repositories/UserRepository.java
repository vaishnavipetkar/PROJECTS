package com.cjc.movers_and_packers.movers_and_packers.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cjc.movers_and_packers.movers_and_packers.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
