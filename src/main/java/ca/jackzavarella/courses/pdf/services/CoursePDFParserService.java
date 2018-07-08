package ca.jackzavarella.courses.pdf.services;

import ca.jackzavarella.courses.pdf.dto.ParsedCourses;
import ca.jackzavarella.courses.api.exceptions.InstitutionNotSupportedException;
import ca.jackzavarella.courses.pdf.exceptions.PDFParseException;
import ca.jackzavarella.courses.pdf.parsers.CoursePDFParser;
import ca.jackzavarella.courses.pdf.parsers.CoursePDFParserUoGImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * Created by Jack Zavarella on 7/4/2018. :)
 */
@Service
public class CoursePDFParserService {

    public ParsedCourses parse(String institution, InputStream pdfStream) throws InstitutionNotSupportedException, PDFParseException {
        CoursePDFParser parser;
        switch (institution) {
            case "University of Guelph":
                parser = new CoursePDFParserUoGImpl(pdfStream);
                break;
            default:
                throw new InstitutionNotSupportedException(institution);
        }

        return parser.extractFromPDF();
    }

}
