import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Karte {
	
	Felder[][] karte;
	ArrayList<ArrayList<Felder>> test= new ArrayList<ArrayList<Felder>>();
	public Karte(Felder[][] karte) {
		super();
		this.karte = karte;
	}
	
	
	public void KartenGenerierung() {
		Boolean checkSchatz =true;
		Boolean checkBurg =true;
		for(int i = 0; i < 4; i++) {
			for(int k = 0; k < 8; k++) {
				Random random = new Random();
				int a = random.nextInt(5);
				if(Felder.SCHATZ != Felder.values()[a]) {
					
					karte[i][k] = Felder.values()[a];
				}
				
				else if(checkSchatz && Felder.SCHATZ == Felder.values()[a] ) {
					checkSchatz = false;
					karte[i][k] = Felder.values()[a];
				}
				
				if(Felder.BURG != Felder.values()[a] ) {
					karte[i][k] = Felder.values()[a];
				}
				
				else if(checkBurg && Felder.BURG == Felder.values()[a] ) {
					checkBurg = false;
					karte[i][k] = Felder.values()[a];
				}
			}
		}
		
	}


	@Override
	public String toString() {
		return "Karte [karte=" + Arrays.toString(karte) + "]";
	}
	

};
