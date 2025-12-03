package acciones;

import java.util.Map;

public class PrintDoctorReportAction extends PrintFichaBaseAction {

    @Override
    protected String getTituloReporte() {
        return "Ficha Doctor";
    }

    @Override
    protected String getCodigoReporte() {
        String identificacion = getView().getValueString("identificacion");
        return "DOC-" + (identificacion == null ? "" : identificacion);
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        Map params = super.getParameters();

        // Ajusta los nombres de propiedad si en Doctor.java se llaman distinto
        params.put("pacienteNombre", getView().getValueString("nombre"));
        params.put("pacienteIdentificacion", getView().getValueString("identificacion"));
        params.put("pacienteFechaNacimiento", getView().getValueString("fechaNacimiento"));
        params.put("pacienteTelefono", getView().getValueString("telefono"));
        params.put("pacienteCorreo", getView().getValueString("correoElectronico"));
        params.put("pacienteNotas", getView().getValueString("notas"));

        return params;
    }
}
