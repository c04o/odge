package acciones;

import java.util.Map;

public class PrintDoctorReportAction extends PrintFichaBaseAction {

    @Override
    protected String getTituloReporte() {
        return "Ficha del Odontólogo";
    }

    @Override
    protected String getCodigoReporte() {
        String codigo = getView().getValueString("codigo");
        return "DOC-" + (codigo == null ? "" : codigo);
    }

    @Override
    protected String getJRXML() {
        // La plantilla individual del doctor
        return "DoctorFicha.jrxml";
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        Map params = super.getParameters();

        // === PARÁMETROS QUE EXISTEN EN DoctorFicha.jrxml ===
        params.put("doctorNombre", getView().getValueString("nombre"));
        params.put("doctorCodigo", getView().getValueString("codigo"));
        params.put("doctorEspecialidad", getView().getValueString("especialidad"));
        params.put("doctorDisponibilidad", getView().getValueString("disponibilidad"));
        params.put("doctorNotas", getView().getValueString("notas"));

        return params;
    }
}
