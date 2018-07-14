package ca.jackzavarella.courses.pdf.controllers;

import ca.jackzavarella.courses.api.dto.InstitutionMetaData;
import ca.jackzavarella.courses.pdf.dto.ParsedCourses;
import ca.jackzavarella.courses.api.exceptions.InstitutionNotSupportedException;
import ca.jackzavarella.courses.pdf.exceptions.PDFParseException;
import ca.jackzavarella.courses.pdf.filters.ParseFilter;
import ca.jackzavarella.courses.pdf.services.CoursePDFParserService;
import ca.jackzavarella.courses.api.services.DatabaseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by Jack Zavarella on 7/4/2018. :)
 */
@RestController
public class ParserController {

    private final CoursePDFParserService coursePDFParserService;
    private final DatabaseService databaseService;

    @Value("${courses.allow-parsing:false}")
    private boolean allowParsing;

    public ParserController(CoursePDFParserService coursePDFParserService, DatabaseService databaseService) {
        this.coursePDFParserService = coursePDFParserService;
        this.databaseService = databaseService;
    }

    @PostMapping("/parse")
    public InstitutionMetaData parseCoursesEndpoint(@RequestParam String institution, @RequestParam MultipartFile pdfFile) throws PDFParseException, InstitutionNotSupportedException {
        try {
            ParsedCourses courses = this.coursePDFParserService.parse(institution, pdfFile.getInputStream());
            this.databaseService.saveParsedCourses(courses);
            return courses.getMetaData();
        } catch (IOException e) {
            throw new PDFParseException(e);
        }
    }

    // This filter prevents new course calendars from being added. Temporary fix until I add authentication
    @Bean
    public FilterRegistrationBean<ParseFilter> parseFilter() {
        FilterRegistrationBean<ParseFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ParseFilter(this.allowParsing));
        registrationBean.addUrlPatterns("/parse/*");

        return registrationBean;
    }
}
