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

import br.com.danielhabib.snake.MovingRules;
import br.com.danielhabib.snake.Point;
import br.com.danielhabib.snake.Snake;

public class SnakeScreen implements Screen {

	private static final int SIZE = 16;
	private Game game;
	private Texture boxImage;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Snake snake;
	private MovingRules movingRules;
	private Sprite boxSprite;
	private Sprite directionSprite;

	public SnakeScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true);

		boxImage = new Texture(Gdx.files.internal("box.png"));
		boxSprite = new Sprite(boxImage);
		boxSprite.setSize(SIZE, SIZE);

		directionSprite = new Sprite(boxImage);
		directionSprite.setSize(SIZE / 4, SIZE / 4);

		snake = new Snake(5, 1).addTail().addTail().addTail();
		movingRules = new MovingRules();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			game.setScreen(new Splash(game));
		}
		if (Gdx.input.isKeyPressed(Input.Keys.M)) {
			snake = movingRules.update(snake);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			snake = movingRules.turnLeft(snake);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			snake = movingRules.turnRight(snake);
		}

		// Drawing
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Point position : snake.getPositions()) {
			boxSprite.setPosition(position.getX() * SIZE, position.getY() * SIZE);
			boxSprite.draw(batch);
		}
		directionSprite.setPosition(SIZE * (snake.getPosition().getX() + snake.getDirection().getX()),
				SIZE * (snake.getPosition().getY() + snake.getDirection().getY()));
		directionSprite.draw(batch);
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
