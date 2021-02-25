/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "HORARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findById", query = "SELECT h FROM Horario h WHERE h.id = :id"),
    @NamedQuery(name = "Horario.findByFecha", query = "SELECT h FROM Horario h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "Horario.findByHorainicio", query = "SELECT h FROM Horario h WHERE h.horainicio = :horainicio"),
    @NamedQuery(name = "Horario.findByHorafin", query = "SELECT h FROM Horario h WHERE h.horafin = :horafin"),
@NamedQuery(name = "Horario.findJornadasNoFinalizadasEmpleado", 
            query = "SELECT h FROM Horario h"
                    + " WHERE h.idEmpleado = :idEmpleado "
                    + "AND h.fecha =:fechaJornada "
                    + " AND h.horafin is NULL"),
    @NamedQuery(name = "Horario.findporIdEmpleado", 
            query = "SELECT h FROM Horario h"
                    + " WHERE h.idEmpleado = :idEmpleado ")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "HORAINICIO")
    @Temporal(TemporalType.TIME)
    private Date horainicio;
    @Column(name = "HORAFIN")
    @Temporal(TemporalType.TIME)
    private Date horafin;
    
    @Column(name = "IDEMPLEADO")
    private Integer idEmpleado; 
    
    @JoinColumn(name = "IDEMPLEADO", referencedColumnName = "ID",
            insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empleado empleado;

    public Horario() {
    }

    public Horario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    public Date getHorafin() {
        return horafin;
    }

    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
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
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.registro.entidades.Horario[ id=" + id + " ]";
    }
    
}
