package org.example.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import org.example.entities.Asignatura;
import org.example.entities.Rol;
import org.example.entities.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AsignaturaServiceTest {

  private AsignaturaService service;

  @BeforeEach
  void setUp() {
    service = new AsignaturaService();
  }

  @Test
  void crearAsignaturaExitosa() {
    Usuario profesor = new Usuario("Ana", "Gomez", Rol.PROFESOR);

    service.cargarAsignatura(profesor, "Matematica", "Mañana");

    assertEquals(
        1,
        service.obtenerAsignaturas().size(),
        "La asignatura debería haberse agregado a la lista del servicio");
  }

  @Test
  void intentarListarMateriaInexistente() {
    Optional<Asignatura> resultado = service.buscarAsignatura("MateriaFalsa");

    assertTrue(
        resultado.isEmpty(),
        "La búsqueda de una materia que no existe debe retornar un Optional vacío");
  }

  @Test
  void cambiarTurnoAsignaturaInexistenteLanzaExcepcion() {
    Usuario profesor = new Usuario("Ana", "Gomez", Rol.PROFESOR);

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          service.cambiarTurno(profesor, "Fisica", "Noche");
        },
        "Debería lanzar IllegalArgumentException si la asignatura no existe o no pertenece al profesor");
  }

  @Test
  void obtenerAsignaturasProfesorExitoso() {
    Usuario profesor = new Usuario("Carlos", "Perez", Rol.PROFESOR);
    service.cargarAsignatura(profesor, "Algoritmos", "Tarde");

    var asignaturas = service.obtenerAsignaturasProfesor(profesor);

    assertNotNull(asignaturas);
    assertEquals(1, asignaturas.size());
  }

  @Test
  void cambiarTurnoExitoso() {
    Usuario profesor = new Usuario("Ana", "Gomez", Rol.PROFESOR);
    service.cargarAsignatura(profesor, "Matematica", "Mañana");

    service.cambiarTurno(profesor, "Matematica", "Noche");
    var asignaturaOpt = service.buscarAsignatura("Matematica");

    assertTrue(asignaturaOpt.isPresent());
    assertEquals("Noche", asignaturaOpt.get().getTurno());
  }
}
