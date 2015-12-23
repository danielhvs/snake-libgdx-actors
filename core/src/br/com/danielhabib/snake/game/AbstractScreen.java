package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public abstract class AbstractScreen extends Stage implements Screen {

	protected Sound soundDied;
	protected Sound soundApple;
	protected Sound soundRevert;
	protected Sound soundWalking;
	private Sound music;

	protected AbstractScreen() {
		super(new ScreenViewport(new OrthographicCamera()), new SpriteBatch());
	}

	public abstract void buildStage();

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		act(delta);
		getCamera().update();
		getBatch().setProjectionMatrix(getCamera().combined);

		draw();
	}

	// FIXME: Dispose.

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		getViewport().update(width, height);
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		// FIXME: Sound manager.
		dispose(soundDied);
		dispose(soundApple);
		dispose(soundRevert);
		dispose(soundWalking);
		super.dispose();
	}

	private void dispose(Sound sound) {
		if (sound != null) {
			sound.stop();
			sound.dispose();
		}
	}

}
