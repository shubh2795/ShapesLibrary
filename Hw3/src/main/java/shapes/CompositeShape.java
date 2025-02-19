package shapes;
import java.awt.*;

import java.util.ArrayList;
import java.util.List;


    public class CompositeShape implements Shapes {

        //Collection of Shapes
        private List<Shapes> shapes;

        public CompositeShape(){
            shapes = new ArrayList<Shapes>();
        }


        public void addShape(Shapes shape){
            this.shapes.add(shape);
        }

        public void removeShape(Shapes shape){
            shapes.remove(shape);
        }

        public void removeAllShapes(){

            this.shapes.clear();
        }

        @Override
        public String stringToTextFile() {
            String text= "CompositeShape,";
            for(Shapes s:shapes){
                text= text+s.stringToTextFile()+"\n";
            }
            return text;

        }

        public CompositeShape(String [] receivedData){

            try{


                for(int i=0; i<receivedData.length; i++) {

                AllShapesEnum.allShapes shape = AllShapesEnum.allShapes.valueOf(receivedData[i]);

                 switch (shape) {

                    case Line:
                        shapes.add(new Line(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2]), Double.parseDouble(receivedData[i+3]), Double.parseDouble(receivedData[i+4])));

                    case Point:
                        shapes.add(new Point(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2])));

                    case Circle:
                        shapes.add(new Circle(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2]), Double.parseDouble(receivedData[i+3])));

                    case Rectangle:
                        shapes.add(new Rectangle(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2]), Double.parseDouble(receivedData[i+3]), Double.parseDouble(receivedData[i+4]), Double.parseDouble(receivedData[i+5]), Double.parseDouble(receivedData[i+6]), Double.parseDouble(receivedData[i+7]), Double.parseDouble(receivedData[i+8])));

                    case Triangle:
                        shapes.add(new Triangle(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2]), Double.parseDouble(receivedData[i+3]), Double.parseDouble(receivedData[i+4]),Double.parseDouble(receivedData[i+5]), Double.parseDouble(receivedData[i+6])));

                    case EmbeddedImage: {
                        shapes.add(new EmbeddedImage(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2]), Double.parseDouble(receivedData[i+3]), Double.parseDouble(receivedData[i+4]), receivedData[i+5]));

                    }

                    case CompositeShape: {
                            while(!(receivedData[i].equals("\n/CompositeShape"))) {
                            String packetString = "";
                            String [] data = null;
                            while(!(receivedData[i].equals("\n/CompositeShape"))) {
                                packetString = packetString.concat(receivedData[i]);
                                packetString = packetString.concat(",");
                                i++;
                            }

                            data = packetString.split(",");
                            CompositeShape compositeShape = new CompositeShape(data);
                            shapes.add(compositeShape);
                        }

                    }
                 }

                 }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public double computeArea() throws ShapeException {
            // As per class discussions the area has to be  sum of areas of all the shapes
            double sumOfAllAreas = 0;
            for(Shapes shape : shapes){
                sumOfAllAreas = sumOfAllAreas + shape.computeArea();
            }
            return sumOfAllAreas;
        }

        @Override
        public void move(double deltaX, double deltaY) throws ShapeException {
            for(Shapes shape : shapes) {
                shape.move(deltaX,deltaY);
            }
        }

        @Override
        public void scale(double scaleFactor) throws ShapeException {
            Validator.validatePositiveDouble(scaleFactor, "Invalid scale factor");
            for(Shapes shape : shapes){
                shape.scale(scaleFactor);
            }
        }

        @Override
        public void render(Graphics graphics) throws ShapeException {
            for(Shapes shape : shapes){
            shape.render(graphics);
        }
    }

    }
