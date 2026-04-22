import edu.macalester.graphics.GraphicsGroup;
import java.util.ArrayList;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsText;



public class Course {
//social sciences 8 credits(two semesters)
//natural sciences and mathematics 
//humanities and fine arts 12 credits 3 semester
//Internationalism
//US ID
//Q3 or two Q2s or a Q2 and a Q1 or 3 Q1s
//Writng: 3 courses at least 1 WA and no more than 1 can be WP
// equivalent of 4 semesters of language
public String courseName;
public ArrayList<String> distributions= new ArrayList<>();
private static final int WIDTH = 50;
private static final int HEIGHT = 20;
public static int x;
public static int y;
public GraphicsGroup courseIcon = new GraphicsGroup();

public Course(String courseName, int x, int y){
    courseName = this.courseName;
    x = this.x;
    y = this.y;
    GraphicsGroup courseIcon = createIcon();

}

public static GraphicsGroup createIcon(){
    GraphicsGroup group = new GraphicsGroup();
    group.add(new Rectangle(x,y, WIDTH, HEIGHT));
    return group;
}

public String getName(){
    return courseName;
}

}
