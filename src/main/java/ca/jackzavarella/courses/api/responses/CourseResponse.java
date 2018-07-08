package ca.jackzavarella.courses.api.responses;

/**
 * Created by Jack Zavarella on 7/5/2018. :)
 */
public abstract class CourseResponse {
    protected String institution, academicYear, programLevel;

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getProgramLevel() {
        return programLevel;
    }

    public void setProgramLevel(String programLevel) {
        this.programLevel = programLevel;
    }
}
