package br.unibh.loja.soap;
import javax.jws.WebParam;
import javax.jws.WebService;
import br.unibh.loja.model.Produto;
@WebService(name="Produto", targetNamespace="http://www.unibh.br")
public interface ServicoProduto {
public Produto[] listarTodos();
public Produto[] listar(@WebParam(name="nome")String nome);
public Produto buscar(@WebParam(name="id") int id);
public Produto salvar(@WebParam(name="Produto") Produto Produto);
public Produto atualizar(@WebParam(name="Produto") Produto Produto);
public void excluir(@WebParam(name="id") int id);
}