package fx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RandomWalkWithBinding extends Application {

	private Random ran = new Random();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage ps) throws Exception {
		double width = 800;
		double height = 800;

		List<Node> nodes = new ArrayList<>();
		List<Elem> elems = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			Elem e = new Elem(width * 0.5, height * 0.5);

			Color col = Color.hsb(ran.nextDouble() * 360, 1, 1.0, 0.1);
			for (int j = 10; j < 190; j += 15) {
				Node node = new Circle(j, col);
				node.translateXProperty().bind(e.x);
				node.translateYProperty().bind(e.y);

				nodes.add(node);

			}

			elems.add(e);
		}

		Group grp = new Group();
		grp.getChildren().addAll(nodes);
		Color bgCol = Color.BLACK;
		Scene scene = new Scene(grp, width, height, bgCol);
		ps.setScene(scene);
		ps.show();

		new AnimationTimer() {

			@Override
			public void handle(long now) {
				pause(50);
				for (Elem elem : elems) {
					double dx = ranDiff();
					double dy = ranDiff();
					elem.move(dx, dy);
				}
			}

			private void pause(int i) {
				try {
					Thread.sleep(i);
				} catch (InterruptedException e) {
				}
			}
		}.start();
	}

	protected double ranDiff() {
		double span = 15;
		return ran.nextDouble() * span - span * 0.5;
	}

	private class Elem {

		DoubleProperty x;
		DoubleProperty y;

		public Elem(double x, double y) {
			super();
			this.x = new SimpleDoubleProperty(x);
			this.y = new SimpleDoubleProperty(y);
		}

		@Override
		public String toString() {
			return "Elem [x=" + x.get() + ", y=" + y.get() + "]";
		}

		void move(double dx, double dy) {
			x.set(x.get() + dx);
			y.set(y.get() + dy);
		}
	}

}
