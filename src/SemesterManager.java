import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

public class SemesterManager {

    private static final int SPACING = 40;
    private static final int MARGIN = 40;
    private static final int STARTINGX = 75;
    private static final int STARTINGY = 80;
    private CanvasWindow canvas;
    private Rectangle panel;
    private static List<Semester> semesters;
    private int numSemesters = 8;
    private Semester curSemester;
    private static ArrayList<Course> coursesPlaced = new ArrayList<>();
    private List<Color> colors = new ArrayList<>(
        List.of(Colors.BROWN, Colors.PINK, Colors.GREEN, Colors.BLUE, Colors.GREEN));

    public SemesterManager(CanvasWindow canvas) {
        this.canvas = canvas;
        semesters = new ArrayList<>();
        panelSetup();
        createSemesters();
    }

    private void createSemesters() {
        int n = 0;
        Color color = colors.get(n);
        double x = STARTINGX;
        double y = STARTINGY;
        for (int i = 0; i < numSemesters; i++) {
            Semester semester = new Semester(color, x, y, panel, canvas);
            semesters.add(semester);
            if (x + semester.getWidth() + MARGIN + SPACING > panel.getWidth() - MARGIN) {
                x = STARTINGX;
                y += semester.getHeight() + SPACING;
                n++;
                color = colors.get(n);
            } else {
                x += semester.getWidth() + SPACING;
                color = color.darker();
            }
        }
    }

    private void panelSetup() {
        panel = new Rectangle(250, 0, 600, canvas.getHeight());
        panel.setFillColor(Colors.SEMESTER_PANEL);
        canvas.add(panel);
        GraphicsText title = new GraphicsText("Semesters");
        title.setFont("courier new", FontStyle.PLAIN, 20);
        canvas.add(title);
        title.setCenter(panel.getCenter().getX(), panel.getHeight() * 0.06);
    }

    public Semester courseOverlaps(Course course) {
        for (Semester s : semesters) {
            if (course.isInBounds(s.getLeftX(), s.getTopY(), s.getLeftX() + s.getWidth(),
                s.getTopY() + s.getHeight())) {
                return s;
            }
        }
        return null;
    }

    public void remove(Course course, Semester semester) {
        if (semester != null) {
            if (semester.getCourses().contains(course)) {
                semester.removeCourse(course);
            }

        }
    }

    public void putCourseInSemester(Course course, Semester semester) {
        if (semester != null && !semester.getCourses().contains(course)) {
            semester.addCourse(course);
        }
    }

    public static ArrayList<Course> allCoursesInAnySemester() {
        ArrayList<Course> coursesPlaced = new ArrayList<>();
        for (Semester s : semesters) {
            coursesPlaced.addAll(s.getCourses());
        }
        return coursesPlaced;
    }

}
