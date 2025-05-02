package es.alejandrosalazargonzalez.army_maker_warhammer.model;
import java.util.Objects;

/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class UnidadEntity {

    private String nombre;
    private int puntos;
    private int numeroModelos;
    private String tipo;

    /**
     * constructor vacio
     */
    public UnidadEntity(){
    }

    public UnidadEntity(String nombre){
        this.nombre=nombre;
    }

    /**
     * constructor completo
     * @param nombre de la unidad
     * @param puntos de la unidad
     * @param numeroModelos de la unidad
     * @param tipo de la unidad
     */
    public UnidadEntity(String nombre, int numeroModelos, int puntos, String tipo) {
        this.nombre = nombre;
        this.numeroModelos = numeroModelos;
        this.puntos = puntos;
        this.tipo = tipo;
    }
    
    //getters y setters
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getNumeroModelos() {
        return this.numeroModelos;
    }

    public void setNumeroModelos(int numeroModelos) {
        this.numeroModelos = numeroModelos;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UnidadEntity)) {
            return false;
        }
        UnidadEntity unidadEntity = (UnidadEntity) o;
        return Objects.equals(nombre, unidadEntity.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return
            " nombre='" + getNombre() + "'" +
            ", puntos='" + getPuntos() + "'" +
            ", numeroModelos='" + getNumeroModelos() + "'" +
            ", tipo='" + getTipo() + "'\n";
    }
    
}
