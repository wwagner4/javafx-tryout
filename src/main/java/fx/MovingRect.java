package fx;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovingRect extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage ps) throws Exception {
		ps.setTitle("FX");
		Group grp = new Group();
		Node node = new Rectangle(50, 50, Color.web("F0F024", 0.9));
		grp.getChildren().add(node);
		//Color white = Color.WHITE;
		//white.darker();
		Scene scene = new Scene(grp, 800, 600, Color.BLACK);
		ps.setScene(scene);
		ps.show();

		Timeline timeline = new Timeline();
		timeline.setCycleCount(Integer.MAX_VALUE);
		{
			KeyValue kv1 = new KeyValue(node.translateXProperty(), 400);
			KeyValue kv2 = new KeyValue(node.translateYProperty(), 300);
			KeyFrame kf = new KeyFrame(Duration.ZERO, kv1, kv2);
			timeline.getKeyFrames().add(kf);
		}
		{
			KeyValue kv1 = new KeyValue(node.translateXProperty(), 100);
			KeyValue kv2 = new KeyValue(node.translateYProperty(), 200);
			KeyFrame kf = new KeyFrame(Duration.seconds(1), kv1, kv2);
			timeline.getKeyFrames().add(kf);
		}
		{
			KeyValue kv1 = new KeyValue(node.translateXProperty(), 700);
			KeyValue kv2 = new KeyValue(node.translateYProperty(), 600);
			KeyFrame kf = new KeyFrame(Duration.seconds(1.1), kv1, kv2);
			timeline.getKeyFrames().add(kf);
		}
		{
			KeyValue kv1 = new KeyValue(node.translateXProperty(), 400);
			KeyValue kv2 = new KeyValue(node.translateYProperty(), 300);
			KeyFrame kf = new KeyFrame(Duration.seconds(1.5), kv1, kv2);
			timeline.getKeyFrames().add(kf);
		}
		timeline.play();
	}

}
