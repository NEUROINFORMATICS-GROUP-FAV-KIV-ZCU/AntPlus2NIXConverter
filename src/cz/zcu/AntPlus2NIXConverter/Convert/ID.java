package cz.zcu.AntPlus2NIXConverter.Convert;

import java.util.Random;

public class ID {

	Random rand = new Random();
	
	public String createID(){
		String id;
		
		id = String.valueOf(rand.nextInt()) + String.valueOf(rand.nextInt());
		
		
		return id;
	}
	
	
}
