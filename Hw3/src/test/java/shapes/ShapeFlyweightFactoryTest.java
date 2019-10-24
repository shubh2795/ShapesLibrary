package shapes;

import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeFlyweightFactoryTest {

    @Test
    public void getShape() throws ShapeException {
        ShapeFlyweightFactory shapeFlyweightFactory=new ShapeFlyweightFactory();
        shapeFlyweightFactory.getShape("Triangle");
        shapeFlyweightFactory.getShape("Circle");


    }

    @Test
    public void objectManager() {
    }
}