package br.com.wmtt.shared.model;

import java.io.Serializable;
import java.sql.Date;

public class Prova implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idProva;
	private String nome;
	private Date dataCriacao;
	private double valor;
	private String disciplina;
	private Professor professor;
	
	public int getIdProva() {
		return idProva;
	}
	public void setIdProva(int idProva) {
		this.idProva = idProva;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	

}
