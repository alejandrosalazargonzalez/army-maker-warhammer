package es.alejandrosalazargonzalez.army_maker_warhammer.model;
import java.util.Objects;

public class GeneralEntity extends UnidadEntity {
    
    /**
     * constructor vacio
     */
    public GeneralEntity() {
    }

    /**
     * Constructor del general
     * @param nombre del general
     * @param puntos del general
     */
    public GeneralEntity(String nombre, int puntos){
        super(nombre, puntos, 1, "General");
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GeneralEntity)) {
            return false;
        }
        GeneralEntity generalEntity = (GeneralEntity) o;
        return Objects.equals(this, generalEntity);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
