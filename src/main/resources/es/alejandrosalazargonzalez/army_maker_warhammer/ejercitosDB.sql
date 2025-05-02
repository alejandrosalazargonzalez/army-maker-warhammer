-- Tabla de usuarios
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nombre_usuario VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE
);

-- Tabla de generales (sin cambios)
CREATE TABLE general (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    habilidad_especial VARCHAR(255)
);

-- Tabla de ejércitos, ahora con referencia a usuario
CREATE TABLE ejercito (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    faccion VARCHAR(100),
    sub_faccion VARCHAR(100),
    general_id INT NOT NULL,
    usuario_id INT NOT NULL,
    puntos INT,
    CONSTRAINT fk_ejercito_general
        FOREIGN KEY (general_id) REFERENCES general(id) ON DELETE CASCADE,
    CONSTRAINT fk_ejercito_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- Tabla de unidades (sin cambios)
CREATE TABLE unidad (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    puntos INT NOT NULL,
    numero_modelos INT NOT NULL,
    tipo VARCHAR(100),
    ejercito_id INT NOT NULL,
    CONSTRAINT fk_unidad_ejercito
        FOREIGN KEY (ejercito_id) REFERENCES ejercito(id) ON DELETE CASCADE
);
