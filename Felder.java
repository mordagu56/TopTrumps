
public enum Felder {
	WIESE("wiese"),
	BERG("berg"),
	WASSER("wasser"),
	BURG("burg"),
	SCHATZ("schatz");

	private String name;

	Felder (String name) {
		
		this.name = name;
	}
	@Override
	public String toString () {
		return name;}
	
};
