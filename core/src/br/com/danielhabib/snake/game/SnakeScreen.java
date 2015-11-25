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

import br.com.danielhabib.snake.AMovingRules;
import br.com.danielhabib.snake.FruitRule;
import br.com.danielhabib.snake.Point;
import br.com.danielhabib.snake.PoisonedFruitRule;
import br.com.danielhabib.snake.RestrictedMovingRules;
import br.com.danielhabib.snake.Snake;

public class SnakeScreen implements Screen {

	private static final int SIZE = 16;
	private Game game;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Snake snake;
	private AMovingRules movingRules;
	private Sprite boxSprite;
	private Sprite directionSprite;
	private Sprite appleSprite;
	private Sprite poisonedSprite;
	private FruitRule fruitRule;
	private PoisonedFruitRule poisonRule;

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
		poisonedSprite = new Sprite(new Texture(Gdx.files.internal("poisoned.jpg")));
		setSizeAndFlip(appleSprite);
		setSizeAndFlip(poisonedSprite);

		// Map
		snake = new Snake(5, 1).addTail().addTail().addTail();
		movingRules = new RestrictedMovingRules();
		fruitRule = new FruitRule(new Point(10, 20));
		poisonRule = new PoisonedFruitRule(new Point(20, 10));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
