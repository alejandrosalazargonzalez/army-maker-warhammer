package es.alejandrosalazargonzalez.army_maker_warhammer.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas.AbstractController;

/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class EjercitoEntity extends AbstractController{
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
    }

        /**
     * Constructor completo
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
     * Constructor completo
     * @param nombre del ejercito
     * @param faccion del ejercito
     * @param subFaccion del ejercito
     * @param general del ejercito
     * @param puntos del ejercito
     */
    public EjercitoEntity(String nombre, String faccion, String subFaccion, GeneralEntity general, ArrayList<UnidadEntity> ejercito, int puntos){
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EjercitoEntity)) {
            return false;
        }
        EjercitoEntity ejercitoEntity = (EjercitoEntity) o;
        return Objects.equals(nombre, ejercitoEntity.nombre) && Objects.equals(faccion, ejercitoEntity.faccion) && Objects.equals(subFaccion, ejercitoEntity.subFaccion) && Objects.equals(general, ejercitoEntity.general) && Objects.equals(ejercito, ejercitoEntity.ejercito) && puntos == ejercitoEntity.puntos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, faccion, subFaccion, general, ejercito, puntos);
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", faccion='" + getFaccion() + "'" +
            ", subFaccion='" + getSubFaccion() + "'" +
            ", general='" + getGeneral() + "'" +
            ", ejercito='" + getEjercito() + "'" +
            ", puntos='" + getPuntos() + "'" +
            "}";
    }
}
