import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Semester {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 100;
    private static final Color BORDER_COLOR = Color.LIGHT_GRAY;
    private Rectangle semester;
    private GraphicsGroup panel;

    public Semester(Color color, double x, double y, GraphicsGroup panel){
        this.panel = panel;
        createSemester(x, y, color);
    }

    private void createSemester(double x, double y, Color color){
        semester = new Rectangle(x, y, WIDTH, HEIGHT);
        semester.setFillColor(color);
        semester.setStrokeColor(BORDER_COLOR);
        panel.add(semester);
        semester.setPosition(x, y);
    }

    public Rectangle getGraphics(){
        return semester;
    }

    public boolean courseOverlaps(){
        // I think this check would need to be in here?
        return true;
    }

    public int getWidth(){
        return WIDTH;
    }

    public int getHeight(){
        return HEIGHT;
    }

}
