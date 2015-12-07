package br.com.danielhabib.snake.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

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
import br.com.danielhabib.snake.rules.PoisonedFruitRule;
import br.com.danielhabib.snake.rules.RotatingEntity;
import br.com.danielhabib.snake.rules.Snake;
import br.com.danielhabib.snake.rules.SnakeController;
import br.com.danielhabib.snake.rules.SnakeDeathRule;
import br.com.danielhabib.snake.rules.SnakeFactory;
import br.com.danielhabib.snake.rules.WormHole;

public class SnakeScreen implements Screen {

	private static final int SIZE = Entity.SIZE;
	private Game game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Snake snake;
	private SnakeController controller;
	private AMovingRules movingRules;
	private float fps = 8;
	private float threshold = 0.125f;
	private Stage stage;

	public SnakeScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true);
		stage = new Stage(new ScreenViewport(camera), batch);

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

		Entity lastHole = new Entity(holeTexture, new Vector2(13, 12));
		Entity initialHole = new RotatingEntity(holeTexture, new Vector2(3, 8), 100);
		Entity apple = new Entity(appleTexture, new Vector2(3, 4));
		Entity poison = new Entity(poisonTexture, new Vector2(8, 17));
		Entity boing = new Entity(boingTexture, new Vector2(15, 12));

		Map<Entity, IRule> map = new HashMap<Entity, IRule>();
		int lastX = -1 + Gdx.graphics.getWidth() / SIZE;
		int lastY = -1 + Gdx.graphics.getHeight() / SIZE;
		for (int x = 1; x < lastX; x++) {
			Entity entity = new RotatingEntity(wallTexture, new Vector2(x, 0), 2);
			map.put(entity, new DestroyEntityRule(entity, map, boingMovingRules));
			map.put(new Entity(wallTexture, new Vector2(x, lastY)), identityRule);
		}
		for (int y = 0; y <= lastY; y++) {
			// map.put(new Entity(wallTexture, new Vector2(0, y)), new
			// MovingRules());
			map.put(new Entity(wallTexture, new Vector2(lastX, y)), boingFruitRule);
		}

		Map<Entity, IRule> fruits = new HashMap<Entity, IRule>();
		fruits.put(apple, regularFruitRule);
		fruits.put(poison, poisonedFruitRule);
		fruits.put(boing, boingFruitRule);
		for (int i = 0; i < 12; i++) {
			fruits.put(new Entity(appleTexture, new Vector2(8, i + 1)), regularFruitRule);
		}

		// Rules
		snake = SnakeFactory.newSnakeAtXY(5, 1, Direction.RIGHT, headTexture, pieceTexture, tailTexture);
		AFruitRule fruitsRule = new AFruitRule(fruits, snake);
		AMovingRules realMovingRules = new HoleMovingRules(new WormHole(initialHole, lastHole), snake);
		movingRules = new MapMovingRules(realMovingRules, identityRule, map, snake);
		controller = new SnakeController(movingRules, snake);

		stage.addActor(controller);
		stage.addActor(fruitsRule);
		stage.addActor(movingRules);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		specialControls();
		stage.act();

		// Drawing
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		stage.draw();
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
		batch.dispose();
	}

}
