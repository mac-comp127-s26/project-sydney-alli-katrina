import edu.macalester.graphics.CanvasWindow;
import java.awt.Color;

public class GraduationPlanner {
    private static final int CANVAS_WIDTH = 1600;
    private static final int CANVAS_HEIGHT = 800;
    private static final Color CANVAS_COLOR = Color.LIGHT_GRAY;

    private CanvasWindow canvas;
    private CourseManager classes;

    public static void main(String[] args) {
        new GraduationPlanner();
        System.out.println("hi");
    }

    public GraduationPlanner(){
        canvas = new CanvasWindow("Graduation Planner", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(CANVAS_COLOR);
        classes = new CourseManager(canvas);
        canvas.draw();
    }


}
