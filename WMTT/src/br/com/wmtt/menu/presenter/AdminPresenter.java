package br.com.wmtt.menu.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.wmtt.client.RPCServiceAsync;
import br.com.wmtt.popup.InformacaoPopup;
import br.com.wmtt.popup.LoadingPopup;
import br.com.wmtt.shared.model._Atividade;
import br.com.wmtt.shared.model._Data;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class AdminPresenter implements Presenter {

	public interface Display {

		Widget asWidget();
		
		Button getCadastrar();
		
		Button getAddData();

		TextBox getTbNome();
		
		TextArea getTbDescricao();
		
		TextBox getTbVagas();
		
		ListBox getLbTipo();
		
		FlexTable getFlexTableDatas();

		PopupPanel getPopup();
		
		void addData(int l);
	}

	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private InformacaoPopup ip;

	public AdminPresenter(RPCServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getAddData().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				display.addData(display.getFlexTableDatas().getRowCount());
				
			}
		});
		
		display.getCadastrar().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				String nome = display.getTbNome().getText();
				String descricao = display.getTbDescricao().getText();
				int vagas = Integer.parseInt(display.getTbVagas().getText());
				String tipo = display.getLbTipo().getValue(display.getLbTipo().getSelectedIndex());
				
				_Atividade a = new _Atividade();
				a.setNomeAtiv(nome);
				a.setDescAtiv(descricao);
				a.setVagasAtiv(vagas);
				a.setTipoAtiv(tipo);
				
				List<_Data> ld = new ArrayList<_Data>();
				for (int i = 0; i<display.getFlexTableDatas().getRowCount();i++){
					_Data d = new _Data();
					try {
						d.setData(((TextBox)display.getFlexTableDatas().getWidget(i, 0)).getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Window.alert("" + e);
					}
					d.setHrInicio((((TextBox)display.getFlexTableDatas().getWidget(i, 2)).getText()));
					d.setHrFim((((TextBox)display.getFlexTableDatas().getWidget(i, 4)).getText()));
					ld.add(d);
				}
				
				
				final LoadingPopup lp = new LoadingPopup("Aguarde ...");
				rpcService.insereAtividade(a, ld, new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						lp.hide();
						if(result){
							ip = new InformacaoPopup("Atividade cadastrada com sucesso!");
							ip.getTela().center();
							ip.getOk().addClickHandler(new ClickHandler() {													
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
									History.newItem("admineventifsecitecifgformosa");
								}
							});
							ip.getFechar().addClickHandler(new ClickHandler() {													
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
								}
							});
						}
						else{
							ip = new InformacaoPopup("Erro no cadastro da atividade. Tente novamente mais tarde!");
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
					
					@Override
					public void onFailure(Throwable caught) {
						lp.hide();
						ip = new InformacaoPopup("Erro no cadastro da atividade. Tente novamente mais tarde!");
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
				});
				
			}
		});
		/*display.getCadastrar().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				String nome = display.getTbNome().getText();
				String email = display.getTbEmail().getText();
				String assunto = display.getTbAssunto().getText();
				String mensagem = display.getTbMensagem().getText();


				if (!nome.equals("") && !email.equals("")&& !assunto.equals("")&& !mensagem.equals("")) {
					display.getPopup().hide();
					final LoadingPopup pp = new LoadingPopup("Sua mensagem está sendo enviada, aguarde ...");
					rpcService.faleConosco(nome, email, mensagem, new AsyncCallback<Boolean>() {
								@Override
								public void onFailure(Throwable caught) {
									pp.hide();
									ip = new InformacaoPopup("A mensagem não foi enviada. Tente novamente.");
									ip.getTela().center();
									ClickHandler ch = new ClickHandler() {
										@Override
										public void onClick(ClickEvent event) {
											display.getPopup().center();
											ip.getTela().hide();
										}
									};
								}
								@Override
								public void onSuccess(Boolean b) {
									if (b) {
										pp.hide();
										display.getPopup().hide();
										//ip = new InformacaoPopup("A mensagem foi enviada.");
										//ip.getTela().center();
										ClickHandler ch = new ClickHandler() {
											@Override
											public void onClick(ClickEvent event) {
												display.getPopup().center();
												display.getTbNome().setText("");
												display.getTbEmail().setText("");
												display.getTbAssunto().setText("");
												display.getTbMensagem().setText("");
												display.getTbNome().setFocus(true);
												ip.getTela().hide();
											}
										};
										ip.getOk().addClickHandler(ch);
										ip.getFechar().addClickHandler(ch);
									} else {
										//display.getPopup().hide();
										pp.hide();
										ip = new InformacaoPopup("A mensagem não foi enviada. Tente novamente.");
										ip.getTela().center();
										ClickHandler ch = new ClickHandler() {
											@Override
											public void onClick(ClickEvent event) {
												display.getPopup().center();
												ip.getTela().hide();
											}
										};
										ip.getOk().addClickHandler(ch);
										ip.getFechar().addClickHandler(ch);
									}
								}
							});
				} else {
					//display.getPopup().hide();
					ip = new InformacaoPopup("Preencha os campos corretamente.");
					ip.getTela().center();
					ClickHandler ch = new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							ip.getTela().hide();
							display.getPopup().center();
							//display.getTbNome().setFocus(true);
							ip.getTela().hide();
						}
					};
					ip.getOk().addClickHandler(ch);
					ip.getFechar().addClickHandler(ch);
				}

			}
		});*/

	}

	public void go(final HasWidgets container) {
		bind();
		display.getPopup().center();
		display.getTbNome().setFocus(true);
	}

	@Override
	public void go() {
		
	}
	
	
}