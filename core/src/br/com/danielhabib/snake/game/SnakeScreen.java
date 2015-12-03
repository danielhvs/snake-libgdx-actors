package br.com.danielhabib.snake.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import br.com.danielhabib.snake.rules.AMovingRules;
import br.com.danielhabib.snake.rules.BoingMovingRules;
import br.com.danielhabib.snake.rules.Direction;
import br.com.danielhabib.snake.rules.FruitRule;
import br.com.danielhabib.snake.rules.Hole;
import br.com.danielhabib.snake.rules.HoleMovingRules;
import br.com.danielhabib.snake.rules.Piece;
import br.com.danielhabib.snake.rules.PoisonedFruitRule;
import br.com.danielhabib.snake.rules.Snake;
import br.com.danielhabib.snake.rules.SnakeController;

public class SnakeScreen implements Screen {

	private Sprite boxSprite;
	private Sprite headSprite;
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
	private List<Vector2> map;

	private static final int FRAME_COLS = 6; // #1
	private static final int FRAME_ROWS = 5; // #2

	Animation walkAnimation; // #3
	Texture walkSheet; // #4
	TextureRegion[] walkFrames; // #5
	TextureRegion currentFrame; // #7
	float stateTime; // #8
	private Stage stage;
	private Texture boxTexture;
	private Texture headTexture;

	public SnakeScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true);
		stage = new Stage();

		walkSheet = new Texture(Gdx.files.internal("animation.png")); // #9
		TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS); // #10
		walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				walkFrames[index++] = tmp[i][j];
			}
		}
		walkAnimation = new Animation(0.025f, walkFrames); // #11
		stateTime = 0f; // #13

		// Map
		map = new ArrayList<Vector2>();
		int lastX = -1 + Gdx.graphics.getWidth() / SIZE;
		int lastY = -1 + Gdx.graphics.getHeight() / SIZE;
		for (int x = 0; x < lastX; x++) {
			map.add(new Vector2(x, 0));
			map.add(new Vector2(x, lastY));
		}
		for (int y = 0; y <= lastY; y++) {
			map.add(new Vector2(0, y));
			map.add(new Vector2(lastX, y));
		}

		headTexture = new Texture(Gdx.files.internal("head.png"));
		headSprite = new Sprite(headTexture);
		boxTexture = new Texture(Gdx.files.internal("box.jpg"));
		boxSprite = new Sprite(boxTexture);
		setSizeAndFlip(boxSprite);
		setSizeAndFlip(headSprite);
		headSprite.setOrigin(headSprite.getWidth() / 2, headSprite.getHeight() / 2);

		// Apples
		appleSprite = new Sprite(new Texture(Gdx.files.internal("apple.png")));
		poisonedSprite = new Sprite(new Texture(Gdx.files.internal("poison.png")));
		setSizeAndFlip(appleSprite);
		setSizeAndFlip(poisonedSprite);

		// Hole
		holeSprite = new Sprite(new Texture(Gdx.files.internal("hole.jpg")));
		setSizeAndFlip(holeSprite);

		// Map
		snake = newSnakeAtXY(5, 1, Direction.RIGHT);
		fruitRule = new FruitRule(new Vector2(10, 20));
		poisonRule = new PoisonedFruitRule(new Vector2(20, 10));
		hole = new Hole(new Vector2(3, 8), new Vector2(24, 14));
		AMovingRules realMovingRules = new HoleMovingRules(hole);
		// AMovingRules realMovingRules = new RestrictedMovingRules();
		controller = new SnakeController(realMovingRules);
		// movingRules = new MapMovingRules(realMovingRules, map);
		// movingRules = new MirrorMapMovingRules(holeMovingRules, lastX,
		// lastY);
		movingRules = new BoingMovingRules(realMovingRules, 1, 1, lastX - 1, lastY - 1);
	}

	// FIXME: DRY
	private Snake newSnakeAtXY(int x, int y, Direction direction) {
		Stack<Piece> pieces = new Stack<Piece>();
		pieces.push(new Piece(new Vector2(x, y), direction, headTexture));
		int size = 10;
		for (int i = 1; i < size; i++) {
			pieces.push(new Piece(new Vector2(x - i, y), direction, boxTexture));
		}
		Snake snake = new Snake(pieces);
		return snake;
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

		controlSnake();

		// Managing FPS
		// FIXME: remove when speed is added
		time += delta;
		if (time > 0.125f) {
			// movingRules.update(snake);
			time = 0;
		}

		// Applying Rules

		fruitRule.update(snake);
		poisonRule.update(snake);

		// Drawing
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		// Fruits
		appleSprite.setPosition(fruitRule.getFruitPosition().x * SIZE, fruitRule.getFruitPosition().y * SIZE);
		poisonedSprite.setPosition(poisonRule.getFruitPosition().x * SIZE, poisonRule.getFruitPosition().y * SIZE);

		// Hole
		Vector2 point = hole.getInitialPoint();
		holeSprite.setPosition(point.x * SIZE, point.y * SIZE);
		holeSprite.draw(batch);
		point = hole.getFinalPoint();
		holeSprite.setPosition(point.x * SIZE, point.y * SIZE);
		holeSprite.draw(batch);

		// Snake
		snake.draw(batch);

		// Map
		for (Vector2 mapPoint : map) {
			boxSprite.setPosition(mapPoint.x * SIZE, mapPoint.y * SIZE);
			boxSprite.draw(batch);
		}

		// Draw to batch
		appleSprite.draw(batch);
		poisonedSprite.draw(batch);

		batch.draw(currentFrame, 50, 50); // #17
		stage.draw();

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
		dispose(headSprite);
		dispose(appleSprite);
		dispose(poisonedSprite);
		dispose(holeSprite);
		batch.dispose();
	}

	private void dispose(Sprite sprite) {
		sprite.getTexture().dispose();
	}

}
