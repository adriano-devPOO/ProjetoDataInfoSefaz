package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entidade.Pessoa;

public class PessoaDAO implements InterfacePessoaDAO{
	
	private EntityManager ent;

	
	public PessoaDAO() {
		
		
	}

	public PessoaDAO(EntityManager ent) {
		this.ent = ent;
	}

	public boolean inserir(Pessoa pessoa) {
		
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.persist(pessoa);
		tx.commit();
		
		return true;
	}

	public void alterar(Pessoa pessoa) {
		
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.merge(pessoa);
		tx.commit();

	}

	public void remover(Pessoa pessoa) {
		
		EntityTransaction tx = ent.getTransaction();
		tx.begin();

		ent.remove(pessoa);
		tx.commit();

	}
	
	public Pessoa pesquisar(String email) {

		Pessoa pessoa = ent.find(Pessoa.class, email);
		
		return pessoa;
		
	}
	
	public List<Pessoa> listarTodos() {

		Query query = ent.createQuery("from Pessoa u");
		
		@SuppressWarnings("unchecked")
		List<Pessoa> pessoas = query.getResultList();
	
		return pessoas;
		
	}

}
