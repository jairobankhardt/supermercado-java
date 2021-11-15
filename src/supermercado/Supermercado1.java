package supermercado;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Supermercado1 {

	private ArrayList<Produto> produtos;


	public Supermercado1( ) {
		this.produtos = new ArrayList<Produto>();
	}

	public void adicionaProduto(Produto prod) {
		this.produtos.add(prod);
	}

	public void listarProdutos() {
		for(Produto prod:produtos) {
			System.out.println(prod.toString());
		}
		System.out.println("Total = " + this.produtos.size() + " produtos listados com sucesso!\n");
	}
	
	public void excluirProduto(Produto prod) {
		if (this.produtos.contains(prod)) {
			this.produtos.remove(prod);
			System.out.println("[Produto " + prod.toString() + "excluido com sucesso!]\n");
		}
		else
			System.out.println("Produto inexistente!\n");
	}

	public void excluirProdutos() {
		produtos.clear();
		System.out.println("Produtos excluidos com sucesso!\n");
	}
	public void gravarProdutos()  {
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream (new FileOutputStream("produtos.txt"));
			for(Produto prod:produtos) {
				outputStream.writeObject(prod);
			}
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (outputStream != null ) {
					outputStream.flush();
					outputStream.close();
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void recuperarProdutos() {
		ObjectInputStream inputStream = null;
		try {
			inputStream	= new ObjectInputStream (new FileInputStream ("produtos.txt"));
			Object obj = null;
			while((obj = inputStream.readObject()) != null) {
				if (obj instanceof Bebida)  
					this.produtos.add((Bebida)obj);
				else if (obj instanceof Limpeza)  
					this.produtos.add((Limpeza)obj);
				else if (obj instanceof Lacticinio)
					this.produtos.add((Lacticinio)obj);
			}
		}catch (EOFException ex) {     // when EOF is reached
			System.out.println ("Chegou ao final do arquivo");
		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}catch (IOException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if (inputStream != null ) {
					inputStream.close();
					System.out.println("Produtos recuperados com sucesso!\n");
				}
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void testaProd() {
		Produto prod = produtos.get(0);
		if (prod instanceof Bebida) {
			System.out.println(((Bebida) prod).getTeor());
		}
		else 
			System.out.println("Não era");
		//System.out.println();
		System.out.println(prod);
	}


	public static void main(String[] args) {
		Supermercado1 prd  = new Supermercado1();

		Bebida gin 			 = new Bebida(1187, "Dry Martini", "London Dry Gin", 32);
		Bebida soda 		 = new Bebida(4429, "Soda Tônica", "Antartica", 0);
		Limpeza desinfetante = new Limpeza(5342, "Pinho Sol", "Bombril", "Pisos em geral e banheiro");
		Limpeza sabao 		 = new Limpeza(3235, "Pedra Sabão", "Crácrá", "Roupas e louça");
		Lacticinio iogurt 	 = new Lacticinio(8793, "Grego", "Batavo", "Pastoso");
		Lacticinio leite 	 = new Lacticinio(6894, "Leite Pasteurizado", "Tirol", "Líquido");
		prd.adicionaProduto(gin);
		prd.adicionaProduto(soda);
		prd.adicionaProduto(desinfetante);
		prd.adicionaProduto(sabao);
		prd.adicionaProduto(iogurt);
		prd.adicionaProduto(leite);
		prd.listarProdutos();
		prd.gravarProdutos();
		prd.excluirProduto(sabao);
		prd.listarProdutos();
		prd.excluirProdutos();
		prd.listarProdutos();
		prd.recuperarProdutos();
		prd.listarProdutos();
		prd.testaProd();
	}

}
