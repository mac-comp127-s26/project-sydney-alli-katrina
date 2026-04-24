import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class Semester {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 100;
    private static final int MARGIN = 5;
    public ArrayList<Course> courses = new ArrayList<>();
    private Rectangle semester;
    private Rectangle checkbox;
    private Path check;
    private GraphicsGroup panel;
    public boolean isBlocked = false;
    private Color color;

    public Semester(Color color, double x, double y, GraphicsGroup panel, CanvasWindow canvas){
        this.panel = panel;
        this.color = color;
        createSemester(x, y, color);
        canvas.onClick(e -> {
            if (canvas.getElementAt(e.getPosition()).equals(checkbox)) {
                setBlockedStatus();
            }
        });
    }

    private void createSemester(double x, double y, Color color){
        semester = new Rectangle(x, y, WIDTH, HEIGHT);
        semester.setFillColor(color);
        semester.setStrokeColor(Colors.BORDER_COLOR);
        panel.add(semester);
        semester.setPosition(x, y);

        checkbox = new Rectangle(x + semester.getWidth() + MARGIN, y, HEIGHT * 0.15, HEIGHT * 0.15);
        checkbox.setFillColor(Colors.CHECKBOX);
        checkbox.setStrokeColor(Colors.BORDER_COLOR);
        panel.add(checkbox);

        Point btmRight = new Point(checkbox.getX() + checkbox.getWidth(), checkbox.getY() + checkbox.getWidth());
        Point btmLeft = new Point(checkbox.getX(), checkbox.getY() + checkbox.getWidth());
        Point topRight = new Point(checkbox.getX() + checkbox.getWidth(), checkbox.getY());
        List<Point> checkList = List.of(checkbox.getPosition(), btmRight, checkbox.getCenter(), btmLeft, topRight);
        check = new Path(checkList, false);
        check.setCenter(checkbox.getCenter());
        check.setStrokeColor(Colors.CHECKBOX);
        panel.add(check);
        
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

    private void setBlockedStatus(){
        if (isBlocked) {
            semester.setFillColor(color);
            check.setStrokeColor(Colors.CHECKBOX);
            isBlocked = false;
        } else {
            semester.setFillColor(Colors.BORDER_COLOR);
            check.setStrokeColor(Color.BLACK);
            isBlocked = true;
        }

    }

}
