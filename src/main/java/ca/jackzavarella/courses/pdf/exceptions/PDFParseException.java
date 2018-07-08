package ca.jackzavarella.courses.pdf.exceptions;

/**
 * Created by Jack Zavarella on 7/4/2018. :)
 */
public class PDFParseException extends Exception {

    public PDFParseException(String errorMessage) {
        super(errorMessage);
    }

    public PDFParseException(Exception e) {
        super("There was an issue parsing the pdf file", e);
    }
}
