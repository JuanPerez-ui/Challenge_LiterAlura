package com.aluracursos.Challenge.LiterAlura.service;

public interface IConvierteDatos {
    <T> T ObtenerDatos (String json, Class<T> clase);

}
