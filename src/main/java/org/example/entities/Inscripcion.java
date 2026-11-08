package org.example.entities;

public class Inscripcion {

  private Usuario estudiante;
  private Asignatura asignatura;

  public Inscripcion(Usuario estudiante, Asignatura asignatura) {
    this.estudiante = estudiante;
    this.asignatura = asignatura;
  }

  public Usuario getEstudiante() {
    return estudiante;
  }

  public Asignatura getAsignatura() {
    return asignatura;
  }
}
