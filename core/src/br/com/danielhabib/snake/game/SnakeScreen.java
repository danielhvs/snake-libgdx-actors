package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.com.danielhabib.snake.FruitRule;
import br.com.danielhabib.snake.Hole;
import br.com.danielhabib.snake.HoleMovingRules;
import br.com.danielhabib.snake.Point;
import br.com.danielhabib.snake.PoisonedFruitRule;
import br.com.danielhabib.snake.Snake;

public class SnakeScreen implements Screen {

	private Sprite boxSprite;
	private Sprite directionSprite;
	private Sprite appleSprite;
	private Sprite poisonedSprite;
	private Sprite holeSprite;
	private static final int SIZE = 16;
	private Game game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Snake snake;
	private HoleMovingRules movingRules;
	private FruitRule fruitRule;
	private PoisonedFruitRule poisonRule;
	private float time;

	public SnakeScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true);

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
		snake = new Snake(5, 1).addTail().addTail().addTail();
		fruitRule = new FruitRule(new Point(10, 20));
		poisonRule = new PoisonedFruitRule(new Point(20, 10));
		movingRules = new HoleMovingRules(new Hole(new Point(3, 8), new Point(24, 14)));
	}

	private void setSizeAndFlip(Sprite sprite) {
		sprite.setSize(SIZE, SIZE);
		sprite.flip(false, true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
		snake = fruitRule.update(snake);
		snake = poisonRule.update(snake);

		// Drawing
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		directionSprite.setPosition(SIZE * (snake.getPosition().getX() + snake.getDirection().getX()),
				SIZE * (snake.getPosition().getY() + snake.getDirection().getY()));
		appleSprite.setPosition(fruitRule.getFruitPosition().getX() * SIZE, fruitRule.getFruitPosition().getY() * SIZE);
		poisonedSprite.setPosition(poisonRule.getFruitPosition().getX() * SIZE,
				poisonRule.getFruitPosition().getY() * SIZE);

		Point point = movingRules.getHole().getInitialPoint();
		holeSprite.setPosition(point.getX() * SIZE, point.getY() * SIZE);
		holeSprite.draw(batch);

		point = movingRules.getHole().getFinalPoint();
		holeSprite.setPosition(point.getX() * SIZE, point.getY() * SIZE);
		holeSprite.draw(batch);

		for (Point position : snake.getPositions()) {
			boxSprite.setPosition(position.getX() * SIZE, position.getY() * SIZE);
			boxSprite.draw(batch);
		}
		directionSprite.draw(batch);
		appleSprite.draw(batch);
		poisonedSprite.draw(batch);

		batch.end();
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
