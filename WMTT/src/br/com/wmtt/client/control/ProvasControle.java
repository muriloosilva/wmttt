package br.com.wmtt.client.control;

import java.util.List;

import br.com.wmtt.client.AppController;
import br.com.wmtt.client.RPCServiceAsync;
import br.com.wmtt.menu.view.ProvasView;
import br.com.wmtt.shared.model.Prova;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;

public class ProvasControle{

	private RPCServiceAsync rpcService = null;
	private ProvasView provasView = null;
	private AppController appC = null;

	public ProvasControle(AppController appC, RPCServiceAsync rpcService, ProvasView provasView, HasWidgets container) {
		
		this.provasView = provasView;
		this.appC = appC;
		this.rpcService = rpcService;
		
		adicionaEventos();
		adicionaConteudo(container);
		pegarListaDeProvas();
		
	}
	
	public void pegarListaDeProvas(){
		
		rpcService.getListProvas(new AsyncCallback<List<Prova>>() {
			
			@Override
			public void onSuccess(List<Prova> result) {
				preencheTabela(result);
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Erro ao buscar lista de provas");
				preencheTabela(null);
				
			}
			
			
		});
	}
	
	public void adicionaEventos(){
		provasView.getBtnCadasatrarProva().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				appC.novoEvento("cadastrarProva");
			}
		});
	}
	
	public void adicionaConteudo(HasWidgets container) {
		// TODO Auto-generated method stub
		container.clear();
		container.add(provasView.asWidget());
	}
	
	private void preencheTabela(List<Prova> provas) {
		if(provas != null){
			provasView.getFtProvas().removeAllRows();
			if(!provas.isEmpty()){
				
				//cria cabecalho tabela
				
				HTML nomeTitulo = new HTML();
				nomeTitulo.setText("Nome");
				nomeTitulo.removeStyleName("gwt-HTML");
				nomeTitulo.setStyleName("fonteTituloTabela");
				
				HTML dataTitulo = new HTML();
				dataTitulo.setText("Data");
				dataTitulo.removeStyleName("gwt-HTML");
				dataTitulo.setStyleName("fonteTituloTabela");
				
				HTML valorTitutlo = new HTML();
				valorTitutlo.setText("Valor");
				valorTitutlo.removeStyleName("gwt-HTML");
				valorTitutlo.setStyleName("fonteTituloTabela");
				
				HTML disciplinaTitulo = new HTML();
				disciplinaTitulo.setText("Disciplina");
				disciplinaTitulo.removeStyleName("gwt-HTML");
				disciplinaTitulo.setStyleName("fonteTituloTabela");
				
				HTML botao = new HTML();
				botao.setText("");
				botao.removeStyleName("gwt-HTML");
				botao.setStyleName("fonteTituloTabela");
				
				provasView.getFtProvas().setWidget(0, 0, nomeTitulo);
				provasView.getFtProvas().setWidget(0, 1, dataTitulo);
				provasView.getFtProvas().setWidget(0, 2, valorTitutlo);
				provasView.getFtProvas().setWidget(0, 3, disciplinaTitulo);
				provasView.getFtProvas().setWidget(0, 4, botao);
				
				provasView.getFtProvas().getCellFormatter().addStyleName(0, 0, "col1");
				provasView.getFtProvas().getCellFormatter().addStyleName(0, 1, "col2");
				provasView.getFtProvas().getCellFormatter().addStyleName(0, 2, "col3");
				provasView.getFtProvas().getCellFormatter().addStyleName(0, 3, "col4");
				provasView.getFtProvas().getCellFormatter().addStyleName(0, 4, "col5");
				
				for (int i = 0; i < provas.size(); ++i) {
					int linha = i+1;
					Prova prova = provas.get(i);
		
					HTML nome = new HTML();
					nome.setText(prova.getNome());
					nome.removeStyleName("gwt-HTML");
					nome.setStyleName("fontePadrao");
					
					HTML data = new HTML();
					DateTimeFormat fmt = DateTimeFormat.getFormat("dd-MM-yyyy");
					data.setText(fmt.format(prova.getDataCriacao()));
					data.removeStyleName("gwt-HTML");
					data.setStyleName("fontePadrao");
					
					HTML valor = new HTML();
					valor.setText(prova.getValor()+"");
					valor.removeStyleName("gwt-HTML");
					valor.setStyleName("fontePadrao");
					
					HTML disciplina = new HTML();
					disciplina.setText(prova.getDisciplina());
					disciplina.removeStyleName("gwt-HTML");
					disciplina.setStyleName("fontePadrao");
					
					Button btnConfigurar = new Button("configurar");
					
								
					provasView.getFtProvas().setWidget(linha, 0, nome);
					provasView.getFtProvas().setWidget(linha, 1, data);
					provasView.getFtProvas().setWidget(linha, 2, valor);
					provasView.getFtProvas().setWidget(linha, 3, disciplina);
					provasView.getFtProvas().setWidget(linha, 4, btnConfigurar);
					
					//btnConfigurar.removeStyleName("gwt-Button");
					//btnConfigurar.addStyleName("btn-info");
					
					
		//			if(!prova.getTipoAtiv().equals("Palestra")){
		////				HTML vagas = new HTML();
		////				vagas.setText("Vagas: "+a.getVagasDisponiveis());
		////				vagas.removeStyleName("gwt-HTML");
		////				ftb.setWidget(i, 3, vagas);
		//				
		//				btInsc = new Button("Inscrever");
		//				btInsc.removeStyleName("gwt-Button");
		//				btInsc.addStyleName("btn-info");
		//				ftb.getCellFormatter().addStyleName(i, 4, "col5");
		//				ftb.getCellFormatter().addStyleName(i, 5, "col6");
		//				ftb.setWidget(i, 5, btInsc);
		//				ftb.getRowFormatter().getElement(i).setAttribute("id", String.valueOf(prova.getIdAtiv()));
		//				vagas(prova, i, ftb);
		//			}
		//			else {
		////				btM.setWidth("199px");
		//				HTML vagas = new HTML();
		////				vagas.setText("Vagas: "+a.getVagasDisponiveis());
		//				vagas.removeStyleName("gwt-HTML");
		//				tabelaPalestras.setWidget(i, 3, vagas);
		//				
		//				ftb.getCellFormatter().addStyleName(i, 4, "col4Palestra");
		//				ftb.getRowFormatter().getElement(i).setAttribute("id", String.valueOf(prova.getIdAtiv()));
		//			}
		//			
		////			vagas(a, i, ftb);
					
					provasView.getFtProvas().getCellFormatter().addStyleName(linha, 0, "col1");
					provasView.getFtProvas().getCellFormatter().addStyleName(linha, 1, "col2");
					provasView.getFtProvas().getCellFormatter().addStyleName(linha, 2, "col3");
					provasView.getFtProvas().getCellFormatter().addStyleName(linha, 3, "col4");
					provasView.getFtProvas().getCellFormatter().addStyleName(linha, 4, "col5");
				}
			}else{
				HTML nome = new HTML();
				nome.setText("Sem provas cadastradas");
				nome.removeStyleName("gwt-HTML");
				provasView.getFtProvas().setWidget(0, 0, nome);
				provasView.getFtProvas().getCellFormatter().addStyleName(0, 0, "col1");
			}
	}else{
		HTML nome = new HTML();
		nome.setText("Erro");
		nome.removeStyleName("gwt-HTML");
		provasView.getFtProvas().setWidget(0, 0, nome);
		provasView.getFtProvas().getCellFormatter().addStyleName(0, 0, "col1");
		}
	}

	
	
}
