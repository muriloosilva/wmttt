package br.com.wmtt.shared.model;

public class QuestaoProvaProfessor {
	
	private int idQuestoesProvaProfessor;
	private Professor professor;
	private Prova prova;
	private Questao questao;
	
	public int getIdQuestoesProvaProfessor() {
		return idQuestoesProvaProfessor;
	}
	public void setIdQuestoesProvaProfessor(int idQuestoesProvaProfessor) {
		this.idQuestoesProvaProfessor = idQuestoesProvaProfessor;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Prova getProva() {
		return prova;
	}
	public void setProva(Prova prova) {
		this.prova = prova;
	}
	public Questao getQuestao() {
		return questao;
	}
	public void setQuestao(Questao questao) {
		this.questao = questao;
	}
	
	

}
