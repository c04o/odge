# 🦷 ODGE: Sistema de Gestión Odontológica  
**Odontological Management System**

ODGE es una aplicación web sencilla para la gestión de clínicas y consultorios odontológicos desarrollada con **OpenXava**.

Elaborado por estudiantes de segundo año de la carrera de **Ingeniería en Sistemas** de la **Universidad Americana**.

El sistema permite administrar las entidades principales relacionadas con la operación diaria de una clínica dental, enfocándose en la gestión de citas, pacientes, doctores y el control de inventario de materiales.

---

## ✨ Características Principales

### 🧑‍⚕️ Administración de Pacientes
- Registro de información personal y de contacto.  
- Validación para asegurar que la fecha de nacimiento no sea posterior a la fecha actual (`Paciente.java`).

### 👨‍⚕️ Gestión de Doctores (Odontólogos)
- Registro de nombre, código, especialidad y disponibilidad.  
- Validación del formato de disponibilidad (ej: `L-V 08:00-16:00`) para asegurar coherencia horaria (`Doctor.java`).

### 📅 Gestión de Citas
- Asignación de citas a un Paciente y un Doctor.  
- Registro de fecha y hora (`appointmentDateTime`) y estado (pendiente, confirmada, atendida, cancelada).  
- Unicidad de la cita por:
  - **Doctor + Fecha/Hora**  
  - **Paciente + Fecha/Hora**  
  (Restricciones `UK_DOCTOR_DATETIME` y `UK_PATIENT_DATETIME` en `Cita.java`)
- Normalización de la fecha/hora truncándola a la hora (redondeo hacia abajo) (`Cita.java`).  
- Manejo de materiales utilizados en la cita (`MaterialCita.java`).

### 📦 Control de Inventario de Materiales
- Registro de materiales, categoría, stock actual y stock mínimo (`Material.java`).  
- Validación para asegurar que el stock actual y el stock mínimo no sean negativos.

### 🧹 Registro de Personal de Limpieza
- Información del personal: nombre, teléfono, correo y turno (`PersonalLimpieza.java`).

---

## 🛠️ Tecnologías Utilizadas
- **OpenXava**: Framework de desarrollo rápido para aplicaciones Java.  
- **JPA / Hibernate**: Persistencia y mapeo objeto-relacional.  
- **Lombok**: Reducción de código repetitivo (getters y setters).  
- **Java 8+**

---
