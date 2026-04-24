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
    private static final int STARTINGX = 40;
    private static final int STARTINGY = 80;
    private CanvasWindow canvas;
    private Rectangle panel;
    private List<Semester> semesters;
    private int numSemesters = 8;
    private Semester curSemester;
    private List<Color> colors = new ArrayList<>(List.of(Colors.BROWN, Colors.PINK, Colors.GREEN, Colors.BLUE, Colors.GREEN));

public SemesterManager(CanvasWindow canvas){
        this.canvas = canvas;
        semesters = new ArrayList<>();
        panelSetup();
        createSemesters();
    }

    private void createSemesters(){
        int n = 0;
        Color color = colors.get(n);
        double x = STARTINGX;
        double y = STARTINGY;
        for (int i = 0; i < numSemesters; i++) {
            Semester semester = new Semester(color, x, y, panel, canvas);
            semesters.add(semester);
            if (x + semester.getWidth() + MARGIN + SPACING > panel.getWidth() - MARGIN){
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

    private void panelSetup(){
        panel = new Rectangle(canvas.getWidth() * 0.25, 0, canvas.getWidth() * 0.75, canvas.getHeight());
        panel.setFillColor(Colors.SEMESTER_PANEL);
        canvas.add(panel);
        GraphicsText title = new GraphicsText("Semesters");
        title.setFont("times new roman", FontStyle.PLAIN, 15);
        canvas.add(title);
        title.setCenter(panel.getCenter().getX(), 20);
    }

    public boolean courseOverlaps(Course course){
        for (Semester s : semesters) {
            if(course.isInBounds(s.getLeftX(), s.getTopY(), s.getLeftX() + s.getWidth(), s.getTopY() + s.getHeight())){
                curSemester = s;
                return true;
            } 
        }return false;
    }

    public void remove(Course course){
        if(curSemester!= null)  {
        curSemester.getCourses().remove(course);
        }
    }
    public void putCourseInSemester(Course course){
        if(curSemester!= null)  {
        curSemester.getCourses().add(course);
            course.setCenter(curSemester.getCenter());
        }
    }

}
