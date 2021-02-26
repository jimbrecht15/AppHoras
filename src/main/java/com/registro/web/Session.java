/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.web;

import com.registro.entidades.Empleado;
import com.registro.excepciones.ExcepcionesEmpleados;
import com.registro.servicios.ServicioEmpleadoLocal;
import com.registro.servicios.serviceLoginLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Named(value = "logear")
@SessionScoped
public class Session implements Serializable {

    @EJB 
    private serviceLoginLocal servicio; 
        
    private String dniUsuario; 
    private String clave;
    private Empleado empleado;
    private HttpSession session;
    private boolean logeado = false;
    private boolean esAdministrador = false; 
    
    
    public Session() {
    }
    
    public String login() {
        
        //obtener la sesion de JSF 
        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(true);
        try {
            servicio.login(dniUsuario, clave, session);
            empleado = (Empleado) session.getAttribute("empleado");
            esAdministrador = esAdministrador(empleado);
            logeado = true;
            return "registroJornada.xhtml";

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
    
    private boolean esAdministrador(Empleado empleado) {
        //obtener la sesion de JSF 
        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(true);
        try {
            servicio.login(dniUsuario, clave, session);
            empleado = (Empleado) session.getAttribute("empleado");
            esAdministrador =  servicio.esAdministrador(empleado);
        } catch (ExcepcionesEmpleados e) {
            this.dniUsuario = "";
            this.clave = "";

            //mns de error para mostrar en el formulario 
            FacesMessage mns = new FacesMessage(e.getMessage());
            //ctx.addMessage("formLogin:psw", mns);
            ctx.addMessage(null, mns);
        }
        return esAdministrador;
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

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    
}
