package br.unibh.loja.soap;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.EntityManager;

import br.unibh.loja.model.Produto;

@WebService(endpointInterface = "br.unibh.loja.soap.ServicoProduto", serviceName = "Produto")
@Stateless
public class ServicoProdutoImpl implements ServicoProduto {
	@Inject
	private EntityManager em;
	@Inject
	private Logger log;

	@SuppressWarnings("unchecked")
	@Override
	public Produto[] listarTodos() {
		log.info("Encontrando todos os Produtos");
		List<Object> lista = em.createQuery("from Produto").getResultList();
		return lista.toArray(new Produto[lista.size()]);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Produto[] listar(String nome) {
		log.info("Encontrando Produto " + nome);
		List<Object> lista = em.createQuery("select c from Produto c where c.descricao like :nome")
				.setParameter("nome", nome + "%").getResultList();
		return lista.toArray(new Produto[lista.size()]);
	}

	@Override
	public Produto buscar(int id) {
		log.info("Encontrando Produto cujo id = " + id);
		return em.find(Produto.class, new Long(id));
	}

	@Override
	public Produto salvar(Produto Produto) {
		log.info("Persistindo " + Produto);
		em.persist(Produto);
		return Produto;
	}

	@Override
	public Produto atualizar(Produto Produto) {
		log.info("Atualizando " + Produto);
		return em.merge(Produto);
	}

	@Override
	public void excluir(int id) {
		log.info("Removendo Produto cujo id = " + id);
		em.remove(em.find(Produto.class, new Long(id)));
	}
}