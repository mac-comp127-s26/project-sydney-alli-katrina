import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;


public class SemesterManager {
    private Rectangle background;
    private Color color = Color.ORANGE;
    public GraphicsGroup main;

    public SemesterManager(CanvasWindow canvas, CourseManager courseManager) {
        double width = courseManager.getWidth();
        background = new Rectangle(0+width, 0, canvas.getWidth() - width, canvas.getHeight());
        background.setFillColor(color);
        main = new GraphicsGroup();
        main.add(background);
       // canvas.add(main);
    }
}

