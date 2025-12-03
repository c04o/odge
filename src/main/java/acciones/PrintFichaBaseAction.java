package acciones;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import org.openxava.actions.JasperReportBaseAction;

public abstract class PrintFichaBaseAction extends JasperReportBaseAction {

    @Override
    protected JRDataSource getDataSource() throws Exception {
        return new JREmptyDataSource();
    }

    @Override
    protected String getJRXML() throws Exception {
        return "OdgeTemplate.jrxml";
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        Map params = new HashMap();

        // Comunes a todos los reportes
        params.put("tituloReporte", getTituloReporte());
        params.put("fechaEmision", new java.util.Date());
        params.put("codigoReporte", getCodigoReporte());

        return params;
    }

    // Cada reporte define su título
    protected abstract String getTituloReporte();

    // Por defecto no manda código, cada uno puede sobreescribirlo
    protected String getCodigoReporte() {
        return "";
    }
}
