
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.Key;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private Rectangle sideBarBackground;
    private Color color = Color.BLUE;
    private GraphicsGroup sidebar;
    private double width;
    private List<Course> listOfCourses = new ArrayList<>();
    private List<String> courseRequirements = List.of("Social Science", "Social Science",
    "Natural Sciences and Mathematics", "Humanities/Fine Arts", "Humanities/Fine Arts", "Humanities/Fine Arts",
    "Internationalism","US ID", "Q3", "WA", "WA/WP/WC","WA/WP/WC", "Language", "Language", "Language", "Language");

    public CourseManager(CanvasWindow canvas) {
        width = canvas.getWidth() * 0.25;
        sideBarBackground = new Rectangle(0, 0, width, canvas.getHeight());
        sideBarBackground.setFillColor(color);
        sidebar = new GraphicsGroup();
        sidebar.add(sideBarBackground);
        int count = 1;
        for(String c : courseRequirements){
            Course course = new Course(c, 100, 45*count, sidebar, canvas);
            count++;
            listOfCourses.add(course);
        }
        canvas.add(sidebar);

    }

    public double getWidth() {
        return width;
    }

    public void drag(CanvasWindow canvas) {
        canvas.onDrag(event -> {
            for (Course course : listOfCourses) {
                if (course.getHoverStatus()) {
                    course.setDragging(true);
                    Point mousePos = event.getPosition();
                    course.setCenter(mousePos);

                }
            }

        });
        canvas.onMouseUp(event -> {
            for (Course course : listOfCourses) {
                if (course.isDragging()) {
                    course.setDragging(false);

                    if (course.isInBounds(0,0,width, canvas.getHeight())) {
            
                        course.returnToStartPos();
                    }
                  
                    } 
    
                }
            
        });


    }

    // public Course giveSelectedCourse(){
    //     for (Course course:listOfCourses){
    //         if(course.isSelectedCourse()){
    //             return course;
    //         }
    //         else{
    //             return null;
    //         }
    //     }
    // }

}
