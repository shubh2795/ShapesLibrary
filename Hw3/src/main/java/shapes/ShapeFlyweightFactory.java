package shapes;

import java.io.File;
import java.util.HashMap;

public class ShapeFlyweightFactory extends AbstractFactory {

    private static HashMap<String,File> shapeMap = new HashMap<String,File>();

    @Override
    public Shapes getShape(String shapeType) throws ShapeException{
        if(shapeType.equalsIgnoreCase(String.valueOf(AllShapesEnum.allShapes.Rectangle))){
            return new Rectangle(1,1,4,1,4,4,1,4);
        }else if(shapeType.equalsIgnoreCase(String.valueOf(AllShapesEnum.allShapes.Circle))){
            return new Circle(5,5,10);
        }
        return null;
    }

    public static File objectManager(String fileName){
        File source = shapeMap.get(fileName);
        if(source == null){
            source = new File(fileName);
            shapeMap.put(fileName,source);
        }
        return source;
    }
}