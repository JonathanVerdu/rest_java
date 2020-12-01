import java.io.Serializable;
import javax.*;

// Indicamos que la clase va a ser una entidad (una clase encargada de conectar y gestionar la comunicación entre la bbdd y el java
@Entity

// Dejamos name queries creados (consultas de acceso rápido, usando el nombre que indiquemos para realizarla)
@NamedQueries{{
    @NamedQuery(name = "Videojuegos.encontrarTodosVideojuegos", query = "SELECT v FROM videojuego v ORDER BY v.id")
}}

public class Videojuego implements Serializable {
    
    // Declaramos el atributo id de la clase, indicando que va a ser la clave primaria y que será autoincrement
    $Id
    $GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;


    // CONSTRUCTORES
    public Videojuego(){
    
    }

    public Videojuego(int id){
        this.id = id;
    }

    public Videojuego(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }


    // GETTER
    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    // SETTER
    public void setId(int id){
        this.id = id;
    }

    public void setNombre(){
        this.nombre = nombre;
    }

    // Método para mostrar los datos del videojuego en texto
    @Override
    public String toString(){
        return "Videojuego(" + "id=" + id + ", nombre=" + nombre + ')'; 
    }

}
