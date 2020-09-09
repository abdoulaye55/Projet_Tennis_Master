package be.diallo.Enum;

import be.diallo.Enum.TMatch;

public enum TMatch {
	Simple("Simple"),
	Double("Double");
	
	private String tMatch;
	
	TMatch(String tm){
		this.tMatch = tm;
	}
	
	public static TMatch fromString(String text) {
		for (TMatch tm : TMatch.values()) {
            if (tm.tMatch.equals(text)) {
                return tm;
            }
        }
        return null;
	}

}
