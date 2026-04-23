
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private Rectangle sideBarBackground;
    private Color color = Color.BLUE;
    private GraphicsGroup sidebar;
    private double width;
    private List<Course> listOfCourses = new ArrayList<>();
    private SemesterManager semesterManager;
    private Course selectedCourse;

    public CourseManager(CanvasWindow canvas, SemesterManager semesterManager) {
        this.semesterManager = semesterManager;
        width = canvas.getWidth() * 0.25;
        sideBarBackground = new Rectangle(0, 0, width, canvas.getHeight());
        sideBarBackground.setFillColor(color);
        sidebar = new GraphicsGroup();
        sidebar.add(sideBarBackground);
        Course course = new Course("test", 100, 100, sidebar, canvas);
        listOfCourses.add(course);
        canvas.add(sidebar);

    }

    public double getWidth() {
        return width;
    }

    public void drag(CanvasWindow canvas) {
        canvas.onDrag(event -> {
            for (Course course : listOfCourses) {
                if (course.getHoverStatus()) {
                    course.setDragging(true);
                    selectedCourse = course;
                    Point mousePos = event.getPosition();
                    course.setCenter(mousePos);

                }
            }

        });
        canvas.onMouseUp(event -> {
            for (Course course : listOfCourses) {
                if (course.isDragging()) {
                    course.setDragging(false);

                    if (semesterManager.courseOverlaps(course)) {
                        semesterManager.putCourseInSemester(course);
                    } else {
                        semesterManager.remove(course);
                        course.setCenter(sidebar.getCenter());

                    }
                }
                selectedCourse = null;
            }
        });


    }

    public Course getSelectedCourse() {
        if (selectedCourse != null)
            return selectedCourse;
        else {
            return null;
        }


    }

}
