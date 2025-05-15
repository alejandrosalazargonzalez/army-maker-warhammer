package es.alejandrosalazargonzalez.army_maker_warhammer.model;

import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;

import jakarta.persistence.*;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;

/**
 *   @author: alejandrosalazargonzalez
 *     Entidad que mapea la tabla ejercito en ejercitoEntity
 *   @version: 1.0.0
 */
@Entity
@Table(name = "ejercito")
public class EjercitoEntity extends AbstractController implements Serializable{
    private UsuarioEntity usuario;
    private String nombre;
    private String faccion;
    private String subFaccion;
    private GeneralEntity general;
    private ArrayList<UnidadEntity> ejercito;
    private int puntos;

    /**
     * constructor vacio
     */
    public EjercitoEntity(){
        ejercito = new ArrayList<>();
    }

    /**
     * Constructor completo para crear
     * @param nombre del ejercito
     * @param faccion del ejercito
     * @param subFaccion del ejercito
     * @param general del ejercito
     * @param puntos del ejercito
     */
    public EjercitoEntity( String nombre, String faccion, String subFaccion, GeneralEntity general, ArrayList<UnidadEntity> ejercito){
        this.usuario=getUsuarioActual();
        this.nombre = nombre;
        this.faccion=faccion;
        this.subFaccion=subFaccion;
        this.ejercito=ejercito;
        this.general=general;
        for (UnidadEntity unidadEntity : ejercito) {
            puntos += unidadEntity.getPuntos();
        }
    }

    /**
     * Constructor completo para mostrar
     * @param nombre del ejercito
     * @param faccion del ejercito
     * @param subFaccion del ejercito
     * @param general del ejercito
     * @param puntos del ejercito
     */
    public EjercitoEntity(String nombre, String faccion, String subFaccion, GeneralEntity general, ArrayList<UnidadEntity> ejercito, int puntos){
        this.usuario=getUsuarioActual();
        this.nombre = nombre;
        this.faccion=faccion;
        this.subFaccion=subFaccion;
        this.ejercito=ejercito;
        this.general=general;
        this.puntos=puntos;
    }

    //getters y setters
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFaccion() {
        return this.faccion;
    }

    public void setFaccion(String faccion) {
        this.faccion = faccion;
    }

    public String getSubFaccion() {
        return this.subFaccion;
    }

    public void setSubFaccion(String subFaccion) {
        this.subFaccion = subFaccion;
    }

    public GeneralEntity getGeneral() {
        return this.general;
    }

    public void setGeneral(GeneralEntity general) {
        this.general = general;
    }

    public ArrayList<UnidadEntity> getEjercito() {
        return this.ejercito;
    }

    public void setEjercito(ArrayList<UnidadEntity> ejercito) {
        this.ejercito = ejercito;
    }

    public int getPuntos() {
        return this.puntos;
    }

    /**
     * agrega una unidad al ejercito
     * @param unidad
     */
    public void addUnidad(UnidadEntity unidad){
        if (unidad == null) {
            return;
        }
        puntos += unidad.getPuntos();
        ejercito.add(unidad);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EjercitoEntity)) {
            return false;
        }
        EjercitoEntity ejercitoEntity = (EjercitoEntity) o;
        return Objects.equals(nombre, ejercitoEntity.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "-Nombre del Ejercito= " + getNombre() + "\n" +
            "-Faccion= " + getFaccion() + "\n" +
            "-SubFaccion= " + getSubFaccion() + "\n" +
            "-General=" + getGeneral() + "\n" +
            "-Ejercito= " + getEjercito().toString() + "\n" +
            "-Puntos= " + getPuntos() + "\n";
    }
}
