-- Tabla de generales
CREATE TABLE general (
    id integer PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(100) NOT NULL,
    puntos INT
);

-- Tabla de ejércitos
CREATE TABLE ejercito (
    id integer PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(100) NOT NULL,
    faccion VARCHAR(100),
    sub_faccion VARCHAR(100),
    general_id INT NOT NULL,
    usuario INT NOT NULL,
    puntos INT,
    CONSTRAINT fk_ejercito_general
        FOREIGN KEY (general_id) REFERENCES general(id) ON DELETE CASCADE,
    CONSTRAINT fk_ejercito_usuario
        FOREIGN KEY (usuario) REFERENCES usuario(nombreUsuario) ON DELETE CASCADE
);

-- Tabla de unidades
CREATE TABLE unidad (
    id integer PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR(100) NOT NULL,
    puntos INT NOT NULL,
    numero_modelos INT NOT NULL,
    tipo VARCHAR(100),
    ejercito_id INT NOT NULL,
    CONSTRAINT fk_unidad_ejercito
        FOREIGN KEY (ejercito_id) REFERENCES ejercito(id) ON DELETE CASCADE
);
