/*
Autor: Guilherme Moreira
Classe Game: cria um jogo; cria as salas do jogo, bem como os itens e personagens de dessas; imprime as instruções do jogo e a mensagem de recepção; 
*/

import java.util.ArrayList;

public class Game{

    	private Parser parser;
	private Room currentRoom;
	private CommandWords process;
        
    public Game(){

        createRooms();
        parser = new Parser();
	process = new CommandWords();
    }

    private void createRooms(){
        Room outside, theatre, pub, lab, office, basement, attic;

	Item item1 = new Item("some trees");
	Item item2 = new Item("many cars");
	Item item3 = new Item("many seats");
	Item item4 = new Item("the board");
	Item item5 = new Item("a sofa");
	Item item6 = new Item("many beer bottles");
	Item item7 = new Item("many computers");
	Item item8 = new Item("a desk");
	Item item9 = new Item("the water heater");
	Item item10 = new Item("some old books");

	Item item11= new Character("a dog");
	Item item12 = new Character("the students");
	Item item13 = new Character("the teacher");
	Item item14 = new Character("the barman");
	Item item15 = new Character("the waitress");
	Item item16 = new Character("the technician");
	Item item17 = new Character("the receptionist");
	Item item18 = new Character("the dean");
	Item item19 = new Character("the janitor");
	Item item20 = new Character("a scary monster");

	Item item21 = new Usable("bread");
	Item item22 = new Usable("water");
	Item item23 = new Usable("potion");

	ArrayList<Item> out = new ArrayList<>();
	out.add(item1); out.add(item2); out.add(item11); out.add(item23);
	ArrayList<Item> t = new ArrayList<>();
	t.add(item3); t.add(item4); t.add(item12); t.add(item13); t.add(item21);
	ArrayList<Item> p = new ArrayList<>();
	p.add(item5); p.add(item6); p.add(item14); p.add(item15); p.add(item22);
	ArrayList<Item> l = new ArrayList<>();
	l.add(item7); l.add(item16);
	ArrayList<Item> off = new ArrayList<>();
	off.add(item8); off.add(item17); off.add(item18); off.add(item21);
	ArrayList<Item> b = new ArrayList<>();
	b.add(item9); b.add(item19); b.add(item23);
	ArrayList<Item> a = new ArrayList<>();
	a.add(item10); a.add(item20); a.add(item23);

        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
	basement = new Room("in the basement");
	attic = new Room("in the attic");

        outside.setExits(null, theatre, lab, pub, null, null);
        theatre.setExits(null, null, null, outside, null, null);
        pub.setExits(null, outside, null, null, null, null);
        lab.setExits(outside, office, null, null, null, null);
        office.setExits(null, null, null, lab, attic, basement);
	basement.setExits(null, null, null, null, office, null);
	attic.setExits(null, null, null, null, null, office);

	outside.setItems(out);
        theatre.setItems(t);
        pub.setItems(p);
        lab.setItems(l);
        office.setItems(off);
	basement.setItems(b);
	attic.setItems(a);

        currentRoom = outside;
    }

    public String play(){

	String exibir = "";
        
	Room nextRoom = null;
                
            Command command = parser.getCommand();		
		switch(process.processCommand(command)){
			case 0 : exibir += "I don't know what you mean...";
				break;
			case 1 : exibir += printHelp();
				break;
			case 2 : nextRoom = currentRoom.goRoom(command.getSecondWord());
				if(nextRoom == null)
					exibir = "There is no door!";
				else{
					currentRoom = nextRoom;

					for(int i=0;i<35;i++){
						exibir += "=";
					}

            				exibir += "\nYou are " + currentRoom.getDescription() + " with " + currentRoom.printCharacters() + "\nThere are " + currentRoom.printItems() + "\nExits: " + currentRoom.printExits();
				}
				break;
			case 21 : exibir += "Go where?";
				break;
			
			case 3 : exibir += process.lookFor(command.getSecondWord(), currentRoom);
				break;
			case 4 : exibir += process.quit(command);
				break;
			case 5 : exibir += process.eat(command.getSecondWord(), currentRoom);
				break;
			case 51 : exibir += "Eat what?";
				break;
		}

		return exibir;
    }

    public String printWelcome(){
	String exibir = "";
	for(int i=0;i<35;i++){
		exibir += "=";
	}

	exibir += "\nWelcome to Adventure!\nAdventure is a new, incredibly boring adventure game.\nType 'help' if you need help.\n";

	for(int i=0;i<35;i++){
		exibir += "=";
	}

	exibir += "\nYou are " + currentRoom.getDescription() + " with " + currentRoom.printCharacters() + "\nThere are " + currentRoom.printItems() + "\nExits: " + currentRoom.printExits();
	return exibir;
    }

    public String printHelp(){
	String exibir = "You are lost. You are alone. You wander\naround at the university.\nYour command words are:\n   go quit help look eat";
	return exibir;
    }

}
