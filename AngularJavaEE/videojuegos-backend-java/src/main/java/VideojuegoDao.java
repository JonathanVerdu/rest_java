import java.util.List;

public interface VideojuegoDao {
    
    // método para encontrar todos los videojuegos y listarlos
    public List<Videojuego> encontrarTodosVideojuegos();

    // método para encontrar un videojuego concreto
    public Videojuego encontrarVideojuego(Videojuego videojuego);
    
    // método para insertar un videojuego nuevo
    public void insertarVideojuego(Videojuego videojuego);

    // método para actualizar los datos de un videojuego concreto
    public void actualizarVideojuego(Videojuego videojuego);

    // método para eliminar un videojuego
    public void eliminarVideojuego(Videojuego videojuego);

}
