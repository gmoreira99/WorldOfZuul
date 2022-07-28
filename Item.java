/*
Autor: Guilherme Moreira
Classe Item: cria um item para uma sala;
*/

public class Item{

	private String description;

	public Item(String description){
		this.description = description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

}