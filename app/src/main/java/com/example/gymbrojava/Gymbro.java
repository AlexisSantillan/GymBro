package com.example.gymbrojava;
import java.time.LocalDateTime;

public class Gymbro {
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String usuario;
    private String contrasena;
    private LocalDateTime fechaRegistro;
    private String estado;

    public Gymbro(int id, String nombre, String apellido, String direccion,
                  String correo, String usuario, String contrasena,
                  LocalDateTime fechaRegistro, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDireccion() { return direccion; }
    public String getCorreo() { return correo; }
    public String getUsuario() { return usuario; }
    public String getContrasena() { return contrasena; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public String getEstado() { return estado; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public void setEstado(String estado) { this.estado = estado; }

    // Métodos adicionales útiles
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public boolean isActivo() {
        return "activo".equalsIgnoreCase(estado);
    }

    @Override
    public String toString() {
        return "Gymbro{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", usuario='" + usuario + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gymbro gymbro = (Gymbro) o;
        return id == gymbro.id &&
                usuario.equals(gymbro.usuario);
    }

    @Override
    public int hashCode() {
        return 31 * id + usuario.hashCode();
    }
}