package br.com.danielhabib.snake.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

import br.com.danielhabib.snake.rules.AMovingRules;
import br.com.danielhabib.snake.rules.Direction;
import br.com.danielhabib.snake.rules.IRule;
import br.com.danielhabib.snake.rules.Piece;
import br.com.danielhabib.snake.rules.RulesManager;
import br.com.danielhabib.snake.rules.Snake;

public class Splash implements Screen {

	private SpriteBatch batch;
	private Game game;
	private Stage stage;
	private Stack<Snake> snakes;
	private static final int SIZE = 16;
	private AMovingRules movingRules;
	private float time;
	private Sprite boxSprite;
	private OrthographicCamera camera;
	private Texture boxTexture;
	private Texture headTexture;
	private Texture tailTexture;
	private Texture pieceTexture;
	private DrawableManager drawingManager;
	private RulesManager rulesManager;

	public Splash(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true);
		stage = new Stage();
		drawingManager = new DrawableManager();

		headTexture = new Texture(Gdx.files.internal("head.png"));
		tailTexture = new Texture(Gdx.files.internal("tail.png"));
		pieceTexture = new Texture(Gdx.files.internal("circle.png"));

		snakes = new Stack<Snake>();

		BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
		LabelStyle labelStyle = new LabelStyle(font, Color.ORANGE);
		Label title = new Label("OMG! Crazy Snakes!", labelStyle);
		Skin skin = new Skin(new TextureAtlas("buttons.pack"));
		TextButtonStyle buttonStyle = newSnakeButtonStyle(skin);
		TextButton playButton = newButton("Go go go!", buttonStyle);
		TextButton quitButton = newButton("I'm out!", buttonStyle);
		Table table = new Table();

		title.setFontScale(1);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.add(title);
		table.getCell(title).spaceBottom(100);
		table.row();
		table.add(playButton).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getHeight() / 10);
		table.getCell(playButton).spaceBottom(10);
		table.row();
		table.add(quitButton).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getHeight() / 10);
		table.getCell(quitButton).spaceBottom(10);
		// table.debug();

		playButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new SnakeScreen(game));
				stage.clear();
				return true;
			}
		});
		quitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
				return true;
			}
		});

		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);

		boxTexture = new Texture(Gdx.files.internal("box.png"));
		boxSprite = new Sprite(boxTexture);
		setSizeAndFlip(boxSprite);
		boxSprite.setColor(Color.YELLOW);
		rulesManager = new RulesManager();

		snakes = newRandomSnakes();
		// movingRules = new RandomMovingRules(new MovingRules(), new
		// BoingMovingRules());

		rulesManager.addRule(new IRule() {

			private Snake turnLeft(Snake snake) {
				Map<Direction, Direction> leftTurningOffsetMap = new HashMap<Direction, Direction>();
				leftTurningOffsetMap.put(Direction.UP, Direction.LEFT);
				leftTurningOffsetMap.put(Direction.LEFT, Direction.DOWN);
				leftTurningOffsetMap.put(Direction.DOWN, Direction.RIGHT);
				leftTurningOffsetMap.put(Direction.RIGHT, Direction.UP);

				return snake.turn(leftTurningOffsetMap.get(snake.getDirection()));
			}

			private Snake turnRight(Snake snake) {
				Map<Direction, Direction> rightTurningOffsetMap = new HashMap<Direction, Direction>();
				rightTurningOffsetMap.put(Direction.UP, Direction.RIGHT);
				rightTurningOffsetMap.put(Direction.RIGHT, Direction.DOWN);
				rightTurningOffsetMap.put(Direction.DOWN, Direction.LEFT);
				rightTurningOffsetMap.put(Direction.LEFT, Direction.UP);

				return snake.turn(rightTurningOffsetMap.get(snake.getDirection()));
			}

			@Override
			public Snake update(Snake snake) {
				double random = Math.random();
				if (random < 0.3) {
					return turnLeft(snake);
				} else if (random > 0.8) {
					return turnRight(snake);
				} else {
					return snake;
				}
			}
		});

		// rulesManager.addRule(movingRules);
		drawingManager.addDrawables(snakes);

	}

	private TextButton newButton(String text, TextButtonStyle buttonStyle) {
		TextButton button = new TextButton(text, buttonStyle);
		return button;
	}

	private TextButtonStyle newSnakeButtonStyle(Skin skin2) {
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin2.getDrawable("button");
		buttonStyle.over = skin2.getDrawable("buttonpressed");
		buttonStyle.down = skin2.getDrawable("buttonpressed");
		buttonStyle.font = new BitmapFont(Gdx.files.internal("font.fnt"));
		return buttonStyle;
	}

	private Stack<Snake> newRandomSnakes() {
		Stack<Snake> stack = new Stack<Snake>();
		stack.push(newSnakeAtXY(10, 1, Direction.RIGHT, headTexture, boxTexture, tailTexture));
		stack.push(newSnakeAtXY(Gdx.graphics.getWidth() / SIZE - 10, 1, Direction.LEFT, tailTexture, headTexture,
				tailTexture));
		stack.push(newSnakeAtXY(10, Gdx.graphics.getHeight() / SIZE - 1, Direction.RIGHT, headTexture, headTexture,
				tailTexture));
		stack.push(
				newSnakeAtXY(Gdx.graphics.getWidth() / SIZE - 10, Gdx.graphics.getHeight() / SIZE - 1,
						Direction.LEFT, headTexture, pieceTexture, tailTexture));
		stack.push(
				newSnakeAtXY(Gdx.graphics.getWidth() / SIZE / 2, Gdx.graphics.getHeight() / SIZE / 2,
						Direction.RIGHT, headTexture, boxTexture, tailTexture));
		return stack;
	}

	// FIXME: DRY
	private Snake newSnakeAtXY(int x, int y, Direction direction, Texture headTexture, Texture pieceTexture,
			Texture tailTexture) {
		Stack<Piece> pieces = new Stack<Piece>();
		pieces.push(new Head(new Vector2(x, y), direction, headTexture));
		int size = 7;
		int i = 1;
		for (i = 1; i < size - 1; i++) {
			pieces.push(new Piece(new Vector2(x - i, y), direction, pieceTexture));
		}
		pieces.push(new Tail(new Vector2(x - i, y), direction, tailTexture));
		Snake snake = new Snake(pieces, pieceTexture);
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

		// Managing FPS
		time += delta;
		if (time > 0.125) {
			for (Snake snake : snakes) {
				rulesManager.applyRules(snake);
			}
			time = 0;
		}

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		drawingManager.update();

		batch.begin();

		drawingManager.render(batch);

		batch.end();

		stage.act();
		stage.draw();
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
		batch.dispose();
	}

	private void dispose(Sprite sprite) {
		sprite.getTexture().dispose();
	}
}
