package org.example.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UsuarioTest {

  @Test
  void obtenerNombreCompletoExitoso() {
    Usuario usuario = new Usuario("Maria", "Gomez", Rol.PROFESOR);

    String nombreCompleto = usuario.getNombreCompleto();

    assertEquals(
        "Maria Gomez",
        nombreCompleto,
        "El nombre completo debe formarse uniendo nombre y apellido con un espacio");
  }

  @Test
  void usuariosIgualesDebenSerEquivalentes() {
    Usuario usuario1 = new Usuario("Juan", "Perez", Rol.ESTUDIANTE);
    Usuario usuario2 = new Usuario("Juan", "Perez", Rol.ESTUDIANTE);
    Usuario usuario3 = new Usuario("Carlos", "Ruiz", Rol.ESTUDIANTE);

    assertEquals(
        usuario1,
        usuario2,
        "Dos usuarios con el mismo nombre, apellido y rol deben ser considerados iguales por equals");

    // Validamos también que el hashCode coincida cuando los usuarios son iguales
    assertEquals(
        usuario1.hashCode(),
        usuario2.hashCode(),
        "El hashCode debe ser igual para usuarios equivalentes");

    // Validamos que usuarios distintos no sean iguales
    assertNotEquals(usuario1, usuario3, "Dos usuarios con datos distintos no deben ser iguales");

    // Validamos la comparación contra nulo de forma segura
    assertNotEquals(null, usuario1, "Un usuario no debe ser igual a null");
  }
}
