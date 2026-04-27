
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private Rectangle sideBar;
    private double width;
    private List<Course> listOfCourses = new ArrayList<>();
    private SemesterManager semesterManager;
    private Course selectedCourse;
    private List<String> courseRequirements = List.of("Social Science", "Social Science",
    "Natural Sciences and Mathematics", "Humanities/Fine Arts", "Humanities/Fine Arts", "Humanities/Fine Arts",
    "Internationalism","US ID", "Q3", "WA", "WA/WP/WC","WA/WP/WC", "Language", "Language", "Language", "Language");

    public CourseManager(CanvasWindow canvas, SemesterManager semesterManager) {
        this.semesterManager = semesterManager;
        sideBarSetup(canvas);
        double count = 5.5;
        for(String c : courseRequirements){
            Course course = new Course(c, sideBar.getWidth(), 30*count, sideBar, canvas);
            count++;
            listOfCourses.add(course);
        }

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

                    if (semesterManager.courseOverlaps(course)) { //adding if overlapping
                        semesterManager.putCourseInSemester(course);
                    } else if (course.isInBounds(0,0,width, canvas.getHeight()) || !semesterManager.courseOverlaps(course)) { //if in bounds but not overlapping, send to og
                      semesterManager.remove(course); 
                        course.returnToStartPos();
    
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

    private void sideBarSetup(CanvasWindow canvas){
        width = canvas.getWidth() * 0.25;
        sideBar = new Rectangle(0, 0, width, canvas.getHeight());
        sideBar.setFillColor(Colors.COURSES_PANEL);
        canvas.add(sideBar);

        GraphicsText sideBarTitle = new GraphicsText("Courses");
        sideBarTitle.setFont("courier new", FontStyle.PLAIN, 20);
        sideBarTitle.setCenter(sideBar.getCenter().getX(), sideBar.getHeight()*0.06);
        canvas.add(sideBarTitle);
    }
}
