package br.com.danielhabib.snake.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class LevelSelectScreen extends AbstractScreen {

	@Override
	public void buildStage() {

		Label title = new VisLabel("OMG! Crazy Snakes!");

		VisTextButton playButton = ButtonFactory.newButton("First level! GO!");
		VisTextButton backButton = ButtonFactory.newButton("<-- Back");

		Table table = new Table();

		title.setFontScale(1);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		table.add(title);
		table.getCell(title).spaceBottom(100);
		table.row();
		table.add(playButton).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getHeight() / 10);
		table.getCell(playButton).spaceBottom(10);
		table.row();
		table.add(backButton).width(Gdx.graphics.getWidth() / 4).height(Gdx.graphics.getHeight() / 10);
		table.getCell(backButton).spaceBottom(10);

		playButton.addListener(UIFactory.createListener(ScreenEnum.GAME));
		backButton.addListener(UIFactory.createListener(ScreenEnum.MAIN_MENU));

		addActor(table);

	}

}
