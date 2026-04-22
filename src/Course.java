
import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
public class Course {
    private double width = 200;
    private double height = 20;
    private Rectangle courseRectangle;
    private boolean isBeingHovered;
    private boolean isBeingDragged = false;
    private boolean selectedCourse = false;
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
public Course(String courseName, double x, double y, GraphicsGroup graphicsGroup, CanvasWindow canvas){
    courseName = this.courseName;
    // x = this.x;
    // y = this.y;
    createIcon(x,y, graphicsGroup, canvas);

}
  
    public void createIcon(double x, double y, GraphicsGroup graphicsGroup, CanvasWindow canvas){
        courseRectangle = new Rectangle(x,y,width,height);
        courseRectangle.setFillColor(Color.RED);
        graphicsGroup.add(courseRectangle);
         canvas.onMouseMove(event -> {
                Point mousePos = event.getPosition();
                double posX = mousePos.getX();
                double posY = mousePos.getY();
                if(posX > courseRectangle.getX() && posX < courseRectangle.getX()+width && posY > courseRectangle.getY() && posY < courseRectangle.getY()+height){
                    isBeingHovered= true;
                }
                else{
                    isBeingHovered= false;
                }
        });
    }

    public void setCenter(Point point){
        courseRectangle.setCenter(point);
    }

      public void setCenter(double x, double y){
        courseRectangle.setCenter(x,y);
    }


    public boolean getHoverStatus(){
        return isBeingHovered;
    }
    
    public double getCenterX(){
        return courseRectangle.getCenter().getX();
    }

    public double getCenterY(){
        return courseRectangle.getCenter().getY();
    }


    public boolean isInBounds(double startX, double startY, double farX, double farY) {
    return getCenterX() > startX && getCenterX() < farX 
        && getCenterY() > startY && getCenterY() < farY;
}
    

    public void setDragging(boolean dragging){
        isBeingDragged = dragging;
    }

    public boolean isDragging(){
        selectedCourse = true;
        return isBeingDragged;
        
    }
  public boolean isSelectedCourse(){
    return selectedCourse;
  }
  public String getName(){
    return courseName;
}
}

 


