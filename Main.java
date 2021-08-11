
public class Main {

	public static void main(String[] args) {
		Felder [][] arr = new Felder[4][8];
		Karte karte = new Karte(arr);
		karte.KartenGenerierung();
		System.out.print(karte.toString());

	}

}
