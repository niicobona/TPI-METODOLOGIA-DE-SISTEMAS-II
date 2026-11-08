package org.example.entities;

public class Asignatura {

  private String nombre;
  private String turno;
  private Usuario profesor;

  public Asignatura(String nombre, String turno, Usuario profesor) {
    this.turno = turno;
    this.nombre = nombre;
    this.profesor = profesor;
  }

  public void cambiarTurno(String turno) {
    this.turno = turno;
  }

  public String getNombre() {
    return nombre;
  }

  public String getTurno() {
    return turno;
  }

  public Usuario getProfesor() {
    return profesor;
  }
}
