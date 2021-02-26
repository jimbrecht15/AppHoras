/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registro.servicios;

import com.registro.entidades.Empleado;
import com.registro.excepciones.ExcepcionesEmpleados;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Local
public interface ServicioEmpleadoLocal {
    
    public void login(String usuario, String clave, HttpSession session) throws ExcepcionesEmpleados;

    public void logout(HttpSession session);

    public void nuevoEmpleado(Empleado empleado) throws ExcepcionesEmpleados;

    public void modificarEmpleado(Empleado empleado) throws ExcepcionesEmpleados;  //Modificar o cambiar de contraseña 

    public void archivarEmpleado(Empleado empleado) throws ExcepcionesEmpleados;

    public Empleado buscarEmpleado(String dni) throws ExcepcionesEmpleados;
    
    public List<Empleado> getAllEmpleados();
    
}
