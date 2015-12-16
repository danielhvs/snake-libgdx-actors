package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Entity extends Actor {

	public static final Entity NOEntity = new NOPEntity();
	public static int SIZE = 32;
	protected Sprite sprite;

	public Entity() {
		// For NOP.
	}

	public void setTexture(Texture texture) {
		this.sprite = new Sprite(texture);
		sprite.setSize(texture.getWidth(), texture.getHeight());
		sprite.setOriginCenter();
		setWidth(texture.getWidth());
		setHeight(texture.getHeight());
	}

	public Entity(Texture texture, Vector2 pos) {
		this.sprite = new Sprite(texture);
		sprite.setSize(texture.getWidth(), texture.getHeight());
		sprite.setOriginCenter();
		setBounds(pos.x, pos.y, texture.getWidth(), texture.getHeight());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		updateAct();
		Color color = getColor();
		sprite.setPosition(getX(), getY());
		sprite.setRotation(getRotation());
		sprite.setColor(color.r, color.g, color.b, color.a);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		sprite.draw(batch);
	}

	protected abstract void updateAct();

	public void dispose() {
		getSprite().getTexture().dispose();
	}

	public Vector2 getPosition() {
		return new Vector2(getX(), getY());
	}

	public Rectangle getBounds() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public Sprite getSprite() {
		return sprite;
	}

}
