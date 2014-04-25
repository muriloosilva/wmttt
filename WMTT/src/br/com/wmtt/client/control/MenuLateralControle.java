package br.com.wmtt.client.control;

import br.com.wmtt.client.AppController;
import br.com.wmtt.client.RPCServiceAsync;
import br.com.wmtt.menu.event.LoginEvent;
import br.com.wmtt.menu.view.LoginView;
import br.com.wmtt.menu.view.MenuLateralView;
import br.com.wmtt.menu.view.MenuView;
import br.com.wmtt.menu.view.ProvasView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MenuLateralControle{

	private final RPCServiceAsync rpcService = null;
	private MenuLateralView menuLateralView;
	private AppController appC = null;

	public MenuLateralControle(AppController appC, RPCServiceAsync rpcService, MenuLateralView menuLateralView, HasWidgets container) {
		
		this.menuLateralView = menuLateralView;
		this.appC = appC;
		
		adicionaEventos();
		adicionaConteudo(container);
	}
	
	public void adicionaEventos(){
		menuLateralView.getQuestoes().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				appC.novoEvento("questoes");
			}
		});

		menuLateralView.getEditar().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				appC.novoEvento("editar");
			}
		});
		
		menuLateralView.getImprimir().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				appC.novoEvento("imprimir");
			}
		});
	}
	
	public void adicionaConteudo(HasWidgets container) {
		container.clear();
		container.add(menuLateralView.asWidget());
	}

	
	
}
