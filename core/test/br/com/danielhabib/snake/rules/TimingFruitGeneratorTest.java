package br.com.danielhabib.snake.rules;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import br.com.danielhabib.snake.entity.EventFirerEntity;
import br.com.danielhabib.snake.entity.builders.TimingFruitGenerator;

public class TimingFruitGeneratorTest extends BaseTest {

	@Before
	public void setup() {
		when(layer.getWidth()).thenReturn(2);
		when(layer.getHeight()).thenReturn(2);
		when(texture.getWidth()).thenReturn(4);
		when(texture.getHeight()).thenReturn(4);
		when(worldManager.getMap()).thenReturn(new Array<Actor>());
		when(entityBuilder.build(Matchers.isA(Vector2.class))).thenReturn(eventFirerEntity);
	}

	@Test
	public void itGeneratesFruit_WhenTimesPassesFromTimeout() throws Exception {
		TimingFruitGenerator generator = newGenerator(1f);

		generator.act(0.1f);
		generator.act(0.9f);
		generator.act(0.9f);

		verify(worldManager).put(eq(eventFirerEntity));
	}

	@Test
	public void itDoesntGenerateFruit_WhenTimeDidntPassFromTimeout() throws Exception {
		TimingFruitGenerator generator = newGenerator(1f);

		generator.act(0.99f);

		verify(worldManager, never()).put(Matchers.isA(EventFirerEntity.class));
	}

	private TimingFruitGenerator newGenerator(float timeoutSeconds) {
		return new TimingFruitGenerator(layer, entityBuilder, worldManager, 0, 0, timeoutSeconds);
	}
}
