package supermercado;

public class Lacticinio extends Produto {

	private static final long serialVersionUID = 1L;
	
	private String textura;

	public Lacticinio(int codigo, String nome, String fornecedor, String textura) {
		super(codigo, nome, fornecedor);
		this.textura = textura;
		this.categoria = "Lacticínio";
	}
	
	public String getTextura() {
		return textura;
	}

	@Override
	public String tipo() {
		return "Produtos lácteos - inclui leite e seus derivados.";
	}
}
