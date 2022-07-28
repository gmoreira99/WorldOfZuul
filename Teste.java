/*
Autor: Guilherme Moreira
Classe Teste: testa a classe Game
*/

public class Teste  extends Exception{

	public static void main(String[] args){

		Game g = new Game();		

		for(int i=0;i<35;i++){
			System.out.printf("=");
		}
		System.out.println();

		System.out.println(g.printWelcome());

		for(int i=0;i<35;i++){
			System.out.printf("=");
		}
		System.out.println();

		boolean teste = true;
		String exibir = "";
		do{
			exibir = g.play();
			System.out.println(exibir);
			if(exibir.equals("Thank you for playing.  Good bye."))
				teste = false;

		}while(teste);

	}

}
