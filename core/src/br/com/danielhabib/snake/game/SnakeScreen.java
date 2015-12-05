package br.com.danielhabib.snake.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.rules.AFruitRule;
import br.com.danielhabib.snake.rules.AMovingRules;
import br.com.danielhabib.snake.rules.BoingFruitRule;
import br.com.danielhabib.snake.rules.BoingMovingRules;
import br.com.danielhabib.snake.rules.Direction;
import br.com.danielhabib.snake.rules.Entity;
import br.com.danielhabib.snake.rules.FruitRule;
import br.com.danielhabib.snake.rules.HoleMovingRules;
import br.com.danielhabib.snake.rules.IRule;
import br.com.danielhabib.snake.rules.IdentityRule;
import br.com.danielhabib.snake.rules.MapMovingRules;
import br.com.danielhabib.snake.rules.Piece;
import br.com.danielhabib.snake.rules.PoisonedFruitRule;
import br.com.danielhabib.snake.rules.RotatingEntity;
import br.com.danielhabib.snake.rules.RulesManager;
import br.com.danielhabib.snake.rules.Snake;
import br.com.danielhabib.snake.rules.SnakeController;
import br.com.danielhabib.snake.rules.SnakeDeathRule;
import br.com.danielhabib.snake.rules.WormHole;

public class SnakeScreen implements Screen {

	private static final int SIZE = Entity.SIZE;
	private Game game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Snake snake;
	private float time;
	private SnakeController controller;
	private AMovingRules movingRules;
	private float fps = 8;
	private float threshold = 0.125f;
	private DrawableManager drawingManager;
	private RulesManager rulesManager;

	public SnakeScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		drawingManager = new DrawableManager();
		rulesManager = new RulesManager();

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true);

		Texture headTexture = new Texture(Gdx.files.internal("head.png"));
		Texture tailTexture = new Texture(Gdx.files.internal("tail.png"));
		Texture pieceTexture = new Texture(Gdx.files.internal("circle.png"));
		Texture wallTexture = new Texture(Gdx.files.internal("box.png"));
		Texture appleTexture = new Texture(Gdx.files.internal("apple.png"));
		Texture poisonTexture = new Texture(Gdx.files.internal("poison.png"));
		Texture holeTexture = new Texture(Gdx.files.internal("hole.jpg"));
		Texture boingTexture = new Texture(Gdx.files.internal("circle.png"));

		IRule boingMovingRules = new BoingMovingRules();
		IRule snakeDeathRule = new SnakeDeathRule(game);
		IRule regularFruitRule = new FruitRule();
		IRule poisonedFruitRule = new PoisonedFruitRule();
		IRule boingFruitRule = new BoingFruitRule();
		IRule identityRule = new IdentityRule();

		// Drawables
		Map<Entity, IRule> map = new HashMap<Entity, IRule>();

		int lastX = -1 + Gdx.graphics.getWidth() / SIZE;
		int lastY = -1 + Gdx.graphics.getHeight() / SIZE;
		for (int x = 1; x < lastX; x++) {
			Entity entity = new RotatingEntity(wallTexture, new Vector2(x, 0), 2);
			map.put(entity, new DestroyEntityRule(entity, map, drawingManager, boingMovingRules));
			map.put(new Entity(wallTexture, new Vector2(x, lastY)), boingMovingRules);
		}
		for (int y = 0; y <= lastY; y++) {
			// map.put(new Entity(wallTexture, new Vector2(0, y)), new
			// MovingRules());
			map.put(new Entity(wallTexture, new Vector2(lastX, y)), boingFruitRule);
		}

		Entity lastHole = new Entity(holeTexture, new Vector2(13, 12));
		Entity initialHole = new RotatingEntity(holeTexture, new Vector2(3, 8), 100);
		Entity apple = new Entity(appleTexture, new Vector2(3, 4));
		Entity poison = new Entity(poisonTexture, new Vector2(8, 17));
		Entity boing = new Entity(boingTexture, new Vector2(15, 12));
		Map<Entity, IRule> fruits = new HashMap<Entity, IRule>();
		fruits.put(apple, regularFruitRule);
		fruits.put(poison, poisonedFruitRule);
		fruits.put(boing, boingFruitRule);

		// Rules
		snake = newSnakeAtXY(5, 1, Direction.RIGHT, headTexture, pieceTexture, tailTexture);
		AFruitRule fruitsRule = new AFruitRule(fruits, drawingManager);

		AMovingRules realMovingRules = new HoleMovingRules(new WormHole(initialHole.getPosition(), lastHole.getPosition()));
		controller = new SnakeController(realMovingRules);
		movingRules = new MapMovingRules(realMovingRules, identityRule, map);

		// The ordering matters
		drawingManager.addDrawables(map.keySet());
		drawingManager.addDrawables(fruits.keySet());
		drawingManager.addDrawable(lastHole);
		drawingManager.addDrawable(snake);
		drawingManager.addDrawable(initialHole);

		// The ordering matters
		rulesManager.addRule(movingRules);
		rulesManager.addRule(fruitsRule);
	}

	// FIXME: DRY. Create a snake factory.
	private Snake newSnakeAtXY(int x, int y, Direction direction, Texture headTexture, Texture pieceTexture, Texture tailTexture) {
		Stack<Piece> pieces = new Stack<Piece>();
		pieces.push(new Head(new Vector2(x, y), direction, headTexture));
		int size = 4;
		int i = 1;
		for (i = 1; i < size - 1; i++) {
			pieces.push(new Piece(new Vector2(x - i, y), direction, pieceTexture));
		}
		pieces.push(new Tail(new Vector2(x - i, y), direction, tailTexture));
		Snake snake = new Snake(pieces, pieceTexture);
		return snake;
	}


	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		specialControls();
		controlSnake();

		// Managing FPS
		// This going to be the "speed".
		time += delta;
		if (time > threshold) {
			rulesManager.applyRules(snake);
			time = 0;
		}

		drawingManager.update();

		// Drawing
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		drawingManager.render(batch);

		batch.end();
	}

	private void specialControls() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			game.setScreen(new Splash(game));
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) {
			System.out.println(threshold);
			setFPS(fps * 1.1f);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)) {
			System.out.println(threshold);
			setFPS(fps * 0.9f);
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			System.out.println(threshold);
			threshold = Integer.MAX_VALUE;
		}
	}

	// This will be the "speed"
	private void setFPS(float fps) {
		this.fps = fps;
		this.threshold = 1 / fps;
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
		drawingManager.dispose();
		batch.dispose();
	}

}
