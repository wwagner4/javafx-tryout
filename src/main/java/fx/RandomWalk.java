package fx;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RandomWalk extends Application {
	
	private Random ran = new Random();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage ps) throws Exception {
		Node node = new Circle(10, Color.web("red", 0.5));
		
		Group grp = new Group();
		grp.getChildren().add(node);

		Color bgCol = Color.WHITE;
		Scene scene = new Scene(grp, 600, 600, bgCol);

		ps.setScene(scene);

		ps.show();

		double width = scene.getWidth();
		double height = scene.getHeight();
		node.translateXProperty().set(width * 0.5);
		node.translateYProperty().set(height * 0.5);
		
		new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				double dx = ranDiff();
				double dy = ranDiff();
				double x = node.getTranslateX();
				double y = node.getTranslateY();
				node.translateXProperty().set(x + dx);
				node.translateYProperty().set(y + dy);
			}
		}.start();
	}

	protected double ranDiff() {
		double span = 10;
		return ran.nextDouble() * span - span * 0.5;
	}

}
