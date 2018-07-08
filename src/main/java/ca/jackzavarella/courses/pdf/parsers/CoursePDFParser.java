package ca.jackzavarella.courses.pdf.parsers;

import ca.jackzavarella.courses.pdf.dto.ParsedCourses;
import ca.jackzavarella.courses.pdf.exceptions.PDFParseException;

import java.io.InputStream;

public abstract class CoursePDFParser {
    protected InputStream pdfInputStream;

    public CoursePDFParser(InputStream pdfInputStream) {
        this.pdfInputStream = pdfInputStream;
    }

    public abstract ParsedCourses extractFromPDF() throws PDFParseException;

    public InputStream getPdfInputStream() {
        return pdfInputStream;
    }

    public void setPdfInputStream(InputStream pdfInputStream) {
        this.pdfInputStream = pdfInputStream;
    }
}
