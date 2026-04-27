import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Path;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Point;
public class Semester {
    private static final int WIDTH = 250;
    private static final int HEIGHT = 100;
    private static final int MARGIN = 5;
    public ArrayList<Course> courses = new ArrayList<>();
    private Rectangle semester;
    private Rectangle checkbox;
    private Path check;
    private Rectangle panel;
    public boolean isBlocked = false;
    private Color color;

    public Semester(Color color, double x, double y, Rectangle panel, CanvasWindow canvas){
        this.panel = panel;
        this.color = color;
        createSemester(x, y, color, canvas);
        canvas.onClick(e -> {
            if (checkbox.testHit(e.getPosition().getX(), e.getPosition().getY())) {
                setBlockedStatus();
            }
        });
    }

    private void createSemester(double x, double y, Color color, CanvasWindow canvas){
        semester = new Rectangle(x + panel.getX(), y, WIDTH, HEIGHT);
        semester.setFillColor(color);
        semester.setStrokeColor(Colors.BORDER_COLOR);
        canvas.add(semester);

        checkbox = new Rectangle(x + panel.getX() + semester.getWidth() + MARGIN, y, HEIGHT * 0.15, HEIGHT * 0.15);
        checkbox.setFillColor(Colors.CHECKBOX);
        checkbox.setStrokeColor(Colors.BORDER_COLOR);
        canvas.add(checkbox);

        Point btmRight = new Point(checkbox.getX() + checkbox.getWidth(), checkbox.getY() + checkbox.getWidth());
        Point btmLeft = new Point(checkbox.getX(), checkbox.getY() + checkbox.getWidth());
        Point topRight = new Point(checkbox.getX() + checkbox.getWidth(), checkbox.getY());
        List<Point> checkList = List.of(checkbox.getPosition(), btmRight, checkbox.getCenter(), btmLeft, topRight);
        check = new Path(checkList, false);
        check.setCenter(checkbox.getCenter());
        check.setStrokeColor(Colors.CHECKBOX);
        canvas.add(check);
        
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

    public Point getCenter(){
        return semester.getCenter();
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

    public void addCourse(Course course){
        if(courses.size() < 4 || isBlocked){
        courses.add(course);
        if (courses.size() > 1){
            course.setPosition(semester.getX(), courses.get(courses.indexOf(course) - 1).getNextY() + MARGIN);
            
        } else course.setPosition(semester.getX(), semester.getY() + MARGIN);//course.setCenter(semester.getCenter().getX(), semester.getY() + course.get + MARGIN);
    }
        if(courses.size()>4){
           course.returnToStartPos(); 
        }
    System.out.println(courses.size());
    }
    public void removeCourse(Course course){
        courses.remove(course);
        System.out.println(courses.size());

    }

}
