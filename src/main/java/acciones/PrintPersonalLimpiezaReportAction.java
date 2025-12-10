package acciones;

import java.util.Map;

public class PrintPersonalLimpiezaReportAction extends PrintFichaBaseAction {

    @Override
    protected String getTituloReporte() {
        return "Ficha Personal de limpieza";
    }

    @Override
    protected String getCodigoReporte() {
        String oid = getView().getValueString("oid");   // ID real de la entidad
        return "LIM-" + (oid == null ? "" : oid);
    }

    // AQUÍ indicamos el jrxml específico para esta ficha
    @Override
    protected String getJRXML() throws Exception {
        return "PersonalLimpiezaFicha.jrxml";
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {

        Map params = super.getParameters();

        String nombre         = getView().getValueString("nombre");
        String identificacion = getView().getValueString("identificacion");
        String telefono       = getView().getValueString("telefono");
        String correo         = getView().getValueString("correo");
        String turno          = getView().getValueString("turno");
        String notas          = getView().getValueString("notas");

        params.put("personalNombre",         nombre == null ? "" : nombre);
        params.put("personalIdentificacion", identificacion == null ? "" : identificacion);
        params.put("personalTelefono",       telefono == null ? "" : telefono);
        params.put("personalCorreo",         correo == null ? "" : correo);
        params.put("personalTurno",          turno == null ? "" : turno);
        params.put("personalNotas",          notas == null ? "" : notas);

        return params;
    }
}
