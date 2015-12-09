package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;

public class SnakeUIInputProcessor extends InputAdapter {

	private Stage stage;

	public SnakeUIInputProcessor(Stage stage) {
		this.stage = stage;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (Keys.ESCAPE == keycode) {
			if (!showingExitDialog) {
				showDialog();
			}
		}
		return super.keyDown(keycode);
	}

	private static boolean showingExitDialog;

	// FIXME: Performance
	private void showDialog() {
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("dialog.atlas"));
		Skin skin = new Skin();
		skin.addRegions(atlas);
		Window.WindowStyle windowStyle = new Window.WindowStyle();
		BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
		windowStyle.titleFont = font;
		NinePatch background = skin.getPatch("background");
		windowStyle.background = new NinePatchDrawable(background);
		LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);
		Label label = new Label("Do you really want to leave?", labelStyle);
		label.setAlignment(Align.center);
		Dialog dialog = new Dialog("", windowStyle) {
			@Override
			protected void result(Object object) {
				boolean exit = (Boolean) object;
				if (exit) {
					Gdx.app.exit();
				} else {
					remove();
				}
				showingExitDialog = false;
			}

			@Override
			public Dialog show(Stage stage) {
				showingExitDialog = true;
				return super.show(stage);
			}

			@Override
			public void cancel() {
				showingExitDialog = false;
				super.cancel();
			}

			@Override
			public float getPrefHeight() {
				return 500f;
			}
		};
		dialog.button("Yes", true, ButtonFactory.getStyle());
		dialog.button("No", false, ButtonFactory.getStyle());
		dialog.key(Input.Keys.ENTER, true);
		dialog.key(Input.Keys.ESCAPE, false);
		dialog.getContentTable().add(label);
		if (!showingExitDialog) {
			dialog.show(stage);
		}
	}
}
