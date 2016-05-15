package fx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class RandomWalkCanvas extends Application {

	private Random ran = new Random();

	public static void main(String[] args) {
		launch(args);
	}

	final double width = 600;
	final double height = 600;
	double size = 200;

	@Override
	public void start(Stage ps) throws Exception {

		Canvas canvas = new Canvas(width, height);

		Group grp = new Group();
		grp.getChildren().add(canvas);

		GraphicsContext context = canvas.getGraphicsContext2D();

		Color bgCol = Color.WHITE;
		Scene scene = new Scene(grp, width, height, bgCol);

		ps.setScene(scene);

		ps.show();

		new AnimationTimer() {

			List<Elem> elems;
			Paint bg = Color.rgb(255, 255, 255, 1.0);

			{
				elems = new ArrayList<RandomWalkCanvas.Elem>();
				for (int i = 0; i < 2; i++) {
					elems.add(new Elem(width * 0.1, height * 0.1, createRandomFill()));
				}

			}

			@Override
			public void handle(long now) {
				context.setFill(bg);
				context.fillRect(0, 0, width, height);
				for (Elem elem : elems) {
					double dx = ranDiff();
					double dy = ranDiff();
					elem.addX(dx);
					elem.addY(dy);
					//context.setFill(elem.p);
					//context.fillRect(elem.x, elem.y, size, size);
					context.drawImage(new Image("fish.png"), elem.x, elem.y);
					//System.out.println(elem);
				}
			}
		}.start();
	}

	protected Paint createRandomFill() {
		Color re = Color.hsb(ran.nextInt(360), 1.0, 0.5, 0.1);
		return re;
	}

	protected double ranDiff() {
		double span = 50;
		return ran.nextDouble() * span - span * 0.5;
	}

	private class Elem {

		private double x;
		private double y;
		private Paint p;

		public Elem(double x, double y, Paint p) {
			super();
			this.x = x;
			this.y = y;
			this.p = p;
		}

		public void addX(double dx) {
			if (x < 0 - size) x = 0;
			if (x > width + size) x = width;
			x += dx;
		}
		
		public void addY(double dy) {
			if (y < 0 - size) y = 0;
			if (y > height + size) y = height;
			y += dy;
		}
		
		@Override
		public String toString() {
			return "Elem [x=" + x + ", y=" + y + "]";
		}
	}

}
