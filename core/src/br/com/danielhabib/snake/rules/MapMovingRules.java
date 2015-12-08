package br.com.danielhabib.snake.rules;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class MapMovingRules extends AMovingRules {

	private Map<Entity, IRule> map;
	private AMovingRules ruleWhenFree;
	private IRule ruleWhenCollidedWithItSelf;

	public MapMovingRules(AMovingRules ruleWhenFree, IRule ruleWhenCollidedWithItSelf, Snake snake) {
		this(ruleWhenFree, ruleWhenCollidedWithItSelf, new HashMap<Entity, IRule>(), snake);
	}

	public MapMovingRules(AMovingRules ruleWhenFree, IRule ruleWhenCollidedWithItSelf, Map<Entity, IRule> map, Snake snake) {
		super(snake);
		this.ruleWhenFree = ruleWhenFree;
		this.ruleWhenCollidedWithItSelf = ruleWhenCollidedWithItSelf;
		this.map = map;
	}

	private static float time = 0;

	@Override
	public void act(float delta) {
		super.act(delta);
		for (Entity entity : map.keySet()) {
			entity.act(delta);
		}
		snake.act(delta);

		time += delta;
		if (time > 0.125) {
			update(delta);
			time = 0;
		}
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		ruleWhenFree.draw(batch, parentAlpha);
		snake.draw(batch, parentAlpha);
		for (Entity entity : map.keySet()) {
			entity.draw(batch, parentAlpha);
		}
	}

	public void update(float delta) {
		int lastX = -1 + Gdx.graphics.getWidth() / Entity.SIZE;
		int lastY = -1 + Gdx.graphics.getHeight() / Entity.SIZE;
		if (snakeWouldEatItSelf(snake)) {
			ruleWhenCollidedWithItSelf.act(delta);
			return;
		} else {
			Entity entity = snakeWouldColide(snake);
			if (!Entity.NOEntity.equals(entity)) {
				map.get(entity).act(delta);
				return;
			}
		}
		// Mirror
		Vector2 nextPosition = snake.getNextPosition();
		if (nextPosition.x > lastX) {
			snake.move(new Vector2(0, snake.getPosition().y));
		} else if (nextPosition.x < 0) {
			snake.move(new Vector2(lastX, snake.getPosition().y));
		} else if (nextPosition.y > lastY) {
			snake.move(new Vector2(snake.getPosition().x, 0));
		} else if (nextPosition.y < 0) {
			snake.move(new Vector2(snake.getPosition().x, lastY));
		} else {
			ruleWhenFree.act(delta);
		}
	}

	private Entity snakeWouldColide(Snake snake) {
		Vector2 nextPositionSnake = snake.getNextPosition();
		for (Entity entity : map.keySet()) {
			if (entity.getPosition().epsilonEquals(nextPositionSnake, 0.01f)) {
				return entity;
			}
		}
		return Entity.NOEntity;
	}

	private boolean snakeWouldEatItSelf(Snake snake) {
		Stack<Vector2> nextPositions = snake.getNextPositions();
		Vector2 headPosition = nextPositions.pop();
		for (Vector2 piecePosition : nextPositions) {
			if (headPosition.epsilonEquals(piecePosition, 0.1f)) {
				return true;
			}
		}
		return false;
	}

}
