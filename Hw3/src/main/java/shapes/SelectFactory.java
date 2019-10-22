package shapes;

public class SelectFactory {

    public static AbstractFactory getFactory(String factoryType){
        if(factoryType.equals("ShapeFlyweightFactory")){
            return new ShapeFlyweightFactory();
        }
        return null;
    }
}
