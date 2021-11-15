package supermercado;

public class Bebida extends Produto {

private static final long serialVersionUID = 1L;
	
	private int teor;

	public Bebida(int codigo, String nome, String fornecedor, int teor) {
		super(codigo, nome, fornecedor);
		this.teor = teor;
		this.categoria = "Bebida";
	}
	
	public int getTeor() {
		return teor;
	}

	@Override
	public String tipo() {
		return "Hidrate-se, refresque-se e brinde.";
	}
}