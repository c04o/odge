package acciones;

import java.util.Map;

public class PrintMaterialReportAction extends PrintFichaBaseAction {

    @Override
    protected String getTituloReporte() {
        return "Ficha Material";
    }

    @Override
    protected String getCodigoReporte() {
        // Ajusta según el identificador de tu Material (por ejemplo "codigo" o "id")
        String codigo = getView().getValueString("codigo");
        return "MAT-" + (codigo == null ? "" : codigo);
    }

    @Override
    @SuppressWarnings("rawtypes")
    protected Map getParameters() throws Exception {
        Map params = super.getParameters();

        // Aquí mapeamos los datos del material a los parámetros genéricos
        // Cambia "nombre", "descripcion", etc. por los nombres reales de tu entidad Material

        params.put("pacienteNombre", getView().getValueString("nombre"));              // Nombre del material
        params.put("pacienteIdentificacion", getView().getValueString("codigo"));      // Código o clave del material
        params.put("pacienteFechaNacimiento", getView().getValueString("tipo"));       // Por ejemplo tipo / categoría
        params.put("pacienteTelefono", getView().getValueString("cantidad"));          // Cantidad (como texto)
        params.put("pacienteCorreo", getView().getValueString("ubicacion"));           // Ubicación o proveedor
        params.put("pacienteNotas", getView().getValueString("notas"));                // Notas del material

        return params;
    }
}
