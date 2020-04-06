package controle;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * 
 * @author Adriano Olimpio
 *
 */

@ManagedBean(name = "LoginPessoaBean")
@RequestScoped
public class LoginPessoaBean {

	private String pessoaLogin;
	private String pessoaSenha;
	private String loginPadrao = "admin";
	private String senhaPadrao = "admin";

	private static final String PESQUISAR = "http://localhost:9099/Projeto%20Fuctura%20Sefaz%20Adriano/faces/paginas/pessoa/pesquisarPessoa.xhtml";
	private static final String NOVOCADASTRO = "http://localhost:9099/Projeto%20Fuctura%20Sefaz%20Adriano/faces/paginas/pessoa/cadastroPessoa.xhtml";

	public void entrar() throws IOException {

		if (this.pessoaLogin.equals(this.loginPadrao) && this.pessoaSenha.equals(this.senhaPadrao)) {

			FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISAR);

		} else {

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Clique em: Esqueci Senha"));

		}

	}

	public void pessoaNova() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext().redirect(NOVOCADASTRO);

	}

	public void limpar() {
		pessoaLogin = null;
		pessoaSenha = null;

	}

	public String getPessoaLogin() {
		return pessoaLogin;
	}

	public void setPessoaLogin(String pessoaLogin) {
		this.pessoaLogin = pessoaLogin;
	}

	public String getPessoaSenha() {
		return pessoaSenha;
	}

	public void setPessoaSenha(String pessoaSenha) {
		this.pessoaSenha = pessoaSenha;
	}

}
