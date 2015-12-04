package br.com.danielhabib.snake.rules;

import com.badlogic.gdx.utils.Array;

public class RulesManager {
	private final Array<IRule> rules = new Array<IRule>();

	public void addRule(IRule rule) {
		rules.add(rule);
	}

	public void applyRules(Snake snake) {
		for (IRule rule : rules) {
			rule.update(snake);
		}
	}
}
