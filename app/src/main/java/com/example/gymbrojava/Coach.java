package com.example.gymbrojava;

public class Coach {
    private int id;
    private String nombreEntrenador;
    private String apellidoEntrenador;
    private String especialidad;
    private String direccion;
    private String telefono;
    private String correo;
    private String instagram;
    private String facebook;
    private String usuario;
    private String contrasena;
    private String estado;

    public Coach(int id, String nombreEntrenador, String apellidoEntrenador,
                 String especialidad, String direccion, String telefono,
                 String correo, String instagram, String facebook,
                 String usuario, String contrasena, String estado) {
        this.id = id;
        this.nombreEntrenador = nombreEntrenador;
        this.apellidoEntrenador = apellidoEntrenador;
        this.especialidad = especialidad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.instagram = instagram;
        this.facebook = facebook;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    // Getters
    public int getId() { return id; }
    public String getNombreEntrenador() { return nombreEntrenador; }
    public String getApellidoEntrenador() { return apellidoEntrenador; }
    public String getEspecialidad() { return especialidad; }
    public String getDireccion() { return direccion; }
    public String getTelefono() { return telefono; }
    public String getCorreo() { return correo; }
    public String getInstagram() { return instagram; }
    public String getFacebook() { return facebook; }
    public String getUsuario() { return usuario; }
    public String getContrasena() { return contrasena; }
    public String getEstado() { return estado; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNombreEntrenador(String nombreEntrenador) { this.nombreEntrenador = nombreEntrenador; }
    public void setApellidoEntrenador(String apellidoEntrenador) { this.apellidoEntrenador = apellidoEntrenador; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setInstagram(String instagram) { this.instagram = instagram; }
    public void setFacebook(String facebook) { this.facebook = facebook; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public void setEstado(String estado) { this.estado = estado; }

    // Métodos adicionales útiles
    public String getNombreCompleto() {
        return nombreEntrenador + " " + apellidoEntrenador;
    }

    public boolean isActivo() {
        return "activo".equalsIgnoreCase(estado);
    }

    public boolean tieneSocialMedia() {
        return !instagram.isEmpty() || !facebook.isEmpty();
    }

    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", nombreEntrenador='" + nombreEntrenador + '\'' +
                ", apellidoEntrenador='" + apellidoEntrenador + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", instagram='" + instagram + '\'' +
                ", facebook='" + facebook + '\'' +
                ", usuario='" + usuario + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return id == coach.id &&
                usuario.equals(coach.usuario);
    }

    @Override
    public int hashCode() {
        return 31 * id + usuario.hashCode();
    }
}