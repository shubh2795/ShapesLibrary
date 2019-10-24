package shapes;

import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AllShapesEnumTest {

    @Test
    public void testAllShapesEnum(){
        assertEquals("Point",AllShapesEnum.allShapes.Point);
        assertEquals("Line",AllShapesEnum.allShapes.Line);
        assertEquals("Circle",AllShapesEnum.allShapes.Circle);
        assertEquals("Rectangle",AllShapesEnum.allShapes.Rectangle);
        assertEquals("Triangle",AllShapesEnum.allShapes.Triangle);
        assertEquals("CompositeShape",AllShapesEnum.allShapes.CompositeShape);
        assertEquals("EmbeddedImage",AllShapesEnum.allShapes.EmbeddedImage);
    }

    }