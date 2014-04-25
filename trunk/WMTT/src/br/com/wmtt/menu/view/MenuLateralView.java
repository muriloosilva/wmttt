package br.com.wmtt.menu.view;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MenuLateralView extends Composite{

	private VerticalPanel vp;
	private Anchor questoes, editar, imprimir;
	
	public MenuLateralView(){
		
		vp = new VerticalPanel();
		initWidget(vp);
		//vp.setBorderWidth(0);
		vp.setSpacing(20);
//		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("100%");
		vp.setHeight("40px");
		//vp.addStyleName("vpLogin");
//		vp.addStyleName("vpMainPageContent");
		
		questoes = new Anchor("Questoes");
		questoes.removeStyleName("gwt-Anchor");
		questoes.setWidth("136px");
		questoes.addStyleName("fonteMenuLateral");
		
		editar = new Anchor("Editar Prova");
		editar.removeStyleName("gwt-Anchor");
		editar.setWidth("136px");
		editar.addStyleName("fonteMenuLateral");
		
		imprimir = new Anchor("Imprimir");
		imprimir.removeStyleName("gwt-Anchor");
		imprimir.setWidth("136px");
		imprimir.addStyleName("fonteMenuLateral");
		
		vp.add(questoes);
		vp.add(editar);
		vp.add(imprimir);
		
	}

	public Widget asWidget() {
		return this;
	}

	public Anchor getQuestoes() {
		return this.questoes;
	}
	
	public Anchor getEditar() {
		return this.editar;
	}
	
	public Anchor getImprimir() {
		return this.imprimir;
	} 

}
