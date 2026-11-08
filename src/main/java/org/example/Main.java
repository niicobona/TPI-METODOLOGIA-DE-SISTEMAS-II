package org.example;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.example.entities.Asignatura;
import org.example.entities.Nota;
import org.example.entities.Rol;
import org.example.entities.Usuario;
import org.example.service.AsignaturaService;
import org.example.service.InscripcionService;
import org.example.service.NotaService;

public class Main {
  private final Scanner sc = new Scanner(System.in);
  private final AsignaturaService asignaturaService = new AsignaturaService();
  private final InscripcionService inscripcionService = new InscripcionService();
  private final NotaService notaService = new NotaService(inscripcionService);

  public static void main(String[] args) {

    Main app = new Main();
    app.mostrarMenuPrincipal();
  }

  private void mostrarMenuPrincipal() {

    int opcionMenuPrincipal = -1;

    while (opcionMenuPrincipal != 0) {

      System.out.println("--- SISTEMA ACADEMICO ---");
      System.out.println("1 - Iniciar Sesión");
      System.out.println("0 - Salir del sistema");
      System.out.print("Seleccione una opción: ");

      opcionMenuPrincipal = sc.nextInt();
      sc.nextLine();

      switch (opcionMenuPrincipal) {
        case 1:
          iniciarSesion();
          break;
        case 0:
          System.out.println("Saliendo del sistema.");
          break;
        default:
          System.out.println("Opción incorrecta.");
          break;
      }
    }
  }

  private void iniciarSesion() {

    System.out.println("Ingrese su nombre: ");
    String nombre = sc.nextLine();

    System.out.println("Ingrese su apellido: ");
    String apellido = sc.nextLine();

    System.out.println("Ingrese su Rol (profesor/estudiante): ");
    String rolIngresado = sc.nextLine();

    if (rolIngresado.equalsIgnoreCase("profesor")) {

      Usuario profesor = new Usuario(nombre, apellido, Rol.PROFESOR);
      mostrarMenuProfesor(profesor);
    } else if (rolIngresado.equalsIgnoreCase("estudiante")) {

      Usuario estudiante = new Usuario(nombre, apellido, Rol.ESTUDIANTE);
      mostrarMenuEstudiante(estudiante);
    } else {

      System.out.println("Rol incorrecto, debe ingresar 'profesor' o 'estudiante'");
    }
  }

  private void mostrarMenuProfesor(Usuario profesor) {

    int opcion;

    do {
      System.out.println("--- Menu (Profesor) ---");
      System.out.println("1-Cargar asignaturas");
      System.out.println("2-Listar asignaturas a cargo");
      System.out.println("3-Cambiar turno de asignaturas");
      System.out.println("4-Publicar notas");
      System.out.println("0-Cerrar Sesión");

      opcion = sc.nextInt();
      sc.nextLine();

      switch (opcion) {
        case 1:
          cargarAsignatura(profesor);
          break;
        case 2:
          listarAsignaturasProfesor(profesor);
          break;
        case 3:
          cambiarTurno(profesor);
          break;
        case 4:
          publicarNota(profesor);
          break;
        case 0:
          System.out.println("Cerrando sesión.");
          break;
        default:
          System.out.println("Opción incorrecta.");
          break;
      }
    } while (opcion != 0);
  }

  private void mostrarMenuEstudiante(Usuario estudiante) {

    int opcion;

    do {
      System.out.println("--- Menu (Estudiante) ---");
      System.out.println("1-Inscripción a asignaturas");
      System.out.println("2-Listar mis asignaturas");
      System.out.println("3-Ver mis notas");
      System.out.println("0-Cerrar Sesión");

      opcion = sc.nextInt();
      sc.nextLine();

      switch (opcion) {
        case 1:
          inscribirEstudiante(estudiante);
          break;
        case 2:
          listarAsignaturasEstudiante(estudiante);
          break;
        case 3:
          verNotas(estudiante);
          break;
        case 0:
          System.out.println("Cerrando sesión.");
          break;
        default:
          System.out.println("Opción incorrecta.");
          break;
      }
    } while (opcion != 0);
  }

  // METODOS DEL PROFESOR

  private void cargarAsignatura(Usuario profesor) {
    System.out.println("\n--- ALTA DE ASIGNATURA ---");

    System.out.println("introducir el nombre de la asignatura: ");
    String nombreAsignatura = sc.nextLine();

    System.out.println("Introducir el turno (Mañana/Tarde): ");
    String turno = sc.nextLine();

    asignaturaService.cargarAsignatura(profesor, nombreAsignatura, turno);

    System.out.println(
        "Asignatura "
            + nombreAsignatura
            + " dada de alta correctamente por el profesor "
            + profesor.getNombreCompleto());
  }

  private void listarAsignaturasProfesor(Usuario profesor) {
    List<Asignatura> asignaturas = asignaturaService.obtenerAsignaturasProfesor(profesor);

    System.out.println("\n--- ASIGNATURAS A CARGO (" + profesor.getNombreCompleto() + ") ---");

    if (asignaturas.isEmpty()) {
      System.out.println("No hay asignaturas disponibles.");
      return;
    }

    for (Asignatura asignatura : asignaturas) {
      System.out.println(
          "Materia: " + asignatura.getNombre() + " | Turno: " + asignatura.getTurno());
    }
  }

  private void cambiarTurno(Usuario profesor) {
    System.out.println("Introducir la asignatura: ");
    String nombreAsignatura = sc.nextLine();

    System.out.println("Introducir nuevo turno: ");
    String nuevoTurno = sc.nextLine();

    try {
      asignaturaService.cambiarTurno(profesor, nombreAsignatura, nuevoTurno);
      System.out.println("Asignatura cambiada correctamente.");

    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  private void publicarNota(Usuario profesor) {
    List<Asignatura> asignaturas = asignaturaService.obtenerAsignaturasProfesor(profesor);

    if (asignaturas.isEmpty()) {
      System.out.println("No hay asignaturas a cargo.");
      return;
    }

    System.out.println("\n--- ASIGNATURAS A CARGO ---");

    for (Asignatura asignatura : asignaturas) {
      System.out.println(
          "Materia: " + asignatura.getNombre() + " | Turno: " + asignatura.getTurno());
    }

    System.out.println("Ingrese asignatura: ");
    String nombreAsignatura = sc.nextLine();

    Asignatura asignaturaSeleccionada = null;

    for (Asignatura asignatura : asignaturas) {
      if (asignatura.getNombre().equalsIgnoreCase(nombreAsignatura)) {
        asignaturaSeleccionada = asignatura;
        break;
      }
    }

    if (asignaturaSeleccionada == null) {
      System.out.println("La asignatura seleccionada no está a su cargo.");
      return;
    }

    List<Usuario> estudiantes =
        inscripcionService.obtenerEstudiantesAsignatura(asignaturaSeleccionada);

    if (estudiantes.isEmpty()) {
      System.out.println("No hay estudiantes inscriptos en esta asignatura.");
      return;
    }

    System.out.println("\n--- ESTUDIANTES INSCRIPTOS ---");

    for (Usuario estudiante : estudiantes) {
      System.out.println("Alumno: " + estudiante.getNombreCompleto());
    }

    System.out.println("Ingrese el nombre completo del estudiante: ");
    String nombreEstudiante = sc.nextLine();

    Usuario estudianteSeleccionado = null;

    for (Usuario estudiante : estudiantes) {
      if (estudiante.getNombreCompleto().equalsIgnoreCase(nombreEstudiante)) {
        estudianteSeleccionado = estudiante;
        break;
      }
    }

    if (estudianteSeleccionado == null) {
      System.out.println("El estudiante no está inscripto en esta materia.");
      return;
    }

    System.out.println("Ingrese nota: ");
    double valor = Double.parseDouble(sc.nextLine());

    try {
      notaService.publicarNota(estudianteSeleccionado, asignaturaSeleccionada, valor);

      System.out.println("Nota publicada correctamente.");

    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    }
  }

  // METODOS DEL ESTUDIANTE

  private void inscribirEstudiante(Usuario estudiante) {
    List<Asignatura> asignaturas = asignaturaService.obtenerAsignaturas();

    System.out.println("\n--- ASIGNATURAS DISPONIBLES ---");

    if (asignaturas.isEmpty()) {
      System.out.println("No hay asignaturas disponibles.");
      return;
    }

    for (Asignatura asignatura : asignaturas) {
      System.out.println(
          "Asignatura: "
              + asignatura.getNombre()
              + " | Profesor: "
              + asignatura.getProfesor().getNombreCompleto()
              + " | Turno: "
              + asignatura.getTurno());
    }

    System.out.println("Introducir la asignatura: ");
    String nombreAsignatura = sc.nextLine();

    Optional<Asignatura> resultado = asignaturaService.buscarAsignatura(nombreAsignatura);

    if (resultado.isEmpty()) {
      System.out.println("Asignatura no disponible.");
      return;
    }

    Asignatura asignatura = resultado.get();

    try {
      inscripcionService.inscribir(estudiante, asignatura);

      System.out.println("Inscripción registrada correctamente.");

    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    }
  }

  private void listarAsignaturasEstudiante(Usuario estudiante) {
    List<Asignatura> asignaturas = inscripcionService.obtenerAsignaturasEstudiante(estudiante);

    System.out.println("\n--- MIS ASIGNATURAS ---");

    if (asignaturas.isEmpty()) {
      System.out.println("No estás inscripto en ninguna asignatura.");
      return;
    }

    for (Asignatura asignatura : asignaturas) {
      System.out.println(
          "Asignatura: " + asignatura.getNombre() + " | Turno: " + asignatura.getTurno());
    }
  }

  private void verNotas(Usuario estudiante) {
    List<Nota> notas = notaService.obtenerNotasEstudiante(estudiante);

    System.out.println("\n--- VER NOTAS ---");

    if (notas.isEmpty()) {
      System.out.println("No hay notas disponibles.");
      return;
    }

    for (Nota nota : notas) {
      System.out.println(
          "Asignatura: " + nota.getAsignatura().getNombre() + " | Nota: " + nota.getValor());
    }
  }
}
