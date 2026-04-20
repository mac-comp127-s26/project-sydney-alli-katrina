
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;


public class CourseManager {
    private Rectangle background;
    private Color color = Color.BLUE;
    public GraphicsGroup sidebar;

    public CourseManager(CanvasWindow canvas) {
        background = new Rectangle(0, 0, canvas.getWidth() * 0.25, canvas.getHeight());
        background.setFillColor(color);
        sidebar = new GraphicsGroup();
        sidebar.add(background);
        canvas.add(sidebar);
    }
}
