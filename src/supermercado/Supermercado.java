/*
 * PONTIFÍCIA UNIVERSIDADE CATÓLICA DO PARANÁ
 * ESCOLA POLITÉCNICA
 * ANÁLISE E DESENVOLVIMENTO DE SISTEMAS
 * FUNDAMENTOS DE PROGRAMAÇÃO ORIENTADA A OBJETOS
 * ALUNOS: JAIRO MOISÉS STUEHLER BANKHARDT
 * 		   MATEUS VINICIUS FRANCO AGRE
 * 		   VINICIUS BASSETO MIRA
 */

package supermercado;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Supermercado {
	private ArrayList<Produto> produtos;

	public Supermercado() {
		this.produtos = new ArrayList<Produto>();
	}
	
	public String[] leValores (String[] dadosIn){
		String[] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");
		return dadosOut;
	}

	public Bebida leBebida (){

		String [] valores = new String [4];
		String [] nomeVal = {"Código", "Nome", "Fornecedor", "Teor alcoolico em %"};
		valores = leValores (nomeVal);

		int cod = this.retornaInteiro(valores[0], "Código");
		int teorAl = this.retornaInteiro(valores[3], "Teor Alcoolico");

		Bebida bebida = new Bebida (cod, valores[1], valores[2], teorAl);
		return bebida;
	}

	public Limpeza leLimpeza (){

		String [] valores = new String [4];
		String [] nomeVal = {"Código", "Nome", "Fornecedor", "Onde usar?"};
		valores = leValores (nomeVal);

		int cod = this.retornaInteiro(valores[0], "Código");

		Limpeza limpeza = new Limpeza (cod, valores[1], valores[2], valores[3]);
		return limpeza;
	}
	public Lacticinio leLacticinio (){

		String [] valores = new String [4];
		String [] nomeVal = {"Código", "Nome", "Fornecedor", "Textura"};
		valores = leValores (nomeVal);

		int cod = this.retornaInteiro(valores[0], "Código");

		Lacticinio lacticinio = new Lacticinio (cod, valores[1], valores[2], valores[3]);
		return lacticinio;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // Método estático, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // Não conseguiu transformar em inteiro e gera erro
			return false;
		}
	}
	
	public int retornaInteiro(String entrada, String campo) { // retorna um valor inteiro

		//Enquanto não for possível converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto para " + campo + "!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaProdutos (ArrayList<Produto> produtos){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("supermercado.dados"));
			for (int i=0; i < produtos.size(); i++)
				outputStream.writeObject(produtos.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Produto> recuperaprodutos (){
		ArrayList<Produto> produtosTemp = new ArrayList<Produto>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("supermercado.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Produto) {
					produtosTemp.add((Produto) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com produtos NÃO existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return produtosTemp;
		}
	}

	public void menuPetStore (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Supermercado\n" +
					"Opções:\n" + 
					"1. Entrar Produto\n" +
					"2. Exibir Produtos\n" +
					"3. Limpar Produtos\n" +
					"4. Gravar Produtos\n" +
					"5. Recuperar Produtos\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada, "Menu");

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Produtos\n" +
						"Opções:\n" + 
						"1. Limpeza\n" +
						"2. Bebida\n" +
						"3. Lacticínio\n";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada, "Menu");

				switch (opc2){
				case 1: produtos.add((Produto)leLimpeza());
				break;
				case 2: produtos.add((Produto)leBebida());
				break;
				case 3: produtos.add((Produto)leLacticinio());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Produto para entrada NÃO escolhido!");
				}

				break;
			case 2: // Exibir dados
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < produtos.size(); i++)	{
					dados += produtos.get(i).toString();
					if (produtos.get(i) instanceof Bebida) {
						dados += "Teor alcoolico: " + ((Bebida) produtos.get(i)).getTeor() + "%\n";
					}
					else if (produtos.get(i) instanceof Limpeza) {
						dados += "Onde usar: " + ((Limpeza) produtos.get(i)).getOndeUsar() + "\n";
					}
					else {
						dados += "Textura: " + ((Lacticinio) produtos.get(i)).getTextura() + "\n";
					}
					dados += "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
			case 3: // Limpar Dados
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
					break;
				}
				produtos.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
			case 4: // Grava Dados
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Entre com produtos primeiramente");
					break;
				}
				salvaProdutos(produtos);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
			case 5: // Recupera Dados
				produtos = recuperaprodutos();
				if (produtos.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo SUPERMERCADO");
				break;
			}
		} while (opc1 != 9);
	}


	public static void main (String [] args){

		Supermercado sup = new Supermercado ();
		sup.menuPetStore();

	}

}
