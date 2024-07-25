package com.challenge.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ysand
 */
@Entity
@Table(name = "telephones")
public class PhoneEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "CODIGO_PAIS")
    private String codigoPais;

    @Column(name = "CODIGO_CIUDAD")
    private String codigoCiudad;

    @Column(name = "NUMERO")
    private String numero;

    public PhoneEntity() {
    }

    public PhoneEntity(String codigoPais, String codigoCiudad, String numero) {
        this.codigoPais = codigoPais;
        this.codigoCiudad = codigoCiudad;
        this.numero = numero;
    }
    
    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCodigoCiudad() {
        return codigoCiudad;
    }

    public void setCodigoCiudad(String codigoCiudad) {
        this.codigoCiudad = codigoCiudad;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
