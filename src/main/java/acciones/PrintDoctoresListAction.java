package acciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;

import ni.edu.uam.odge.modelo.Doctor;

public class PrintDoctoresListAction extends JasperReportBaseAction {

    private List<Doctor> doctores;

    @Override
    protected JRDataSource getDataSource() throws Exception {
        return new JRBeanCollectionDataSource(getDoctores());
    }

    @Override
    protected String getJRXML() throws Exception {
        // Nombre del archivo JRXML de la lista de doctores
        return "DoctoresList.jrxml";
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        Map params = new HashMap();
        params.put("tituloReporte", "Listado de Doctores");
        params.put("fechaEmision", new java.util.Date());
        return params;
    }

    private List<Doctor> getDoctores() {
        if (doctores == null) {
            doctores = XPersistence.getManager()
                    .createQuery("from Doctor", Doctor.class)
                    .getResultList();
        }
        return doctores;
    }
}

