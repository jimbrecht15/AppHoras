
package com.registro.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Respuesta implements Serializable{
    
    private boolean sinErrores;
    private String mensaje;

    public Respuesta() {
       
    }
    
    public Respuesta(boolean sinErrores, String mensaje) {
        this.sinErrores = sinErrores;
        this.mensaje = mensaje;
    }

    public boolean isSinErrores() {
        return sinErrores;
    }

    public void setSinErrores(boolean sinErrores) {
        this.sinErrores = sinErrores;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
