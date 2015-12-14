package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor {

	public static final Entity NOEntity = new NOPEntity();
	public static int SIZE = 32;
	protected Sprite sprite;

	protected Entity() {
	}

	public Entity(Texture texture, Vector2 pos) {
		this.sprite = new Sprite(texture);
		sprite.setSize(SIZE, SIZE);
		sprite.setOriginCenter();
		setBounds(pos.x, pos.y, SIZE, SIZE);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		update();
		sprite.setPosition(getX() * SIZE, getY() * SIZE);
		sprite.setRotation(getRotation());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		sprite.draw(batch);
	}

	public abstract void update();

	public void dispose() {
		getSprite().getTexture().dispose();
	}

	public Vector2 getPosition() {
		return new Vector2(getX(), getY());
	}

	public Sprite getSprite() {
		return sprite;
	}

}
