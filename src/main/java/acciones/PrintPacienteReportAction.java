package acciones;

import java.util.Map;

public class PrintPacienteReportAction extends PrintFichaBaseAction {

    @Override
    protected String getTituloReporte() {
        return "Ficha Paciente";
    }

    @Override
    protected String getCodigoReporte() {
        // Código del reporte basado en la identificación del paciente
        String identificacion = getView().getValueString("identificacion");
        return "PAC-" + identificacion;
    }

    // IMPORTANTE: aquí indicamos explícitamente la plantilla OdgeTemplate.jrxml
    @Override
    protected String getJRXML() {
        // Debe coincidir exactamente con el nombre del archivo en resources/reports
        return "OdgeTemplate.jrxml";
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        // Primero obtenemos los parámetros comunes (tituloReporte, fechaEmision, codigoReporte)
        Map params = super.getParameters();

        // Ahora los específicos del paciente: deben coincidir con los parámetros del OdgeTemplate
        params.put("pacienteNombre",          getView().getValueString("nombre"));
        params.put("pacienteIdentificacion",  getView().getValueString("identificacion"));
        params.put("pacienteFechaNacimiento", getView().getValueString("fechaNacimiento"));
        params.put("pacienteTelefono",        getView().getValueString("telefono"));

        // CORRECCIÓN: Se usa "email" que es la propiedad definida en la entidad Paciente.
        params.put("pacienteCorreo",          getView().getValueString("email"));
        params.put("pacienteNotas",           getView().getValueString("notas"));

        return params;
    }
}

