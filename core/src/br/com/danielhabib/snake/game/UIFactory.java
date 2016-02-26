package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class UIFactory {

	public static ImageButton createButton(Texture texture) {
		return new ImageButton(new TextureRegionDrawable(new TextureRegion(texture)));
	}

	public static InputListener createListener(final ScreenEnum dstScreen, final Object... params) {
		return new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				ScreenManager.getInstance().showScreen(dstScreen, params);
				return false;
			}
		};
	}

	public static void addButtonToTable(VisTextButton playButton, Table table) {
		int width = Gdx.graphics.getWidth() / 4;
		int height = Gdx.graphics.getHeight() / 10;
		table.row();
		table.add(playButton).width(width).height(height);
		table.getCell(playButton).spaceBottom(10);
	}

	// FIXME: Use pool to manage memory
	public static Label newLabel() {
		BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
		LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);
		return new Label("", labelStyle);
	}

}
