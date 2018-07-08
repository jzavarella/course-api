package ca.jackzavarella.courses.api.responses;

import ca.jackzavarella.courses.api.models.Course;

import java.util.List;

/**
 * Created by Jack Zavarella on 7/5/2018. :)
 */
public class MultipleCourseResponse extends CourseResponse {
    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
