package acciones;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;

import ni.edu.uam.odge.modelo.Cita;

public class PrintCitasListAction extends JasperReportBaseAction {

    private List<Cita> citas;

    @Override
    protected JRDataSource getDataSource() throws Exception {
        return new JRBeanCollectionDataSource(getCitas());
    }

    @Override
    protected String getJRXML() throws Exception {
        // Nombre exacto del archivo en /resources/reports
        return "CitasList.jrxml";
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        Map params = new HashMap();
        params.put("tituloReporte", "Listado de Citas");
        params.put("fechaEmision", new Date());
        return params;
    }

    private List<Cita> getCitas() {
        if (citas == null) {
            citas = XPersistence.getManager()
                    .createQuery("from Cita", Cita.class)
                    .getResultList();
        }
        return citas;
    }
}
