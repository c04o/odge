package acciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;

import ni.edu.uam.odge.modelo.PersonalLimpieza;

public class PrintPersonalLimpiezaListAction extends JasperReportBaseAction {

    private List<PersonalLimpieza> personal;

    @Override
    protected JRDataSource getDataSource() throws Exception {
        return new JRBeanCollectionDataSource(getPersonal());
    }

    @Override
    protected String getJRXML() throws Exception {
        // Nombre del reporte JRXML de lista de personal de limpieza
        return "PersonalLimpiezaList.jrxml";
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        Map params = new HashMap();
        params.put("tituloReporte", "Listado de Personal de Limpieza");
        params.put("fechaEmision", new java.util.Date());
        return params;
    }

    private List<PersonalLimpieza> getPersonal() {
        if (personal == null) {
            personal = XPersistence.getManager()
                    .createQuery("from PersonalLimpieza", PersonalLimpieza.class)
                    .getResultList();
        }
        return personal;
    }
}
