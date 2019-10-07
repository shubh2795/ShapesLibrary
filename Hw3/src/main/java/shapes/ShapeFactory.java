package shapes;
import java.util.HashMap;


public class ShapeFactory {
    @SuppressWarnings("unchecked")
    private static final HashMap circleMap = new HashMap();

    public static Shapes getCircle(String color) {
        Circle circle = (Circle)circleMap.get(color);

        if(circle == null) {
            circle = new Circle();
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
