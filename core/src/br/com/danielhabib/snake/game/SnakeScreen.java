package br.com.danielhabib.snake.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import br.com.danielhabib.snake.AMovingRules;
import br.com.danielhabib.snake.BoingMovingRules;
import br.com.danielhabib.snake.Direction;
import br.com.danielhabib.snake.FruitRule;
import br.com.danielhabib.snake.Hole;
import br.com.danielhabib.snake.HoleMovingRules;
import br.com.danielhabib.snake.Point;
import br.com.danielhabib.snake.PoisonedFruitRule;
import br.com.danielhabib.snake.Snake;
import br.com.danielhabib.snake.SnakeController;

public class SnakeScreen implements Screen {

	private Sprite boxSprite;
	private Sprite directionSprite;
	private Sprite appleSprite;
	private Sprite poisonedSprite;
	private Sprite holeSprite;
	private static final int SIZE = 32;
	private Game game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Snake snake;
	private FruitRule fruitRule;
	private PoisonedFruitRule poisonRule;
	private float time;
	private SnakeController controller;
	private AMovingRules movingRules;
	private Hole hole;
	private List<Point> map;

	private static final int FRAME_COLS = 6; // #1
	private static final int FRAME_ROWS = 5; // #2

	Animation walkAnimation; // #3
	Texture walkSheet; // #4
	TextureRegion[] walkFrames; // #5
	TextureRegion currentFrame; // #7
	float stateTime; // #8

	public SnakeScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		walkSheet = new Texture(Gdx.files.internal("animation.png")); // #9
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS); // #10
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.025f, walkFrames); // #11
		stateTime = 0f; // #13

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true);

		// Map
		map = new ArrayList<Point>();
		int lastX = -1 + Gdx.graphics.getWidth() / SIZE;
		int lastY = -1 + Gdx.graphics.getHeight() / SIZE;
		for (int x = 0; x < lastX; x++) {
			map.add(new Point(x, 0));
			map.add(new Point(x, lastY));
		}
		for (int y = 0; y <= lastY; y++) {
			map.add(new Point(0, y));
			map.add(new Point(lastX, y));
		}

		// Snake
		boxSprite = new Sprite(new Texture(Gdx.files.internal("box.png")));
		directionSprite = new Sprite(new Texture(Gdx.files.internal("box.png")));
		setSizeAndFlip(boxSprite);
		directionSprite.setSize(SIZE / 4, SIZE / 4);

		// Apples
		appleSprite = new Sprite(new Texture(Gdx.files.internal("apple.png")));
		poisonedSprite = new Sprite(new Texture(Gdx.files.internal("poison.png")));
		setSizeAndFlip(appleSprite);
		setSizeAndFlip(poisonedSprite);

		// Hole
		holeSprite = new Sprite(new Texture(Gdx.files.internal("hole.jpg")));
		setSizeAndFlip(holeSprite);

		// Map
		snake = new Snake(5, 1, Direction.RIGHT.getDirection()).addTail().addTail().addTail();
		fruitRule = new FruitRule(new Point(10, 20));
		poisonRule = new PoisonedFruitRule(new Point(20, 10));
		hole = new Hole(new Point(3, 8), new Point(24, 14));
		AMovingRules holeMovingRules = new HoleMovingRules(hole);
		controller = new SnakeController(holeMovingRules);
		// movingRules = new MapMovingRules(holeMovingRules, map);
		// movingRules = new MirrorMapMovingRules(holeMovingRules, lastX,
		// lastY);
		movingRules = new BoingMovingRules(holeMovingRules, lastX, lastY);
	}

	private void setSizeAndFlip(Sprite sprite) {
		sprite.setSize(SIZE, SIZE);
		sprite.flip(false, true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stateTime += delta; // #15
		currentFrame = walkAnimation.getKeyFrame(stateTime, true); // #16

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			game.setScreen(new Splash(game));
		}

		// Managing FPS
		time += delta;
		if (time > 0.125f) {
			snake = movingRules.update(snake);
			time = 0;
		}

		// Applying Rules
		controlSnake();

		snake = fruitRule.update(snake);
		snake = poisonRule.update(snake);

		// Drawing
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		// Snake direction
		directionSprite.setPosition(SIZE * (snake.getPosition().getX() + snake.getDirection().getX()),
				SIZE * (snake.getPosition().getY() + snake.getDirection().getY()));
		// Fruits
		appleSprite.setPosition(fruitRule.getFruitPosition().getX() * SIZE, fruitRule.getFruitPosition().getY() * SIZE);
		poisonedSprite.setPosition(poisonRule.getFruitPosition().getX() * SIZE,
				poisonRule.getFruitPosition().getY() * SIZE);

		// Hole
		Point point = hole.getInitialPoint();
		holeSprite.setPosition(point.getX() * SIZE, point.getY() * SIZE);
		holeSprite.draw(batch);
		point = hole.getFinalPoint();
		holeSprite.setPosition(point.getX() * SIZE, point.getY() * SIZE);
		holeSprite.draw(batch);

		// Snake
		for (Point position : snake.getPositions()) {
			boxSprite.setPosition(position.getX() * SIZE, position.getY() * SIZE);
			boxSprite.draw(batch);
		}

		// Map
		for (Point mapPoint : map) {
			boxSprite.setPosition(mapPoint.getX() * SIZE, mapPoint.getY() * SIZE);
			boxSprite.draw(batch);
		}

		// Draw to batch
		directionSprite.draw(batch);
		appleSprite.draw(batch);
		poisonedSprite.draw(batch);

		batch.draw(currentFrame, 50, 50); // #17

		batch.end();
	}

	private void controlSnake() {
		if (Gdx.input.isKeyPressed(Input.Keys.M)) {
			snake = movingRules.update(snake);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
			snake = movingRules.update(snake);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
			snake = movingRules.turnLeft(snake);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
			snake = movingRules.turnRight(snake);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			snake = controller.left(snake);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			snake = controller.right(snake);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			snake = controller.up(snake);
		} else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
			snake = controller.down(snake);
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		dispose(boxSprite);
		dispose(directionSprite);
		dispose(appleSprite);
		dispose(poisonedSprite);
		dispose(holeSprite);
		batch.dispose();
	}

	private void dispose(Sprite sprite) {
		sprite.getTexture().dispose();
	}

}
