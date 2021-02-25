
package com.registro.excepciones;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ExcepcionesEmpleados extends Exception {

    
    public ExcepcionesEmpleados() {
    }

    public ExcepcionesEmpleados(String msg) {
        super(msg);
    }
}
