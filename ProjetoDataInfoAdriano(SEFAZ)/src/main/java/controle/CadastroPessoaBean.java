package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.InterfacePessoaDAO;
import dao.PessoaDAO;
import entidade.Pessoa;
import entidade.Telefone;
import util.JpaUtil;

/**
 * 
 * @author Adriano Olimpio
 *
 */

@ManagedBean(name = "CadastroPessoaBean")
@SessionScoped
public class CadastroPessoaBean {

	private Pessoa pessoa;
	private Telefone telefone;
	private List<Pessoa> listaPessoas;
	private String emailSelecionado;

	private InterfacePessoaDAO interPessoaDAO;

	private static final String PESQUISA = "http://localhost:9099/Projeto%20Fuctura%20Sefaz%20Adriano/faces/paginas/pessoa/pesquisarPessoa.xhtml";
	private static final String CADASTROPESSOA = "http://localhost:9099/Projeto%20Fuctura%20Sefaz%20Adriano/faces/paginas/pessoa/cadastroPessoa.xhtml";

	public CadastroPessoaBean() {

		this.pessoa = new Pessoa();
		this.pessoa.setTelefones(new ArrayList<Telefone>());

		this.telefone = new Telefone();
		this.listaPessoas = new ArrayList<Pessoa>();

		this.interPessoaDAO = new PessoaDAO(JpaUtil.getEntityManager());

		this.listaPessoas = this.interPessoaDAO.listarTodos();

		System.out.println(this.listaPessoas);

	}

	public void salvar() throws IOException {

		if (this.interPessoaDAO.inserir(this.pessoa)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Sucesso !!!"));

			abrirPerquisarPessoa();

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao inserir !!!"));
		}
		
		this.pessoa = null;

	}

	public void abrirPerquisarPessoa() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext().redirect(PESQUISA);

	}

	public void pesquisar() {
		
		this.listaPessoas = this.interPessoaDAO.listarTodos();
		System.out.println("Entrou PEsquisar ====");

	}

	public void cadastroPessoa() throws IOException {

		FacesContext.getCurrentInstance().getExternalContext().redirect(CADASTROPESSOA);

	}

	public void adicionarTelefone() {

		if (!this.existeTelefone(telefone)) {

			Telefone telefoneNovo = new Telefone();

			telefoneNovo.setDdd(this.telefone.getDdd());
			telefoneNovo.setNumero(this.telefone.getNumero());
			telefoneNovo.setTipo(this.telefone.getTipo());
			telefoneNovo.setPessoa(this.pessoa);

			this.pessoa.getTelefones().add(telefoneNovo);

			this.telefone = new Telefone();

		} else {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Telefone já existe !!!"));

		}

	}

	private boolean existeTelefone(Telefone telefone) {
		boolean retorno = false;

		for (Telefone telLista : this.pessoa.getTelefones()) {
			if (telLista.getDdd() == telefone.getDdd() && telLista.getNumero().equals(telefone.getNumero())) {
				retorno = true;
			}
		}

		return retorno;
	}

	public void editar() throws IOException {

		Pessoa pessoaEdicao = this.interPessoaDAO.pesquisar(emailSelecionado);
		this.pessoa = pessoaEdicao;

		cadastroPessoa();

	}

	public String remover() {

		Pessoa pessoaRemocao = this.interPessoaDAO.pesquisar(emailSelecionado);
		this.interPessoaDAO.remover(pessoaRemocao);
		this.listaPessoas = this.interPessoaDAO.listarTodos();

		return "";

	}
	
	public void limpar() {
		
		pessoa.setNome(null);
		pessoa.setEmail(null);
		pessoa.setSenha(null);
		telefone.setDdd(0);
		telefone.setNumero(null);
		telefone.setTipo(null);
		
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

	public String getEmailSelecionado() {
		return emailSelecionado;
	}

	public void setEmailSelecionado(String emailSelecionado) {
		this.emailSelecionado = emailSelecionado;
	}

}
