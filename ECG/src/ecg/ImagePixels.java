package ecg;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class ImagePixels {

    public static void usingBufferedWritter(String fileContent) throws IOException {
        //  String fileContent = "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image5.jpg";

//        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Areej\\Desktop\\graduation project\\merge\\ECG\\imagename.txt"));
//        // writer.write(fileContent);
//        //  writer.close();
//
//        switch (fileContent) {
//
//            case "C:\Users\Areej\Desktop\graduation project\ecg\img2signal\datatest\image1.jpg":
//                String img1 = "datatest/image1.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//            case "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image2.jpg":
//                String img2 = "datatest/image2.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//            case "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image3.jpg":
//                String img3 = "datatest/image3.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//            case "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image4.jpg":
//                String img4 = "datatest/image4.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//            case "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image5.jpg":
//                String img5 = "datatest/image5.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//            case "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image6.jpg":
//                String img6 = "datatest/image6.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//            case "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image7.jpg":
//                String img7 = "datatest/image7.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//            case "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image8.jpg":
//                String img8 = "datatest/image8.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//            case "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image9.jpg":
//                String img9 = "datatest/image9.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//            case "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image10.jpg":
//                String img10 = "datatest/image10.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//
//            case "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image11.jpg":
//                String img11 = "datatest/image11.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//
//            case "C:\\Users\\Areej\\Desktop\\graduation project\\ecg\\img2signal\\datatest\\image12.jpg":
//                String img12 = "datatest/image12.jpg";
//                writer.write(fileContent);
//                writer.close();
//
//                break;
//
//        }

//Get the file reference
int x=fileContent.length();
String st="";
System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmy x"+x);
//for(int i=x;i>30;i--){
//st=st+fileContent.charAt(i);
//}
System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmy st"+st);
Path path = Paths.get("C:\\Users\\Areej\\Desktop\\graduation project\\merge\\ECG\\imagename.txt");
 
//Use try-with-resource to get auto-closeable writer instance
try (BufferedWriter writer = Files.newBufferedWriter(path))
{
    writer.write(fileContent);
}
    }

    public static int getPixel() throws IOException {
        BufferedImage image = null;
        File f = null;
        int width = -1;
        try {
            f = new File("pixelimage.jpg"); //image file path

            //  image = new BufferedImage();
            image = ImageIO.read(f);
//      width =image.getWidth();
            System.out.println("Reading complete." + width);
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return width;
    }
}
