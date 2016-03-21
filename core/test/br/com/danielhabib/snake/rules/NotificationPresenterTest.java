package br.com.danielhabib.snake.rules;

import org.junit.Test;

import br.com.danielhabib.snake.animation.NotificationPresenter;

public class NotificationPresenterTest {
	@Test
	public void addListeners() throws Exception {
		NotificationPresenter presenter = new NotificationPresenter();

		presenter.addListeners();
	}
}
