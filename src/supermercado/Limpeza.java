package supermercado;

public class Limpeza extends Produto {

	private static final long serialVersionUID = 1L;
	
	private String ondeUsar;

	public Limpeza(int codigo, String nome, String fornecedor, String ondeUsar) {
		super(codigo, nome, fornecedor);
		this.ondeUsar = ondeUsar;
		this.categoria = "Limpeza";
	}
	
	public String getOndeUsar() {
		return ondeUsar;
	}
	
	@Override
	public String tipo() {
		return "Para deixar tudo limpo.";
	}
}
