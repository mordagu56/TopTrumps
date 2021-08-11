package Quartett;

import java.util.*;

public class Player implements Comparable<Player>{
    private String name;
    private Queue<VehicleCard> deck = new ArrayDeque<VehicleCard>();
    public Player(String name) {
        if (name.isEmpty() || name == null) throw new IllegalArgumentException();
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void addCards(Collection<VehicleCard> cards) {
       
        for (VehicleCard card: cards) {
            addCard(card);
        }
    }

    public void addCard(VehicleCard card) {
        deck.add(card);
    }
    public void clearDeck() {
        deck.clear();
    }
    public VehicleCard playNextCard() {
        return deck.poll();
    }
   
    public int totalBonus() {
        int t = 0;
        for(VehicleCard c: deck) {
            t += c.totalBonus();
        }
        return t;
    }
    public int compareTo(Player other) {
         return other.name.toLowerCase().compareTo(this.name.toLowerCase());
    }
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deck == null) ? 0 : deck.hashCode());
		result = prime * result + ((name.toLowerCase() == null) ? 0 : name.toLowerCase().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (deck == null) {
			if (other.deck != null)
				return false;
		} else if (!deck.equals(other.deck))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.toLowerCase().equals(other.name.toLowerCase()))
			return false;
		return true;
	}

	public boolean challengePlayer(Player p) {
		  if (p == null || p == this) 
		        	throw new IllegalArgumentException();
					
		        ArrayList<VehicleCard> this_pc= new ArrayList<VehicleCard>();
		        ArrayList<VehicleCard>p_pc = new ArrayList<VehicleCard>();
		        
		        VehicleCard this_card = null;
		        VehicleCard p_card = null;
		        int cmp=0;
				boolean check1 = !p.deck.isEmpty();
				boolean check2 = !this.deck.isEmpty();
		        while(check1 && check2) {
		        	this_card = this.playNextCard();
		        	p_card = p.playNextCard();
		        	 this_pc.add(this_card);
		        	 p_pc.add(p_card);
		        	if (  this_pc.size() != 0 && p_pc.size() != 0 ){
		        	  cmp = this_card.compareTo(p_card);
		        	if( this_card == null || p_card == null || !this_card.equals(p_card)) {
		        		if(cmp < 0) {
		        			p.addCards(p_pc);
		        			p.addCards( this_pc);
		        			return false;
		        		}
		        		else {
		        			this.addCards( this_pc);
		        			this.addCards(p_pc);
		        			return true;
		        		}
					 }	
		        	}
		        }
		        this.deck.addAll(this_pc);
		        p.deck.addAll(p_pc);
		        return false;
				
				}
    
    
    @Override
    public String toString(){
        String r = "";
        r += this.name + "("+ totalBonus() + "):\n";
        boolean first = true;
        for ( VehicleCard card: deck) {
        	if(first) first = false;
        	else r += '\n';
            r += card.toString();
        }
        return r;
    }
	public static Comparator<Player> compareByBonus() {
		
		return new Comparator<Player>() {
			@Override
			public int compare(Player lop, Player rop) {
				return (int) (lop.totalBonus() - rop.totalBonus());
			}
		};
	}
	
	public static Comparator<Player> compareByDeckSize() {
		return new Comparator<Player>() {
			@Override
			public int compare(Player lop, Player rop) {
				return (lop.deck.size()-rop.deck.size());
			}
		};
    }
	

}