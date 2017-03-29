package com.my.black.jack;

public class Card {
	public int suit;
	public int number;
	
	public Card(int s,int n)
	{
		suit = s;
		number = n;
	}
	
	
	public int getValue() {
        if (number == 0)
            return 11;
        if (number > 8)
            return 10;
        return number+1;
	}
}
