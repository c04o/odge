package acciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;

import ni.edu.uam.odge.modelo.Paciente;


import ni.edu.uam.odge.modelo.Paciente;

public class PrintPacientesListAction extends JasperReportBaseAction {

    private List<Paciente> pacientes;

    @Override
    protected JRDataSource getDataSource() throws Exception {
        return new JRBeanCollectionDataSource(getPacientes());
    }

    @Override
    protected String getJRXML() throws Exception {
        // El nombre del archivo que acabas de crear
        return "PacientesList.jrxml";
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        Map params = new HashMap();
        params.put("tituloReporte", "Listado de Pacientes");
        params.put("fechaEmision", new java.util.Date());
        return params;
    }

    private List<Paciente> getPacientes() {
        if (pacientes == null) {
            pacientes = XPersistence.getManager()
                    .createQuery("from Paciente", Paciente.class)
                    .getResultList();
        }
        return pacientes;
    }
}
