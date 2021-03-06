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
import javax.persistence.EntityManagerFactory;
import javax.persistence.OptimisticLockException;
import javax.persistence.Persistence;
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
    //EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistencePU");
    //EntityManager em = entityManagerFactory.createEntityManager();
    

    @Override
    public void archivarEmpleado(Empleado empleado ) throws ExcepcionesEmpleados {
       try {
            Empleado e = em.find(Empleado.class, empleado.getId());
            if (e ==  null){
                 throw new ExcepcionesEmpleados("Error en archivar el empleado");
            }
            em.merge(empleado);
            
        } catch (OptimisticLockException ole) {
            throw new ExcepcionesEmpleados ("La persona interesada actual ya ha sido modificada "
                    + " por otra persona. \n Por favor vuelva a recuperarlo. ");
        }
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

    @Override
    public Empleado buscarEmpleado(String dni) throws ExcepcionesEmpleados {
        Query query = em.createNamedQuery("Empleado.findByDni");
      
        query.setParameter("dni", dni);
        
        query.setMaxResults(1);
        String consulta = query.toString();
        List<Empleado> empleados = query.getResultList();
        
        if(empleados == null || empleados.size()==0){
                throw new ExcepcionesEmpleados("Error en la busqueda - No hay empleados con el DNI incluido \n"
                        + " puedes generar uno Nuevo");
        }
        return empleados.get(0);
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        System.out.println("He entrado ");
       Query query = em.createNamedQuery("Empleado.findAll");
        
        return query.getResultList();
    }
    
    
    
}
