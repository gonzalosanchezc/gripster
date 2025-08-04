package com.gripster.model;

public class Modelo {
    private int idModelo;
    private int idMarca;
    private String nombre;

    public Modelo() {}

    public Modelo(int idModelo, int idMarca, String nombre) {
        this.idModelo = idModelo;
        this.idMarca = idMarca;
        this.nombre = nombre;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}