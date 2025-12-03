package acciones;

import java.util.Map;

public class PrintPersonalLimpiezaReportAction extends PrintFichaBaseAction {

    @Override
    protected String getTituloReporte() {
        return "Ficha Personal de limpieza";
    }

    @Override
    protected String getCodigoReporte() {
        String identificacion = getView().getValueString("identificacion");
        return "LIM-" + (identificacion == null ? "" : identificacion);
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        Map params = super.getParameters();

        // Ajusta si las propiedades se llaman diferente en PersonalLimpieza.java
        params.put("pacienteNombre", getView().getValueString("nombre"));
        params.put("pacienteIdentificacion", getView().getValueString("identificacion"));
        params.put("pacienteFechaNacimiento", getView().getValueString("fechaNacimiento"));
        params.put("pacienteTelefono", getView().getValueString("telefono"));
        params.put("pacienteCorreo", getView().getValueString("correoElectronico"));
        params.put("pacienteNotas", getView().getValueString("notas"));

        return params;
    }
}
