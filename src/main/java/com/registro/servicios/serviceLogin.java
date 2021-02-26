/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.servicios;

import com.registro.entidades.Empleado;
import com.registro.excepciones.ExcepcionesEmpleados;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Stateless
public class serviceLogin implements serviceLoginLocal {

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
    
    public boolean esAdministrador(Empleado empleado) throws ExcepcionesEmpleados {
        boolean esAdministrador = false; 
        if (empleado.getAdministrador()){
            esAdministrador= true; 
        }
        return esAdministrador;
        
    }
}
