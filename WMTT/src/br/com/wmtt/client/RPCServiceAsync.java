package br.com.wmtt.client;

import java.util.List;

import br.com.wmtt.shared.model.Professor;
import br.com.wmtt.shared.model.Prova;
import br.com.wmtt.shared.model._Atividade;
import br.com.wmtt.shared.model._Data;
import br.com.wmtt.shared.model._User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RPCServiceAsync {
	
	public void getListProvas(AsyncCallback<List<Prova>> callback);
	
	public void cadastrarProva(Prova prova, AsyncCallback<Boolean> callback);

	public void getAtividades(AsyncCallback<List<_Atividade>> callback);

	public void cadastraUsuario(_User user, AsyncCallback<Boolean> callback);
	
	public void login(String login, String senha, AsyncCallback<Integer> callback);
	
	public void faleConosco(String name, String email, String msg, AsyncCallback<Boolean> callback);
	
	public void insereAtividade(_Atividade a,  List<_Data> ld, AsyncCallback<Boolean> callback);
	
	public void reenviaConfirmacaoCadastro(_User user, AsyncCallback<Boolean> callback);
	
	public void reenviaConfirmacaoAlteracaoDados(_User user, AsyncCallback<Boolean> callback);
	
	public void recuperarSenha(_User user, AsyncCallback<Boolean> callback);
	
	public void alterarDados(_User user, AsyncCallback<Integer> callback);

	public void getSession(AsyncCallback<_User> callback);

	public void getSessao(AsyncCallback<Boolean> callback);
	
	public void pegarSessao(AsyncCallback<Professor> callback);

	public void inscrever(int codAtividade, AsyncCallback<Integer> callback);
	
	public void cancelar(int codAtividade, AsyncCallback<Boolean> callback);

	public void getAtividadesUsuario(AsyncCallback<List<_Atividade>> callback);

	public void removeSessao(AsyncCallback<Void> callback);

	public void getAtividade(int idAtividade, AsyncCallback<_Atividade> callback);

	public void getCpf(String cpf, AsyncCallback<Boolean> callback);

	public void getEmail(String email, AsyncCallback<Boolean> callback);

	public void getMinicursosDoAluno(AsyncCallback<Boolean> callback);
}
