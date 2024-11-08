package com.example.gymbrojava;

public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String usuario;

    public Usuario(int id, String nombre, String apellido, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + usuario + ")";
    }
}