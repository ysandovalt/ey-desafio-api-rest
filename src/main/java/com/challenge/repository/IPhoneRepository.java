package com.challenge.repository;

import com.challenge.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ysand
 */
public interface IPhoneRepository extends JpaRepository<PhoneEntity, Long>{
    
}
