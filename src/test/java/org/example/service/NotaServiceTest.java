package org.example.service;

import static org.junit.jupiter.api.Assertions.*;

import org.example.entities.Asignatura;
import org.example.entities.Rol;
import org.example.entities.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotaServiceTest {

  private NotaService notaService;
  private InscripcionService inscripcionService;

  @BeforeEach
  void setUp() {
    inscripcionService = new InscripcionService();
    notaService = new NotaService(inscripcionService); // Inyección de dependencia del contrato
  }

  @Test
  void publicarNotaExitosamente() {
    Usuario profesor = new Usuario("Ana", "Gomez", Rol.PROFESOR);
    Asignatura asignatura = new Asignatura("Matematica", "Mañana", profesor);
    Usuario estudiante = new Usuario("Juan", "Perez", Rol.ESTUDIANTE);

    // Primero se inscribe para que pase la validación
    inscripcionService.inscribir(estudiante, asignatura);

    notaService.publicarNota(estudiante, asignatura, 9.5);

    var notas = notaService.obtenerNotasEstudiante(estudiante);
    assertEquals(1, notas.size());
    assertEquals(9.5, notas.get(0).getValor());
  }

  @Test
  void publicarNotaSinEstarInscriptoLanzaExcepcion() {
    Usuario profesor = new Usuario("Ana", "Gomez", Rol.PROFESOR);
    Asignatura asignatura = new Asignatura("Matematica", "Mañana", profesor);
    Usuario estudiante = new Usuario("Juan", "Perez", Rol.ESTUDIANTE);

    // No lo inscribimos, por lo que debe fallar
    assertThrows(
        IllegalStateException.class,
        () -> {
          notaService.publicarNota(estudiante, asignatura, 8.0);
        },
        "Debe lanzar excepción si el estudiante no está inscripto");
  }
}
