package com.challenge.service.implement;

import com.challenge.entity.UserEntity;
import com.challenge.repository.IUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author ysand
 */
@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<UserEntity> usuario = userRepository.findByCorreoIgnoreCaseAndActivo(email, Boolean.TRUE);
        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuario no existe");
        }
        if(!usuario.get().getActivo()){
            throw new DisabledException("Usuario inactivo");
        }
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ADMIN"));
        return new User(usuario.get().getCorreo(), usuario.get().getClave(), authorityList);
    }
}
