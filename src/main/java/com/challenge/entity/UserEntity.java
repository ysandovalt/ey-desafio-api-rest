package com.challenge.entity;

import com.challenge.entity.PhoneEntity;
import com.challenge.payload.PhoneCreateRequest;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ysand
 */
@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {

    @Id
    @Column(columnDefinition = "uuid default random_uuid()")
    private UUID id;
   
    @Column(name = "CORREO", unique = true, nullable = false)
    private String correo;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "CLAVE", nullable = false)
    private String clave;

    @Column(name = "TOKEN")
    private String token;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "CREADO", nullable = false)
    private LocalDateTime creado;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "MODIFICADO")
    private LocalDateTime modificado;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Column(name = "ULTIMO_LOGIN")
    private LocalDateTime ultLogin;

    @Column(name = "ACTIVO")
    private Boolean activo = true;

    @Column(name = "TELEFONOS")
    @ElementCollection(targetClass=PhoneEntity.class)
    private List<PhoneEntity> telefonos = new ArrayList<>();

    public void addPhone(PhoneEntity phone) {
        this.telefonos.add(phone);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreado() {
        return creado;
    }

    public void setCreado(LocalDateTime creado) {
        this.creado = creado;
    }

    public LocalDateTime getModificado() {
        return modificado;
    }

    public void setModificado(LocalDateTime modificado) {
        this.modificado = modificado;
    }

    public LocalDateTime getUltLogin() {
        return ultLogin;
    }

    public void setUltLogin(LocalDateTime ultLogin) {
        this.ultLogin = ultLogin;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<PhoneEntity> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<PhoneEntity> telefonos) {
        this.telefonos = telefonos;
    }

    public List<PhoneCreateRequest> getPhones() {
        List<PhoneCreateRequest> phones = new ArrayList<>();
        for(PhoneEntity ent : telefonos){
            phones.add(new PhoneCreateRequest(ent.getCodigoPais(),ent.getCodigoCiudad(),ent.getNumero()));
        }
        return phones;
    }    


    
}
