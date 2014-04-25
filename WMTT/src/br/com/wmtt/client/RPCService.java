package br.com.wmtt.client;

import java.util.List;

import br.com.wmtt.shared.model.Professor;
import br.com.wmtt.shared.model.Prova;
import br.com.wmtt.shared.model._Atividade;
import br.com.wmtt.shared.model._Data;
import br.com.wmtt.shared.model._User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("RPCService")
public interface RPCService extends RemoteService {

	List<Prova> getListProvas();
	boolean cadastrarProva(Prova prova);
	List<_Atividade> getAtividades();
	List<_Atividade> getAtividadesUsuario();
	boolean cadastraUsuario(_User user);
	int login(String login, String senha);
	_User getSession();
	Professor pegarSessao();
	boolean faleConosco(String name, String email, String msg);
	boolean insereAtividade(_Atividade a, List<_Data> ld);
	boolean reenviaConfirmacaoCadastro(_User user);
	boolean reenviaConfirmacaoAlteracaoDados(_User user);
	int alterarDados(_User user);
	boolean recuperarSenha(_User user);
	boolean getSessao();
	int inscrever(int codAtividade);
	boolean cancelar(int codAtividade);
	void removeSessao();
	_Atividade getAtividade(int idAtividade);
	boolean getCpf(String cpf);
	boolean getEmail(String email);
	boolean getMinicursosDoAluno();
}
