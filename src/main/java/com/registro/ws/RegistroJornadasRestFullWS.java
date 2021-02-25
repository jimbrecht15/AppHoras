
package com.registro.ws;


import com.registro.entidades.Horario;
import com.registro.excepciones.JornadasExcepcion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.registro.servicios.ServicioJornadasLocal;


@Stateless
@Path("jornadas")
public class RegistroJornadasRestFullWS {
    
    @EJB
    private ServicioJornadasLocal servicio;
    
    @GET
    @Path("empleado/{idEmpleado}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Horario> find(@PathParam("idEmpleado") Integer id ) throws JornadasExcepcion{
        List<Horario> lista = (List<Horario>)
                servicio.getAllRegistrosEmpleado(id);
        return lista;
    }
    
    @GET
    @Path("iniciarJornada/empleado/{idEmpleado}")
    //@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void iniciarJornada (@PathParam("idEmpleado") Integer id ) throws JornadasExcepcion{
        servicio.iniciarJornada(id);
    }
    
}
