package br.com.wmtt.client;

import br.com.wmtt.client.control.CadastrarProvaControle;
import br.com.wmtt.client.control.MenuControle;
import br.com.wmtt.client.control.MenuLateralControle;
import br.com.wmtt.client.control.ProvasControle;
import br.com.wmtt.menu.presenter.InfoUsuarioPresenter;
import br.com.wmtt.menu.presenter.MenuLateralPresenter;
import br.com.wmtt.menu.presenter.MenuPresenter;
import br.com.wmtt.menu.presenter.Presenter;
import br.com.wmtt.menu.view.CadastroProvaView;
import br.com.wmtt.menu.view.InfoUsuarioView;
import br.com.wmtt.menu.view.MenuLateralView;
import br.com.wmtt.menu.view._MenuLateralView;
import br.com.wmtt.menu.view.MenuView;
import br.com.wmtt.menu.view._MenuView;
import br.com.wmtt.menu.view.ProvasView;
import br.com.wmtt.shared.model.Professor;
import br.com.wmtt.shared.model._User;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final RPCServiceAsync rpcService;
	private HasWidgets container;
	private MenuPresenter presenterMenu;
	private MenuLateralPresenter menuLateral;
	private InfoUsuarioPresenter presenterInfoUsuario;
	private String token;

	public AppController(RPCServiceAsync rpcService, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		loadMenu();
		iniciarControleEventos();
	}
	
	public void novoEvento(String evento){
		History.newItem(evento);
	}

	private void iniciarControleEventos() {
		History.addValueChangeHandler(this);

//		eventBus.addHandler(ApresentationEvent.TYPE,
//				new ApresentationEventHandler() {
//					public void onApresentation(ApresentationEvent event) {
//						History.newItem("apresentacao");
//					}
//				});
//
//		eventBus.addHandler(ProgramacaoEvent.TYPE,
//				new ProgramacaoEventHandler() {
//					public void onProgramacao(ProgramacaoEvent event) {
//						History.newItem("programacao");
//					}
//				});
//
//		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
//			public void onLogin(LoginEvent event) {
//				History.newItem("login");
//			}
//		});
//		eventBus.addHandler(FaleConoscoEvent.TYPE, new FaleConoscoEventHandler() {
//			public void onFaleConosco(FaleConoscoEvent event) {
//				History.newItem("faleConosco");
//			}
//		});
//		eventBus.addHandler(SobreEvent.TYPE, new SobreEventHandler() {
//			public void onSobre(SobreEvent event) {
//				History.newItem("sobre");
//			}
//		});
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("provas");
//			Window.alert("sem caminho");
//			rpcService.pegarSessao(new AsyncCallback<Professor>() {
//				
//				@Override
//				public void onSuccess(Professor professor) {
//					Window.alert("nome:" + professor.getEmail());
//					//presenterMenu.getNomeUsuario().setText(nome);
//				}
//				
//				@Override
//				public void onFailure(Throwable caught) {
//					Window.alert("falha");
//				}
//			});
			
			//History.newItem("provas");
		} else {
			Window.alert("caminho");
//			rpcService.pegarSessao(new AsyncCallback<Professor>() {
//				
//				@Override
//				public void onSuccess(Professor professor) {
//					Window.alert("nome:" + professor.getEmail());
//					//presenterMenu.getNomeUsuario().setText(nome);
//				}
//				
//				@Override
//				public void onFailure(Throwable caught) {
//					
//				}
//			});
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		token = event.getValue();
		Window.alert(token);
		if (token != null) {
			if(token.equals("provas")){
				new ProvasControle(this, rpcService, new ProvasView(), container);
			}
			else if(token.equals("configurar")){
				loadMenuLateral();
			}
			else if(token.equals("cadastrarProva")){
				new CadastrarProvaControle(this, rpcService, new CadastroProvaView(), container);
			}
			
		}
	}

	private void loadMenu() {
		new MenuControle(this, rpcService, new MenuView(), RootPanel.get("menuC"));
	}

	private void loadMenuLateral() {
		new MenuLateralControle(this, rpcService, new MenuLateralView(), RootPanel.get("menuLateralC"));
	}

	@Override
	public void go(){}

	public void infoUsuario() {
		presenterInfoUsuario = new InfoUsuarioPresenter(rpcService, eventBus,
				new InfoUsuarioView());
		presenterInfoUsuario.go(RootPanel.get("menuDir"));
	} 

	public void configuraMenuLateral(){
		menuLateral.getLogin().setVisible(false);
		menuLateral.getMinhasAtividades().setVisible(true);
	}
	
	public void configurarMenuUsuario(){
		rpcService.getSession(new AsyncCallback<_User>() {
			
			@Override
			public void onSuccess(_User result) {
				presenterMenu.getHpUsuario().setVisible(true);
				String nome = result.getNome_partic().split(" ")[0];
				if(nome.length()>21){
					nome = nome.subSequence(0, 19).toString();
					nome += "...";
				}
				presenterMenu.getNomeUsuario().setText(nome);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}
	
	public void verificaSessao() {
//		rpcService.getSessao(new AsyncCallback<Boolean>() {
//			@Override
//			public void onSuccess(Boolean result) {
//				if (result) {
//					if(token.equals("apresentacao")){
//						configurarMenuUsuario();
//						configuraMenuLateral();
//						Presenter presenter = new ApresentationPresenter(rpcService, eventBus,
//								new ApresentationView());
//						if (presenter != null)
//							presenter.go(container);
//					}
//					else if(token.equals("programacao")){
//						configurarMenuUsuario();
//						configuraMenuLateral();
//						Presenter presenter = new UsuarioPresenter(rpcService, eventBus,
//								new UsuarioView());
//						if (presenter != null)
//							presenter.go(container);
//					}
//					else if(token.equals("login")){
//						configurarMenuUsuario();
//						configuraMenuLateral();
//						Presenter presenter = new UsuarioPresenter(rpcService, eventBus,
//								new UsuarioView());
//						if (presenter != null)
//							presenter.go(container);
//					}
//					else if(token.equals("faleConosco")){
//						configurarMenuUsuario();
//						configuraMenuLateral();
//						Presenter presenter = new FaleConoscoPresenter(rpcService, eventBus,
//								new FaleConoscoView());
//						if (presenter != null)
//							presenter.go(container);
//					}
//					else if(token.equals("sobre")){
//						configurarMenuUsuario();
//						configuraMenuLateral();
//						Presenter presenter = new SobrePresenter(new SobreView());
//						if (presenter != null)
//							presenter.go(container);
//					}
//					else if(token.equals("admineventifsecitecifgformosa")){
//						presenterMenu.getHpUsuario().setVisible(false);
//						configuraMenuLateral();
//						Presenter presenter = new AdminPresenter(rpcService, eventBus,new AdminView());
//						if (presenter != null)
//							presenter.go(container);
//					}
//				} else {
//					if(token.equals("apresentacao")){
//						loadMenuLateral();
//						presenterMenu.getHpUsuario().setVisible(false);
//						Presenter presenter = new ApresentationPresenter(rpcService, eventBus,
//								new ApresentationView());
//						if (presenter != null)
//							presenter.go(container);
//					}
//					else if(token.equals("programacao")){
//						loadMenuLateral();
//						presenterMenu.getHpUsuario().setVisible(false);
//						Presenter presenter = new ProgramacaoPresenter(rpcService, eventBus,
//								new ProgramacaoView());
//						if (presenter != null)
//							presenter.go(container);
//					}
//					else if(token.equals("login")){
//						loadMenuLateral();
//						presenterMenu.getHpUsuario().setVisible(false);
////						Presenter presenter = new ApresentationPresenter(rpcService, eventBus,
////								new ApresentationView());
////						if (presenter != null)
////							presenter.go(container);
//						eventBus.fireEvent(new ApresentationEvent("apresentacao"));
//					}
//					else if(token.equals("faleConosco")){
//						loadMenuLateral();
//						presenterMenu.getHpUsuario().setVisible(false);
//						Presenter presenter = new FaleConoscoPresenter(rpcService, eventBus,
//								new FaleConoscoView());
//						if (presenter != null)
//							presenter.go(container);
//					}
//					else if(token.equals("sobre")){
//						loadMenuLateral();
//						presenterMenu.getHpUsuario().setVisible(false);
//						Presenter presenter = new SobrePresenter(new SobreView());
//						if (presenter != null)
//							presenter.go(container);
//					}
//					else if(token.equals("admineventifsecitecifgformosa")){
//						loadMenuLateral();
//						presenterMenu.getHpUsuario().setVisible(false);
//						Presenter presenter = new AdminPresenter(rpcService, eventBus,new AdminView());
//						if (presenter != null)
//							presenter.go(container);
//					}
//				}
			}

//			@Override
//			public void onFailure(Throwable caught) {
//			}
////		});
//	}
}