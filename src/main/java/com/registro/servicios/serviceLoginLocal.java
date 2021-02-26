/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.servicios;

import com.registro.entidades.Empleado;
import com.registro.excepciones.ExcepcionesEmpleados;
import javax.ejb.Local;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Local
public interface serviceLoginLocal {
    
    public void login(String usuario, String clave, HttpSession session) throws ExcepcionesEmpleados;

    public void logout(HttpSession session);
        
    public boolean esAdministrador(Empleado empleado) throws ExcepcionesEmpleados;
    
}
