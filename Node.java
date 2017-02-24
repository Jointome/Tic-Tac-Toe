public class Node {
	Node next;
	char table[][];
			int depth,value;
	Node(){	
		table=new char[3][3];
		depth=0;value=0;
		next=null;
	}
	Node(char t[][],Node n){
		table=new char[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				table[i][j]=t[i][j];	
		depth=0;value=0;
		next=n;
	}
	Node(char t[][],int depth,int value,Node n){
		table=new char[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				table[i][j]=t[i][j];	
		this.depth=depth;
		this.value=value;
		next
		
		
		=n;
	}
	Node(Node n){
		table=new char[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				table[i][j]=n.table[i][j];	
		depth=n.depth;
		value=n.value;
		next=n.next;
		}
	Node(char t[][],int depth,int value,int c,Node n){
		table=new char[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				table[i][j]=t[i][j];	
		this.depth=depth;
		this.value=value;
		next=n;
	}
	
	/**
	 * devolve a profundidade do elemento;
	 * @return depth	 profundidade 
	 */
		public int getDepth(){
			return depth;
		}

	/**
	 * devolve tabela
	 * @return table
	 */
		public char[][] getTable(){
			
			return table;
		}
		public int getValue(){
			return value;
		}
	
	}