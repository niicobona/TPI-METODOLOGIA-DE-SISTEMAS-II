package org.example.service;

import java.util.ArrayList;
import java.util.List;
import org.example.entities.Asignatura;
import org.example.entities.Inscripcion;
import org.example.entities.Usuario;

// Gestiona las inscripciones y permite consultar si un estudiante pertenece a una asignatura.
public class InscripcionService implements InscripcionConsulta {

  private final List<Inscripcion> inscripciones = new ArrayList<>();

  // Registra al estudiante en una asignatura.
  public void inscribir(Usuario estudiante, Asignatura asignatura) {
    if (estaInscripto(estudiante, asignatura)) {
      throw new IllegalStateException("El estudiante ya está inscripto en esta asignatura.");
    }
    Inscripcion inscripcion = new Inscripcion(estudiante, asignatura);
    inscripciones.add(inscripcion);
  }

  // Obtiene todas las asignaturas en las que está inscripto el estudiante.
  public List<Asignatura> obtenerAsignaturasEstudiante(Usuario estudiante) {
    List<Asignatura> asignaturasEstudiante = new ArrayList<>();
    for (Inscripcion inscripcion : inscripciones) {
      if (inscripcion.getEstudiante().equals(estudiante)) {
        asignaturasEstudiante.add(inscripcion.getAsignatura());
      }
    }
    return asignaturasEstudiante;
  }

  // Devuelve los estudiantes inscriptos en una asignatura determinada.
  public List<Usuario> obtenerEstudiantesAsignatura(Asignatura asignatura) {
    List<Usuario> estudiantes = new ArrayList<>();
    for (Inscripcion inscripcion : inscripciones) {
      if (inscripcion.getAsignatura().equals(asignatura)) {
        estudiantes.add(inscripcion.getEstudiante());
      }
    }
    return estudiantes;
  }

  // Permite comprobar una inscripción sin depender directamente de InscripcionService
  @Override
  public boolean estaInscripto(Usuario estudiante, Asignatura asignatura) {
    for (Inscripcion inscripcion : inscripciones) {
      if (inscripcion.getEstudiante().equals(estudiante)
          && inscripcion.getAsignatura().equals(asignatura)) {
        return true;
      }
    }
    return false;
  }
}