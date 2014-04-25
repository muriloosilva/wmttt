package br.com.wmtt.server;

import java.util.List;

import javax.servlet.http.HttpSession;

import br.com.wmtt.client.RPCService;
import br.com.wmtt.server.dao.ProvaDAO;
import br.com.wmtt.server.dao._AtiDataDAO;
import br.com.wmtt.server.dao._AtividadeDAO;
import br.com.wmtt.server.dao._DataDAO;
import br.com.wmtt.server.dao._InscricaoDAO;
import br.com.wmtt.server.dao._LoginDAO;
import br.com.wmtt.server.dao._ParticipanteDAO;
import br.com.wmtt.shared.model.Professor;
import br.com.wmtt.shared.model.Prova;
import br.com.wmtt.shared.model._Atividade;
import br.com.wmtt.shared.model._Data;
import br.com.wmtt.shared.model._User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RPCServiceImpl extends RemoteServiceServlet implements RPCService {

	@Override
	public List<Prova> getListProvas(){
		Professor professor = pegarSessao();
		return ProvaDAO.listProva(professor.getIdProfessor());
		
	}
	
	@Override
	public boolean cadastrarProva(Prova prova){
		return ProvaDAO.addProva(prova);
	}
	
	@Override
	public List<_Atividade> getAtividades() {

		List<_Atividade> atividades = _AtividadeDAO.getTodasAtividades();

		return atividades;
	}

	@Override
	public boolean cadastraUsuario(_User user) {
		if (_ParticipanteDAO.loginDisponivel(user.getLogin_partic())) {
			_ParticipanteDAO.cadastraParticipante(user);
			//return ConfirmacaoCadastro.enviaConfirmacaoCadastro(user);
			return true;
		} else
			return false;
	}
	
	@Override
	public boolean reenviaConfirmacaoCadastro(_User user) {
		return true;
	}

	@Override
	public int login(String login, String senha) {
		//0 erro no login
		//1 inativo cadastro
		//2 login ok
		//3 não confirmou alteração dados
		// TODO Auto-generated method stub
		_User user = _LoginDAO.loginUsuario(login, senha);
		if(user != null){
//			System.out.println("user logado:" +user.isLogado());
			if(user.isLogado()){
				if(user.getAtivo()==0){
					return 1;
				}
				else if (user.getAtivo()==1) {
					HttpSession session = getThreadLocalRequest().getSession();
					session.setAttribute("user", user);
					return 2;
				}
				else if(user.getAtivo()==2){
					return 3;
				}
				else{
					return 0;
				}
			}
			else{
				return 0;
			}
		}
		else {
			return 0;
		}
		
	}

	@Override
	public _User getSession() {
		// TODO Auto-generated method stub
		HttpSession session = getThreadLocalRequest().getSession();
		_User user = (_User) session.getAttribute("user");
		return user;
	}
	
	@Override
	public Professor pegarSessao() {
		// TODO Auto-generated method stub
		HttpSession session = getThreadLocalRequest().getSession();
		Professor professor = (Professor) session.getAttribute("professor");
		return professor;
	}

	@Override
	public boolean getSessao() {
		_User user = null;
		HttpSession session = getThreadLocalRequest().getSession();
		user = (_User) session.getAttribute("user");
		if (user != null)
			return user.isLogado();
		else
			return false;
	}

	public double formataHora(String h) {
		double dH;
		if (h.charAt(0) == '0') {
			h = h.substring(1);
			dH = Double.parseDouble(h.substring(0, 4).replace(':', '.'));
		} else {
			dH = Double.parseDouble(h.substring(0, 5).replace(':', '.'));
		}
		return dH;
	}

	//return 0: conflito de horário
	//return 1: não tem vagas
	//return 2: ok
	@Override
	public int inscrever(int codAtividade) {
		// TODO Auto-generated method stub
		_User user = getSession();
		List<_Atividade> atividades = getAtividadesUsuario();
		_Atividade atividade = getAtividade(codAtividade);

		if (atividades != null) {
			
			for (int i = 0; i < atividades.size(); i++) {
				//verificar se já está inscrito na atividade
				List<_Data> datas = atividades.get(i).getDatas();
				
				for (int j = 0; j < datas.size(); j++) {
					double hr_ini_data = formataHora(datas.get(j).getHrInicio().toString());
					double hr_fim_data = formataHora(datas.get(j).getHrFim().toString());
					List<_Data> datasAtividadesInteresse = atividade.getDatas();
					
					for (int k = 0; k < datasAtividadesInteresse.size(); k++) {
						double hora_ini_interesse = formataHora(datasAtividadesInteresse.get(k).getHrInicio().toString());
						double hora_fim_interesse = formataHora(datasAtividadesInteresse.get(k).getHrFim().toString());
						
						if (datasAtividadesInteresse.get(k).getData().equals(datas.get(j).getData())) {
							if (hora_ini_interesse > hr_ini_data) {
								if (hora_ini_interesse < hr_fim_data) {
									return 0;
								}
							} else {
								if (hora_fim_interesse > hr_ini_data) {
									return 0;
								}
							}
						}
						
					}
				
				}
			}
		}
		if(atividade.getVagasDisponiveis() > 0){
			_InscricaoDAO.inscrever(codAtividade, user.getId_partic());
			_AtividadeDAO.decrementaVagas(codAtividade);
			return 2;
		}
		else{
			return 1;
		}
	}

	@Override
	public List<_Atividade> getAtividadesUsuario() {
		// TODO Auto-generated method stub
		_User user = (_User) getThreadLocalRequest().getSession(true)
				.getAttribute("user");
		List<_Atividade> atividades = _AtividadeDAO.getAtividadesUsuario(user
				.getId_partic());

		if (atividades != null)
			return atividades;
		else
			return null;
	}

	@Override
	public void removeSessao() {
		// TODO Auto-generated method stub
		getThreadLocalRequest().getSession(true).removeAttribute("user");
		getThreadLocalRequest().getSession(true).invalidate();
		_User user = (_User) getThreadLocalRequest().getSession(true)
				.getAttribute("user");
		if (user != null)
			System.out.println("usuario na sessão: " + user.getNome_partic());
		else
			System.out.println("sem usuario na sessão");
	}

	@Override
	public _Atividade getAtividade(int idAtividade) {
		// TODO Auto-generated method stub
		_Atividade atividade = _AtividadeDAO.getAtividade(idAtividade);
		return atividade;
	}

	@Override
	public boolean cancelar(int codAtividade) {
		_User user = getSession();
		return _InscricaoDAO.cancelar(codAtividade, user.getId_partic());
	}

	@Override
	public boolean getCpf(String cpf) {
		return _ParticipanteDAO.getCPF(cpf);
	}

	@Override
	public boolean getEmail(String email) {
		// TODO Auto-generated method stub
		return _ParticipanteDAO.getEmail(email);
	}

	@Override
	public boolean getMinicursosDoAluno() {
		_User user = (_User) getThreadLocalRequest().getSession(true)
				.getAttribute("user");
		
		return _AtividadeDAO.getMinicursosDoAluno(user.getId_partic());
	}

	@Override
	public boolean faleConosco(String name, String email, String msg) {
		return true;
	}

	@Override
	public boolean recuperarSenha(_User user) {
		_User userBD = _ParticipanteDAO.getParticipante(user.getEmail_partic());
		if(userBD != null){
			if(user.getCpf_partic().equals(userBD.getCpf_partic())){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean reenviaConfirmacaoAlteracaoDados(_User user) {
		return true;
	}

	@Override
	public int alterarDados(_User user) {
		//0 erro
		//1 alerado, mas com confirmação
		//2 alterado, sem confirmação
		//3 já existe um usuário com este cpf
		//4 já ... com este email
		_User userSession = getSession();
		if(userSession!= null && user != null){
			if(!user.getCpf_partic().equals(userSession.getCpf_partic())){
				if(_ParticipanteDAO.getCPF(user.getCpf_partic()) == false)
					return 3;
			}
			else{
				if(!user.getEmail_partic().trim().equals(userSession.getEmail_partic().trim())){
					if(_ParticipanteDAO.getParticipante(user.getEmail_partic())!= null){
						return 4;
					}
					else{
						//confirmar alteração por e-mail
						_ParticipanteDAO.alterarDados(userSession.getEmail_partic(), user, false);
						return 2;
						//if(ConfirmacaoCadastro.enviaConfirmacaoAlteracaoDados(user)){
						//	return 1;
						//}
						//else{
						//	return 0;
						//}
					}
				}
				else{
					//alterar dados apenas
					if(_ParticipanteDAO.alterarDados(userSession.getEmail_partic(), user, false)){
						return 2;
					}
					else{
						return 0;
					}
				}
			}
		}
		return 0;
	}

	@Override
	public boolean insereAtividade(_Atividade a,  List<_Data> ld) {
		
		int idAtividade = _AtividadeDAO.addAtividade(a);
		System.out.println("Id Atividade: " + idAtividade);
		if(idAtividade == 0)
			return false;
		for(int i = 0; i<ld.size(); i++){
			int id = _DataDAO.addData(ld.get(i));
			System.out.println("Id Data: " + id);
			if(id == 0)
				return false;
			else{
				_AtiDataDAO.addAtiData(id, idAtividade);
			}
		}
		return true;
	}
}
