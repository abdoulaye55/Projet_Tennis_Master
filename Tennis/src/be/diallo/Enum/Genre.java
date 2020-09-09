package be.diallo.Enum;

import be.diallo.Enum.Genre;

public enum Genre {
	Homme("Homme"),
	Femme("Femme"),
	Mixte("Mixte");
	
	private final String genre;
	
	Genre(final String g){
		this.genre = g;
	}
	
	@Override
	public String toString() {
		return genre;
	}
	
	public static Genre fromString(String text) {
		for (Genre g : Genre.values()) {
            if (g.genre.equals(text)) {
                return g;
            }
        }
        return null;
	}
}

