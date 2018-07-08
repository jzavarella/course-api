package ca.jackzavarella.courses.pdf.parsers;

import ca.jackzavarella.courses.pdf.dto.ParsedCourses;
import ca.jackzavarella.courses.pdf.exceptions.PDFParseException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Jack Zavarella on 7/6/2018. :)
 */
public class CoursePDFParserUoGImplTests {

    private ParsedCourses parsedCourses;

    public CoursePDFParserUoGImplTests() throws PDFParseException {
        CoursePDFParser parser = new CoursePDFParserUoGImpl(getClass().getClassLoader().getResourceAsStream("18-19GuelphCourseCalendar.pdf"));
        this.parsedCourses = parser.extractFromPDF();
    }

    @Test
    public void parseSuccessfulTest() {
        Assert.assertNotNull(this.parsedCourses);
    }

    @Test
    public void metaInfoTest() {
        Assert.assertEquals("2018-2019", parsedCourses.getMetaData().getAcademicYear());
        Assert.assertEquals("University of Guelph", parsedCourses.getInstitution());
    }

    @Test
    public void courseTest() {
        Assert.assertEquals(parsedCourses.getCourses().size(), 1762);
    }
}
