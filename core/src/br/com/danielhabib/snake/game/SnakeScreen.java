package br.com.danielhabib.snake.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import br.com.danielhabib.snake.rules.AFruitRule;
import br.com.danielhabib.snake.rules.AMovingRules;
import br.com.danielhabib.snake.rules.BoingMovingRules;
import br.com.danielhabib.snake.rules.CounterSnakeListener;
import br.com.danielhabib.snake.rules.Direction;
import br.com.danielhabib.snake.rules.Entity;
import br.com.danielhabib.snake.rules.FruitRule;
import br.com.danielhabib.snake.rules.HoleMovingRules;
import br.com.danielhabib.snake.rules.IRule;
import br.com.danielhabib.snake.rules.MapMovingRules;
import br.com.danielhabib.snake.rules.MovingRules;
import br.com.danielhabib.snake.rules.NOPRule;
import br.com.danielhabib.snake.rules.Piece;
import br.com.danielhabib.snake.rules.PoisonedFruitRule;
import br.com.danielhabib.snake.rules.RotatingEntity;
import br.com.danielhabib.snake.rules.Snake;
import br.com.danielhabib.snake.rules.SnakeController;
import br.com.danielhabib.snake.rules.SnakeDeathRule;
import br.com.danielhabib.snake.rules.SnakeFactory;
import br.com.danielhabib.snake.rules.SnakeListener;
import br.com.danielhabib.snake.rules.StaticEntity;
import br.com.danielhabib.snake.rules.TextFactory;
import br.com.danielhabib.snake.rules.WormHole;

public class SnakeScreen extends AbstractScreen {

	private static final int SIZE = Entity.SIZE;
	private int level;
	private OrthogonalTiledMapRenderer renderer;

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
		Map<Entity, IRule> fruits = new HashMap<Entity, IRule>();
		IRule identityRule = new NOPRule();
		IRule boingRule = new BoingMovingRules(this);
		Texture pieceTexture = null;
		PoisonedFruitRule poisonRule = new PoisonedFruitRule(this);
		Map<Entity, IRule> wallsMap = new HashMap<Entity, IRule>();
		IRule regularFruitRule = new FruitRule(this);
		Texture texture = null;
		Stack<Piece> pieces = new Stack<Piece>();
		List<Piece> piecesList = new ArrayList<Piece>();
		Head head = null;
		Tail tail = null;
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		for (int x = 0; x < layer.getWidth(); x++) {
			for (int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					TiledMapTile tile = cell.getTile();
					Object rule = tile.getProperties().get("rule");
					texture = tile.getTextureRegion().getTexture();
					// FIXME: Polymorfism
					if ("fruit".equals(rule.toString())) {
						fruits.put(new StaticEntity(texture, new Vector2(x, y)), regularFruitRule);
					} else if ("poison".equals(rule.toString())) {
						fruits.put(new StaticEntity(texture, new Vector2(x, y)), poisonRule);
					} else if ("identityRule".equals(rule.toString())) {
						wallsMap.put(new StaticEntity(texture, new Vector2(x, y)), identityRule);
					} else if ("boingRule".equals(rule.toString())) {
						wallsMap.put(new StaticEntity(texture, new Vector2(x, y)), boingRule);
					} else if ("head".equals(rule.toString())) {
						head = new Head(new Vector2(x, y), Direction.RIGHT, texture);
					} else if ("piece".equals(rule.toString())) {
						pieceTexture = texture;
						piecesList.add(new Piece(new Vector2(x, y), Direction.RIGHT, texture));
					} else if ("tail".equals(rule.toString())) {
						tail = new Tail(new Vector2(x, y), Direction.RIGHT, texture);
					}
				}
			}
		}

		pieces.push(head);
		// FIXME: Order pieces!? Indicate only head and tail in the map? Init
		// with only one piece?
		pieces.addAll(piecesList);
		pieces.push(tail);

		Snake snake = new Snake(pieces, pieceTexture);
		AFruitRule fruitRule = new AFruitRule(fruits, snake);
		AMovingRules movingRules = new MapMovingRules(new MovingRules(snake), identityRule, wallsMap, snake,
				layer.getWidth() - 1, layer.getHeight() - 1);
		Actor controller = new SnakeController(movingRules, snake);

		addListenerTo(snake);
		addListenersTo(title);

		addActor(movingRules);
		addActor(controller);
		addActor(fruitRule);
		addActor(snake);
		addActor(title);

		// FIXME: Use this renderer?
		// renderer = new OrthogonalTiledMapRenderer(map);
		// renderer.setView((OrthographicCamera) getCamera());
	}

	private void buildLevel1() {
		BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"));
		LabelStyle labelStyle = new LabelStyle(font, Color.WHITE);
		final Label title = new Label("", labelStyle);
		final Label counter1 = new Label("", labelStyle);
		final Label counter2 = new Label("", labelStyle);
		final Label counter3 = new Label("", labelStyle);

		Texture headTexture = new Texture(Gdx.files.internal("head.png"));
		Texture tailTexture = new Texture(Gdx.files.internal("tail.png"));
		Texture pieceTexture = new Texture(Gdx.files.internal("circle.png"));
		Texture wallTexture = new Texture(Gdx.files.internal("box.png"));
		Texture appleTexture = new Texture(Gdx.files.internal("apple.png"));
		Texture poisonTexture = new Texture(Gdx.files.internal("poison.png"));
		Texture holeTexture = new Texture(Gdx.files.internal("hole.jpg"));

		final Snake snake = SnakeFactory.newSnakeAtXY(5, 1, Direction.RIGHT, headTexture, pieceTexture, tailTexture);

		IRule boingMovingRules = new BoingMovingRules(this);
		IRule snakeDeathRule = new SnakeDeathRule();
		IRule regularFruitRule = new FruitRule(this);
		IRule poisonedFruitRule = new PoisonedFruitRule(this);
		IRule boingFruitRule = new BoingMovingRules(this);
		IRule identityRule = new NOPRule();

		Entity lastHole = new StaticEntity(holeTexture, new Vector2(13, 12));
		Entity initialHole = new RotatingEntity(holeTexture, new Vector2(3, 8), 100);

		Map<Entity, IRule> map = createMap(wallTexture, boingMovingRules, identityRule);
		Map<Entity, IRule> fruits = createFruits(appleTexture, poisonTexture, wallTexture, regularFruitRule,
				poisonedFruitRule, boingFruitRule);

		AFruitRule fruitsRule = new AFruitRule(fruits, snake);
		AMovingRules realMovingRules = new HoleMovingRules(new WormHole(initialHole, lastHole), snake);
		AMovingRules movingRules = new MapMovingRules(realMovingRules, identityRule, map, snake, 0, 0);
		Actor controller = new SnakeController(movingRules, snake);

		counter1.addListener(new CounterSnakeListener(0) {
			@Override
			public boolean addTail(Actor source, Event event) {
				incrementCounter();
				TextFactory.addCountingAnimation(counter1, String.valueOf(getCounter()), Color.WHITE, Entity.SIZE,
						Gdx.graphics.getHeight() - 2 * Entity.SIZE);
				return false;
			}
		});
		counter2.addListener(new CounterSnakeListener(0) {
			@Override
			public boolean removeTail(Actor source, Event event) {
				incrementCounter();
				TextFactory.addCountingAnimation(counter2, String.valueOf(getCounter()), Color.RED, 2 * Entity.SIZE,
						Gdx.graphics.getHeight() - 2 * Entity.SIZE);
				return false;
			}
		});
		counter3.addListener(new CounterSnakeListener(0) {
			@Override
			public boolean revert(Actor source, Event event) {
				incrementCounter();
				TextFactory.addCountingAnimation(counter3, String.valueOf(getCounter()), Color.ORANGE, 3 * Entity.SIZE,
						Gdx.graphics.getHeight() - 2 * Entity.SIZE);
				return false;
			}
		});

		addListenersTo(title);

		addListenerTo(snake);

		addActor(movingRules);
		addActor(controller);
		addActor(fruitsRule);
		addActor(snake);
		addActor(title);
		addActor(counter1);
		addActor(counter2);
		addActor(counter3);
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

	private void addListenerTo(final Snake snake) {
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

	private Map<Entity, IRule> createFruits(Texture appleTexture, Texture poisonTexture, Texture boingTexture,
			IRule regularFruitRule, IRule poisonedFruitRule, IRule boingFruitRule) {
		Map<Entity, IRule> fruits = new HashMap<Entity, IRule>();
		for (int i = 0; i < 12; i++) {
			fruits.put(new StaticEntity(appleTexture, new Vector2(8, i + 1)), regularFruitRule);
		}
		for (int i = 0; i < 12; i++) {
			fruits.put(new RotatingEntity(poisonTexture, new Vector2(i + 8, i + 2), -2), poisonedFruitRule);
		}
		for (int i = 0; i < 12; i++) {
			fruits.put(new RotatingEntity(boingTexture, new Vector2(i + 5, i + 3), .2f), boingFruitRule);
		}

		return fruits;
	}

	private Map<Entity, IRule> createMap(Texture wallTexture, IRule boingMovingRules, IRule identityRule) {
		Map<Entity, IRule> map = new HashMap<Entity, IRule>();
		int lastX = -1 + Gdx.graphics.getWidth() / SIZE;
		int lastY = -1 + Gdx.graphics.getHeight() / SIZE;
		for (int x = 1; x < lastX; x++) {
			Entity entity = new RotatingEntity(wallTexture, new Vector2(x, 0), 2);
			map.put(entity, new DestroyEntityRule(entity, map, boingMovingRules));
			map.put(new StaticEntity(wallTexture, new Vector2(x, lastY)), identityRule);
		}
		for (int y = 0; y <= lastY; y++) {
			map.put(new StaticEntity(wallTexture, new Vector2(lastX, y)), boingMovingRules);
		}
		return map;
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
		// TODO Auto-generated method stub
	}

}
