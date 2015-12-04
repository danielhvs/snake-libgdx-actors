package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.math.Vector2;

import br.com.danielhabib.snake.game.DrawableManager;

/**
 * FIXME: Duplication with PoisonedFruitRule
 */
public class FruitRule implements IRule {

	private Vector2 fruitPosition;
	private DrawableManager drawingManager;
	private Entity apple;

	public FruitRule(Vector2 fruitPosition) {
		this.fruitPosition = fruitPosition;
	}

	public FruitRule(Entity apple, DrawableManager drawingManager) {
		this.apple = apple;
		this.drawingManager = drawingManager;
		this.fruitPosition = apple.getPosition();
	}

	@Override
	public Snake update(Snake snake) {
		return snake.getPosition().epsilonEquals(fruitPosition, 0.01f) ? consumesFruit(snake) : snake;
	}

	// FIXME: Extract this method to strategy design pattern: apply rule after
	// consuming fruit.
	private Snake consumesFruit(Snake snake) {
		fruitPosition = new Vector2(-1, -1);
		drawingManager.remove(apple);
		return snake.addTail();
	}

}
