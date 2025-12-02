# 🦷 ODGE: Sistema de Gestión Odontológica  
**Odontological Management System**

ODGE es una aplicación web desarrollada con **OpenXava** para gestionar de forma integral clínicas y consultorios odontológicos. El sistema permite administrar pacientes, doctores, citas, inventario, personal de limpieza, historial clínico y estadísticas en un entorno centralizado y fácil de usar.

Proyecto elaborado por estudiantes de segundo año de **Ingeniería en Sistemas** de la **Universidad Americana**.

---

## ✨ Características Principales

### 🧑‍⚕️ Administración de Pacientes
- Registro de datos personales y de contacto.  
- Validación para impedir fechas de nacimiento posteriores a la fecha actual.  
- Acceso al historial de citas del paciente.  
- **Historial clínico detallado**, incluyendo diagnósticos, procedimientos, observaciones y materiales utilizados.

---

### 👨‍⚕️ Gestión de Doctores (Odontólogos)
- Registro de nombre, código profesional único y especialidad.  
- Configuración de disponibilidad laboral (ej. `L-V 08:00-16:00`).  
- Asociación directa con las citas que atienden.  
- Vista de desempeño dentro del dashboard estadístico.

---

### 📅 Gestión de Citas Odontológicas
- Asignación de citas a **Paciente + Doctor + Fecha/Hora**.  
- Estados: *pendiente, confirmada, atendida, cancelada*.  
- Restricciones de unicidad:
  - **Doctor + Fecha/Hora**
  - **Paciente + Fecha/Hora**
- Prevención automática de:
  - citas duplicadas,  
  - horarios fuera de disponibilidad,  
  - citas en fechas pasadas,  
  - traslapes en la agenda.  
- Registro y consumo de materiales por cita mediante la entidad `MaterialCita`.  

---

### 📦 Control de Inventario de Materiales
- Registro de materiales, categoría, proveedor, stock actual y mínimo.  
- Validación que impide asignar valores negativos.  
- Alertas por bajo inventario.  
- Descuento automático del stock según los materiales utilizados en las citas.

---

### 🧹 Gestión de Personal de Limpieza
- Registro de nombre, turno, teléfono y correo.  
- Administración básica del personal operativo.

---

## 📊 Dashboard Estadístico
El sistema incluye un panel de estadísticas para visualización rápida:

- **Citas del día**: citas agendadas, completadas y canceladas.  
- **Consumo de materiales**: materiales más utilizados por fecha o por especialidad.  
- **Desempeño del odontólogo**: citas atendidas, tasa de puntualidad, carga de trabajo.

---

## 🔐 Sistema de Roles
ODGE incorpora control de acceso según permisos:

- **Administrador**: acceso total al sistema.  
- **Recepcionista**: gestión de citas, pacientes y agenda.  
- **Odontólogo**: acceso a sus citas, historial clínico y registro de materiales por cita.

---

## 🛠️ Tecnologías Utilizadas
- **OpenXava**  
- **Java 8+**  
- **JPA / Hibernate**  
- **Lombok**  
- **MySQL / PostgreSQL**  

---
