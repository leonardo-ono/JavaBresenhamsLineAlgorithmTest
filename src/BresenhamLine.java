
/**
 * Bresenham's Line Algorithm implementation test
 * 
 * Reference: https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm
 * 
 * @author leo
 */

public class BresenhamLine {
    
    public static final int[][] screen = new int[120][160]; // screen[row][col]

    public static void clearScreen() {
        for (int[] screen1 : screen) {
            for (int x = 0; x < screen1.length; x++) {
                screen1[x] = 0;
            }
        }
    }

    public static void drawLine(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        
        if (dx >= 0 && dy >= 0) { // quadrant 0
            drawLineQuadrant(x1, y1, x2, y2, dx, dy, 0);
        }
        else if (dx < 0 && dy >= 0) { // quadrant 1
            drawLineQuadrant(y1, -x1, y2, -x2, dy, -dx, 1);
        }
        else if (dx < 0 && dy < 0) { // quadrant 2
            drawLineQuadrant(-x1, -y1, -x2, -y2, -dx, -dy, 2);
        }
        else if (dx >= 0 && dy < 0) { // quadrant 3
            drawLineQuadrant(-y1, x1, -y2, x2, -dy, dx, 3);
        }
    }   
    
    private static void drawLineQuadrant(int x1, int y1, int x2, int y2, int dx, int dy, int quadrant) {
        if (dx >= dy) {
            drawLineOctant(x1, y1, x2, y2, 2 * quadrant); // octant (2 * q + 0)
        }
        else {
            drawLineOctant(y1, x1, y2, x2, 2 * quadrant + 1); // octant (2 * q + 1)
        }
    }
    
    private static void drawLineOctant(int x1, int y1, int x2, int y2, int octant) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        
        int d = 2 * dy - dx;
        int y = y1;
        for (int x = x1; x <= x2; x++) {
            setScreenPixel(x, y, octant, 1);
            if (d <= 0) {
                d += 2 * dy;
            }
            else {
                d += 2 * dy - 2 * dx;
                y += 1;
            }
        }
        
        setScreenPixel(x1, y1, octant, 2);
        setScreenPixel(x2, y2, octant, 2);
    }
    
    private static void setScreenPixel(int x, int y, int octant, int color) {
        switch (octant) {
            case 0: screen[y][x] = color; break;
            case 1: screen[x][y] = color; break;
            case 2: screen[x][-y] = color; break;
            case 3: screen[y][-x] = color; break;
            case 4: screen[-y][-x] = color; break;
            case 5: screen[-x][-y] = color; break;
            case 6: screen[-x][y] = color; break;
            case 7: screen[-y][x] = color; break;
        }
    }
    
}
