package org.example.service;

import org.example.entities.Asignatura;
import org.example.entities.Usuario;

// Abstracción utilizada para consultar inscripciones.
// Permite que NotaService dependa de un contrato y no de una implementación concreta.
public interface InscripcionConsulta {

  boolean estaInscripto(Usuario estudiante, Asignatura asignatura);
}
