package br.com.danielhabib.snake.rules;

public class IdentityRule extends IRule {

	@Override
	public boolean fireEvent(float delta) {
		return false;
	}


}
