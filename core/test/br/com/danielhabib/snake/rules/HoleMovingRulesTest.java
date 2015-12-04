package br.com.danielhabib.snake.rules;

import org.junit.Test;

import com.badlogic.gdx.math.Vector2;

public class HoleMovingRulesTest extends BaseTest {

	@Test
	public void move_ThereIsAHoleInSnakeFront_TeleportsToTheEndOfTheHole() throws Exception {
		Snake finalSnakePosition = newSnake(10, 0);

		Snake snake = newSnake(0, 0);

		AMovingRules rules = new HoleMovingRules(new WormHole(new Vector2(1, 0), new Vector2(10, 0)));

		Snake finalPosition = rules.update(snake);
		finalPosition = rules.update(finalPosition);

		assertPoints(finalSnakePosition.getPosition(), finalPosition.getPosition());
	}

}
