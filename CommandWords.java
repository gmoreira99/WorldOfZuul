/*
Autor: Guilherme Moreira
Classe CommandWords: reserva as palavras comando; interpreta a palavra digitada pelo usuario
*/

public class CommandWords{

    private static final String validCommands[] = {"go", "quit", "help", "look", "eat"};

    public CommandWords(){}

    public boolean isCommand(String aString){
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        return false;
    }

	public int processCommand(Command command){	

        	if(command.isUnknown())
            		return 0;

	        String commandWord = command.getCommandWord();
        	if (commandWord.equals("help"))
            		return 1;
		else if (commandWord.equals("go") && command.hasSecondWord())
			return 2;
        	else if (commandWord.equals("go") && !command.hasSecondWord())
            		return 21;
		else if (commandWord.equals("look"))
			return 3;
		else if (commandWord.equals("quit"))
            		return 4;
		else if (commandWord.equals("eat") && command.hasSecondWord())
			return 5;
		else if (commandWord.equals("eat") && !command.hasSecondWord())
			return 51;

		return 0;
    }

	public String quit(Command command){

        	if(command.hasSecondWord()) {
            		return "Quit what?";
        }
        else
            return "Thank you for playing.  Good bye.";
    }

	public String lookFor(String what, Room where){
		String aux = "";
		if(what == null)
			aux += "You are " + where.getDescription() + " with " + where.printCharacters() + "\nThere are " + where.printItems() + "\nExits: " + where.printExits();
		else
		switch(what){
			case "items" : aux += "There are " + where.printItems();
					break;
			case "characters" : aux += "You're with " + where.printCharacters();
						break;
			case "doors" :
			case "exits" : aux += "Exits: " + where.printExits();
						break;
			default : aux += "Look for what?";
		}	
		return aux;
	}

	public String eat(String what, Room where){
		switch(where.useItem(what)){
			case 1 : return "You ate " + what;
			case 2 : return "Eat what?";
		}
		return null;
	}
}