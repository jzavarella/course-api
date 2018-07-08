package ca.jackzavarella.courses.api.dto;

/**
 * Created by Jack Zavarella on 7/4/2018. :)
 */
public class InstitutionMetaData {
    private String programLevel, academicYear;
    private int courses;

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

    public int getCourses() {
        return courses;
    }

    public void setCourses(int courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InstitutionMetaData)) {
            return false;
        }
        InstitutionMetaData metaData = (InstitutionMetaData) o;
        return this.getAcademicYear().equalsIgnoreCase(metaData.getAcademicYear()) && this.getProgramLevel().equalsIgnoreCase(metaData.getProgramLevel());
    }
}
