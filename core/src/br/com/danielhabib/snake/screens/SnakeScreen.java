package br.com.danielhabib.snake.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.animation.MapGenerator;
import br.com.danielhabib.snake.animation.NotificationPresenter;
import br.com.danielhabib.snake.entity.BoingWall;
import br.com.danielhabib.snake.entity.Entity;
import br.com.danielhabib.snake.entity.EventFirerEntity;
import br.com.danielhabib.snake.entity.Fruit;
import br.com.danielhabib.snake.entity.Piece;
import br.com.danielhabib.snake.entity.PoisonedFruit;
import br.com.danielhabib.snake.entity.RotatingEntity;
import br.com.danielhabib.snake.entity.SpeedFruit;
import br.com.danielhabib.snake.entity.StaticEntity;
import br.com.danielhabib.snake.entity.TextureManager;
import br.com.danielhabib.snake.entity.Wall;
import br.com.danielhabib.snake.entity.WormHole;
import br.com.danielhabib.snake.entity.builders.FruitBuilder;
import br.com.danielhabib.snake.entity.builders.PoisonBuilder;
import br.com.danielhabib.snake.entity.builders.SpeedBuilder;
import br.com.danielhabib.snake.entity.builders.TimingFruitGenerator;
import br.com.danielhabib.snake.entity.builders.WallBuilder;
import br.com.danielhabib.snake.listeners.SnakeEvent;
import br.com.danielhabib.snake.listeners.SnakeEvent.Type;
import br.com.danielhabib.snake.listeners.SnakeListener;
import br.com.danielhabib.snake.rules.AFruitRule;
import br.com.danielhabib.snake.rules.AMovingRules;
import br.com.danielhabib.snake.rules.FruitCountingGoal;
import br.com.danielhabib.snake.rules.HoleMovingRules;
import br.com.danielhabib.snake.rules.IRule;
import br.com.danielhabib.snake.rules.MapMovingRules;
import br.com.danielhabib.snake.rules.NOPRule;
import br.com.danielhabib.snake.rules.Snake;
import br.com.danielhabib.snake.rules.SnakeController;
import br.com.danielhabib.snake.rules.WorldManager;
import br.com.danielhabib.snake.sound.SoundManager;

public class SnakeScreen extends GameScreen {

	private int level;
	private TextureManager textures;

	public SnakeScreen(SoundManager soundManager, Object... params) {
		super(soundManager);
		this.level = (Integer) params[0];
	}

	@Override
	public void buildStage() {
		super.buildStage();
		buildTiledLevel();
	}

	private void buildTiledLevel() {
		TiledMap map = new TmxMapLoader().load("map" + level + ".tmx");
		IRule identityRule = new NOPRule();
		Texture holeTexture = new Texture(Gdx.files.internal("hole.png"));
		Array<EventFirerEntity> fruitsList = Array.with();
		Array<EventFirerEntity> wallsList = Array.with();
		Array<Actor> worldMap = Array.with();
		Texture texture = null;
		Array<Piece> pieces = Array.with();
		Array<Piece> piecesList = Array.with();
		textures = new TextureManager();
		FruitBuilder fruitBuilder = new FruitBuilder(textures);
		SpeedBuilder speedBuilder = new SpeedBuilder(textures);
		PoisonBuilder poisonBuilder = new PoisonBuilder(textures);
		WallBuilder wallBuilder = new WallBuilder(textures);
		Piece head = null;
		Piece tail = null;
		Texture pieceTexture = null;
		List<Entity> inits = new ArrayList<Entity>();
		List<Entity> ends = new ArrayList<Entity>();
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
		Array<WormHole> wormHoles = loadWormHoles(map, holeTexture, inits, ends);

		for (int x = 0; x < layer.getWidth(); x++) {
			for (int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null) {
					TiledMapTile tile = cell.getTile();
					Object rule = tile.getProperties().get("rule");
					texture = tile.getTextureRegion().getTexture();
					textures.put(rule.toString(), texture);
					if ("fruit".equals(rule.toString())) {
						fruitsList
						.add(new Fruit(texture, new Vector2(x * texture.getWidth(), y * texture.getHeight())));
					} else if ("poison".equals(rule.toString())) {
						fruitsList.add(new PoisonedFruit(texture,
								new Vector2(x * texture.getWidth(), y * texture.getHeight())));
					} else if ("speed".equals(rule.toString())) {
						fruitsList.add(new SpeedFruit(texture,
								new Vector2(x * texture.getWidth(), y * texture.getHeight())));
					}
					else if ("identityRule".equals(rule.toString())) {
						final Wall wall = new Wall(texture,
								new Vector2(x * texture.getWidth(), y * texture.getHeight()));
						wallsList.add(wall);
						addListenersTo(wall);
					} else if ("boingRule".equals(rule.toString())) {
						final BoingWall boingWall = new BoingWall(texture,
								new Vector2(x * texture.getWidth(), y * texture.getHeight()));
						wallsList.add(boingWall);
						addListenersTo(boingWall);
					} else if ("head".equals(rule.toString())) {
						head = new Piece(new Vector2(x * texture.getWidth(), y * texture.getHeight()), texture);
					} else if ("piece".equals(rule.toString())) {
						pieceTexture = texture;
						piecesList.add(new Piece(new Vector2(x * texture.getWidth(), y * texture.getHeight()), texture));
					} else if ("tail".equals(rule.toString())) {
						tail = new Piece(new Vector2(x * texture.getWidth(), y * texture.getHeight()), texture);
					}
				}
			}
		}

		pieces.add(head);
		// FIXME: Order pieces!? Indicate only head and tail in the map? Init
		// with only one piece?
		pieces.addAll(piecesList);
		pieces.add(tail);

		snake = new Snake(pieces, pieceTexture, new Vector2(5 * head.getWidth(), 0));
		AMovingRules internalMovingRules = new HoleMovingRules(wormHoles, snake);
		AFruitRule fruitRule = new AFruitRule(worldMap, fruitsList, snake);
		AMovingRules movingRules = new MapMovingRules(internalMovingRules, identityRule, worldMap, wallsList, snake,
				layer.getTileWidth() * (layer.getWidth() - 1), layer.getTileWidth() * (layer.getHeight() - 1));
		Actor controller = new SnakeController(movingRules, snake);

		addListenersTo(snake);

		addActor(movingRules);
		addActor(controller);
		addActor(fruitRule);

		worldMap.add(snake);
		addActor(snake);
		NotificationPresenter notificationPresenter = new NotificationPresenter();
		notificationPresenter.addListeners();
		addActor(notificationPresenter);

		TimingFruitGenerator fruitGenerator = new TimingFruitGenerator(layer, fruitBuilder, fruitRule, layer.getWidth() - 1,
				layer.getHeight() - 1, 3f);
		TimingFruitGenerator poisonGenerator = new TimingFruitGenerator(layer, speedBuilder, fruitRule, layer.getWidth() - 1,
				layer.getHeight() - 1, 5f);
		TimingFruitGenerator wallGenerator = new TimingFruitGenerator(layer, wallBuilder, (WorldManager) movingRules,
				layer.getWidth() - 1, layer.getHeight() - 1, 1f);

		Array<Actor> actors = Array.with();
		actors.addAll(wallsList);
		actors.addAll(fruitsList);
		for (WormHole wormHole : wormHoles) {
			actors.add(wormHole.getFinalPoint());
			actors.add(wormHole.getInitialPoint());
		}
		Array<Actor> piecesActors = Array.with();
		piecesActors.addAll(pieces);
		MapGenerator generator = new MapGenerator(actors, worldMap, piecesActors);
		addActor(generator);

		// FIXME: Separate level configurations.
		if (level == 1) {
			FruitCountingGoal goal = new FruitCountingGoal(2, wallsList.toArray());
			goal.addListeners();
			addActor(fruitGenerator);
			addActor(poisonGenerator);
			addActor(goal);
			// addActor(wallGenerator);
		}
	}

	private void addListenersTo(final Wall wall) {
		wall.addListener(new SnakeListener() {
			@Override
			public boolean handle(Actor source, Type type) {
				if (type.equals(SnakeEvent.Type.win)) {
					wall.addAction(Actions.sequence(bigAndSmallAction()));
				}
				return super.handle(source, type);
			}

			@Override
			public boolean colided(Actor actor, Event event) {
				if (wall == actor) {
					addAnimationTo(wall);
				}
				return super.revert(actor, event);
			}

			private void addAnimationTo(final Wall wall) {
				wall.addAction(
						Actions.sequence(
								bigAndSmallAction(),
								resetLevel()));
			}

			private Action resetLevel() {
				return new Action() {
					@Override
					public boolean act(float delta) {
						ScreenManager.getInstance().showScreen(ScreenEnum.GAME, level);
						return false;
					}
				};
			}

			private RepeatAction bigAndSmallAction() {
				return Actions.repeat(3,
						Actions.sequence(
								Actions.scaleTo(1.5f, 1.5f, .5f),
								Actions.scaleTo(1f, 1f, .5f)
								));
			}
		});
	}

	private void addListenersTo(final BoingWall boingWall) {
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
	}

	private Array<WormHole> loadWormHoles(TiledMap map, Texture holeTexture, List<Entity> inits, List<Entity> ends) {
		Array<WormHole> wormHoles = Array.with();
		if (map.getLayers().getCount() > 1) {
			MapObjects objects = map.getLayers().get(1).getObjects();
			Map<String, Entity> mapa = new HashMap<String, Entity>();
			for (MapObject object : objects) {
				if (object.getName().startsWith("init")) {
					Rectangle pos = getRectangle(object);
					Integer index = Integer.valueOf(StringUtils.substringAfter(object.getName(), "init"));
					mapa.put("init" + index, new RotatingEntity(holeTexture, new Vector2(pos.x, pos.y), 10f));
				} else if (object.getName().startsWith("end")) {
					Rectangle pos = getRectangle(object);
					Integer index = Integer.valueOf(StringUtils.substringAfter(object.getName(), "end"));
					mapa.put("end" + index, new StaticEntity(holeTexture, new Vector2(pos.x, pos.y)));
				}
			}

			for (int i = 0; i < mapa.size() / 2; i++) {
				wormHoles.add(new WormHole(mapa.get("init" + i), mapa.get("end" + i)));
			}
		}
		return wormHoles;
	}

	private Rectangle getRectangle(MapObject object) {
		RectangleMapObject rectangle = (RectangleMapObject) object;
		return rectangle.getRectangle();
	}

	private void addListenersTo(final Snake snake) {
		snake.addListener(new SnakeListener() {
			@Override
			public boolean handle(Actor source, Type type) {
				if (type.equals(SnakeEvent.Type.speed)) {
					sounds.play(Type.speed);
					snake.incSpeed(1.1f);
				}
				return false;
			}

			@Override
			public boolean colided(Actor source, Event event) {
				// DRY: it can be
				// sounds.play(type)
				// inside the handle method.
				sounds.play(Type.died);
				snake.die();
				return false;
			}

			@Override
			public boolean revert(Actor source, Event event) {
				sounds.play(Type.revert);
				snake.revert();
				return false;
			}

			@Override
			public boolean addTail(Actor source, Event event) {
				sounds.play(Type.addTail);
				snake.addTail();
				return false;
			}

			@Override
			public boolean removeTail(Actor source, Event event) {
				sounds.play(Type.removeTail);
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
		super.dispose();
		textures.dispose();
	}

}
