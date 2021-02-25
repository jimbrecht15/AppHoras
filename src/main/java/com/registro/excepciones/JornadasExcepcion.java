
package com.registro.excepciones;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class JornadasExcepcion extends Exception {

    
    public JornadasExcepcion() {
    }

    public JornadasExcepcion(String msg) {
        super(msg);
    }
}
