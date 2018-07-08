package ca.jackzavarella.courses.pdf.parsers;

import ca.jackzavarella.courses.api.models.Course;
import ca.jackzavarella.courses.pdf.dto.ParsedCourses;
import ca.jackzavarella.courses.pdf.exceptions.PDFParseException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CoursePDFParserUoGImpl extends CoursePDFParser {

    private static final Pattern courseStringPattern =
            Pattern.compile("([A-Z]+\\*\\d{4}(?:/\\d)?) (.+)(?= ((?:(?:W|S|F|U|(?:P\\d)),?)+)) .+(?=\\[)\\[(\\d+\\.\\d+)]" +
                    "((?:\\n.*?)*?(?<=Department\\(s\\):).+)");
    private static final Pattern courseInfoPattern = Pattern.compile("(.+?)(?=((?:Offering|Equate|Prerequisite|Co-requisite|Restriction|Department)\\(s\\):.+))");
    private static final Pattern fileTitlePattern = Pattern.compile("(\\d{4}-\\d{4}) (.+?) Calendar");
    private final String INSTITUTION = "University of Guelph";
    private PDFParser parser;
    private BodyContentHandler contentHandler;
    private Metadata metadata;
    private ParseContext parseContext;

    public CoursePDFParserUoGImpl(InputStream pdfInputStream) {
        super(pdfInputStream);
        parser = new PDFParser();
        contentHandler = new BodyContentHandler(-1);
        metadata = new Metadata();
        parseContext = new ParseContext();
    }

    @Override
    public ParsedCourses extractFromPDF() throws PDFParseException {
        try {
            parser.parse(pdfInputStream, contentHandler, metadata, parseContext);
        } catch (Exception e) {
            throw new PDFParseException(e);
        }

        ParsedCourses courses = new ParsedCourses();

        Matcher m = fileTitlePattern.matcher(contentHandler.toString());
        if (!m.find()) {
            throw new PDFParseException("Unable to extract document title");
        }
        courses.setAcademicYear(m.group(1));
        courses.setProgramLevel(m.group(2));

        m = courseStringPattern.matcher(contentHandler.toString());

        while (m.find()) {
            Course course = new Course();

            String courseCode = m.group(1).trim();
            course.setCode(courseCode);

            String courseTitle = m.group(2).trim();
            course.setTitle(courseTitle);

            String semesters = m.group(3).trim();
            course.setSemesters(semesters);

            double courseWeight = Double.parseDouble(m.group(4));
            course.setWeight(courseWeight);

            String courseBody = m.group(5).trim().replace("\n", " ").replace(" {2,}", " ");
            extractCourseInfo(course, courseBody);
            course.setInstitution(this.INSTITUTION);
            course.setAcademicYear(courses.getAcademicYear());
            course.setProgramLevel(courses.getProgramLevel());
            courses.addCourse(course);
        }
        courses.setInstitution(this.INSTITUTION);
        return courses;
    }

    private void extractCourseInfo(Course course, String courseString) {
        Matcher m = courseInfoPattern.matcher(courseString);

        List<String> props = new ArrayList<>();
        int lastIndex = 0;
        while (m.find()) {
            props.add(m.group(1));
            lastIndex = m.end(1);
        }

        props.add(courseString.substring(lastIndex));
        course.setDescription(props.get(0).trim());

        for (int i = 1; i < props.size(); i++) {
            String prop = props.get(i);
            if (prop.startsWith("Offering(s): ")) {
                course.setOfferings(prop.replace("Offering(s): ", "").trim());
            } else if (prop.startsWith("Equate(s): ")) {
                course.setEquates(prop.replace("Equate(s): ", "").trim());
            } else if (prop.startsWith("Prerequisite(s): ")) {
                course.setPrerequisites(prop.replace("Prerequisite(s): ", "").trim());
            } else if (prop.startsWith("Co-requisite(s): ")) {
                course.setCorequisites(prop.replace("Co-requisite(s): ", "").trim());
            } else if (prop.startsWith("Restriction(s): ")) {
                course.setRestrictions(prop.replace("Restriction(s): ", "").trim());
            } else if (prop.startsWith("Department(s): ")) {
                course.setDepartments(prop.replace("Department(s): ", "").trim());
            } else {
                throw new Error("Could not parse prop: ' " + prop + "'");
            }
        }
    }
}
