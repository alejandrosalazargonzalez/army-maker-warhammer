
package es.alejandrosalazargonzalez.army_maker_warhammer.controller.abstractas;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioServiceModel;


/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public abstract class AbstractController {

    static final String PATH_DB ="src/main/resources/es/alejandrosalazargonzalez/army_maker_warhammer/usuarios.db";

    private UsuarioServiceModel usuarioServiceModel;

    private Properties propertiesIdioma;

    /**
     * Constructor
     */
    protected AbstractController() {
        try {
            usuarioServiceModel = new UsuarioServiceModel(PATH_DB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * setea el properties
     * @param properties
     */
    public void setpropertiesIdioma(Properties properties) {
        propertiesIdioma = properties;
    }

    /**
     * retorna el properties
     * @return Properties
     */
    public Properties getPropertiesIdioma() {
        return propertiesIdioma;
    }

    /**
     * carga el fichero de properties idiomas
     * @param nombreFichero
     * @param idioma
     * @return
     */
    public Properties loadIdioma(String nombreFichero, String idioma) {
        Properties properties = new Properties();
        
        if (nombreFichero == null || idioma == null) {
            return properties;
        }
        
        String path = "src/main/resources/" + nombreFichero+"-"+idioma+".properties";
        File file = new File(path);

        if (!file.exists() || !file.isFile()) {
            System.out.println("Path:"+file.getAbsolutePath());
            return properties;
        }
        
        try {
            FileInputStream input = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(input, "UTF-8");
            properties.load(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * retorna el usuarioServiceModel
     * @return UsuarioServiceModel
     */
    public UsuarioServiceModel getUsuarioServiceModel() {
        return this.usuarioServiceModel;
    }


}
