/*
Autor: Guilherme Moreira
Classe Room: cria uma sala do jogo que contém uma coleção "array" de itens e uma coleção "hash map" de salas vizinhas; imprime as saídas, as personagens e os itens da sala; adiciona e remove (usa) um item
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Room{
    	private String description;
	private HashMap<String, Room> roomExits;
	private ArrayList<Item> items;

    public Room(String description){

        this.description = description;
	roomExits = new HashMap<>();
	items = new ArrayList<>();
    }

    public void setExits(Room north, Room east, Room south, Room west, Room up, Room down) {
        if(north != null)
		roomExits.put("northExit", north);
        if(east != null)
		roomExits.put("eastExit", east);
        if(south != null)
		roomExits.put("southExit", south);
        if(west != null)
		roomExits.put("westExit", west);
	if(up != null)
		roomExits.put("upExit", up);
	if(down != null)
		roomExits.put("downExit", down);
    }

    public String getDescription(){
        return description;
    }

	public String printExits(){
		String aux;
		String exibir = "";
		Set<String> keys = roomExits.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			aux = it.next();
			if(aux.equals("northExit"))
				exibir += "north ";
			if(aux.equals("southExit"))
				exibir += "south ";
			if(aux.equals("eastExit"))
				exibir += "east ";
			if(aux.equals("westExit"))
				exibir += "west ";
			if(aux.equals("upExit"))
				exibir += "up ";
			if(aux.equals("downExit"))
				exibir += "down ";
		}
		return exibir;
	}

	public Room goRoom(String direction){
		if(direction.equals("north"))
			return roomExits.get("northExit");
		if(direction.equals("south"))
			return roomExits.get("southExit");
		if(direction.equals("east"))
			return roomExits.get("eastExit");
		if(direction.equals("west"))
			return roomExits.get("westExit");
		if(direction.equals("up"))
			return roomExits.get("upExit");
		if(direction.equals("down"))
			return roomExits.get("downExit");
		return null;
	}

	public void addItem(Item item){
		items.add(item);
	}

	public void setItems(ArrayList<Item> items){
		this.items = items;
	}

	public String printCharacters(){
		Item aux;
		String exibir = "";
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			aux = it.next();
			if(aux instanceof Character)
				exibir += aux.getDescription() + "; ";
		}
		return exibir;
	}

	public String printItems(){
		Item aux;
		String exibir = "";
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			aux = it.next();
			if(!(aux instanceof Character))
				exibir += aux.getDescription() + "; ";
		}
		return exibir;
	}

	public int useItem(String description){
		Item aux;
		Iterator<Item> it = items.iterator();
		while(it.hasNext()){
			aux = it.next();
			if(aux instanceof Usable){
				if(aux.getDescription().equals(description)){
					items.remove(aux);
					return 1;		
				}
			}
		}
		return 2;
	}

}