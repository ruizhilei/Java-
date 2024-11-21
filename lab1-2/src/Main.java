import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        HBox controlBox = new HBox(10);
        controlBox.setLayoutY(10);


        // 创建赛车对象
        Car car1 = new Car("Car1", 10);

        // 显示赛车速度的文本
        Text car1Text = new Text(car1.getName() + ": ");
        TextField speedField = new TextField(String.valueOf(car1.getSpeed()));
        speedField.setPrefWidth(50);

        // 当文本框的值改变时更新赛车速度
        speedField.setOnAction(e -> {
            try {
                int speed = Integer.parseInt(speedField.getText());
                if (speed < 1 || speed > 100) throw new NumberFormatException();
                car1.setSpeed(speed);
            } catch (NumberFormatException ex) {
                speedField.setText(String.valueOf(car1.getSpeed()));
            }
        });

        // 将文本框和赛车速度显示在控制面板上
        controlBox.getChildren().addAll(car1Text, speedField);

        // 动画区域
        Pane carPane = createCar();
        carPane.setLayoutY(100);

        // 启动动画
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            carPane.setLayoutX(carPane.getLayoutX() + car1.getSpeed() * 0.1);
            if (carPane.getLayoutX() > root.getWidth()) {
                carPane.setLayoutX(-150); // 重置到初始位置
            }
        }));
        // 循环播放
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        // 将动画区域和控制面板添加到根节点上
        root.getChildren().addAll(controlBox, carPane);

        Scene scene = new Scene(root, 600, 200);
        primaryStage.setTitle("Car Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * 创建赛车的组合图形
     */
    private Pane createCar(){
        Pane carPane = new Pane();

        // 车身
        Rectangle body = new Rectangle(60, 10, Color.BLUE);
        body.setLayoutY(10);

        // 车头 (梯形)
        Polygon head = new Polygon();
        head.getPoints().addAll(
                40.0, 0.0,  // 右上角
                20.0, 0.0,  // 左上角
                10.0, 10.0,  // 左下角
                50.0, 10.0    // 右下角
        );
        head.setFill(Color.RED);

        // 车轮
        Circle wheel1 = new Circle(7, Color.BLACK);
        wheel1.setLayoutY(25);
        wheel1.setLayoutX(15);

        Circle wheel2 = new Circle(7, Color.BLACK);
        wheel2.setLayoutY(25);
        wheel2.setLayoutX(45);
        // 将车身、车头、车轮添加到车窗上
        carPane.getChildren().addAll(body, head, wheel1, wheel2);
        return carPane;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

class Car {
    private String name;
    private int speed; // 速度范围 1-100

    public Car(String name, int speed) {
        this.name = name;
        setSpeed(speed);
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed < 1) {
            this.speed = 1;
        } else if (speed > 100) {
            this.speed = 100;
        } else {
            this.speed = speed;
        }
    }
}
