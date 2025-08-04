package com.gripster.model;

import java.sql.Timestamp;

public class Sesion {
    private String idSesion;
    private Timestamp fechaCreacion;
    private Timestamp fechaExpiracion;

    public Sesion() {}

    public Sesion(String idSesion, Timestamp fechaCreacion, Timestamp fechaExpiracion) {
        this.idSesion = idSesion;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Timestamp fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
}