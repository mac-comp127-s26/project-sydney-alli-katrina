
import java.awt.Color;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.CanvasWindow;
public class Course {
    private double x = 50;
    private double y = 50;
    private double width = 200;
    private double height = 50;
    private Rectangle course = new Rectangle(x,y,width,height);
    private boolean isBeingHovered;
    private boolean isBeingDragged = false;
  public ArrayList<String> distributions= new ArrayList<>();
  public String courseName;
  //social sciences 8 credits(two semesters)
//natural sciences and mathematics 
//humanities and fine arts 12 credits 3 semester
//Internationalism
//US ID
//Q3 or two Q2s or a Q2 and a Q1 or 3 Q1s
//Writng: 3 courses at least 1 WA and no more than 1 can be WP
// equivalent of 4 semesters of language
public Course(String courseName, int x, int y){
    courseName = this.courseName;
    x = this.x;
    y = this.y;
    GraphicsGroup courseIcon = createIcon();

}
  
    public static void createIcon(GraphicsGroup graphicsGroup, CanvasWindow canvas){
        course.setFillColor(Color.RED);
        graphicsGroup.add(course);
         canvas.onMouseMove(event -> {
                Point mousePos = event.getPosition();
                double posX = mousePos.getX();
                double posY = mousePos.getY();
                if(posX > course.getX() && posX < course.getX()+width && posY > course.getY() && posY < course.getY()+height){
                    isBeingHovered= true;
                }
                else{
                    isBeingHovered= false;
                }
        });
    }

    public void setCenter(Point point){
        course.setCenter(point);
    }

      public void setCenter(double x, double y){
        course.setCenter(x,y);
    }


    public boolean getHoverStatus(){
        return isBeingHovered;
    }
    
    public double getCenterX(){
        return course.getCenter().getX();
    }

    public double getCenterY(){
        return course.getCenter().getY();
    }


    public boolean isInBounds(double sidebarWidth, double sidebarHeight) {
    return getCenterX() > 0 && getCenterX() < sidebarWidth 
        && getCenterY() > 0 && getCenterY() < sidebarHeight;
}
    

    public void setDragging(boolean dragging){
        isBeingDragged = dragging;
    }

    public boolean isDragging(){
        return isBeingDragged;
    }
  
  public String getName(){
    return courseName;
}


 


