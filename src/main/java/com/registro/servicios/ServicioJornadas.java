/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.servicios;

import com.registro.entidades.Empleado;
import com.registro.entidades.Horario;
import com.registro.excepciones.ExcepcionesEmpleados;
import com.registro.excepciones.JornadasExcepcion;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Stateless
public class ServicioJornadas implements ServicioJornadasLocal{
    
    @PersistenceContext (unitName = "persistencePU")
    private EntityManager em;

    @Override
    public void iniciarJornada(Integer idEmpleado) throws JornadasExcepcion {
        /*
        1. Validar idEmpleado no sea null
        2. ver si no esta ya la jornada iniciada para el empleado
        jornada iniciada es que hay un registro de jornada para el empleado
        con fecha de inicio de hoy y hora de fin null
        3. si hay jornada iniciada enviar excepcion 
        4. sino inserta registro de jornada de fecha de hoy hora inicio
        la del sistema y hora fin null
        */
        
        if (idEmpleado == null ){
            throw new JornadasExcepcion("Debe indicar el id de empleado");
        }
        
        Query query = em.createNamedQuery("Horario.findJornadasNoFinalizadasEmpleado");
        query.setParameter("idEmpleado", idEmpleado);
        Date hoy = new Date();    
        query.setParameter("fechaJornada", hoy, TemporalType.DATE);
        List<Horario> resultado = query.getResultList();
        if (resultado.size() > 0){
            throw new JornadasExcepcion("La jornada ya esta iniciada");
        }else {
            Horario h = new Horario();
            h.setIdEmpleado(idEmpleado);
            h.setFecha(hoy); //solo guarda día, mes, año 
            h.setHorainicio(hoy); // solo guarda min, horas, segundos 
            h.setHorafin(null);
            em.persist(h);
        }
        
    }
    
    @Override
    public Collection<Horario> getAllRegistrosEmpleado(Integer idEmpleado) throws JornadasExcepcion {     
        //validar el id null
        if (idEmpleado == null) {
            throw new JornadasExcepcion("Debe indicar el id de Empleado");
        }
        System.out.println("....... estoy aqui");
        Query query = em.createNamedQuery("Horario.findporIdEmpleado");
        query.setParameter("idEmpleado", idEmpleado);
        return query.getResultList();
    }

    @Override
    public void recuentoSemanal(int idEmpleado) throws ExcepcionesEmpleados {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
