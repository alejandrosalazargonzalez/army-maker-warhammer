package es.alejandrosalazargonzalez.army_maker_warhammer.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UsuarioEntityTest {

    @Test
    void testDefaultConstructor() {
        UsuarioEntity usuario = new UsuarioEntity();
        assertNull(usuario.getUsuario());
        assertNull(usuario.getEmail());
        assertNull(usuario.getNombre());
        assertNull(usuario.getContrasenia());
    }

    @Test
    void testParameterizedConstructorValidInputs() {
        UsuarioEntity usuario = new UsuarioEntity("user123", "user@example.com", "User Name", "password123");
        assertEquals("user123", usuario.getUsuario());
        assertEquals("user@example.com", usuario.getEmail());
        assertEquals("User Name", usuario.getNombre());
        assertEquals("password123", usuario.getContrasenia());
    }

    @Test
    void testParameterizedConstructorInvalidEmail() {
        ExceptionInInitializerError exception = assertThrows(ExceptionInInitializerError.class, () -> {
            new UsuarioEntity("user123", "invalid-email", "User Name", "password123");
        });
        assertEquals("El email debe tener un formato correcto", exception.getMessage());
    }

    @Test
    void testSetEmailValid() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail("valid@example.com");
        assertEquals("valid@example.com", usuario.getEmail());
    }

    @Test
    void testSetEmailInvalid() {
        UsuarioEntity usuario = new UsuarioEntity();
        ExceptionInInitializerError exception = assertThrows(ExceptionInInitializerError.class, () -> {
            usuario.setEmail("invalid-email");
        });
        assertEquals("El email debe tener un formato correcto", exception.getMessage());
    }

    @Test
    void testEqualsSameObject() {
        UsuarioEntity usuario = new UsuarioEntity("user123", "user@example.com", "User Name", "password123");
        assertTrue(usuario.equals(usuario));
    }

    @Test
    void testEqualsDifferentObjectSameEmail() {
        UsuarioEntity usuario1 = new UsuarioEntity("user123", "user@example.com", "User Name", "password123");
        UsuarioEntity usuario2 = new UsuarioEntity("user456", "user@example.com", "Another Name", "password456");
        assertTrue(usuario1.equals(usuario2));
    }

    @Test
    void testEqualsDifferentObjectDifferentEmail() {
        UsuarioEntity usuario1 = new UsuarioEntity("user123", "user1@example.com", "User Name", "password123");
        UsuarioEntity usuario2 = new UsuarioEntity("user456", "user2@example.com", "Another Name", "password456");
        assertFalse(usuario1.equals(usuario2));
    }

    @Test
    void testHashCodeConsistency() {
        UsuarioEntity usuario = new UsuarioEntity("user123", "user@example.com", "User Name", "password123");
        int hashCode1 = usuario.hashCode();
        int hashCode2 = usuario.hashCode();
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void testToString() {
        UsuarioEntity usuario = new UsuarioEntity("user123", "user@example.com", "User Name", "password123");
        String expected = "{usuariouser123, email=user@example.com, nombre=User Name, contrasenia=password123}";
        assertEquals(expected, usuario.toString());
    }
}