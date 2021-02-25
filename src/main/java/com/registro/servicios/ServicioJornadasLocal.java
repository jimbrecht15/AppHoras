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
import javax.ejb.Local;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@Local
public interface ServicioJornadasLocal {

    
    public void recuentoSemanal(int idEmpleado) throws ExcepcionesEmpleados;
    
    public void iniciarJornada (Integer idEmpleado) throws JornadasExcepcion;
    public Collection<Horario> getAllRegistrosEmpleado(Integer idEmpleado) throws JornadasExcepcion;
}
