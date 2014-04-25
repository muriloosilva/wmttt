package br.com.wmtt.menu.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CadastroProvaView extends Composite{

	private VerticalPanel main;
	private Button btnCadasatrarProva;
	
	public CadastroProvaView(){
		
		main = new VerticalPanel();
		initWidget(main);
		main.setBorderWidth(0);
		main.setSpacing(5);
		main.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
		main.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		main.setWidth("100%");
		main.setHeight("100%");
		main.addStyleName("vpMainPageContent");
		
		HorizontalPanel hpTop = new HorizontalPanel();
		hpTop.setBorderWidth(0);
		hpTop.setSpacing(0);
		hpTop.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		hpTop.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		hpTop.setWidth("100%");
		hpTop.setHeight("24px");

		HTML titlePage = new HTML("Cadastrar nova prova");
		titlePage.setStyleName("fonteTitulo");
		hpTop.add(titlePage);
		
		HorizontalPanel hpMiddle = new HorizontalPanel();
		hpMiddle.setBorderWidth(0);
		hpMiddle.setSpacing(0);
		hpTop.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		hpTop.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		hpTop.setWidth("100%");
		hpTop.setHeight("24px");
		
		HTML labelNome = new HTML("Nome:");
		labelNome.setStyleName("fontePadrao");
		hpMiddle.add(labelNome);
		
		TextBox tbNome = new TextBox();
		tbNome.setWidth("100px");
		hpMiddle.add(tbNome);
		
		HTML labelData = new HTML("Data:");
		labelData.setStyleName("fontePadrao");
		hpMiddle.add(labelData);
		
		TextBox tbData = new TextBox();
		tbData.setWidth("100px");
		hpMiddle.add(tbData);
		
		HTML labelValor = new HTML("Valor:");
		labelValor.setStyleName("fontePadrao");
		hpMiddle.add(labelValor);
		
		TextBox tbValor = new TextBox();
		tbValor.setWidth("100px");
		hpMiddle.add(tbValor);
		
		HTML labelDiscplina = new HTML("Disciplina");
		labelDiscplina.setStyleName("fontePadrao");
		hpMiddle.add(labelDiscplina);
		
		TextBox tbDisciplina = new TextBox();
		tbDisciplina.setWidth("100px");
		hpMiddle.add(tbDisciplina);

		btnCadasatrarProva = new Button("Cadastrar");

		main.add(hpTop);
		main.setCellHeight(hpTop.asWidget(), "30px");
		main.add(hpMiddle);
		main.add(btnCadasatrarProva);
		
	}

	public Widget asWidget() {
		return this;
	}
	
	public Button getBtnCadasatrarProva(){
		return btnCadasatrarProva;
	}

}
