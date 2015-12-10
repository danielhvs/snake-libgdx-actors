package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;

public class SnakeUIInputProcessor extends InputAdapter {

	private AbstractScreen screen;
	private VisDialog dialog;

	public SnakeUIInputProcessor(AbstractScreen screen) {
		this.screen = screen;
		this.dialog = buildDialog();
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

	private void showDialog() {
		if (!showingExitDialog) {
			dialog.show(screen);
			screen.pauseGame();
		}
	}

	private VisDialog buildDialog() {
		Label label = new VisLabel("Do you really want to leave?");
		label.setAlignment(Align.center);
		VisDialog dialog = new VisDialog("") {
			@Override
			protected void result(Object object) {
				boolean exit = (Boolean) object;
				if (exit) {
					Gdx.app.exit();
					screen.dispose();
				} else {
					remove();
					screen.unpauseGame();
				}
				showingExitDialog = false;
			}

			@Override
			public VisDialog show(Stage stage) {
				showingExitDialog = true;
				screen.pauseGame();
				return super.show(stage);
			}

			@Override
			public void cancel() {
				showingExitDialog = false;
				screen.unpauseGame();
				super.cancel();
			}

		};

		dialog.getContentTable().add(label);
		dialog.button("Yes", true);
		dialog.button("No", false);
		dialog.key(Input.Keys.ENTER, true);
		dialog.key(Input.Keys.ESCAPE, false);
		return dialog;
	}
}
