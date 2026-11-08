package org.example.service;

import static org.junit.jupiter.api.Assertions.*;

import org.example.entities.Asignatura;
import org.example.entities.Rol;
import org.example.entities.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InscripcionServiceTest {

  private InscripcionService inscripcionService;

  @BeforeEach
  void setUp() {
    inscripcionService = new InscripcionService();
  }

  @Test
  void inscribirEstudianteExitosoYVerificarInscripcion() {
    Usuario profesor = new Usuario("Ana", "Gomez", Rol.PROFESOR);
    Asignatura asignatura = new Asignatura("Matematica", "Mañana", profesor);
    Usuario estudiante = new Usuario("Juan", "Perez", Rol.ESTUDIANTE);

    inscripcionService.inscribir(estudiante, asignatura);

    boolean inscripto = inscripcionService.estaInscripto(estudiante, asignatura);
    assertTrue(inscripto, "El estudiante debería figurar como inscripto");
  }

  @Test
  void inscribirEstudianteDosVecesLanzaExcepcion() {
    Usuario profesor = new Usuario("Ana", "Gomez", Rol.PROFESOR);
    Asignatura asignatura = new Asignatura("Matematica", "Mañana", profesor);
    Usuario estudiante = new Usuario("Juan", "Perez", Rol.ESTUDIANTE);

    inscripcionService.inscribir(estudiante, asignatura);

    assertThrows(
        IllegalStateException.class,
        () -> {
          inscripcionService.inscribir(estudiante, asignatura);
        },
        "Debería lanzar IllegalStateException si el estudiante ya está inscripto");
  }

  @Test
  void obtenerEstudiantesAsignaturaExitoso() {
    Usuario profesor = new Usuario("Ana", "Gomez", Rol.PROFESOR);
    Asignatura asignatura = new Asignatura("Fisica", "Tarde", profesor);
    Usuario estudiante = new Usuario("Maria", "Lopez", Rol.ESTUDIANTE);

    inscripcionService.inscribir(estudiante, asignatura);

    var estudiantes = inscripcionService.obtenerEstudiantesAsignatura(asignatura);

    assertNotNull(estudiantes);
    assertEquals(1, estudiantes.size());
    assertEquals("Maria", estudiantes.get(0).getNombre());
  }

  @Test
  void obtenerAsignaturasEstudianteExitoso() {
    Usuario profesor = new Usuario("Ana", "Gomez", Rol.PROFESOR);
    Asignatura asignatura = new Asignatura("Quimica", "Mañana", profesor);
    Usuario estudiante = new Usuario("Carlos", "Ruiz", Rol.ESTUDIANTE);

    inscripcionService.inscribir(estudiante, asignatura);

    var asignaturas = inscripcionService.obtenerAsignaturasEstudiante(estudiante);

    assertNotNull(asignaturas);
    assertEquals(1, asignaturas.size());
    assertEquals("Quimica", asignaturas.get(0).getNombre());
  }
}
