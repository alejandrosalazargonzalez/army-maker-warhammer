package es.alejandrosalazargonzalez.army_maker_warhammer.controller;

import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioEntity;
import es.alejandrosalazargonzalez.army_maker_warhammer.model.UsuarioServiceModel;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    private LoginController controller;
    private UsuarioServiceModel mockUsuarioService;

    @BeforeEach
    void setUp() {
        controller = new LoginController();
        mockUsuarioService = mock(UsuarioServiceModel.class);
        controller.setUsuarioServiceModel(mockUsuarioService); 
        
        // Simula los nodos de JavaFX
        controller.usuarioTextField = new TextField();
        controller.contraseniaTextField = new PasswordField();
        controller.errorText = new Text();
        controller.iniciarButton = new Button();
        controller.crearCuentaButton = new Button();
        controller.idiomaComboBox = new ComboBox<>();
        controller.iniciarText = new Label();
        controller.usuarioText = new Label();
        controller.contraseniaText = new Label();
        controller.olvidasteText = new Hyperlink();
        controller.nuevoUsuarioText = new Text();
        controller.inputProgress = new Label();
        controller.crearCuentaButton = new Button();
    }

    @Test
    void testRevisarCamposLogin_usuarioVacio() {
        controller.usuarioTextField.setText("");
        controller.contraseniaTextField.setText("password");

        boolean result = controller.revisarCamposLogin();

        assertFalse(result);
        assertEquals("Usuario no puede estar vacio", controller.errorText.getText());
    }

    @Test
    void testRevisarCamposLogin_contraseniaVacia() {
        controller.usuarioTextField.setText("usuario");
        controller.contraseniaTextField.setText("");

        boolean result = controller.revisarCamposLogin();

        assertFalse(result);
        assertEquals("Contraseña no puede estar vacio", controller.errorText.getText());
    }

    @Test
    void testRevisarCamposLogin_usuarioNoExiste() {
        controller.usuarioTextField.setText("usuario");
        controller.contraseniaTextField.setText("password");

        when(mockUsuarioService.obtenerUsuarioPorUsuario("usuario")).thenReturn(null);

        boolean result = controller.revisarCamposLogin();

        assertFalse(result);
        assertEquals("el usuario no existe", controller.errorText.getText());
    }

    @Test
    void testRevisarCamposLogin_contraseniaIncorrecta() {
        controller.usuarioTextField.setText("usuario");
        controller.contraseniaTextField.setText("wrongpassword");

        UsuarioEntity fakeUser = new UsuarioEntity();
        fakeUser.setUsuario("usuario");
        fakeUser.setContrasenia("correctpassword");

        when(mockUsuarioService.obtenerUsuarioPorUsuario("usuario")).thenReturn(fakeUser);

        boolean result = controller.revisarCamposLogin();

        assertFalse(result);
        assertEquals("error en usuario o contraseña", controller.errorText.getText());
    }

    @Test
    void testRevisarCamposLogin_correcto() {
        controller.usuarioTextField.setText("usuario");
        controller.contraseniaTextField.setText("password");

        UsuarioEntity fakeUser = new UsuarioEntity();
        fakeUser.setUsuario("usuario");
        fakeUser.setContrasenia("password");

        when(mockUsuarioService.obtenerUsuarioPorUsuario("usuario")).thenReturn(fakeUser);

        boolean result = controller.revisarCamposLogin();

        assertTrue(result);
    }

    @Test
    void testComboBoxCambiarIdioma() {
        controller.idiomaComboBox.getItems().addAll("es", "en");
        controller.idiomaComboBox.setValue("en");

        controller.comboBoxCambiarIdioma();

        assertEquals("en", controller.getIdioma());
    }
}
