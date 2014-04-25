package br.com.wmtt.client.control;

import br.com.wmtt.client.AppController;
import br.com.wmtt.client.RPCServiceAsync;
import br.com.wmtt.menu.view.CadastroProvaView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class CadastrarProvaControle{

	private final RPCServiceAsync rpcService = null;
	private CadastroProvaView cadastroProvaView;
	private AppController appC = null;

	public CadastrarProvaControle(AppController appC, RPCServiceAsync rpcService, CadastroProvaView cadastroProvaView, HasWidgets container) {
		
		this.cadastroProvaView = cadastroProvaView;
		this.appC = appC;
		
		adicionaEventos();
		adicionaConteudo(container);
	}
	
	public void adicionaEventos(){
		cadastroProvaView.getBtnCadasatrarProva().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				rpcService.cadastrarProva(null, new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						appC.novoEvento("provas");
					}
					
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Falha ao cadastrar prova. Tente novamente mais tarde.");
						appC.novoEvento("provas");
					}
					
				});
				
				
				
			}
		});
	}
	
	public void adicionaConteudo(HasWidgets container) {
		container.clear();
		container.add(cadastroProvaView.asWidget());
	}

	
	
}
