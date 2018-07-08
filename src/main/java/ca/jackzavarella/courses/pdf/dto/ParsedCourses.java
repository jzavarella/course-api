package ca.jackzavarella.courses.pdf.dto;

import ca.jackzavarella.courses.api.models.Course;
import ca.jackzavarella.courses.api.dto.InstitutionMetaData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jack Zavarella on 7/4/2018. :)
 */
public class ParsedCourses implements Iterable<Course> {
    private List<Course> courses = new ArrayList<>();
    private String institution, programLevel, academicYear;

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getProgramLevel() {
        return programLevel;
    }

    public void setProgramLevel(String programLevel) {
        this.programLevel = programLevel;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    @Override
    public Iterator<Course> iterator() {
        return this.courses.iterator();
    }

    public int size() {
        return this.courses.size();
    }

    public InstitutionMetaData getMetaData() {
        InstitutionMetaData metaData = new InstitutionMetaData();
        metaData.setAcademicYear(this.academicYear);
        metaData.setProgramLevel(this.programLevel);
        metaData.setCourses(this.courses.size());
        return metaData;
    }
}
