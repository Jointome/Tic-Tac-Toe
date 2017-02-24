import java.util.LinkedList;

public class Comp {
	public static char[][] MiniMaxDecision(Node n,int depth,int depthwhant) {
		LinkedList<Node> sucess = new LinkedList<Node>();
		Node s = new Node();
		Node best_node = new Node();
		int value;
		int p = Integer.MIN_VALUE;
		int v = Integer.MIN_VALUE;
		sucess = Sucessors(n);
		while (!sucess.isEmpty()) {
			s = sucess.remove();
			value = Max(v, MinValue(s,depth,depthwhant));
			if(value>v){
				v=value;}
			else if(value==v){
				if(best_node.depth>s.depth)
				best_node = new Node(s);
			}
			if(p!=v){
				p=v;
				best_node = new Node(s);
			}
		}
		return best_node.table;
	}

	public static int MaxValue(Node n,int depth,int depthwhant) {
		LinkedList<Node> sucess = new LinkedList<Node>();
		Node s = new Node();
		int value;
		int winner = haswinner(n.table, depth);
		if (winner == 10 || winner == -10)
			return winner;
		if ((n.depth-depth) == depthwhant || n.depth==8) {
			return findchances(n.table, depth, 0);
		}
		int v = Integer.MIN_VALUE;
		sucess = Sucessors(n);
		while (!sucess.isEmpty()) {
			s = sucess.remove();
			value = Max(v, MinValue(s,depth,depthwhant));
			if(value>v){
				v=value;}
		}
		return v;
	}

	public static int MinValue(Node n, int depth,int depthwhant) {
		LinkedList<Node> sucess = new LinkedList<Node>();
		Node s = new Node();
		int value;
		int winner = haswinner(n.table, depth);
		if (winner == 10 || winner == -10)
			return winner;

		if ((n.depth-depth) == depthwhant || n.depth==8) {
			return findchances(n.table,depth, 0);
		}

		int v = Integer.MAX_VALUE;
		sucess = Sucessors(n);
		while (!sucess.isEmpty()) {
			s = sucess.remove();
			value = Min(v, MaxValue(s,depth,depthwhant));
			if(value<v){
				v=value;}
		}
		return v;
	}

	public static LinkedList<Node> Sucessors(Node n) {
		LinkedList<Node> sucess = new LinkedList<Node>();
		Node m = new Node();
		for (int i = 2; i >= 0; i--) {
			for (int j = 2; j >= 0; j--) {
				if ((n.table[i][j] == ' ')) {
					if (n.depth % 2 == 0) {
						n.table[i][j] = 'X';
					} else {
						n.table[i][j] = 'O';
					}
					m = new Node(n.table, n.depth + 1, 0, null);
					sucess.addFirst(m);
					n.table[i][j] = ' ';
				}
			}
		}
		return sucess;
	}

	public static int Max(int v, int t) {
		if (v > t)
			return v;
		else
			return t;
	}

	public static int Min(int v, int t) {
		if (v > t)
			return t;
		else
			return v;
	}

	public static char[][] AlphaBetaDecision(Node n,int depth,int depthwhant){
		LinkedList<Node> sucess = new LinkedList<Node>();
		Node s = new Node();
		Node best_node = new Node();
		int p = Integer.MIN_VALUE;
		int v = Integer.MIN_VALUE;
		sucess = Sucessors(n);
		while (!sucess.isEmpty()) {
			s = sucess.remove();
			v=Max(v,MinValueABeta(s,Integer.MIN_VALUE,Integer.MAX_VALUE,depth,depthwhant));
			if(p!=v){
				p=v;
				best_node = new Node(s);
			}
		}
		return best_node.table;
	}

	public static int MaxValueABeta(Node n, int alpha,int beta,int depth,int depthwhant){
		LinkedList<Node> sucess = new LinkedList<Node>();
		Node s = new Node();
		int winner = haswinner(n.table, depth);
		if (winner == 10 || winner == -10)
			return winner;
		if (n.depth == 8||(n.depth-depth)==depthwhant) {
			return findchances(n.table, depth, 0);
		}
		int v = Integer.MIN_VALUE;
		sucess = Sucessors(n);
		while (!sucess.isEmpty()) {
			s = sucess.remove();
			v = Max(v, MinValueABeta(s,alpha,beta,depth,depthwhant));
			if(v>=beta)
				return v;
			alpha=Max(alpha,v);
		}
		return v;
	}

	public static int MinValueABeta(Node n,int alpha,int beta,int depth,int depthwhant){
		LinkedList<Node> sucess = new LinkedList<Node>();
		Node s = new Node();
		int winner = haswinner(n.table, depth);
		if (winner == 10 || winner == -10)
			return winner;
		if (n.depth == 8 || (n.depth-depth) == depthwhant) {
			return findchances(n.table, depth, 0);
		}
		int v = Integer.MAX_VALUE;
		sucess = Sucessors(n);
		while (!sucess.isEmpty()) {
			s = sucess.remove();
			v = Min(v, MaxValueABeta(s,alpha,beta,depth,depthwhant));
			if(v<=alpha)
				return v;
			beta=Min(beta,v);
		}
		return v;
	}

	public static Position playcomp(char[][] compu, char [][] posicoes) {
		Position computer = new Position(10, 10);
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (compu[i][j] != posicoes[i][j]) {
					return computer = new Position(i, j);
			}
		return computer;
	}

	public static int findchances(char[][] posicoes, int depth, int haspossible) {
		int countO = 0;
		int countX = 0;
		int countcO = 0;
		int countcX = 0;
		int countdO = 0;
		int countdX = 0;
		int countO1 = 0;
		int countX1 = 0;
		int countOs = 0;
		int countXs = 0;
		int counte = 0;
		int countce = 0;
		int countde = 0;
		int countde1 = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// ifs = contam quantos por linha e coluna
				if (posicoes[i][j] == 'O')
					countO++;
				if (posicoes[i][j] == 'X')
					countX++;
				if (posicoes[i][j] == ' ')
					counte++;
				if (posicoes[j][i] == 'O')
					countcO++;
				if (posicoes[j][i] == 'X')
					countcX++;
				if (posicoes[j][i] == ' ')
					countce++;
			}
			// contam as possibilidades de fazer colunas de Os e de Xs
			if (countO + counte == 3)
				countOs++;
			if (countX + counte == 3)
				countXs++;
			if (countcO + countce == 3)
				countOs++;
			if (countcX + countce == 3)
				countXs++;
			if (countO == 3 || countcO == 3)
				
				if (depth % 2 == 0)
					return -10;
				else
					return 10;
			if (countX == 3 || countcX == 3)
				if (depth % 2 == 0)
					return 10;
				else
					return -10;
			// contam a quanidade de Os, de Xs e de espaços vazios nas diagonais
			if (posicoes[i][i] == ' ')
				countde++;
			if (posicoes[i][i] == 'O')
				countO1++;
			if (posicoes[i][i] == 'X')
				countX1++;
			if (posicoes[i][2 - i] == 'O')
				countdO++;
			if (posicoes[i][2 - i] == 'X')
				countdX++;
			if (posicoes[i][2 - i] == ' ')
				countde1++;

			countO = 0;
			countcO = 0;
			countX = 0;
			countcX = 0;
			counte = 0;
			countce = 0;
		}
		if (countO1 + countde == 3)
			countOs++;
		if (countdO + countde1 == 3)
			countOs++;
		if (countX1 + countde == 3)
			countXs++;
		if (countdX + countde1 == 3)
			countXs++;

		if (countO1 == 3 || countdO == 3)
			if (depth % 2 == 0)
				return -10;
			else
				return 10;
		if (countX1 == 3 || countdX == 3)
			if (depth % 2 == 0)
				return 10;
			else
				return -10;

		if (depth % 2 == 0) {
			return countXs - countOs;
		} else {
			return countOs - countXs;
		}
	}

	public static int haswinner(char[][] posicoes, int depth) {
		int countO = 0;
		int countX = 0;
		int countcO = 0;
		int countcX = 0;
		int countdO = 0;
		int countdX = 0;
		int countO1 = 0;
		int countX1 = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (posicoes[i][j] == 'O')
					countO++;
				if (posicoes[i][j] == 'X')
					countX++;
				if (posicoes[j][i] == 'O')
					countcO++;
				if (posicoes[j][i] == 'X')
					countcX++;
			}

			if (countO == 3)
				if (depth % 2 == 0)
					return -10;
				else
					return 10;
			if (countX == 3)
				if (depth % 2 == 0)
					return 10;
				else
					return -10;
			if (countcO == 3)
				if (depth % 2 == 0)
					return -10;
				else
					return 10;
			if (countcX == 3)
				if (depth % 2 == 0)
					return 10;
				else
					return -10;
			if (posicoes[i][i] == 'O')
				countO1++;
			if (posicoes[i][i] == 'X')
				countX1++;
			if (posicoes[i][2 - i] == 'O')
				countdO++;
			if (posicoes[i][2 - i] == 'X')
				countdX++;

			countO = 0;
			countX = 0;
			countcX = 0;
			countcO = 0;
		}
		if (countO1 == 3 || countdO == 3)
			if (depth % 2 == 0)
				return -10;
			else
				return 10;
		if (countX1 == 3 || countdX == 3)
			if (depth % 2 == 0)
				return 10;
			else
				return -10;

		return 0;
	}
}
