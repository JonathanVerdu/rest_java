import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import VideojuegoDao;

// Indicamos que la ruta para este webservice es /videojuegos
@Stateless
@Path("/videojuegos")
public class VideojuegoServiceRS {
    
    // Inyectamos la interfaz de videojuegosDao
    @Inject
    private VideojuegoDao videojuegoDao; 
    
    // Definimos el método para que puedas obtener (GET) en el service todos los videojuegos. El servicio produce datos en tipo JSON
    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    public List<Videojuego> listarVideojuegos(){
        List<Videojuego> videojuegos = videojuegoDao.encontrarTodosVideojuegos();
        System.out.println("videojuegos encontrados: " + videojuegos);
        return videojuegos;
    }

    // Definimos el método para que puedas obtener (GET) en el service un videojuego concreto por su id, pasado en la ruta. El servicio produce datos en tipo JSON
    @GET
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("(id)") // hace referencia al path: /videojuegos/(id), ej. /videojuegos/1
    public Videojuego encontrarVideojuego(@PathParam("id") int id){
        Videojuego videojuego = videojuegoDao.encontrarVideojuego(new Videojuego(id)); // El parámetro de encontrarVideojuego es un objeto nuevo con el constructor que define la id
        System.out.println("videojuego encontrado: " + videojuego);
        return videojuego;
    }

    // Definimos el método para que puedas agregar (POST) en el service un nuevo videojuego. El servicio consume y produce datos en tipo JSON
    @POST
    @Consume(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    public Videojuego agregarVideojuego(Videojuego videojuego){
        videojuegoDao.insertarVideojuego(videojuego);
        System.out.println("Videojuego agregado: " + videojuego);
        return videojuego;
    }

    // Definimos el método para que puedas modificar (PUT) en el service un videojuego. El servicio consume y produce datos en tipo JSON
    @PUT
    @Consume(value=MediaType.APPLICATION_JSON)
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("(id)")   
    public Response modificarVideojuego(@PathParam("id"), int id, Videojuego videojuegoModificado){
        Videojuego videojuego = videojuegoDao.encontrarVideojuego( new Videojuego(id));
        if(videojuego != null){
            videojuegoDao.actualizarVideojuego(videojuegoModificado);
            System.out.println("Videojuego modificado: "+ videojuegoModificado);
            return Response.ok().entity(videojuegoModificado).build(); // construimos una respuesta pasando los datos del videojuego modificado
        }else{
            return Response.status(Status.NOT_FOUND).build(); // construimos una respuesta pasando el status de no encontrado
        }
    }

    // Definimos el método para que puedas eliminar (DELETE) en el service un videojuego. El servicio produce datos en tipo JSON
    @DELETE
    @Produces(value=MediaType.APPLICATION_JSON)
    @Path("(id)")
    public Response eliminarVideojuego(@PathParam("id"), int id){
        videojuegoDao.eliminarVideojuego(new Videojuego(id));
        System.out.println("El videojuego eliminado tenía el id: " + id);
        return Response.ok().build();
    }

}
