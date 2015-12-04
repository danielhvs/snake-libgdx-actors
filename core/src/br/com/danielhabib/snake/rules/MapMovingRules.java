package br.com.danielhabib.snake.rules;

import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.math.Vector2;

public class MapMovingRules extends AMovingRules {

	private IRule rule;
	private List<Entity> map;

	public MapMovingRules(IRule rule, List<Entity> map) {
		this.rule = rule;
		this.map = map;
	}

	@Override
	public Snake update(Snake snake) {
		return snakeWouldEatItSelf(snake) ? snake 
				: snakeWouldColide(snake) ? rule.update(snake) 
										  : snake.move();
	}

	private boolean snakeWouldColide(Snake snake) {
		Vector2 nextPositionSnake = snake.getNextPosition();
		for (Entity entity : map) {
			if (entity.getPosition().epsilonEquals(nextPositionSnake, 0.01f)) {
				return true;
			}
		}
		return false;
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
