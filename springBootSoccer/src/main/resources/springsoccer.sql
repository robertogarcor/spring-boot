-- Database spring db --

CREATE ROLE spring WITH LOGIN PASSWORD 'spring';
CREATE DATABASE springsoccer OWNER spring ENCODING 'UTF8' TEMPLATE template1;
GRANT ALL PRIVILEGES ON DATABASE springsoccer TO spring;


\connect springsoccer;

ALTER SCHEMA public OWNER TO spring;


-- Table equipo --


CREATE TABLE IF NOT EXISTS equipo(
    id SERIAL,
    nombre VARCHAR(25) UNIQUE NOT NULL
);


ALTER TABLE public.equipo
    ADD PRIMARY KEY (id);


ALTER TABLE public.equipo OWNER TO spring;

-- Insert values equipo --

INSERT INTO public.equipo (nombre) VALUES
    ('Barcelona'),
    ('Madrid'),
    ('Juventus'),
    ('Sevilla'),
    ('Florentina');


-- Table marca --


CREATE TABLE IF NOT EXISTS marca(
    id SERIAL,
    nombre VARCHAR(25) UNIQUE NOT NULL

);

ALTER TABLE public.marca
    ADD PRIMARY KEY (id);

ALTER TABLE public.marca OWNER TO spring;

-- Insert values marca --

INSERT INTO public.marca (nombre) VALUES
    ('Nike'),
    ('Adidas'),
    ('Puma'),
    ('Rebook'),
    ('Joma');


-- Table camiseta --


CREATE TABLE IF NOT EXISTS camiseta(
    id SERIAL,
    nombre VARCHAR(25) UNIQUE NOT NULL,
    marca_id INT
);

ALTER TABLE public.camiseta
    ADD PRIMARY KEY (id),
    ADD CONSTRAINT fk_marca_camiseta FOREIGN KEY (marca_id)
        REFERENCES marca(id) ON UPDATE CASCADE ON DELETE SET null;

ALTER TABLE public.camiseta OWNER TO spring;

-- Insert values camiseta --

INSERT INTO public.camiseta (nombre, marca_id) VALUES
    ('Local', 1),
    ('Visitante', 3),
    ('Liga', 2),
    ('Europea', 4),
    ('Regional', 5);


-- Table jugador --


CREATE TABLE IF NOT EXISTS jugador(
    id SERIAL,
    nombre VARCHAR(25) NOT NULL,
    equipo_id INT,
    camiseta_id INT
);

ALTER TABLE public.jugador
    ADD PRIMARY KEY (id),
    ADD CONSTRAINT fk_equipo_jugador FOREIGN KEY (equipo_id)
        REFERENCES equipo(id) ON UPDATE CASCADE ON DELETE SET null,
    ADD CONSTRAINT fk_camiseta_jugador FOREIGN KEY (camiseta_id)
        REFERENCES camiseta(id) ON UPDATE CASCADE ON DELETE SET null;

ALTER TABLE public.jugador OWNER TO spring;


-- Insert values jugador --

INSERT INTO public.jugador (nombre, equipo_id, camiseta_id) VALUES
    ('Roberto', 4, 2),
    ('Pedro', 5, 1),
    ('Iniesta', 1, 4),
    ('Messi', 1, 2),
    ('Ronaldo', 2, 3);


