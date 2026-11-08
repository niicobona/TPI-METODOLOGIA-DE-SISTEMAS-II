package org.example.service;

import java.util.ArrayList;
import java.util.List;
import org.example.entities.Asignatura;
import org.example.entities.Nota;
import org.example.entities.Usuario;

// Gestiona las notas y utiliza inscripcionConsulta para validar previamente que el estudiante esté
// inscripto.
public class NotaService {

  private final List<Nota> notas = new ArrayList<>();
  private final InscripcionConsulta inscripcionConsulta;

  // La dependencia se recibe por constructor para evitar que NotaService cree o dependa
  // directamente de InscripcionService.
  public NotaService(InscripcionConsulta inscripcionConsulta) {
    this.inscripcionConsulta = inscripcionConsulta;
  }

  public void publicarNota(Usuario estudiante, Asignatura asignatura, double valor) {
    if (!inscripcionConsulta.estaInscripto(estudiante, asignatura)) {
      throw new IllegalStateException(
          "No se puede publicar una nota: el estudiante no está inscripto.");
    }
    Nota nota = new Nota(estudiante, asignatura, valor);
    notas.add(nota);
  }

  // Obtener notas de un estudiante específico.
  public List<Nota> obtenerNotasEstudiante(Usuario estudiante) {
    List<Nota> notasEstudiante = new ArrayList<>();
    for (Nota nota : notas) {
      if (nota.getEstudiante().equals(estudiante)) {
        notasEstudiante.add(nota);
      }
    }
    return notasEstudiante;
  }
}