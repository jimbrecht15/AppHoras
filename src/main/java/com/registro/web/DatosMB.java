/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.web;

import com.registro.entidades.Empleado;
import com.registro.excepciones.ExcepcionesEmpleados;
import com.registro.servicios.ServicioEmpleadoLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Named(value = "datosMB")
@ViewScoped
public class DatosMB implements Serializable{

    @EJB 
    private ServicioEmpleadoLocal servicio; 
    private String dniUsuario;
    private String nombre; 
    private String apellidos; 
    private String correo;
    private String cargo; 
    private Boolean administrador; 
    private int id;
    private String clave;
    private Empleado empleado = null;
    
    
    public DatosMB() {
    }
    
    public String login() {
        
        //obtener la sesion de JSF 
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
        try {
            servicio.login(dniUsuario, clave, session);
            empleado = (Empleado) session.getAttribute("empleado");
            return "resgistro1.xhtml";

        } catch (ExcepcionesEmpleados e) {
            this.dniUsuario = "";
            this.clave = "";

            //mns de error para mostrar en el formulario 
            FacesMessage mns = new FacesMessage(e.getMessage());
            //ctx.addMessage("formLogin:psw", mns);
            ctx.addMessage(null, mns);

            return "login.xhtml";
        }
    }

    public String logout() {
        
        //obtener la sesion de JSF 
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) ctx.getExternalContext().getSession(true);
        servicio.logout(sesion);
        
        return "login.xhtml";
    }
    
    
    public String nuevoEmpleado(){
        //Empleado nuevo = new Empleado();
        FacesContext ctx = FacesContext.getCurrentInstance();
        try{
            servicio.nuevoEmpleado(empleado);
            
        }catch(ExcepcionesEmpleados e ){
            //mns de error para mostrar en el formulario 
            
            FacesMessage mns = new FacesMessage("Tenemos inconvenientes para agregar un nuevo usuario");
            //ctx.addMessage("formLogin:psw", mns);
            ctx.addMessage(null, mns);

            return "login.xhtml";
        }
        return null; 
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Boolean administrador) {
        this.administrador = administrador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    
    
    
}
