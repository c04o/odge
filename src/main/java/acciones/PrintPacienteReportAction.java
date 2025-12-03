package acciones;

import java.util.Map;

public class PrintPacienteReportAction extends PrintFichaBaseAction {

    @Override
    protected String getTituloReporte() {
        return "Ficha Paciente";
    }

    @Override
    protected String getCodigoReporte() {
        // Puedes ajustar el formato si quieres
        String identificacion = getView().getValueString("identificacion");
        return "PAC-" + identificacion;
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        // Primero obtenemos los parámetros comunes (título, fecha, código)
        Map params = super.getParameters();

        // Ahora los específicos del paciente
        params.put("pacienteNombre", getView().getValueString("nombre"));
        params.put("pacienteIdentificacion", getView().getValueString("identificacion"));
        params.put("pacienteFechaNacimiento", getView().getValueString("fechaNacimiento"));
        params.put("pacienteTelefono", getView().getValueString("telefono"));
        params.put("pacienteCorreo", getView().getValueString("correoElectronico"));
        params.put("pacienteNotas", getView().getValueString("notas"));

        return params;
    }
}

