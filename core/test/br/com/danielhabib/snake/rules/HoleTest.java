package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;

import com.badlogic.gdx.math.Vector2;

public class HoleTest extends BaseTest {
	@Ignore("See later when the application is stable")
	public void hole_ConstructorOnly_DefinesInitialAndFinalPosition() throws Exception {
		WormHole hole = new WormHole(new StaticEntity(texture, new Vector2(0, 0)), new StaticEntity(texture, new Vector2(1, 1)));

		assertEquals(new Vector2(0, 0), hole.getInitialPoint());
		assertEquals(new Vector2(1, 1), hole.getFinalPoint());
	}
}
