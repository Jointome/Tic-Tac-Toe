import java.util.Scanner;
import java.util.LinkedList;

public class Jogo extends Comp {
	Position posicaojoga;
	boolean verificaposicao = true;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		char[][] aux_table = new char[3][3];
		char[][] posicions_table = new char[3][3];
		LinkedList<Position> posicions_played = new LinkedList<Position>();
		char[][] exemple = new char[3][3];
		String s = "123456789";
		int exit = 0;
		int k = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				exemple[i][j] = s.charAt(k++);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				posicions_table[i][j] = ' ';
				aux_table[i][j] = ' ';
			}
		}

		System.out.println("Jogo do Galo");
		System.out
				.println("Jogue usando as cordenadas demonstradas no quadro a baixo:");
		board(exemple);
		System.out.println("Escolha o Algoritmo que quer Perder");
		System.out.println("1 - MinMax");
		System.out.println("2 - AlphaBeta");
		int MinMax = in.nextInt();
		while (MinMax != 1 && MinMax != 2) {
			System.out
					.println("Não existem outros Algoritmos escolha outravez");
			MinMax = in.nextInt();
		}
		System.out.println("Agora escolha quem joga primeiro:");
		System.out.println("1 - Computador");
		System.out.println("2 - jogador");
		int player1 = in.nextInt();
		while (player1 != 1 && player1 != 2) {
			System.out.println("Não existem outros jogadores escolha outravez");
			player1 = in.nextInt();
		}
		System.out.println("E para ultima escolha, escolha a dificulty:");
		System.out.println("1 - Facil");
		System.out.println("2 - Medio");
		System.out.println("3 - Difícil");
		System.out.println("4 - Impossível ganhar");
		int dificulty = in.nextInt();
		int depthwhant = 0;
		//Escolher a profundidade para alterar a dificuldade
		while (depthwhant != 1 && depthwhant != 3 && depthwhant != 5
				&& depthwhant != 7) {
			if (dificulty == 1)
				depthwhant = 1;
			else if (dificulty == 2)
				depthwhant = 3;
			else if (dificulty == 3)
				depthwhant = 5;
			else if (dificulty == 4)
				depthwhant = 7;
			else {
				System.out.println("dificulty não existe");
				dificulty = in.nextInt();
			}
		}
		System.out.println();
		//desenhar o quadro
		board(posicions_table);
		System.out.println();
		int depth = 0;
		
		//inicia o jogo
		while (exit == 0 || exit == 1) {
			System.out.println("Sua vez de jogar");
			System.out.println();

			// entra se o jogador for o primeiro  a jogar
			if ((depth != 0 && player1 == 1) || player1 == 2) {

				// O jogador joga sem poder jogar nas posiçoes ocupadas
				Position playposition = getPosition(in);
				while (!jogada(posicions_played, playposition))
					playposition = getPosition(in);
				posicions_played.addFirst(playposition);

				// verifica com qual a pessa que o jogador joga
				if (player1 == 2){
					posicions_table[posicions_played.getFirst().point_x][posicions_played.getFirst().point_y] = 'X';
					aux_table[posicions_played.getFirst().point_x][posicions_played.getFirst().point_y] = 'X';
				}
				else{
					posicions_table[posicions_played.getFirst().point_x][posicions_played.getFirst().point_y] = 'O';
					aux_table[posicions_played.getFirst().point_x][posicions_played.getFirst().point_y] = 'O';
				}

				// desenha o tabuleiro
				System.out.println("Sua jogada");
				board(posicions_table);
				System.out.println();

				// verifica se o jogador ganhou
				if (haswinner(posicions_table, depth) == 10 && exit==0) {
					System.out.println("GANHOU");
					exit = 1;
				}
				//verifica se o jogador empatou
				if (posicions_played.size() == 9 && exit==0) {
					System.out.println("Empate");
					exit = 1;
				}

				depth++;
			}
			if (exit == 0) {
				// joga o computador que retorna uma tabela
				Node n = new Node(posicions_table, depth, 0, null);

				long tempoInicio = System.currentTimeMillis(); 
				if (MinMax == 1)
					posicions_table = MiniMaxDecision(n, depth, depthwhant);
				else
					posicions_table = AlphaBetaDecision(n, depth, depthwhant);

				// adiciona a posição que o computador joga
				posicions_played.addFirst(playcomp(aux_table, posicions_table));
				System.out.println();
				for (int i = 0; i < 3; i++)
					for (int j = 0; j < 3; j++)
						aux_table[i][j] = posicions_table[i][j];

				// desenha o tabuleiro
				System.out.println("Jogada do Computador");
				board(posicions_table);

				//verifica se o computador ganhou
				if (haswinner(posicions_table, depth) == 10 && exit == 0) {
					System.out.println("PERDEU");
					exit = 1;
				}

				//verifica se o computador emptou o jogo
				if (posicions_played.size() == 9 && exit == 0) {
					System.out.println("Empate");
					exit = 1;
				}
				depth++;
			}
			//caso tenha terminado o jogo saber se o jogador quer reiniciar
			if (exit == 1) {
				System.out.println("Escolha opção:");
				System.out.println("1 - Escolher novo Algoritmo");
				System.out.println("2 - Escolher novo primeiro jogador");
				System.out.println("3 - Escolher nova dificulty");
				System.out.println("4 - Jogar novamente");
				System.out.println("5 - Sair");
				int restart = in.nextInt();
				if (restart == 1) {
					System.out.println("1 - MinMax");
					System.out.println("2 - AlphaBeta");
					MinMax = in.nextInt();
					exit = 0;
				} else if (restart == 2) {
					System.out.println("1 - Computador");
					System.out.println("2 - jogador");
					player1 = in.nextInt();
					exit = 0;
				} else if (restart == 3) {
					System.out.println("1 - Facil");
					System.out.println("2 - Medio");
					System.out.println("3 - Difícil");
					System.out.println("4 - Impossível ganhar");
					dificulty = in.nextInt();
					depthwhant = 0;
					while (depthwhant != 1 && depthwhant != 3
							&& depthwhant != 5 && depthwhant != 7) {
						if (dificulty == 1)
							depthwhant = 1;
						else if (dificulty == 2)
							depthwhant = 3;
						else if (dificulty == 3)
							depthwhant = 5;
						else if (dificulty == 4)
							depthwhant = 7;
						else {
							System.out.println("dificulty não existe");
							dificulty = in.nextInt();
						}
					}
					exit = 0;
				} else if (restart == 4) {
					exit=0;
				}
				else if(restart == 5)
					exit = 3;
				if (exit != 3) {
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							posicions_table[i][j] = ' ';
							aux_table[i][j] = ' ';
						}
					}
					while(posicions_played.size()!=0)
						posicions_played.remove();
					System.out.println(posicions_played.size());
					depth = 0;
					board(posicions_table);
				}
			}
		}
	}

	public static void board(char[][] posicions_table) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(posicions_table[i][j]);
				System.out.print(" | ");
			}

			System.out.println(posicions_table[i][2]);
			if (i < 2)
				System.out.println("---------");
		}
	}

	public static boolean jogada(LinkedList<Position> posicions_played,
			Position playposition) {
		int x = playposition.point_x, y = playposition.point_y;

		if (x <= 2 && x >= 0 && y <= 2 && y >= 0)
			for (int i = 0; i < posicions_played.size(); i++) {
				while (playposition.point_x == posicions_played.get(i).point_x
						&& playposition.point_y == posicions_played.get(i).point_y) {
					System.out
							.println("Essa posição já está ocupada jogue uma diferente");
					return false;
				}
			}
		else {
			System.out.println("Posição fora do tabuleiro jogue novamente");
			return false;
		}
		return true;
	}

	public static Position getPosition(Scanner in) {
		Position getPos = new Position(0, 0);
		switch (in.nextInt()) {
		case 1:
			return getPos;
		case 2:
			return getPos = new Position(0, 1);
		case 3:
			return getPos = new Position(0, 2);
		case 4:
			return getPos = new Position(1, 0);
		case 5:
			return getPos = new Position(1, 1);
		case 6:
			return getPos = new Position(1, 2);
		case 7:
			return getPos = new Position(2, 0);
		case 8:
			return getPos = new Position(2, 1);
		case 9:
			return getPos = new Position(2, 2);
		default:
			return getPos = new Position(10, 10);
		}

	}

}
