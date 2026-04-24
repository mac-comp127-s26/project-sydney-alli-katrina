
import java.awt.Color;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsText;

public class Course {
    private double width = 200;
    private double height = 20;
    private Rectangle courseRectangle;
    private GraphicsText courseLabel;
    private boolean isBeingHovered;
    private boolean isBeingDragged = false;
    private boolean selectedCourse = false;
    private final double resetX;
    private final double resetY;
    public ArrayList<String> distributions= new ArrayList<>();
  public String courseName;
public Course(String courseName, double x, double y, Rectangle graphicsGroup, CanvasWindow canvas){
    this.courseName = courseName;
    this.resetX = graphicsGroup.getCenter().getX();
    this.resetY = y;
    createIcon(x,y, graphicsGroup, canvas);

}
  
    public void createIcon(double x, double y , Rectangle graphicsGroup, CanvasWindow canvas){
        courseRectangle = new Rectangle(x,y,width,height);
        courseRectangle.setFillColor(Color.RED);
        courseRectangle.setCenter(graphicsGroup.getCenter().getX(), y);
        courseLabel = new GraphicsText(courseName);
        courseLabel.setFillColor(Color.BLACK);
        courseLabel.setFontSize(10);
        courseLabel.setCenter(courseRectangle.getCenter());
        canvas.add(courseRectangle);
        canvas.add(courseLabel);
         canvas.onMouseMove(event -> {
                Point mousePos = event.getPosition();
                double posX = mousePos.getX();
                double posY = mousePos.getY();
                if(posX > courseRectangle.getX() && posX < courseRectangle.getX()+width && posY > courseRectangle.getY() && posY < courseRectangle.getY()+height){
                    courseRectangle.setFillColor(Color.YELLOW);
                    isBeingHovered= true;
                }
                else{
                    isBeingHovered= false;
                    courseRectangle.setFillColor(Color.RED);
                }
        });
    }

    public void setCenter(Point point){
        courseRectangle.setCenter(point);
        courseLabel.setCenter(point);
    }

      public void setCenter(double x, double y){
        courseRectangle.setCenter(x,y);
        courseLabel.setCenter(x,y);
    }

    public void setPosition(double x, double y){
        courseRectangle.setPosition(x,y);
        courseLabel.setCenter(courseRectangle.getCenter());
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

    public double getY(){
        return courseRectangle.getY();
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

public void returnToStartPos(){
    courseRectangle.setCenter(resetX,resetY);
    courseLabel.setCenter(resetX,resetY);
}

public double getNextY(){
    return courseRectangle.getY() + height;
}
}

 


