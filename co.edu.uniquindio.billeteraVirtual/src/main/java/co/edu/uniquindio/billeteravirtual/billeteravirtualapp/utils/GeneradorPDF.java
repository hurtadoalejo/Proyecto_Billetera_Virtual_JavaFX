package co.edu.uniquindio.billeteravirtual.billeteravirtualapp.utils;

import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.CuentaDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.TransaccionDto;
import co.edu.uniquindio.billeteravirtual.billeteravirtualapp.mapping.dto.UsuarioDto;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

public class GeneradorPDF {

    private static final float[] BORDE_COMPLETO = {612f};
    private static final float[] COLUMNAS_TITULO = {340f, 100f, 100f};
    private static final float[] DOS_COLUMNAS = {300f, 300f};
    private static final float[] COLUMNAS_INGRESOS = {80f, 200f, 125f, 125f};
    private static final float[] COLUMNAS_GASTOS = {80f, 150f, 110f, 110f, 80f};
    private static final float[] COLUMNAS_SALDOS = {100f, 100f, 130f, 100f, 100f};
    private static final Border BORDE_PUNTEADO = new DashedBorder(ColorConstants.GRAY, 0.5f);
    private static final Paragraph ESPACIO = new Paragraph("\n");

    public static void exportarTransacciones(UsuarioDto usuario, String tipoReporte,
                                             LinkedList<TransaccionDto> transacciones, LinkedList<CuentaDto> listaCuentas) {
        String nombreArchivo = "reporte_Usuario.pdf";
        String rutaSalida = "src/main/java/co/edu/uniquindio/billeteravirtual/billeteravirtualapp/utils/" + nombreArchivo;

        try {
            PdfWriter writer = new PdfWriter(rutaSalida);
            PdfDocument pdf = new PdfDocument(writer);
            pdf.setDefaultPageSize(PageSize.LETTER);
            Document documentos = new Document(pdf);
            PdfFont letra = PdfFontFactory.createFont("Helvetica");
            PdfFont letraBold = PdfFontFactory.createFont("Helvetica-Bold");

            Table tablaTitulo = new Table(COLUMNAS_TITULO);
            Cell cellTitulo = new Cell().add(new Paragraph("Reporte de " + tipoReporte))
                    .setBorder(Border.NO_BORDER)
                    .setFont(letraBold)
                    .setFontSize(14)
                    .setVerticalAlignment(VerticalAlignment.TOP);
            Cell cellFecha = new Cell().add(new Paragraph("Fecha Reporte: "))
                    .setBorder(Border.NO_BORDER)
                    .setFont(letraBold)
                    .setFontSize(11)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE);
            Cell cellFechaActual = new Cell().add(new Paragraph(String.valueOf(LocalDate.now())))
                    .setBorder(Border.NO_BORDER)
                    .setFont(letra)
                    .setFontSize(11)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE);
            tablaTitulo.addCell(cellTitulo);
            tablaTitulo.addCell(cellFecha);
            tablaTitulo.addCell(cellFechaActual);
            documentos.add(tablaTitulo);

            documentos.add(ESPACIO);
            Border border = new SolidBorder(ColorConstants.GRAY, 1.5f);
            Table tableBorde = new Table(BORDE_COMPLETO);
            tableBorde.setBorder(border);
            documentos.add(tableBorde);

            documentos.add(ESPACIO);
            documentos.add(obtenerTextoColumna("Información Usuario", letraBold).setTextAlignment(TextAlignment.CENTER));
            documentos.add(ESPACIO);

            Table tablaUsuario1 = new Table(DOS_COLUMNAS);
            tablaUsuario1.addCell(obtenerTextoColumna("Nombre Completo:", letraBold));
            tablaUsuario1.addCell(obtenerTextoColumna("Cédula:", letraBold));
            documentos.add(tablaUsuario1);

            Table tablaUsuario2 = new Table(DOS_COLUMNAS);
            tablaUsuario2.addCell(obtenerTextoColumna(usuario.nombreCompleto(), letra));
            tablaUsuario2.addCell(obtenerTextoColumna(usuario.idUsuario(), letra));
            documentos.add(tablaUsuario2.setMarginBottom(12f));

            Table tablaUsuario3 = new Table(DOS_COLUMNAS);
            tablaUsuario3.addCell(obtenerTextoColumna("Correo Electrónico:", letraBold));
            tablaUsuario3.addCell(obtenerTextoColumna("Número de Teléfono:", letraBold));
            documentos.add(tablaUsuario3);

            Table tablaUsuario4 = new Table(DOS_COLUMNAS);
            tablaUsuario4.addCell(obtenerTextoColumna(usuario.correoElectronico(), letra));
            tablaUsuario4.addCell(obtenerTextoColumna(usuario.numeroTelefono(), letra));
            documentos.add(tablaUsuario4.setMarginBottom(12f));

            documentos.add(obtenerTextoColumna("Dirección:", letraBold)
                    .setTextAlignment(TextAlignment.LEFT));
            documentos.add(obtenerTextoColumna(usuario.direccion(), letra)
                    .setTextAlignment(TextAlignment.LEFT));

            Table tablaDivisoraTransacciones = new Table(BORDE_COMPLETO).setBorder(BORDE_PUNTEADO);
            documentos.add(tablaDivisoraTransacciones
                    .setMarginTop(15f)
                    .setMarginBottom(10f));

            switch (tipoReporte) {
                case "Ingresos":
                    documentos.add(obtenerTextoColumna("Depositos", letraBold)
                            .setTextAlignment(TextAlignment.LEFT));
                    generarListaDepositoRetiro(documentos, transacciones, letra, tablaDivisoraTransacciones);
                        break;
                case "Gastos":
                        generarListaGastos(documentos, transacciones, letra, letraBold, tablaDivisoraTransacciones);
                        break;
                case "Saldos":
                    generarListaSaldos(documentos, listaCuentas, letra, letraBold, tablaDivisoraTransacciones);
                    break;
            }

            documentos.close();
            File archivo = new File(rutaSalida);
            if (archivo.exists()) {
                Desktop.getDesktop().open(archivo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generarListaSaldos(Document documento, LinkedList<CuentaDto> cuentas, PdfFont letra, PdfFont letraBold, Table tablaDivisoraTransacciones) {
        Table tablaCuentas = new Table(COLUMNAS_SALDOS);
        configurarTablaPrincipal(tablaCuentas);

        tablaCuentas.addCell(new Cell().add(obtenerTextoColumna("Número Cuenta", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        tablaCuentas.addCell(new Cell().add(obtenerTextoColumna("Nombre Banco", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        tablaCuentas.addCell(new Cell().add(obtenerTextoColumna("Nombre Presupuesto", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        tablaCuentas.addCell(new Cell().add(obtenerTextoColumna("Tipo Cuenta", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        tablaCuentas.addCell(new Cell().add(obtenerTextoColumna("Saldo", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        documento.add((tablaCuentas));

        Table listaCuentas = new Table(COLUMNAS_SALDOS);
        listaCuentas.setWidth(100);
        listaCuentas.setFixedLayout();
        double totalSaldo = 0;
        for (CuentaDto cuenta : cuentas) {
            listaCuentas.addCell(obtenerTextoColumna(cuenta.numCuenta(), letra));
            listaCuentas.addCell(obtenerTextoColumna(cuenta.nombreBanco(), letra));
            listaCuentas.addCell(obtenerTextoColumna(cuenta.presupuestoAsociado(), letra));
            listaCuentas.addCell(obtenerTextoColumna(cuenta.tipoCuenta().toString(), letra));
            listaCuentas.addCell(obtenerTextoColumna(String.format("$%,.0f", cuenta.saldo()), letra));
            totalSaldo += cuenta.saldo();
        }
        listaCuentas.setBorder(Border.NO_BORDER);
        documento.add(listaCuentas);

        float[] columnasTotal = {330f, 200f};
        Table tablaTotal = obtenerDivisorMontoTotal(columnasTotal, tablaDivisoraTransacciones);
        documento.add(tablaTotal);

        float[] columnasMontoTotal = {330f, 100f, 100f};
        Table tablaMontoTotal = obtenerTablaMontoTotal(columnasMontoTotal, letra,
                totalSaldo);
        documento.add(tablaMontoTotal);
    }

    private static double generarListaDepositoRetiro(Document documento, LinkedList<TransaccionDto> transacciones,
                                             PdfFont letra, Table tablaDivisoraTransacciones) {
        Table tablaTransacciones = new Table(COLUMNAS_INGRESOS);
        tablaTransacciones.setWidth(100);
        tablaTransacciones.setFixedLayout();
        tablaTransacciones.setBackgroundColor(ColorConstants.BLACK, 0.7f);

        tablaTransacciones.addCell(new Cell().add(obtenerTextoColumna("Fecha", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        tablaTransacciones.addCell(new Cell().add(obtenerTextoColumna("Descripción", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        tablaTransacciones.addCell(new Cell().add(obtenerTextoColumna("Cuenta", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        tablaTransacciones.addCell(new Cell().add(obtenerTextoColumna("Monto", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        documento.add(new Cell().add(tablaTransacciones).setBorder(Border.NO_BORDER));

        Table listaTransacciones = new Table(COLUMNAS_INGRESOS);
        double total = 0;
        for (TransaccionDto t : transacciones) {
            if (!t.nombreCategoria().equals("Transferencia")) {
                listaTransacciones.addCell(obtenerTextoColumna(t.fecha().toString(), letra));
                listaTransacciones.addCell(obtenerTextoColumna(t.descripcion(), letra));
                listaTransacciones.addCell(obtenerTextoColumna(t.numCuentaOrigen(), letra));
                listaTransacciones.addCell(obtenerTextoColumna(String.format("$%,.0f", t.monto()), letra));
                total += t.monto();
            }
        }
        documento.add(new Cell().add(listaTransacciones).setBorder(Border.NO_BORDER));

        float[] columnasTotal = {280f, 250f};
        Table tablaTotal = obtenerDivisorMontoTotal(columnasTotal, tablaDivisoraTransacciones);
        documento.add(tablaTotal);

        float[] columnasMontoTotal = {280f, 125f, 125f};
        Table tablaMontoTotal = obtenerTablaMontoTotal(columnasMontoTotal, letra, total);
        documento.add(tablaMontoTotal);
        return total;
    }

    private static void generarListaGastos(Document documento, LinkedList<TransaccionDto> transacciones,
                                           PdfFont letra, PdfFont letraBold, Table tablaDivisoraTransacciones) {
        documento.add(obtenerTextoColumna("Retiros", letraBold)
                .setTextAlignment(TextAlignment.LEFT));
        double saldoGastos = generarListaDepositoRetiro(documento, transacciones, letra, tablaDivisoraTransacciones);

        documento.add(obtenerTextoColumna("Transferencias", letraBold)
                .setTextAlignment(TextAlignment.LEFT));
        saldoGastos += generarListaTransferencias(documento, transacciones, letra, tablaDivisoraTransacciones);

        documento.add(ESPACIO);
        documento.add(obtenerTextoColumna("Total Gastos", letra)
                .setTextAlignment(TextAlignment.CENTER));
        documento.add(obtenerTextoColumna(String.format("$%,.0f", saldoGastos), letra)
                .setTextAlignment(TextAlignment.CENTER));

    }

    private static double generarListaTransferencias(Document documento, LinkedList<TransaccionDto> transacciones,
                                                   PdfFont letra, Table tablaDivisoraTransacciones) {
        Table tablaTransacciones = new Table(COLUMNAS_GASTOS);
        configurarTablaPrincipal(tablaTransacciones);

        tablaTransacciones.addCell(new Cell().add(obtenerTextoColumna("Fecha", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        tablaTransacciones.addCell(new Cell().add(obtenerTextoColumna("Descripción", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        tablaTransacciones.addCell(new Cell().add(obtenerTextoColumna("Cuenta Origen", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        tablaTransacciones.addCell(new Cell().add(obtenerTextoColumna("Cuenta Destino", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        tablaTransacciones.addCell(new Cell().add(obtenerTextoColumna("Monto", letra))
                .setFontColor(ColorConstants.WHITE)
                .setBorder(Border.NO_BORDER));
        documento.add(new Cell().add(tablaTransacciones).setBorder(Border.NO_BORDER));

        Table listaTransacciones = new Table(COLUMNAS_GASTOS);
        listaTransacciones.setWidth(100);
        listaTransacciones.setFixedLayout();
        double totalTransferencia = 0;
        for (TransaccionDto t : transacciones) {
            if (t.nombreCategoria().equals("Transferencia")) {
                listaTransacciones.addCell(obtenerTextoColumna(t.fecha().toString(), letra));
                listaTransacciones.addCell(obtenerTextoColumna(t.descripcion(), letra));
                listaTransacciones.addCell(obtenerTextoColumna(t.numCuentaOrigen(), letra));
                listaTransacciones.addCell(obtenerTextoColumna(t.numCuentaDestino(), letra));
                listaTransacciones.addCell(obtenerTextoColumna(String.format("$%,.0f", t.monto()), letra));
                totalTransferencia += t.monto();
            }
        }
        listaTransacciones.setBorder(Border.NO_BORDER);
        documento.add(listaTransacciones);

        float[] columnasTotal = {340f, 190f};
        Table tablaTotal = obtenerDivisorMontoTotal(columnasTotal, tablaDivisoraTransacciones);
        documento.add(tablaTotal);

        float[] columnasMontoTotal = {340f, 110f, 80f};
        Table tablaMontoTotal = obtenerTablaMontoTotal(columnasMontoTotal, letra,
                totalTransferencia);
        documento.add(tablaMontoTotal);
        return totalTransferencia;
    }

    private static void configurarTablaPrincipal(Table tabla) {
        tabla.setWidth(100);
        tabla.setFixedLayout();
        tabla.setBackgroundColor(ColorConstants.BLACK, 0.7f);
    }

    private static Table obtenerDivisorMontoTotal(float[] columnasTotal, Table tablaDivisora) {
        Table tablaTotal = new Table(columnasTotal).setBorder(Border.NO_BORDER);
        tablaTotal.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tablaTotal.addCell(new Cell().add(tablaDivisora).setBorder(Border.NO_BORDER));
        return tablaTotal;
    }

    private static Table obtenerTablaMontoTotal(float[] columnasTotal, PdfFont letra, double total) {
        Table tablaMontoTotal = new Table(columnasTotal);
        tablaMontoTotal.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        tablaMontoTotal.addCell(obtenerTextoColumna("Total", letra));
        tablaMontoTotal.addCell(obtenerTextoColumna("$" + String.format("%,.0f", total), letra));
        return tablaMontoTotal;
    }

    private static Cell obtenerTextoColumna(String nombreColumna, PdfFont letra) {
        return new Cell().add(new Paragraph(nombreColumna))
                .setBorder(Border.NO_BORDER)
                .setFontSize(11f)
                .setFont(letra)
                .setTextAlignment(TextAlignment.LEFT);
    }
}