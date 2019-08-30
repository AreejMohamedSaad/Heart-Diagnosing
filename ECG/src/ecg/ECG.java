package ecg;

//import ECGjar.Class1;
//import TheMain.matlabJava;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ECG extends Application {
StackPane graph;
    Text txt2;
    Text txt1;
 HBox imagegif;
  Scene scene;
  VBox mainLayout;
  StackPane result;
  HBox imagechose;
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
      mainLayout = new VBox();
        mainLayout.setAlignment(Pos.TOP_CENTER);

        StackPane titel = setTitel();
        imagegif = setimage();
   result = emptybox() ;
        StackPane twobtn = set2btn(primaryStage);
        
        mainLayout.getChildren().addAll(titel, imagegif, twobtn,result );
         scene = new Scene(mainLayout, 800, 600);
        mainLayout.setStyle("-fx-background-color:  	#e5e0e0;");
        primaryStage.setTitle("HEALTH IS WEALTH!");
        primaryStage.setScene(scene);
        primaryStage.show(); //primaryStage.setFullScreen(true);
        primaryStage.setTitle("Heart Diagnosing");
    }

    public static void main(String[] args) {
        launch(args);
    }

   public StackPane setTitel() {
        StackPane stackPane = new StackPane();

        stackPane.setPadding(new Insets(15, 12, 15, 12));

        Text title = new Text(" Heart Diagnosing ");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setStyle("-fx-text-color: 	#191b27;");
        // title.setFill(Color.GREEN);
        title.setTextAlignment(TextAlignment.CENTER);
        stackPane.getChildren().addAll(title);
        stackPane.setMinHeight(100);
        return stackPane;
    }

    public StackPane set2btn(Stage primaryStage) {
        StackPane stackPane = new StackPane();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(30, 30, 30, 30));
        hbox.setSpacing(80);

        hbox.setAlignment(Pos.CENTER);
   // hbox.setStyle("-fx-background-color:  	#f3ffd6;");

        Button recordButton = new Button("Upload your record");
        recordButton.setPrefSize(180, 35);
        recordButton.setStyle("-fx-background-color: #191b27;-fx-text-fill: #fff");
        recordButton.setOnAction(e -> {
            final FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter
                    = new FileChooser.ExtensionFilter("TEXT files ", "*.csv");
            File defaultDirectory = new File("C:\\Users\\Areej\\Desktop\\graduation project\\merge\\ECG");

            fileChooser.setInitialDirectory(defaultDirectory);
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                System.out.println(file);
                recordFile(file,primaryStage);
            }
        });
        Button imgButton = new Button("Upload your ECG Image");
        imgButton.setPrefSize(180, 35);
    //56cafb
imgButton.setOnAction(e->{ 
     final FileChooser fileChooser = new FileChooser();
               FileChooser.ExtensionFilter extFilter = 
                        new FileChooser.ExtensionFilter("TEXT files ", "*.JPG");
                fileChooser.getExtensionFilters().add(extFilter);
                 File defaultDirectory = new File("C:\\Users\\Areej\\Desktop\\graduation project\\merge\\ECG");
       
        fileChooser.setInitialDirectory(defaultDirectory);
            File file = fileChooser.showOpenDialog(primaryStage);
        
                    if (file != null) {
                        System.out.println(file);
                 
                     
                        String name=file.getPath();
                       resultImageLayout(primaryStage,name);
                         imgFile(file);
                    }
       });
        imgButton.setStyle("-fx-background-color: #191b27;-fx-text-fill: #fff");
        //new code
        Button resetButton = new Button("Reset");
        resetButton.setPrefSize(100, 30);
        resetButton.setStyle("-fx-background-color: #191b27;-fx-text-fill: #fff");
         resetButton.setOnAction(e->{ 
    
                       ResetLayout(primaryStage);
                   
       });

        hbox.getChildren().addAll(recordButton, imgButton,resetButton);
        //  hbox.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(hbox);
        StackPane.setAlignment(hbox, Pos.CENTER_LEFT);

        return stackPane;
    }
    //new code
     private void ResetLayout(Stage primaryStage){
      
         VBox mainLayout2 = new VBox();
        mainLayout2.setAlignment(Pos.TOP_CENTER);
    try {
        imagechose=setimage();
    } catch (FileNotFoundException ex) {
        Logger.getLogger(ECG.class.getName()).log(Level.SEVERE, null, ex);
    }
        StackPane titel = setTitel();
       result= emptybox() ;
  
        StackPane twobtn = set2btn(primaryStage);
     
        mainLayout2.getChildren().addAll(titel,imagechose, twobtn,result );
         scene = new Scene(mainLayout2, 800, 600);
        mainLayout.setStyle("-fx-background-color:  	#e5e0e0;");
        
        primaryStage.setScene(scene);
        primaryStage.show(); 
       // primaryStage.setFullScreen(true);
      
      }
     private void resultImageLayout(Stage primaryStage,String name){
      
         VBox mainLayout2 = new VBox();
        mainLayout2.setAlignment(Pos.TOP_CENTER);
    try {
        imagechose=setImageResult( name);
    } catch (FileNotFoundException ex) {
        Logger.getLogger(ECG.class.getName()).log(Level.SEVERE, null, ex);
    }
        StackPane titel = setTitel();
        
  
        StackPane twobtn = set2btn(primaryStage);
     
        mainLayout2.getChildren().addAll(titel,imagechose, twobtn,result );
         scene = new Scene(mainLayout2, 800, 600);
        mainLayout.setStyle("-fx-background-color:  	#e5e0e0;");
        primaryStage.setTitle("HEALTH IS WEALTH");
        primaryStage.setScene(scene);
        primaryStage.show(); 
       // primaryStage.setFullScreen(true);
      
      }
      public HBox setImageResult(String name) throws FileNotFoundException {
        
 
           Image image = new Image(new FileInputStream(name));  
      
      //Setting the image view 

      ImageView imageView = new ImageView(image); 
      
      //Setting the position of the image 
      imageView.setX(1000); 
     imageView.setY(1000); 
      
      //setting the fit height and width of the image view 
      imageView.setFitHeight(300); 
      imageView.setFitWidth(2000); 
      
      //Setting the preserve ratio of the image view 
      //imageView.setPreserveRatio(true);  
      
HBox hbox = new HBox(imageView);
        hbox.setPadding(new Insets(12, 12, 12, 12));
        hbox.setSpacing(10);
        hbox.setMinHeight(300);

        return hbox;
    }


public StackPane setchart(double[] ecg,double[] q) {
    StackPane st=new StackPane();
        HBox hbox = new HBox();
        int[]x={100,200,300};
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setMinHeight(300);
        
         final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        
         xAxis.setLabel("Samle");
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
      
        lineChart.setTitle("Ecg");
             
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Portfolio 1");
     for (int i=0;i<2160;i=i+10){
       series1.getData().add(new XYChart.Data(i,ecg[i]-800));
     }

        XYChart.Series series2 = new XYChart.Series();
       
         for (int i=0;i<2160;i++){
             if(q[i]==1){
              series2.getData().add(new XYChart.Data(i,ecg[i]-800));
         }else{
                series2.getData().add(new XYChart.Data(i,q[i]));
             }
             
  
     }
        
        
     
          lineChart.getStylesheets().add("Chart.css");
         lineChart.applyCss();

//        series2.getData().add(new XYChart.Data("Jan", 33));
//        series2.getData().add(new XYChart.Data("Feb", 34));
//        series2.getData().add(new XYChart.Data("Mar", 25));
//        series2.getData().add(new XYChart.Data("Apr", 44));
//        series2.getData().add(new XYChart.Data("May", 39));
//        series2.getData().add(new XYChart.Data("Jun", 16));
//        series2.getData().add(new XYChart.Data("Jul", 55));
//        series2.getData().add(new XYChart.Data("Aug", 54));
//        series2.getData().add(new XYChart.Data("Sep", 48));
//        series2.getData().add(new XYChart.Data("Oct", 27));
//        series2.getData().add(new XYChart.Data("Nov", 37));
//        series2.getData().add(new XYChart.Data("Dec", 29));
//        
       series2.setName("blue");
        series2.setName("blfffue");
      
//        for (XYChart.Series<Number, Number> series : lineChart.getData()) {
//
//      if (series.getName().equals("blue")) //if Name is "blue" then continue
//        continue;
//
//      //for all series, take date, each data has Node (symbol) for representing point
//      for (XYChart.Data<Number, Number> data : series.getData()) {
//        // this node is StackPane
//        StackPane stackPane = (StackPane) data.getNode();
//        stackPane.setVisible(false);
//      }
//    }

         lineChart.getData().addAll(series1,series2);
      //lineChart.createSymbolsProperty();
         hbox.getChildren().add(lineChart);
         st.getChildren().add(lineChart);
        return st;
    }
   public StackPane emptybox() {
        StackPane stackPane = new StackPane();
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(30, 30, 30, 30));
        vbox.setSpacing(20);
        txt1 = new Text("");
        txt1.setFont(Font.font("Arial", FontWeight.BOLD,26));
        txt1.setStyle("-fx-text-color:#191b27;");
        txt2 = new Text("");
        txt2.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        txt2.setStyle("-fx-text-color:#191b27;");
        vbox.setAlignment(Pos.CENTER);
        //  vbox.setStyle("-fx-background-color:  	#f3ffd6;");

        vbox.getChildren().addAll(txt1, txt2);

        stackPane.getChildren().add(vbox);
        StackPane.setAlignment(vbox, Pos.CENTER_LEFT);

        return stackPane;
    }
    
    

    public HBox setimage() throws FileNotFoundException {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setMinHeight(300);
        javafx.scene.image.Image image = new javafx.scene.image.Image(new FileInputStream("5.gif"));

        BackgroundImage myBI = new BackgroundImage(image,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        hbox.setBackground(new Background(myBI));

        return hbox;
    }

    private void recordFile(File file,Stage primaryStage) {
        String name = file.getPath();
        System.out.println(name);
        double[] ecg=Result.ReadData(name);
    //  imagegif =setchart();
        
                  double[] q=Result.qrsArray(ecg);
        double x = Result.recordResult(q);
        
String type;
        txt1.setText("Your Heart Rate = " + x + " bpm");
        if(x>60 && x<100){
        type="Normal";
        }else{
             type="Abnormal";
        }
        
        txt2.setText(type);
      resultRecordLayout( primaryStage,ecg,q);
    }

      private void resultRecordLayout(Stage primaryStage,double[] ecg,double[] q){
      
         VBox mainLayout2 = new VBox();
        mainLayout2.setAlignment(Pos.TOP_CENTER);

        StackPane titel = setTitel();
        
  
        StackPane twobtn = set2btn(primaryStage);
         graph = setchart(ecg,q);
        mainLayout2.getChildren().addAll(titel, graph, twobtn,result );
         scene = new Scene(mainLayout2, 800, 600);
        mainLayout.setStyle("-fx-background-color:  	#e5e0e0;");
        primaryStage.setTitle("HEALTH IS WEALTH");
        primaryStage.setScene(scene);
        primaryStage.show(); 
        primaryStage.setFullScreen(true);
      
      }
       private void resultImageLayout(Stage primaryStage,double[] ecg,double[] q){
      
         VBox mainLayout2 = new VBox();
        mainLayout2.setAlignment(Pos.TOP_CENTER);

        StackPane titel = setTitel();
        
  
        StackPane twobtn = set2btn(primaryStage);
         graph = setchart(ecg,q);
        mainLayout2.getChildren().addAll(titel, graph, twobtn,result );
         scene = new Scene(mainLayout2, 800, 600);
        mainLayout.setStyle("-fx-background-color:  	#e5e0e0;");
        primaryStage.setTitle("HEALTH IS WEALTH");
        primaryStage.setScene(scene);
        primaryStage.show(); 
        primaryStage.setFullScreen(true);
      
      }
   private void imgFile(File file) {

        try {
            
            System.out.println(file.getPath());
            
//            matlabJava m = new matlabJava();
//            m.mainprogram();
        String name=file.getPath();
       System.out.println(name);
      double x=Result.imgResult(name);
    txt1.setText("Your Heart Rate = " + x + " bpm");
String type;
    
      if(x>60 && x<100){
//        type="Normal";
txt2.setText("Normal");
      }else{
//             type="Abnormal";
txt2.setText("Abnormal");

        }
        
        } catch (Exception ex) {

            Logger.getLogger(ECG.class.getName()).log(Level.SEVERE, null, ex);
        }}}