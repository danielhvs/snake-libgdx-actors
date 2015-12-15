package br.com.danielhabib.snake.rules;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.Actor;


public class TimingFruitGeneratorTest {
	@Test
	public void itGeneratesFruit_WhenTimesPassesFromTimeout() throws Exception {
		List<Actor> map = new ArrayList<Actor>();
		TimingFruitGenerator generator = newGenerator(map, 1f);

		generator.act(0.1f);
		generator.act(0.9f);
		generator.act(0.9f);

		assertEquals(1, map.size());
	}

	@Test
	public void itDoesntGenerateFruit_WhenTimeDidntPassFromTimeout() throws Exception {
		List<Actor> map = new ArrayList<Actor>();
		TimingFruitGenerator generator = newGenerator(map, 1f);

		generator.act(0.99f);

		assertEquals(0, map.size());
	}

	private TimingFruitGenerator newGenerator(List<Actor> list, float timeoutSeconds) {
		return new TimingFruitGenerator(null, null, 0, 0, timeoutSeconds);
	}
}
