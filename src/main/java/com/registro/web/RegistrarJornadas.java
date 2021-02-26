
package com.registro.web;

import com.registro.entidades.Empleado;
import com.registro.entidades.Horario;
import com.registro.excepciones.JornadasExcepcion;
import com.registro.servicios.ServicioJornadas;
import com.registro.ws.Respuesta;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;


@Named(value = "registrarJornadasMB")
@RequestScoped
public class RegistrarJornadas implements Serializable{

    @EJB 
    private ServicioJornadas servJornadas;
    
    @Inject
    private Session loginSession; 
    
    private Empleado empleado;
    private Horario horario; 
    private boolean jornadaIniciada;
    private boolean jornadaFinalizada;
    
    
    public RegistrarJornadas() {
    }
    
    @PostConstruct
    public void revisarJornadasAbiertas(){
        //revisar si hay jornadas iniciadas
        Integer idEmpleado = loginSession.getEmpleado().getId();
        //Horario registroHorario = servJornadas.horasIniciadasNoFinalizadas(idEmpleado);
    }
    
    public void iniciarJornada () throws JornadasExcepcion{
        
        try {
            servJornadas.iniciarJornada(loginSession.getEmpleado().getId());
            jornadaIniciada = true;
        } catch (JornadasExcepcion e) {
            jornadaIniciada = false;
            System.out.println("Error " + e.getMessage());
        }
        
    }
    
    
    public void finalizarJornada () throws JornadasExcepcion{
        
        try {
            servJornadas.finalizarJornada(loginSession.getEmpleado().getId());
            jornadaFinalizada= true;
        } catch (JornadasExcepcion e) {
            jornadaFinalizada = false;
            System.out.println("Error " + e.getMessage());
        }
        
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public boolean isJornadaIniciada() {
        return jornadaIniciada;
    }

    public void setJornadaIniciada(boolean jornadaIniciada) {
        this.jornadaIniciada = jornadaIniciada;
    }

    public boolean isJornadaFinalizada() {
        return jornadaFinalizada;
    }

    public void setJornadaFinalizada(boolean jornadaFinalizada) {
        this.jornadaFinalizada = jornadaFinalizada;
    }
    
    
    
}
