import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

public class Semester {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 100;
    private static final Color BORDER_COLOR = Color.LIGHT_GRAY;
    public ArrayList<Course> courses= new ArrayList<>();
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

    public int getWidth(){
        return WIDTH;
    }

    public int getHeight(){
        return HEIGHT;
    }

    public double getLeftX(){
        return semester.getX();
    }

    public double getTopY(){
        return semester.getY();
    }

    public List<Course> getCourses(){
        return courses;
    }
    

}
