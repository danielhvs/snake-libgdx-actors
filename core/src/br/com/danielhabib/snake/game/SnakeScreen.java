package br.com.danielhabib.snake.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import br.com.danielhabib.snake.rules.AFruitRule;
import br.com.danielhabib.snake.rules.AMovingRules;
import br.com.danielhabib.snake.rules.BoingWall;
import br.com.danielhabib.snake.rules.Entity;
import br.com.danielhabib.snake.rules.IRule;
import br.com.danielhabib.snake.rules.MapMovingRules;
import br.com.danielhabib.snake.rules.MovingRules;
import br.com.danielhabib.snake.rules.NOPRule;
import br.com.danielhabib.snake.rules.Piece;
import br.com.danielhabib.snake.rules.Snake;
import br.com.danielhabib.snake.rules.SnakeController;
import br.com.danielhabib.snake.rules.SnakeListener;
import br.com.danielhabib.snake.rules.TextFactory;
import br.com.danielhabib.snake.rules.TimingFruitGenerator;
import br.com.danielhabib.snake.rules.Wall;
import br.com.danielhabib.snake.rules.WorldManager;

public class SnakeScreen extends AbstractScreen {

	private static final int SIZE = Entity.SIZE;
	private int level;
	private OrthogonalTiledMapRenderer renderer;
	private TextureManager manager;

	public SnakeScreen(Object... params) {
		this.level = (Integer) params[0];
	}

	@Override
	public void buildStage() {
		buildTiledLevel();
	}

	@Override
	public void draw() {
		// FIXME: Use this renderer?
		// renderer.render();
		super.draw();
	}

	private void buildTiledLevel() {
		BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
		LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);
		final Label title = new Label("", labelStyle);
		TiledMap map = new TmxMapLoader().load("map" + level + ".tmx");
		IRule identityRule = new NOPRule();
		List<EventFirerEntity> fruitsList = new ArrayList<EventFirerEntity>();
		List<EventFirerEntity> wallsList = new ArrayList<EventFirerEntity>();
		List<Actor> worldMap = new ArrayList<Actor>();
		Texture texture = null;
		List<Piece> pieces = new ArrayList<Piece>();
		List<Piece> piecesList = new ArrayList<Piece>();
		manager = new TextureManager();
		FruitBuilder fruitBuilder = new FruitBuilder(manager);
		PoisonBuilder poisonBuilder = new PoisonBuilder(manager);
		WallBuilder wallBuilder = new WallBuilder(manager);
		Head head = null;
		Tail tail = null;
		Texture pieceTexture = null;

		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		for (int x = 0; x < layer.getWidth(); x++) {
			for (int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					TiledMapTile tile = cell.getTile();
					Object rule = tile.getProperties().get("rule");
					texture = tile.getTextureRegion().getTexture();
					manager.put(rule.toString(), texture);
					if ("fruit".equals(rule.toString())) {
						fruitsList.add(new Fruit(texture, new Vector2(x * SIZE, y * SIZE)));
					} else if ("poison".equals(rule.toString())) {
						fruitsList.add(new PoisonedFruit(texture, new Vector2(x * SIZE, y * SIZE)));
					} else if ("identityRule".equals(rule.toString())) {
						wallsList.add(new Wall(texture, new Vector2(x * SIZE, y * SIZE)));
					} else if ("boingRule".equals(rule.toString())) {
						final BoingWall boingWall = new BoingWall(texture, new Vector2(x * SIZE, y * SIZE));
						wallsList.add(boingWall);
						boingWall.addListener(new SnakeListener() {
							@Override
							public boolean revert(Actor actor, Event event) {
								if (boingWall == actor) {
									boingWall.addAction(
											Actions.sequence(Actions.rotateBy(25f, 0.1f), Actions.rotateBy(-25f, 0.1f),
													Actions.rotateBy(-25f, 0.1f), Actions.rotateBy(25f, 0.1f))
											);
								}
								return super.revert(actor, event);

							}
						});

					} else if ("head".equals(rule.toString())) {
						head = new Head(new Vector2(x * SIZE, y * SIZE), texture);
					} else if ("piece".equals(rule.toString())) {
						pieceTexture = texture;
						piecesList.add(new Piece(new Vector2(x * SIZE, y * SIZE), texture));
					} else if ("tail".equals(rule.toString())) {
						tail = new Tail(new Vector2(x * SIZE, y * SIZE), texture);
					}
				}
			}
		}

		pieces.add(head);
		// FIXME: Order pieces!? Indicate only head and tail in the map? Init
		// with only one piece?
		pieces.addAll(piecesList);
		pieces.add(tail);

		Snake snake = new Snake(pieces, pieceTexture, new Vector2(Entity.SIZE, 0));
		AFruitRule fruitRule = new AFruitRule(worldMap, fruitsList, snake);
		AMovingRules movingRules = new MapMovingRules(new MovingRules(snake), identityRule, worldMap, wallsList, snake,
				SIZE * (layer.getWidth() - 1), SIZE * (layer.getHeight() - 1));
		Actor controller = new SnakeController(movingRules, snake);

		addListenersTo(snake);
		addListenersTo(title);

		addActor(movingRules);
		addActor(controller);
		addActor(fruitRule);

		worldMap.add(snake);
		for (Actor actor : wallsList) {
			worldMap.add(actor);
		}
		for (Actor actor : fruitsList) {
			worldMap.add(actor);
		}

		for (Actor actor : worldMap) {
			addActor(actor);
		}

		addActor(title);

		TimingFruitGenerator fruitGenerator = new TimingFruitGenerator(layer, fruitBuilder, fruitRule, layer.getWidth() - 1,
				layer.getHeight() - 1, 4f);
		TimingFruitGenerator poisonGenerator = new TimingFruitGenerator(layer, poisonBuilder, fruitRule, layer.getWidth() - 1,
				layer.getHeight() - 1, 2f);
		TimingFruitGenerator wallGenerator = new TimingFruitGenerator(layer, wallBuilder, (WorldManager) movingRules,
				layer.getWidth() - 1, layer.getHeight() - 1, 6f);

		// addActor(fruitGenerator);
		// addActor(poisonGenerator);
		// addActor(wallGenerator);

		// FIXME: Use this renderer?
		// renderer = new OrthogonalTiledMapRenderer(map);
		// renderer.setView((OrthographicCamera) getCamera());
	}

	private void addListenersTo(final Label title) {
		title.addListener(new SnakeListener() {
			@Override
			public boolean addTail(Actor source, Event event) {
				TextFactory.addNotifyAnimation(title, source, "yummi!", Color.WHITE);
				return false;
			}

			@Override
			public boolean removeTail(Actor source, Event event) {
				TextFactory.addNotifyAnimation(title, source, "ick!", Color.RED);
				return false;
			}

			@Override
			public boolean revert(Actor source, Event event) {
				TextFactory.addNotifyAnimation(title, source, "boing!", Color.YELLOW);
				return false;
			}
		});
	}

	private void addListenersTo(final Snake snake) {
		snake.addListener(new SnakeListener() {
			@Override
			public boolean revert(Actor source, Event event) {
				snake.revert();
				return false;
			}

			@Override
			public boolean addTail(Actor source, Event event) {
				snake.addTail();
				return false;
			}

			@Override
			public boolean removeTail(Actor source, Event event) {
				snake.removeTail();
				return false;
			}
		});
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
		manager.dispose();
	}

}
