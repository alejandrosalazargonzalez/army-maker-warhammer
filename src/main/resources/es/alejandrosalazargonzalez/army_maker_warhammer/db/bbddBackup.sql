PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE usuario (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombreUsuario TEXT NOT NULL,
    nombre TEXT NOT NULL,
    contrasenia TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL
);
INSERT INTO usuario VALUES(1,'Jperez','Juan Pérez','pass123','juan@example.com');
INSERT INTO usuario VALUES(2,'Alopez','Ana López','securePass','ana@example.com');
INSERT INTO usuario VALUES(3,'Cgomez','Carlos Gómez','claveSegura','carlos@example.com');
INSERT INTO usuario VALUES(4,'sala','ale','123','sala@gmail.com');
CREATE TABLE rol (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT UNIQUE NOT NULL
);
INSERT INTO rol VALUES(1,'Administrador');
INSERT INTO rol VALUES(2,'Editor');
INSERT INTO rol VALUES(3,'Usuario');
CREATE TABLE usuario_rol (
    usuario_id INTEGER,
    rol_id INTEGER,
    PRIMARY KEY (usuario_id, rol_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (rol_id) REFERENCES rol(id) ON DELETE CASCADE
);
INSERT INTO usuario_rol VALUES(1,1);
INSERT INTO usuario_rol VALUES(2,2);
INSERT INTO usuario_rol VALUES(3,3);
INSERT INTO usuario_rol VALUES(2,3);
DELETE FROM sqlite_sequence;
INSERT INTO sqlite_sequence VALUES('rol',3);
INSERT INTO sqlite_sequence VALUES('usuario',4);
COMMIT;