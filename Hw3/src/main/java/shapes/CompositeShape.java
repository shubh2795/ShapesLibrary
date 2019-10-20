package shapes;
import Common.AllShapesEnum;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


    public class CompositeShape implements Shapes {

        //Collection of Shapes
        private List<Shapes> shapes = new ArrayList<Shapes>();

        public void addShape(Shapes shape){
            this.shapes.add(shape);
        }

        public void removeShape(Shapes shape){
            shapes.remove(shape);
        }

        public void removeAllShapes(){

            this.shapes.clear();
        }

        //TODO:: remove conditions
        public CompositeShape(String [] receivedData){

            try{

                for(int i=0; i<receivedData.length; i++) {

                    if(receivedData[i].equals(AllShapesEnum.allShapes.Point)){
                        System.out.println("Adding Point");
                        shapes.add(new Point(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2])));
                    }

                    if(receivedData[i].equals(AllShapesEnum.allShapes.Line)) {
                        System.out.println("Adding Line");
                        shapes.add(new Line(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2]), Double.parseDouble(receivedData[i+3]), Double.parseDouble(receivedData[i+4])));
                    }

                    if(receivedData[i].equals(AllShapesEnum.allShapes.Circle)){
                        System.out.println("Adding Circle");
                        shapes.add(new Circle(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2]), Double.parseDouble(receivedData[i+3])));
                    }

                    if(receivedData[i].equals(AllShapesEnum.allShapes.Triangle)){
                        System.out.println("Adding Triangle");
                        shapes.add(new Triangle(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2]), Double.parseDouble(receivedData[i+3]), Double.parseDouble(receivedData[i+4]),Double.parseDouble(receivedData[i+5]), Double.parseDouble(receivedData[i+6])));
                    }

                    if(receivedData[i].equals(AllShapesEnum.allShapes.Rectangle)){
                        System.out.println("Adding Rectangle");
                        shapes.add(new Rectangle(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2]), Double.parseDouble(receivedData[i+3]), Double.parseDouble(receivedData[i+4]), Double.parseDouble(receivedData[i+5]), Double.parseDouble(receivedData[i+6]), Double.parseDouble(receivedData[i+7]), Double.parseDouble(receivedData[i+8])));
                    }


                    if(receivedData[i].equals(AllShapesEnum.allShapes.CompositeShape)){
                        System.out.println("Adding Composite Shape");
                        while(!(receivedData[i].equals("\n/CompositeShape")))
                        {

                            String packetString = "";
                            String [] data = null;
                            while(!(receivedData[i].equals("\n/CompositeShape")))
                            {
                                packetString = packetString.concat(receivedData[i]);
                                packetString = packetString.concat(",");
                                i++;
                            }
                            data = packetString.split(",");
                            CompositeShape compositeShape = new CompositeShape(data);
                            shapes.add(compositeShape);
                        }
                    }
                    if(receivedData[i].equals(AllShapesEnum.allShapes.EmbeddedImage)){
                        shapes.add(new EmbeddedImage(Double.parseDouble(receivedData[i+1]), Double.parseDouble(receivedData[i+2]), Double.parseDouble(receivedData[i+3]), Double.parseDouble(receivedData[i+4]), receivedData[i+5]));
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public File createFile(String sampleFile) {
            try {
                File file = new File(sampleFile);
                if (!file.exists()) {
                    file.createNewFile();
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    bufferedWriter.newLine();
                    for (Shapes shape : shapes) {
                        System.out.println(shape.getClass().getName());
                        if (!(shape.getClass().getName().equals("examples.shapes.CompositeShape"))) {
                            bufferedWriter.write(shape.toString());
                            bufferedWriter.newLine();
                        }
                        else{
                            bufferedWriter.write("CompositeShape,");
                            bufferedWriter.newLine();
                            bufferedWriter.write("/CompositeShape,");
                            bufferedWriter.newLine();
                        }
                    }
                    bufferedWriter.close();
                    return file;
                }
            } catch (Exception e) {

            }
            return null;
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
