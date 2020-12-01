import java.util.List;
import java.ejb.Stateless;
import javax.persistance.*;
import Videojuego;

// Esta clase es la que implementará la interface de videojuego
@Stateless
public class VideojuegoDaoImpl implements VideojuegoDao{

    // Para utilizar la persistencia la llamamos con el mismo nombre de unidad que le pusimos en el archivo de persistence.xml
    @PersistenceContext (unitName = "VideojuegoPU")

    // Creamos el objeto entity manager para poder conectar con la base de datos
    EntityManager em;

    @Override
    public List<Videojuego> encontrarTodosVideojuegos() {
        // Usamos una query con nombre de las definidas en la clase Videojuego, para en este caso devolver todos los objetos videojuegos
        return em.createNamedQuery("encontrarTodosVideojuegos").getResultList();
    };

    @Override
    public Videojuego encontrarVideojuego(Videojuego videojuego) {
        // Usando el métoodo del entity manager de find, podemos buscar en la clase videojuego, por el id pasado por parámetro
        return em.find(Videojuego.class, videojuego.getId());
    };
    
    @Override
    public void insertarVideojuego(Videojuego videojuego) {
        // Con el entity manager persistimos el objeto videojuego (persistir es guardar en base de datos)
        em.persist(videojuego);
        // Con flush actualizamos la base de datos, para que se muestre el cambio realizado antes
        em.flush();
    };

    @Override
    public void actualizarVideojuego(Videojuego videojuego) {
        // Con el método merge del entity manager se pueden "mezclar" los datos del objeto videojuego pasado con el estado actual del mismo en la BBDD
        em.merge(videojuego);
    };

    @Override
    public void eliminarVideojuego(Videojuego videojuego) {
        // Borramos usando el métod remove del entity manager, aunque nos aseguramos que primero se haya actualizado la bbdd con merge para evitar errores
        em.remove(em.merge(videojuego));
    };

}
