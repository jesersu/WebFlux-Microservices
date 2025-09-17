
-- Table: estados
CREATE TABLE estados (
                         id_estado SERIAL PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         descripcion TEXT
);

-- Table: tipo_prestamo
CREATE TABLE tipo_prestamo (
                               id_tipo_prestamo SERIAL PRIMARY KEY,
                               nombre VARCHAR(100) NOT NULL,
                               monto_minimo NUMERIC(15,2) NOT NULL,
                               monto_maximo NUMERIC(15,2) NOT NULL,
                               tasa_interes NUMERIC(5,2) NOT NULL, -- e.g., 12.50 (%)
                               validacion_automatica BOOLEAN NOT NULL DEFAULT false
);

-- Table: solicitud
CREATE TABLE solicitud (
                           id_solicitud SERIAL PRIMARY KEY,
                           monto NUMERIC(15,2) NOT NULL,
                           plazo INT NOT NULL, -- plazo en meses o días (ajustar según negocio)
                           email VARCHAR(255) NOT NULL,
                           id_estado INT NOT NULL,
                           id_tipo_prestamo INT NOT NULL,

    -- Foreign keys
                           CONSTRAINT fk_solicitud_estado
                               FOREIGN KEY (id_estado) REFERENCES estados (id_estado),
                           CONSTRAINT fk_solicitud_tipo
                               FOREIGN KEY (id_tipo_prestamo) REFERENCES tipo_prestamo (id_tipo_prestamo)
);

-- Insert into estados
INSERT INTO estados (nombre, descripcion)
VALUES
    ('Pendiente', 'Solicitud recién creada, en espera de validación'),
    ('Aprobado', 'Solicitud aprobada y lista para desembolso');

-- Insert into tipo_prestamo
INSERT INTO tipo_prestamo (nombre, monto_minimo, monto_maximo, tasa_interes, validacion_automatica)
VALUES
    ('Préstamo Personal', 1000.00, 20000.00, 12.50, true),
    ('Préstamo Hipotecario', 50000.00, 500000.00, 8.75, false);

-- Insert into solicitud
INSERT INTO solicitud (monto, plazo, email, id_estado, id_tipo_prestamo)
VALUES
    (5000.00, 24, 'juan.perez@example.com', 1, 1),
    (120000.00, 120, 'maria.garcia@example.com', 2, 2);