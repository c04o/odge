package acciones;

import ni.edu.uam.odge.modelo.Material;
import org.openxava.actions.JasperReportBaseAction;
import org.openxava.jpa.XPersistence;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintMaterialListAction extends JasperReportBaseAction {

    private List<Material> materiales;

    @Override
    protected JRDataSource getDataSource() throws Exception {
        return new JRBeanCollectionDataSource(getMateriales());
    }

    @Override
    protected String getJRXML() throws Exception {
        return "MaterialList.jrxml"; // ajusta al nombre real de tu reporte
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        Map params = new HashMap();
        params.put("tituloReporte", "Listado de Materiales");
        params.put("fechaEmision", new Date());
        return params;
    }

    private List<Material> getMateriales() {
        if (materiales == null) {
            materiales = XPersistence.getManager()
                    .createQuery("from Material", Material.class)
                    .getResultList();
        }
        return materiales;
    }
}
