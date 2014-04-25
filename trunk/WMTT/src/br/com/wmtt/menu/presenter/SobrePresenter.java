package br.com.wmtt.menu.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class SobrePresenter implements Presenter {

	public interface Display {

		Widget asWidget();
		
		Button getEnviar();

		PopupPanel getPopup();
	}

	private final Display display;

	public SobrePresenter(Display view) {
		this.display = view;
	}

	public void bind() {
		display.getEnviar().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				display.getPopup().hide();

			}
		});

	}

	public void go(final HasWidgets container) {}

	@Override
	public void go() {
		bind();
		display.getPopup().center();
	}
}