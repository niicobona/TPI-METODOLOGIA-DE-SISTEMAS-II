package org.example.entities;

public class Nota {

  private Usuario estudiante;
  private Asignatura asignatura;
  private double valor;

  public Nota(Usuario estudiante, Asignatura asignatura, double valor) {
    this.estudiante = estudiante;
    this.asignatura = asignatura;
    this.valor = valor;
  }

  public Usuario getEstudiante() {
    return estudiante;
  }

  public Asignatura getAsignatura() {
    return asignatura;
  }

  public double getValor() {
    return valor;
  }
}
