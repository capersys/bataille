package jeubataille;

public class Carte {

    private String titre;
    private int puissance;

    public Carte(String titre, int puissance) {
        this.titre = titre;
        this.puissance = puissance;
    }

    public boolean estPlusForteQue(Carte unAutreCarte) {
        return puissance > unAutreCarte.puissance;
    }

    public boolean estEgale(Carte unAutreCarte) {
        return puissance == unAutreCarte.puissance;
    }

    public String getTitre() {
        return titre;
    }

}
