package be.diallo.POJO;

public class Court implements java.io.Serializable {

	private static final long serialVersionUID = 4L;
    private int ID;
    //private int nbrPlaceSpectateur;
	private boolean couvert;
	private String typeSurface;
	private String nom;
	public int getId() {
		return ID;
	}
	public void setId(int id) {
		this.ID = id;
	}
	/*public int getNbrPlaceSpectateur() {
		return nbrPlaceSpectateur;
	}
	public void setNbrPlaceSpectateur(int nbrPlaceSpectateur) {
		this.nbrPlaceSpectateur = nbrPlaceSpectateur;
	}*/
	public boolean getCouvert() {
		return couvert;
	}
	public void setCouvert(boolean couvert) {
		this.couvert = couvert;
	}
	public String getTypeSurface() {
		return typeSurface;
	}
	public void setTypeSurface(String typeSurface) {
		this.typeSurface = typeSurface;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Court() {}
	public Court(int Id, int nbrPlaceS, Boolean Couvert, String TypeSurface) {
		this.ID = Id;
		//this.nbrPlaceSpectateur = nbrPlaceS;
		this.couvert = Couvert;
		this.typeSurface = TypeSurface;
	}
	public Court(int id, String nom, boolean couvert, String typeSurface) {
		this.ID = id;
		this.nom = nom;
		this.couvert = couvert;
		this.typeSurface = typeSurface;
	}
}

