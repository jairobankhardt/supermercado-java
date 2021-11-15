package supermercado;

import java.io.Serializable;

public abstract class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int codigo;
	private String nome;
	private String fornecedor;
	protected String categoria;

	public Produto(int codigo, String nome, String fornecedor) {
		this.codigo = codigo;
		this.nome = nome;
		this.fornecedor = fornecedor;
	}
	
	public abstract String tipo();

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Código: " + this.codigo + "\n");
		sb.append("Nome: " + this.nome + "\n");
		sb.append("Fornecedor: " + this.fornecedor + "\n");
		sb.append("Categoria: " + this.categoria + "\n");
		sb.append("Tipo: " + tipo() + "\n");
		return sb.toString();
	}
	
}
