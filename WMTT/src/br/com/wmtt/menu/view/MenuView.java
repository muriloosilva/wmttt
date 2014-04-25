package br.com.wmtt.menu.view;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class MenuView extends Composite{

	private FlowPanel vp;
	private Anchor provas, sobre, contato;
	
	public MenuView(){
		
		vp = new FlowPanel();
		initWidget(vp);
		//vp.setBorderWidth(0);
		//vp.setSpacing(0);
//		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		//vp.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		vp.setWidth("100%");
		vp.setHeight("100%");
		//vp.addStyleName("vpLogin");
//		vp.addStyleName("vpMainPageContent");
		
		provas = new Anchor("Provas");
		provas.removeStyleName("gwt-Anchor");
		provas.setWidth("136px");
		provas.addStyleName("fonteMenu");
		
		sobre = new Anchor("Sobre");
		sobre.removeStyleName("gwt-Anchor");
		sobre.setWidth("136px");
		sobre.addStyleName("fonteMenu");
		
		contato = new Anchor("Contato");
		contato.removeStyleName("gwt-Anchor");
		contato.setWidth("136px");
		contato.addStyleName("fonteMenu");
		
		vp.add(provas);
		vp.add(sobre);
		vp.add(contato);
		
	}

	public Widget asWidget() {
		return this;
	}

	public Anchor getProvas() {
		return this.provas;
	}
	
	public Anchor getSobre() {
		return this.sobre;
	}
	
	public Anchor getContato() {
		return this.contato;
	} 

}
