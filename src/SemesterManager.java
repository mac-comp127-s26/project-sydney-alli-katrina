import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;

public class SemesterManager {
    private static final Color PINK = new Color(204, 165, 191);
    private static final Color TAN = new Color(214, 196, 163);
    private static final Color GREEN = new Color(173, 191, 159);
    private static final Color BLUE = new Color(163, 179, 194);
    private static final int SPACING = 40;
    private static final int MARGIN = 40;
    private static final int STARTINGX = MARGIN;
    private static final int STARTINGY = 10;
    private CanvasWindow canvas;
    private GraphicsGroup panel;
    private List<Semester> semesters;
    private int numSemesters = 8;
    private List<Color> colors = new ArrayList<>(List.of(PINK, TAN, GREEN, BLUE));

public SemesterManager(CanvasWindow canvas){
        this.canvas = canvas;
        semesters = new ArrayList<>();
        panelSetup();
        createSemesters();
        canvas.add(panel); // add this last so everything added to the graphics group is added tg
    }

    private void createSemesters(){
        int n = 0;
        Color color = colors.get(n);
        double x = STARTINGX;
        double y = STARTINGY;
        for (int i = 0; i < numSemesters; i++) {
            Semester semester = new Semester(color, x, y, panel);
            panel.add(semester.getGraphics());
            semesters.add(semester);
            if (x + semester.getWidth() + MARGIN > panel.getWidth() - MARGIN){
                x = STARTINGX;
                y += semester.getHeight() + SPACING;
                n++;
                color = colors.get(n);
            } else x += semester.getWidth() + SPACING;
        }
    }

    private void panelSetup(){
        Rectangle background = new Rectangle(0, 0, canvas.getWidth() * 0.75, canvas.getHeight());
        background.setFillColor(Color.WHITE);
        panel = new GraphicsGroup(canvas.getWidth() * 0.25, 0);
        panel.add(background);
    }
}
