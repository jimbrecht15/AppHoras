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
    private int id;
    private String clave;
    private Empleado empleado;
    private boolean visible = false;
    
    
    public DatosMB() {
        empleado = new Empleado();
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
            if (empleado.getAdministrador() == null){
                empleado.setAdministrador(false);
            }
            empleado.setActivo(true);
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

    public String modificarEmpleado(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        try{
            if (empleado.getAdministrador() == null){
                empleado.setAdministrador(false);
            }
            
        servicio.modificarEmpleado(empleado);
                        
        }catch(ExcepcionesEmpleados e ){
            //mns de error para mostrar en el formulario 
            
            FacesMessage mns = new FacesMessage("Tenemos inconvenientes para agregar un nuevo usuario");
            //ctx.addMessage("formLogin:psw", mns);
            ctx.addMessage(null, mns);

            return "login.xhtml";
        }
        return null; 
    }
    
    public String buscar(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            
            this.empleado = servicio.buscarEmpleado(empleado.getDni());
            System.out.println(empleado);
            visible = true;
            return null;
        } catch (ExcepcionesEmpleados ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
    }
    
    public String archivar(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        try {
            empleado.setActivo(false);
            servicio.archivarEmpleado(empleado);
            return null;
        } catch (ExcepcionesEmpleados ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(ex.getMessage()));
            return null;
        }
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    
    
}
