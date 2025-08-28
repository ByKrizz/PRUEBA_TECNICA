CREATE DATABASE `accountdb` 


-- Tabla: tbl_customer
CREATE TABLE tbl_customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    direccion VARCHAR(255),
    telefono VARCHAR(50),
    password VARCHAR(255),
    estado VARCHAR(50)
);

-- Tabla: tbl_account
CREATE TABLE tbl_account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(255) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial DOUBLE NOT NULL,
    saldo_disponible DOUBLE NOT NULL,
    estado BOOLEAN NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    cliente_id BIGINT NOT NULL,
    CONSTRAINT fk_account_customer
        FOREIGN KEY (cliente_id) REFERENCES tbl_customer(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Tabla: tbl_movements

CREATE TABLE tbl_movements (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME,
    tipo_movimiento VARCHAR(50),
    monto DOUBLE,
    saldo_disponible DOUBLE,
    estado BOOLEAN,
    account_id BIGINT NOT NULL,
    CONSTRAINT fk_movement_account
        FOREIGN KEY (account_id) REFERENCES tbl_account(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);