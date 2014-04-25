package br.com.wmtt.menu.view;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProvasView extends Composite{

	private VerticalPanel main;
	private FlexTable ftProvas;
	private Button btnCadasatrarProva;
	
	public ProvasView(){
		
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

		HTML titlePage = new HTML("Lista de Provas");
		titlePage.setStyleName("fonteTitulo");
		hpTop.add(titlePage);

		ftProvas = new FlexTable();
		ftProvas.setBorderWidth(0);
		ftProvas.setCellSpacing(0);
		ftProvas.setCellPadding(0);
		ftProvas.setStyleName("flexTable");
		
		btnCadasatrarProva = new Button("Cadastrar nova Prova");


		main.add(hpTop);
		main.setCellHeight(hpTop.asWidget(), "30px");
		main.add(ftProvas);
		main.add(btnCadasatrarProva);
		
	}

	public Widget asWidget() {
		// TODO Auto-generated method stub
		return this;
	}

	public FlexTable getFtProvas() {
		// TODO Auto-generated method stub
		return this.ftProvas;
	}
	
	public Button getBtnCadasatrarProva(){
		return btnCadasatrarProva;
	}

}
