package ca.jackzavarella.courses.api.responses;

import ca.jackzavarella.courses.api.models.Course;

/**
 * Created by Jack Zavarella on 7/5/2018. :)
 */
public class SingleCourseResponse extends CourseResponse {

    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
