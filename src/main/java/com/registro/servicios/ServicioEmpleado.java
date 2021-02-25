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
public class ServicioEmpleado implements ServicioEmpleadoLocal {

    @PersistenceContext (unitName = "persistencePU")
    private EntityManager em;
    
    
    @Override
    public void login(String usuariodni, String clave, HttpSession session) throws ExcepcionesEmpleados {
        Query query = em.createNamedQuery("Empleado.login");
        query.setParameter("dni", usuariodni);
        query.setParameter("clave", clave);
        
        try {
            Empleado empleadoLogin = (Empleado) query.getSingleResult();
            session.setAttribute("empleado", empleadoLogin);
        } catch (javax.persistence.NoResultException e) {
           throw new ExcepcionesEmpleados("La contraseña o el número de identificación no coinciden");
        }
    }

    @Override
    public void logout(HttpSession session) {
         session.invalidate();
    }

    @Override
    public void archivarEmpleado(int idEmpleado) throws ExcepcionesEmpleados {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void nuevoEmpleado(Empleado empleado) throws ExcepcionesEmpleados {
        //sesion = HibernateUtil.getCurrentSession();
        //sesion.beginTransaction();
        //sesion.save(empleado);
        //sesion.getTransaction().commit();
        //sesion.close();
        em.persist(empleado);
    }

    @Override
    public void modificarEmpleado(Empleado empleado) throws ExcepcionesEmpleados {
        try {
            Empleado e = em.find(Empleado.class, empleado.getId());
            if (e ==  null){
                 throw new ExcepcionesEmpleados("Error en actualizar el empleado");
            }
            em.merge(empleado);
            
        } catch (OptimisticLockException ole) {
            throw new ExcepcionesEmpleados ("La persona interesada actual ya ha sido modificada "
                    + " por otra persona. \n Por favor vuelva a recuperarlo. ");
        }
    }

    
    
    
}
