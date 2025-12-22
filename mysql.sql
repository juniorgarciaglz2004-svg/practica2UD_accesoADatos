CREATE DATABASE IF NOT EXISTS practica2ud;
__
USE practica2ud;
__
CREATE TABLE IF NOT EXISTS codicion_producto (
id_condicion int auto_increment primary key,
nombre varchar(50) not null,
descripcion varchar(200) not null,
fecha_de_creacion date,
fecha_de_actualizacion date
);
__
CREATE TABLE IF NOT EXISTS clasificacion_producto (
id_clasificacion int auto_increment primary key,
nombre varchar(50) not null,
descripcion varchar(200) not null,
fecha_de_creacion date,
fecha_de_actualizacion date
);
__
CREATE TABLE IF NOT EXISTS kit_educativo (
id_kit int auto_increment primary key,
nombre varchar(50) not null,
descripcion varchar(200) not null,
cantidad int not null,
fecha_de_creacion date,
fecha_de_actualizacion date,
id_condicion int not null,
id_clasificacion int not null
);
__
alter table kit_educativo
add foreign key (id_condicion) references codicion_producto(id_condicion),
add foreign key (id_clasificacion) references clasificacion_producto(id_clasificacion);
