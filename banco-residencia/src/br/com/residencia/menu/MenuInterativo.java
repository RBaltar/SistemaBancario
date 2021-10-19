package br.com.residencia.menu;

import java.io.IOException;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import br.com.residencia.IO.LeituraEscrita;
import br.com.residencia.contas.Conta;
import br.com.residencia.contas.ContaCorrente;
import br.com.residencia.enums.PessoasTipoEnum;
import br.com.residencia.main.Principal;
import br.com.residencia.pessoas.Usuarios;

public class MenuInterativo {

	double valor;
	static String cpf;
	int escolha;


	
	public static void menuInterativo(Usuarios usuario, Conta conta)
			throws IOException {
		Principal principal = new Principal();
		ContaCorrente cc = new ContaCorrente();
		
		
		try {
			
			principal.pularLinha();
			System.out.println("[1]\tSaque");
			System.out.println("[2]\tDeposito");
			System.out.println("[3]\tTransfer�ncia");
			System.out.println("[4]\tSaldo");
			System.out.println("[5]\tRelat�rio");
			System.out.println("[6]\tSair");
			principal.imprimirLinha();
			
			System.out.print("Escolha uma op��o para seguir: ");
//			JOptionPane.showMessageDialog(null, "Escolha uma op��o para seguir: ");
			int opcao = principal.sc.nextInt();
//			String valor1;
			
			switch(opcao) {
			case 1:
				System.out.print("Digite o valor que gostaria de retirar: ");
//				valor1 = JOptionPane.showInputDialog("Digite o valor para a opera��o: ");
//				Double valor = Double.parseDouble(valor1);
//				JOptionPane.showMessageDialog(null, "Digite o valor que gostaria de retirar: ");
				Double valor = Double.parseDouble(principal.sc.next());
				
						
				conta.sacar(valor);
				
				LeituraEscrita.comprovanteSaque(conta, valor);
				
				principal.pularLinha();
				break;
			
			case 2:
				System.out.print("Digite o valor apra deposito: ");
				valor = Double.parseDouble(principal.sc.next());
//				valor1 = JOptionPane.showInputDialog("Digite o valor para a opera��o: ");
//				valor = Double.parseDouble(valor1);
				
				conta.depositar(valor);
				
				LeituraEscrita.comprovanteDeposito(conta, valor);
				
				principal.pularLinha();
				break;
				
			case 3:
				System.out.print("Digite o cpf para o qual deseja transferir: ");
				cpf = principal.sc.next();
//				cpf = JOptionPane.showInputDialog("Digite o cpf para o qual deseja transferir: ");
				
				Conta destinatario = Conta.mapaContas.get(cpf);
				
				while(destinatario == null) {
					System.out.println("Cpf n�o encontrado no sistema!\n\n");
//					JOptionPane.showMessageDialog(null, "Cpf n�o encontrado no sistema!\n\n");
					System.out.print("Digite o cpf para o qual deseja transferir: ");
					cpf = principal.sc.next();
//					cpf = JOptionPane.showInputDialog("Digite o cpf para o qual deseja transferir: ");
					destinatario = Conta.mapaContas.get(cpf);
				}
				System.out.println("Informe o valor a ser transferido: ");
				valor = Double.parseDouble(principal.sc.next());
//				valor1 = JOptionPane.showInputDialog("Digite o valor para a opera��o: ");
//				valor = Double.parseDouble(valor1);
				
				conta.transferir(valor, destinatario);
				
				LeituraEscrita.comprovanteTransferencia(conta, valor, destinatario);
				
				principal.pularLinha();
				break;
				
			case 4:
				System.out.println("Seu saldo � de: R$" + String.format("%.d", conta.getSaldo()));
//				JOptionPane.showMessageDialog(null, "Seu saldo � de: R$" + String.format("%.2d", conta.getSaldo()));
				
				principal.pularLinha();
				break;
				
			case 5:
				if (usuario.getCargo().equalsIgnoreCase(PessoasTipoEnum.CLIENTE.getTipoUsuario())) {
					MenuRelatorio.menuRelatorio(PessoasTipoEnum.CLIENTE.getIdTipoUsuario(), usuario, conta);
				} else if (usuario.getCargo().equalsIgnoreCase(PessoasTipoEnum.GERENTE.getTipoUsuario())) {
					MenuRelatorio.menuRelatorio(PessoasTipoEnum.GERENTE.getIdTipoUsuario(), usuario, conta);
				} else if (usuario.getCargo().equalsIgnoreCase(PessoasTipoEnum.DIRETOR.getTipoUsuario())) {
					MenuRelatorio.menuRelatorio(PessoasTipoEnum.DIRETOR.getIdTipoUsuario(), usuario, conta);
				} else if (usuario.getCargo().equalsIgnoreCase(PessoasTipoEnum.PRESIDENTE.getTipoUsuario())) {
					MenuRelatorio.menuRelatorio(PessoasTipoEnum.PRESIDENTE.getIdTipoUsuario(), usuario, conta);
				}
				
				break;
				
			case 6:
				
				principal.menuInterativo();
				
				break;
			
			default:
				
//				System.out.println("Essa op��o n�o � v�lida!");
//				JOptionPane.showMessageDialog(null, "Essa op��o n�o � v�lida!");
				
			}
			menuInterativo(usuario, conta);

		}  catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			menuInterativo(usuario, conta);
		}
	}

			
}

