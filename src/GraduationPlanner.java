import edu.macalester.graphics.CanvasWindow;
import java.awt.Color;

public class GraduationPlanner {
    private static final int CANVAS_WIDTH = 900;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private CourseManager courses;
    private SemesterManager semesters;

    public static void main(String[] args) {
        new GraduationPlanner();
    }

    public GraduationPlanner(){
        canvas = new CanvasWindow("Graduation Planner", CANVAS_WIDTH, CANVAS_HEIGHT);
        
        semesters = new SemesterManager(canvas);
        courses = new CourseManager(canvas, semesters);
        courses.percentCompleteSetUp(canvas);
        courses.drag(canvas);
        canvas.draw();
        
    }


}
