package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class GeneradorPDF {

    public static void exportarTransacciones(String nombreUsuario, String tipoTransaccion, LinkedList<TransaccionDto> transacciones) {
        String nombreArchivo = "reporte" + tipoTransaccion + ".pdf";
        String rutaSalida = "src/main/java/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/utils/" + nombreArchivo;

        try {
            PdfWriter writer = new PdfWriter(rutaSalida);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Cargar la fuente para negrita en iText 7
            PdfFont boldFont = PdfFontFactory.createFont("Helvetica-Bold");

            // Título del reporte
            document.add(new Paragraph("Transacciones - " + tipoTransaccion).setFont(boldFont).setFontSize(16));

            // Usuario
            document.add(new Paragraph("Usuario: " + nombreUsuario).setFontSize(12));

            // Tabla
            float[] columnWidths = {100, 200, 100, 100};  // Ancho de las columnas
            Table table = new Table(columnWidths);

            table.setWidth(100);
            table.setFixedLayout();

            // Encabezado de la tabla con negrita
            table.addCell(new Cell().add(new Paragraph("Fecha").setFont(boldFont)));
            table.addCell(new Cell().add(new Paragraph("Descripción").setFont(boldFont)));
            table.addCell(new Cell().add(new Paragraph("Monto").setFont(boldFont)));
            table.addCell(new Cell().add(new Paragraph("Cuenta Origen").setFont(boldFont)));

            double total = 0;

            // Recorrer las transacciones (con LinkedList)
            for (TransaccionDto t : transacciones) {
                table.addCell(t.fecha().toString());
                table.addCell(t.descripcion());
                table.addCell(String.format("$%.2f", t.monto()));
                table.addCell(t.numCuentaOrigen());
                total += t.monto();
            }

            document.add(table);

            // Total
            document.add(new Paragraph("Total: $" + String.format("%.2f", total)).setFont(boldFont).setFontSize(12));

            // Cerrar el documento
            document.close();

            // Abrir el archivo generado
            File archivo = new File(rutaSalida);
            if (archivo.exists()) {
                Desktop.getDesktop().open(archivo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}