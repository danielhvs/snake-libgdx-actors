package br.com.danielhabib.snake.rules;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class TimingFruitGenerator {

	private float timeout;
	private float time;
	private List<Actor> map;

	public TimingFruitGenerator(List<Actor> map, float timeout) {
		this.map = map;
		this.timeout = timeout;
	}

	public void act(float delta) {
		if (timeout(delta)) {
			map.add(new Actor());
		}
	}

	private boolean timeout(float delta) {
		time += delta;
		if (time >= timeout) {
			time = 0;
			return true;
		} else {
			return false;
		}
	}

}
