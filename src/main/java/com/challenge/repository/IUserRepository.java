package com.challenge.repository;

import com.challenge.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ysand
 */
public interface IUserRepository extends JpaRepository<UserEntity, UUID>{
    
    Optional<UserEntity> findByCorreoIgnoreCase(String correo);
    
    Optional<UserEntity> findByCorreoIgnoreCaseAndActivo(String correo, Boolean estado);
    
    List<UserEntity> findAllByActivo(Boolean estado);
}
