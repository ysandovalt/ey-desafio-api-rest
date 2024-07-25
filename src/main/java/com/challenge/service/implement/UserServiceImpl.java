package com.challenge.service.implement;

import com.challenge.entity.UserEntity;
import com.challenge.entity.PhoneEntity;
import com.challenge.repository.IPhoneRepository;
import com.challenge.repository.IUserRepository;
import com.challenge.payload.PhoneCreateRequest;
import com.challenge.payload.UserCreateRequest;
import com.challenge.payload.UserCreateResponse;
import com.challenge.exception.ValidationException;
import com.challenge.payload.Login;
import com.challenge.payload.UserListResponse;
import com.challenge.payload.UserUpdateRequest;
import com.challenge.service.IUserService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author ysand
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IPhoneRepository phoneRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(IUserRepository userRepository, IPhoneRepository phoneRepository) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public UserCreateResponse create(UserCreateRequest request) throws ValidationException {
        log.info("Creacion de Usuario");
        String emailPattern = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w{2,}([-.]\\w+)*$";
        if (!request.getEmail().matches(emailPattern)) {
            log.error("Formato Email incorrecto {}", request.getEmail());
            throw new ValidationException("Formato Email incorrecto");
        }

        String passPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d{2})[A-Za-z\\d]{6,8}$"; //"([A-Z][a-z]*//d[a-z]*//d[a-z]+)"; 
        Pattern pattern = Pattern.compile(passPattern);

        if (!pattern.matcher(request.getPassword()).matches()) {
            log.error("Formato clave incorrecto {}", request.getEmail());
            throw new ValidationException("Formato clave incorrecto");
        }

        Optional<UserEntity> entity = userRepository.findByCorreoIgnoreCase(request.getEmail());
        UserEntity user = new UserEntity();

        if (entity.isPresent()) {
            if (entity.get().getActivo()) {
                throw new ValidationException("Email ya existe");
            } else {
                user = entity.get();
                user.setActivo(Boolean.TRUE);
                user.setModificado(LocalDateTime.now());
                user.setTelefonos(new ArrayList<>());
            }
        } else {
            user.setId(UUID.randomUUID());
            user.setCreado(LocalDateTime.now());
        }
        
        user.setClave(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setCorreo(request.getEmail());
        user.setNombre(request.getName());

        for (PhoneCreateRequest fono : request.getPhones()) {
            PhoneEntity phone = new PhoneEntity(fono.getContrycode(), fono.getCitycode(), fono.getNumber());
            user.addPhone(phone);
            phoneRepository.save(phone);
        }
        user = userRepository.save(user);
        return makeResponse(user);
    }

    @Override
    public UserCreateResponse modify(String email, UserUpdateRequest request) throws ValidationException {

        String passPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d{2})[A-Za-z\\d]{6,8}$"; //"([A-Z][a-z]*//d[a-z]*//d[a-z]+)"; 
        Pattern pattern = Pattern.compile(passPattern);
        if (!pattern.matcher(request.getPassword()).matches()) {
            log.error("Formato Clave incorrecto {}", email);
            throw new ValidationException("Formato Clave incorrecto");
        }
        Optional<UserEntity> entity = userRepository.findByCorreoIgnoreCaseAndActivo(email, Boolean.TRUE);
        if (!entity.isPresent()) {
            log.error("Email no existe {}", email);
            throw new ValidationException("Email no existe");
        }

        UserEntity user = entity.get();
        user.setClave(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setNombre(request.getName());
        user.setModificado(LocalDateTime.now());
        user.setTelefonos(new ArrayList<>());
        for (PhoneCreateRequest fono : request.getPhones()) {
            PhoneEntity phone = new PhoneEntity(fono.getContrycode(), fono.getCitycode(), fono.getNumber());
            user.addPhone(phone);
            phoneRepository.save(phone);
        }
        log.debug("Guarda data");
        user = userRepository.save(user);
        return makeResponse(user);
    }

    private UserCreateResponse makeResponse(UserEntity user) throws EntityNotFoundException {
        UserCreateResponse response = new UserCreateResponse();
        response.setId(user.getId().toString());
        response.setCreated(user.getCreado());
        response.setIsActive(user.getActivo());
        response.setLast_login(user.getUltLogin());
        response.setModified(user.getModificado());
        response.setToken(user.getToken());
        return response;
    }

    @Override
    public List<UserListResponse> getAll() {
        List<UserEntity> usuarios = userRepository.findAllByActivo(Boolean.TRUE);
        List<UserListResponse> result = new ArrayList<>();
        for (UserEntity userEntity : usuarios) {
            UserListResponse user = new UserListResponse();
            user.setCreated(userEntity.getCreado());
            user.setEmail(userEntity.getCorreo());
            user.setId(userEntity.getId().toString());
            user.setStatus(userEntity.getActivo() ? "Activo" : "Inactivo");
            user.setLast_login(userEntity.getUltLogin());
            user.setModified(userEntity.getModificado());
            user.setName(userEntity.getNombre());
            user.setPhones(userEntity.getPhones());
            result.add(user);
        }
        return result;
    }

    @Override
    public List<UserListResponse> getOne(String email) throws EntityNotFoundException {
        List<UserListResponse> result = new ArrayList<>();
        Optional<UserEntity> entidad = userRepository.findByCorreoIgnoreCaseAndActivo(email, Boolean.TRUE);
        if (entidad.isPresent()) {
            UserEntity userEntity = entidad.get();
            UserListResponse user = new UserListResponse();
            user.setCreated(userEntity.getCreado());
            user.setEmail(userEntity.getCorreo());
            user.setId(userEntity.getId().toString());
            user.setStatus(userEntity.getActivo() ? "Activo" : "Inactivo");
            user.setLast_login(userEntity.getUltLogin());
            user.setModified(userEntity.getModificado());
            user.setName(userEntity.getNombre());
            user.setPhones(userEntity.getPhones());
            result.add(user);
        }
        return result;
    }

    @Override
    public void register(Login login) throws EntityNotFoundException {
        UserEntity userEntity = userRepository.findByCorreoIgnoreCase(login.getEmail()).orElseThrow(() -> new EntityNotFoundException());
        userEntity.setToken(login.getJwtToken());
        userEntity.setUltLogin(LocalDateTime.now());
        userRepository.save(userEntity);
    }

    @Override
    public void delete(String email) throws EntityNotFoundException {
        UserEntity userEntity = userRepository.findByCorreoIgnoreCase(email).orElseThrow(() -> new EntityNotFoundException("No existe"));
        userEntity.setActivo(Boolean.FALSE);
        userEntity.setModificado(LocalDateTime.now());
        userRepository.save(userEntity);
    }

}
