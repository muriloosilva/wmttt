package br.com.wmtt.client.control;

import br.com.wmtt.client.AppController;
import br.com.wmtt.client.RPCServiceAsync;
import br.com.wmtt.menu.event.LoginEvent;
import br.com.wmtt.menu.view.LoginView;
import br.com.wmtt.menu.view.MenuView;
import br.com.wmtt.menu.view.ProvasView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MenuControle{

	private final RPCServiceAsync rpcService = null;
	private MenuView menuView;
	private AppController appC = null;

	public MenuControle(AppController appC, RPCServiceAsync rpcService, MenuView menuView, HasWidgets container) {
		
		this.menuView = menuView;
		this.appC = appC;
		
		adicionaEventos();
		adicionaConteudo(container);
	}
	
	public void adicionaEventos(){
		menuView.getProvas().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				appC.novoEvento("provas");
			}
		});

		menuView.getContato().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				appC.novoEvento("contato");
			}
		});
		
		menuView.getSobre().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				appC.novoEvento("sobre");
			}
		});
	}
	
	public void adicionaConteudo(HasWidgets container) {
		container.clear();
		container.add(menuView.asWidget());
	}

	
	
}
