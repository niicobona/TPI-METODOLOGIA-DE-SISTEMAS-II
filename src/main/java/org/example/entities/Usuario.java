package org.example.entities;

import java.util.Objects;

public class Usuario {

  private String nombre;
  private String apellido;
  private Rol rol;

  public Usuario(String nombre, String apellido, Rol rol) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.rol = rol;
  }

  public String getNombreCompleto() {
    String nombreCompleto = nombre + " " + apellido;
    return nombreCompleto;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public Rol getRol() {
    return rol;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Usuario usuario)) {
      return false;
    }
    return nombre.equals(usuario.nombre) && apellido.equals(usuario.apellido) && rol == usuario.rol;
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre, apellido, rol);
  }
}
