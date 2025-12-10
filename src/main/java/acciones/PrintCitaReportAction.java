package acciones;

import org.openxava.view.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PrintCitaReportAction extends PrintFichaBaseAction {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Override
    protected String getTituloReporte() {
        return "Ficha Cita";
    }

    @Override
    protected String getCodigoReporte() {
        String codigo = getView().getValueString("oid");
        return "CIT-" + (codigo == null ? "" : codigo);
    }

    @Override
    protected String getJRXML() {
        return "CitaFicha.jrxml";
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {

        Map params = super.getParameters();
        View view = getView();

        String paciente = view.getValueString("paciente.nombre");
        String doctor   = view.getValueString("doctor.nombre");
        String estado   = view.getValueString("estado");
        String obs      = view.getValueString("observaciones");

        LocalDateTime dt = (LocalDateTime) view.getValue("appointmentDateTime");
        String fecha = dt == null ? "" : dt.format(FORMATTER);

        String codigoCita = getCodigoReporte();

        params.put("citaPaciente",      paciente == null ? "" : paciente);
        params.put("citaDoctor",        doctor == null ? "" : doctor);
        params.put("citaCodigo",        codigoCita);
        params.put("citaEstado",        estado == null ? "" : estado);
        params.put("citaFecha",         fecha);
        params.put("citaObservaciones", obs == null ? "" : obs);

        return params;
    }
}

