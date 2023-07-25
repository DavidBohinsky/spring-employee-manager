package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.UserM;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserM, Long> {
   UserM findByUsername(String username);
}