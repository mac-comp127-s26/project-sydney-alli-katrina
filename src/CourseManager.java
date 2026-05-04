
import java.awt.Color;
import java.text.DecimalFormat;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import java.text.DecimalFormat; 
import edu.macalester.graphics.events.Key;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private Rectangle gradSideBar;
    private Rectangle majorSideBar;
    private double width;
    private static List<Course> listOfCourses = new ArrayList<>();
    private static List<Course> listOfMajCourses = new ArrayList<>();
    private SemesterManager semesterManager;
    private Course selectedCourse;
    private static double percentComplete = 0;
    public static final DecimalFormat ONE_DECIMAL_PLACE = new DecimalFormat("#0.0");
    static GraphicsText completenessTracker = new GraphicsText(
        "Percent Graduation Requirements Complete " + percentComplete + "%");
    private List<String> courseRequirements = List.of("Social Science", "Social Science",
        "Natural Sciences and Mathematics", "Humanities/Fine Arts", "Humanities/Fine Arts", "Humanities/Fine Arts",
        "Internationalism", "US ID", "Q3", "WA", "WA/WP/WC", "WA/WP/WC", "Language", "Language", "Language",
        "Language");

    private List<String> majorRequirements = Majors.getMajor();  // call a method that returns selected major courses

    public CourseManager(CanvasWindow canvas, SemesterManager semesterManager) {
        this.semesterManager = semesterManager;
        gradSideBarSetup(canvas);
        majorSideBarSetup(canvas);
        double count = 5.5;

        for (String c : courseRequirements) {
            Course course = new Course(c, gradSideBar.getWidth(), 30 * count, gradSideBar, canvas);
            count++;
            listOfCourses.add(course);

        }
        count = 5.5;
        for (String m : majorRequirements) {
            if (m != "None") {
                Course mCourse = new Course(m, majorSideBar.getWidth(), 30 * count, majorSideBar, canvas);
                count++;
                listOfCourses.add(mCourse);

            }
        }
    }

    public double getWidth() {
        return width;
    }

    public void drag(CanvasWindow canvas) {
        canvas.onDrag(event -> {
            for (Course course : listOfCourses) {
                if (course.getHoverStatus()) {
                    Semester curSemester = semesterManager.courseOverlaps(course);
                    course.setDragging(true);
                    selectedCourse = course;
                    Point mousePos = event.getPosition();
                    course.setCenter(mousePos);
                    semesterManager.remove(course, curSemester);
                }
            }

        });

        canvas.onMouseUp(event -> {
            for (Course course : listOfCourses) {
                if (course.isDragging()) {
                    course.setDragging(false);
                    Semester curSemester = semesterManager.courseOverlaps(course);
                    if (curSemester != null) { // adding if overlapping
                        semesterManager.putCourseInSemester(course, curSemester);
                    } else { // if in bounds but not overlapping, send to og
                        course.returnToStartPos();

                    }

                }
                selectedCourse = null;
            }
            updatePercentComplete();
        });


    }

    public Course getSelectedCourse() {
        if (selectedCourse != null)
            return selectedCourse;
        else {
            return null;
        }
    }

    private void gradSideBarSetup(CanvasWindow canvas) {
        width = 250;
        gradSideBar = new Rectangle(0, 0, width, canvas.getHeight());
        gradSideBar.setFillColor(Colors.COURSES_PANEL);
        canvas.add(gradSideBar);

        GraphicsText sideBarTitle = new GraphicsText("Grad Courses");
        sideBarTitle.setFont("courier new", FontStyle.PLAIN, 20);
        sideBarTitle.setCenter(gradSideBar.getCenter().getX(), gradSideBar.getHeight() * 0.06);
        canvas.add(sideBarTitle);

    }

    private void majorSideBarSetup(CanvasWindow canvas) {
        width = 250;
        majorSideBar = new Rectangle(850, 0, width, canvas.getHeight());
        majorSideBar.setFillColor(Colors.COURSES_PANEL);
        canvas.add(majorSideBar);

        GraphicsText sideBarTitle = new GraphicsText("Major Courses");
        sideBarTitle.setFont("courier new", FontStyle.PLAIN, 20);
        sideBarTitle.setCenter(majorSideBar.getCenter().getX(), majorSideBar.getHeight() * 0.06);
        canvas.add(sideBarTitle);

    }

    public void percentCompleteSetUp(CanvasWindow canvas) {
        completenessTracker.setFont("courier new", FontStyle.PLAIN, 20);
        completenessTracker.setCenter(550, 750);
        canvas.add(completenessTracker);
    }

    public static void updatePercentComplete() {
        double placedSize = SemesterManager.allCoursesInAnySemester().size();
        double coursesSize = listOfCourses.size();
        if (placedSize > 0) {
            percentComplete = placedSize / coursesSize * 100;
            String percentCompleteFormatted = FormatTemp(percentComplete);
            completenessTracker.setText("Percent Graduation Requirements Complete " + percentCompleteFormatted + "%");
        } else {
            percentComplete = 0;
            completenessTracker.setText("Percent Graduation Requirements Complete " + percentComplete + "%");
        }
    }


    public static String FormatTemp(Double value) {
         return ONE_DECIMAL_PLACE.format(value);
    }

}
