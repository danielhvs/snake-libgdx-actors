package br.com.danielhabib.snake.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Splash implements Screen {

	private SpriteBatch batch;
	private Sprite sprite;
	private Game game;
	private Stage stage;
	private Skin skin;
	private TextureAtlas buttonAtlas;
	private TextButtonStyle buttonStyle;
	private TextButton button;

	public Splash(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));

		skin = new Skin();
		buttonAtlas = new TextureAtlas("buttons.pack");
		skin.addRegions(buttonAtlas);
		buttonStyle = new TextButtonStyle();
		buttonStyle.up = skin.getDrawable("button");
		buttonStyle.over = skin.getDrawable("buttonpressed");
		buttonStyle.down = skin.getDrawable("buttonpressed");
		buttonStyle.font = font;
		button = new TextButton("PLAY", buttonStyle);
		button.setWidth(Gdx.graphics.getWidth() / 3);
		button.setHeight(Gdx.graphics.getHeight() / 6);
		button.setPosition(0, 0);

		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(new SnakeScreen(game));
				stage.clear();
				return true;
			}
		});

		stage = new Stage();
		stage.addActor(button);
		Gdx.input.setInputProcessor(stage);

		batch = new SpriteBatch();
		sprite = new Sprite(new Texture("badlogic.jpg"));
		sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		sprite.draw(batch);
		batch.end();

		stage.act();
		stage.draw();

		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			game.setScreen(new SnakeScreen(game));
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
		dispose(sprite);
		batch.dispose();
	}

	private void dispose(Sprite sprite) {
		sprite.getTexture().dispose();
	}
}
