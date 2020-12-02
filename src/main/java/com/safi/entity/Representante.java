/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.safi.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Matias Zakowicz
 */
@Entity
@Table(name = "representantes")
public class Representante implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name= "fecha_inicio")
    private Date fechaInicio;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name= "fecha_fin")
    private Date fechaFin;
    @OneToOne
    private EstadoRepresentante estadoRepresentante;
    @OneToOne
    private Rol rol;
    @OneToOne
    private Agente agente;
    private boolean estado;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public EstadoRepresentante getEstadoRepresentante() {
        return estadoRepresentante;
    }

    public void setEstadoRepresentante(EstadoRepresentante estadoRepresentante) {
        this.estadoRepresentante = estadoRepresentante;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Representante)) {
            return false;
        }
        Representante other = (Representante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.safi.entity.Representante[ id=" + id + " ]";
    }
    
}
