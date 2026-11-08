# Sistema de Gestión Académica

**📷 [¡Mirá el video explicativo!] (PEGAR_LINK_DEL_VIDEO)**

Aplicación de consola desarrollada en Java para la gestión académica, que permite administrar asignaturas, inscripciones de estudiantes y calificaciones. El proyecto se enfoca en la aplicación de principios de **Clean Code**, buenas prácticas de diseño y técnicas de refactorización para mejorar la organización, legibilidad y mantenibilidad del código.

---

## 🚀 DESCRIPCIÓN DEL PROYECTO

El sistema permite gestionar las principales operaciones académicas a través de dos perfiles de usuario: profesor y estudiante.

### 👨‍🏫 1. Funcionalidades del profesor

- **Gestión de asignaturas:** permite crear, listar y modificar el turno de las asignaturas.
- **Consulta de asignaturas:** permite visualizar las asignaturas a cargo del profesor.
- **Gestión de calificaciones:** permite publicar notas para los estudiantes inscriptos en sus asignaturas.
- **Consulta de estudiantes:** permite visualizar los estudiantes inscriptos en cada asignatura.

### 🎓 2. Funcionalidades del estudiante

- **Inscripción a asignaturas:** permite inscribirse en las asignaturas disponibles.
- **Consulta de asignaturas:** permite visualizar las asignaturas en las que se encuentra inscripto.
- **Consulta de calificaciones:** permite consultar las notas publicadas.

### 🏗️ 3. Diseño y arquitectura

El proyecto fue refactorizado para mejorar la separación de responsabilidades y reducir el acoplamiento entre sus componentes.

Se organiza principalmente en tres partes:

- **Main:** gestiona la interacción con el usuario mediante la consola y coordina los distintos casos de uso.
- **Servicios:** contienen la lógica de negocio y las operaciones principales del sistema.
- **Entidades:** representan los conceptos centrales del dominio académico.

Entre sus principales componentes se encuentran:

- `AsignaturaService`: administra las asignaturas y sus operaciones.
- `InscripcionService`: gestiona las inscripciones y evita que un estudiante se inscriba más de una vez en la misma asignatura.
- `NotaService`: administra las calificaciones y verifica que el estudiante esté inscripto antes de publicar una nota.
- `InscripcionConsulta`: interfaz utilizada para desacoplar la consulta de inscripciones de la implementación concreta del servicio.

Además, se incorporaron objetos de dominio como `Usuario`, `Asignatura`, `Inscripcion` y `Nota`, reemplazando estructuras genéricas por modelos más claros y representativos.

---

## 🛠️ TECNOLOGÍAS Y HERRAMIENTAS UTILIZADAS

- **Java 17:** lenguaje de programación utilizado para desarrollar la aplicación.
- **Gradle:** gestor de construcción y dependencias.
- **JUnit 5:** framework utilizado para desarrollar y ejecutar pruebas unitarias.
- **JaCoCo:** herramienta para medir la cobertura del código mediante las pruebas.
- **PMD:** herramienta de análisis estático para detectar posibles problemas de calidad en el código.
- **Spotless:** herramienta para unificar y verificar el formato del código fuente.
- **IntelliJ IDEA:** entorno de desarrollo utilizado durante el proyecto.

---

## 🧪 PRUEBAS Y VERIFICACIÓN

Para verificar el comportamiento y la calidad del sistema se utilizaron pruebas de caja negra, pruebas unitarias y herramientas de análisis de código.

- **Pruebas de caja negra:** se diseñaron nueve casos de prueba para verificar las funcionalidades principales de la aplicación.
- **Pruebas unitarias:** se implementaron 13 pruebas con JUnit 5 para comprobar el comportamiento de los servicios y las entidades.
- **Cobertura de código:** mediante JaCoCo se obtuvo un 98 % de cobertura de instrucciones en los servicios y un 93 % en las entidades.
- **Análisis estático:** se utilizó PMD sobre los servicios y las entidades. La clase `Main` fue excluida temporalmente del análisis.
- **Formato del código:** se utilizó Spotless con Google Java Format para mantener una estructura y formato uniformes.

---

## 📦 INSTRUCCIONES DE EJECUCIÓN

### 1. Requisitos previos

Para ejecutar el proyecto se necesita:

- Tener instalado **JDK 17** o superior.
- Contar con **IntelliJ IDEA**, Eclipse o algún IDE compatible con proyectos Java.
- Tener Gradle disponible o utilizar el Gradle Wrapper incluido en el proyecto, si está disponible.

### 2. Ejecución del proyecto

**Desde IntelliJ IDEA:**

1. Abrir el proyecto en IntelliJ IDEA.
2. Esperar a que Gradle sincronice las dependencias.
3. Ubicar la clase principal `Main.java`, dentro de `src/main/java/org/example/`.
4. Ejecutar el método `main` mediante la opción **Run 'Main.main()'**.

### 4. Generación del reporte de cobertura

Para generar el reporte de JaCoCo:

```bash
./gradlew jacocoTestReport
```

El reporte HTML se encuentra en:

```text
build/reports/jacoco/test/html/index.html
```

### 5. Análisis de calidad del código

Para ejecutar PMD:

```bash
./gradlew pmdMain
```

Para verificar el formato del código con Spotless:

```bash
./gradlew spotlessCheck
```

Para aplicar el formato automáticamente:

```bash
./gradlew spotlessApply
```

---

## 👩‍💻 AUTORES

- **Marianela Soledad Albrigi**
- **Nicolás Bonano**

*Desarrollo realizado para el Trabajo Práctico Integrador de la asignatura Metodología de Sistemas II.*
