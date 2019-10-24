package shapes;

import org.junit.Test;

import static org.junit.Assert.*;

public class SelectFactoryTest {

    @Test
    public void getFactory() {
        SelectFactory selectFactory= new SelectFactory();
        selectFactory.getFactory("ShapeFlyweightFactory");
        selectFactory.getFactory("Factory");
    }
}