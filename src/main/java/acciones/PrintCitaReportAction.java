package acciones;

import java.util.Map;

public class PrintCitaReportAction extends PrintFichaBaseAction {

    @Override
    protected String getTituloReporte() {
        return "Ficha Cita";
    }

    @Override
    protected String getCodigoReporte() {
        // Ejemplo: CIT-<id>
        String id = getView().getValueString("oid"); // o "id" según tu entidad Cita
        return "CIT-" + (id == null ? "" : id);
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        Map params = super.getParameters();

        // Ajusta los nombres de propiedad según tu Cita.java:
        // fecha, hora, paciente, doctor, notas, etc.

        params.put("pacienteNombre", getView().getValueString("paciente"));          // por ejemplo paciente seleccionado
        params.put("pacienteIdentificacion", getView().getValueString("doctor"));    // doctor asignado
        params.put("pacienteFechaNacimiento", getView().getValueString("fecha"));    // fecha de la cita
        params.put("pacienteTelefono", getView().getValueString("hora"));            // hora
        params.put("pacienteCorreo", getView().getValueString("estado"));            // estado de la cita
        params.put("pacienteNotas", getView().getValueString("notas"));              // notas de la cita

        return params;
    }
}
