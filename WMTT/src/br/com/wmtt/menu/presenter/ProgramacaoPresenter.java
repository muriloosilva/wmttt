package br.com.wmtt.menu.presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.wmtt.client.RPCServiceAsync;
import br.com.wmtt.menu.view.LoginView;
import br.com.wmtt.menu.view.MaisInformacaoView;
import br.com.wmtt.popup.InformacaoPopup;
import br.com.wmtt.popup.LoadingPopup;
import br.com.wmtt.shared.model._Atividade;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ProgramacaoPresenter implements Presenter {

	public interface Display {

		void setData(List<_Atividade> oficinas, List<_Atividade> minicursos,
				List<_Atividade> palestras);

		int[] getOficina(ClickEvent event);

		int[] getMinicurso(ClickEvent event);

		int[] getPalestra(ClickEvent event);

		FlexTable getListaOficina();

		FlexTable getListaMinicurso();

		FlexTable getListaPalestra();

		Widget asWidget();
	}

	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private List<_Atividade> oficinas;
	private List<_Atividade> minicursos;
	private List<_Atividade> palestras;
	private InformacaoPopup ip;

	public ProgramacaoPresenter(RPCServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	private void eventoMaisInformacao(int idAtividade){
		Presenter presenter = new MaisInformacaoPresenter(
				rpcService, eventBus, new MaisInformacaoView("Inscrever"),
				idAtividade);
		if (presenter != null)
			presenter.go();
	}
	
	private void eventoInscrever(final int idAtividade){
		
		final LoadingPopup pp = new LoadingPopup("Aguarde ...");
		
		rpcService.getSessao(new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				pp.hide();
			}
			@Override
			public void onSuccess(Boolean result) {
				pp.hide();
				//se estiver logado
				if (result) {
//					inscrever(idAtividade);
				} else {
					Presenter presenter = new LoginPresenter(
							rpcService, eventBus, new LoginView());
					if (presenter != null)
						presenter.go();
				}
			}
		});
	}
	
	private void eventoInscreverMinicurso(final int idAtividade){
		
		final LoadingPopup pp = new LoadingPopup("Aguarde ...");
		
		rpcService.getSessao(new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
			}
			@Override
			public void onSuccess(Boolean result) {
				//se estiver logado
				if (result) {
					//verificar minicurso
					rpcService.getMinicursosDoAluno(new AsyncCallback<Boolean>() {

						@Override
						public void onFailure(Throwable caught) {
							pp.hide();
						}

						@Override
						public void onSuccess(Boolean result) {
							// TODO Auto-generated method stub
							if(result){
								pp.hide();
//								inscrever(idAtividade);
							}
							else{
								pp.hide();
								//n�o pode ser inscrito
								ip = new InformacaoPopup("Você já está inscrito em um Minicurso!");
								ip.getTela().center();
								ip.getOk().addClickHandler(new ClickHandler() {													
									@Override
									public void onClick(ClickEvent event) {
										ip.getTela().hide();
									}
								});
								ip.getFechar().addClickHandler(new ClickHandler() {													
									@Override
									public void onClick(ClickEvent event) {
										ip.getTela().hide();
									}
								});
							}
								
						}
					});
				} else {
					pp.hide();
					Presenter presenter = new LoginPresenter(
							rpcService, eventBus, new LoginView());
					if (presenter != null)
						presenter.go();
				}
			}
		});
	}
	
	public void bind() {		 
		
		//Eventos tabela OFICINAS
		//Mais Informação
		for (int i = 0; i < display.getListaOficina().getRowCount(); i++) {
			final int j = i;
			((Button)display.getListaOficina().getWidget(i, 4)).addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					int idAtividade = Integer.parseInt(display.getListaOficina().getRowFormatter().getElement(j).getAttribute("id"));
					eventoMaisInformacao(idAtividade);
				}
			});
		}
		//Inscrever
		for (int i = 0; i < display.getListaOficina().getRowCount(); i++) {
			final int j = i;
			((Button)display.getListaOficina().getWidget(i, 5)).addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					int idAtividade = Integer.parseInt(display.getListaOficina().getRowFormatter().getElement(j).getAttribute("id"));
					eventoInscrever(idAtividade);
				}
			});
		}
		
		//Eventos tabela MINICURSOS
		//Mais Informação
		for (int i = 0; i < display.getListaMinicurso().getRowCount(); i++) {
			final int j = i;
			((Button)display.getListaMinicurso().getWidget(i, 4)).addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					int idAtividade = Integer.parseInt(display.getListaMinicurso().getRowFormatter().getElement(j).getAttribute("id"));
					eventoMaisInformacao(idAtividade);
				}
			});
		}
		//Inscrever
		for (int i = 0; i < display.getListaMinicurso().getRowCount(); i++) {
			final int j = i;
			((Button)display.getListaMinicurso().getWidget(i, 5)).addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					int idAtividade = Integer.parseInt(display.getListaMinicurso().getRowFormatter().getElement(j).getAttribute("id"));
					eventoInscreverMinicurso(idAtividade);
				}
			});
		}
		
		//Eventos tabela PALESTRAS
		//Mais Informação
		for (int i = 0; i < display.getListaPalestra().getRowCount(); i++) {
			final int j = i;
			((Button)display.getListaPalestra().getWidget(i, 4)).addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					int idAtividade = Integer.parseInt(display.getListaPalestra().getRowFormatter().getElement(j).getAttribute("id"));
					eventoMaisInformacao(idAtividade);
				}
			});
		}
		
//		display.getListaOficina().addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				int e[] = display.getOficina(event);
//				eventoBotoes(e);
//			}
//		});

//		display.getListaMinicurso().addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				int e[] = display.getMinicurso(event);
//				eventoBotoesMinicursos(e);
//			}
//		});
//
//		display.getListaPalestra().addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				int e[] = display.getPalestra(event);
////				eventoBotoes(e);
//				Presenter presenter = new MaisInformacaoPresenter(
//						rpcService, eventBus, new MaisInformacaoView("Inscrever"),
//						e[0]);
//				if (presenter != null)
//					presenter.go();
//			}
//		});
	}

//	public void eventoBotoesMinicursos(final int[] e){
//		if (e[1] == 4) {
//			Presenter presenter = new MaisInformacaoPresenter(
//					rpcService, eventBus, new MaisInformacaoView("Inscrever"),
//					e[0]);
//			if (presenter != null)
//				presenter.go();
//		} else if (e[1] == 5) {
//			rpcService.getSessao(new AsyncCallback<Boolean>() {
//				@Override
//				public void onFailure(Throwable caught) {
//
//				}
//				@Override
//				public void onSuccess(Boolean result) {
//					//se estiver logado
//					if (result) {
//						//verificar minicurso
//						rpcService.getMinicursosDoAluno(new AsyncCallback<Boolean>() {
//
//							@Override
//							public void onFailure(Throwable caught) {
//								// TODO Auto-generated method stub
//								
//							}
//
//							@Override
//							public void onSuccess(Boolean result) {
//								// TODO Auto-generated method stub
//								if(result)
//									inscrever(e[0]);
//								else{
//									//n�o pode ser inscrito
//									ip = new InformacaoPopup("Você já está inscrito em um Minicurso!");
//									ip.getTela().center();
//									ip.getOk().addClickHandler(new ClickHandler() {													
//										@Override
//										public void onClick(ClickEvent event) {
//											ip.getTela().hide();
//										}
//									});
//									ip.getFechar().addClickHandler(new ClickHandler() {													
//										@Override
//										public void onClick(ClickEvent event) {
//											ip.getTela().hide();
//										}
//									});
//								}
//									
//							}
//						});
//					} else {
//						Presenter presenter = new LoginPresenter(
//								rpcService, eventBus, new LoginView());
//						if (presenter != null)
//							presenter.go();
//					}
//				}
//			});
//		}
//	}
//	
//	public void eventoBotoes(final int[] e){
//		if (e[1] == 4) {
//			Presenter presenter = new MaisInformacaoPresenter(
//					rpcService, eventBus, new MaisInformacaoView("Inscrever"),
//					e[0]);
//			if (presenter != null)
//				presenter.go();
//		} else if (e[1] == 5) {
//			rpcService.getSessao(new AsyncCallback<Boolean>() {
//				@Override
//				public void onFailure(Throwable caught) {
//
//				}
//				@Override
//				public void onSuccess(Boolean result) {
//					//se estiver logado
//					if (result) {
//						//verificar minicurso
//						inscrever(e[0]);
//					} else {
//						Presenter presenter = new LoginPresenter(
//								rpcService, eventBus, new LoginView());
//						if (presenter != null)
//							presenter.go();
//					}
//				}
//			});
//		}
//	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		final LoadingPopup pp = new LoadingPopup("Aguarde ...");
		rpcService.getAtividades(new AsyncCallback<List<_Atividade>>() {
			@Override
			public void onSuccess(List<_Atividade> result) {
				pp.hide();
				oficinas = new ArrayList<_Atividade>();
				palestras = new ArrayList<_Atividade>();
				minicursos = new ArrayList<_Atividade>();
				for (int i = 0; i < result.size(); i++) {
					_Atividade a = result.get(i);
					if (a.getTipoAtiv().equalsIgnoreCase("Oficina"))
						oficinas.add(a);
					else if (a.getTipoAtiv().equalsIgnoreCase("Palestra"))
						palestras.add(a);
					else
						minicursos.add(a);
				}
				Collections.sort(oficinas);
				Collections.sort(minicursos);
				Collections.sort(palestras);
				display.setData(oficinas, minicursos, palestras);
				bind();
			}
			@Override
			public void onFailure(Throwable caught) {
				pp.hide();
			}
		});
	}
	
	@Override
	public void go() {

	}
	
//	private void inscrever(int e){
//		final LoadingPopup pp = new LoadingPopup("Aguarde ...");
//		rpcService.inscrever(e,
//				new AsyncCallback<Boolean>() {
//					@Override
//					public void onFailure(Throwable caught) {
//						pp.hide();
//					}
//					@Override
//					public void onSuccess(Boolean result) {
//						pp.hide();
//						if(result){
//							ip = new InformacaoPopup("Inscrição efetuada com sucesso!");
//							ip.getTela().center();
//							ip.getOk().addClickHandler(new ClickHandler() {														
//								@Override
//								public void onClick(ClickEvent event) {
//									ip.getTela().hide();
//									eventBus.fireEvent(new LoginEvent("login"));
//								}
//							});
//							ip.getFechar().addClickHandler(new ClickHandler() {														
//								@Override
//								public void onClick(ClickEvent event) {
//									ip.getTela().hide();
//									eventBus.fireEvent(new LoginEvent("login"));
//								}
//							});
//							
//						}
//						else{
//							ip = new InformacaoPopup("Você está inscrito em outra(s) atividade(s) no mesmo"
//									+ " horário!");
//							ip.getTela().center();
//							ip.getOk().addClickHandler(new ClickHandler() {													
//								@Override
//								public void onClick(ClickEvent event) {
//									ip.getTela().hide();
//								}
//							});
//							ip.getFechar().addClickHandler(new ClickHandler() {													
//								@Override
//								public void onClick(ClickEvent event) {
//									ip.getTela().hide();
//								}
//							});
//						}
//					}
//				});
//	}
}