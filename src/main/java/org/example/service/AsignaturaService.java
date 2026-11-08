package org.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.entities.Asignatura;
import org.example.entities.Usuario;

// Gestiona las asignaturas del sistema y centra la lógica.
public class AsignaturaService {

  private final List<Asignatura> asignaturas = new ArrayList<>();

  // Crea una nueva asignatura asociada al profesor recibido.
  public void cargarAsignatura(Usuario profesor, String nombre, String turno) {
    Asignatura asignatura = new Asignatura(nombre, turno, profesor);
    asignaturas.add(asignatura);
  }

  // Devuelve únicamente las asignaturas que pertenecen al profesor
  public List<Asignatura> obtenerAsignaturasProfesor(Usuario profesor) {

    List<Asignatura> asignaturasProfesor = new ArrayList<>();

    for (Asignatura asignatura : asignaturas) {
      if (asignatura.getProfesor().equals(profesor)) {
        asignaturasProfesor.add(asignatura);
      }
    }
    return asignaturasProfesor;
  }

  // Devuelve todas las asignaturas disponibles en el sistema.
  public List<Asignatura> obtenerAsignaturas() {
    return new ArrayList<>(asignaturas);
  }

  public Optional<Asignatura> buscarAsignatura(String nombre) {
    for (Asignatura asignatura : asignaturas) {
      if (asignatura.getNombre().equalsIgnoreCase(nombre)) {
        return Optional.of(asignatura);
      }
    }

    return Optional.empty();
  }

  public void cambiarTurno(Usuario profesor, String nombreAsignatura, String nuevoTurno) {
    for (Asignatura asignatura : asignaturas) {
      if (asignatura.getNombre().equalsIgnoreCase(nombreAsignatura)
          && asignatura.getProfesor().equals(profesor)) {
        asignatura.cambiarTurno(nuevoTurno);
        return;
      }
    }
    throw new IllegalArgumentException("La asignatura no existe o no pertenece al profesor.");
  }
}