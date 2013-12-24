package jeubataille;

import java.util.ArrayList;

public class Paquet {

    private ArrayList<Carte> cartes;

    public Paquet(String etat) {

        cartes = new ArrayList<Carte>();

        switch (etat) {

            case "depart":
                cartes.addAll(creerFamille("Pique"));
                cartes.addAll(creerFamille("Coeur"));
                cartes.addAll(creerFamille("Carreau"));
                cartes.addAll(creerFamille("Trèfle"));
                break;

            case "vide":
                // rien a faire
                break;

            default:
                System.out.println("erreur");
        }
    }

    private ArrayList<Carte> creerFamille(String couleur) {

        ArrayList<Carte> cartesDeLaCouleur = new ArrayList<Carte>();

        cartesDeLaCouleur.add(new Carte("As de " + couleur, 14));
        cartesDeLaCouleur.add(new Carte("Deux de " + couleur, 2));
        cartesDeLaCouleur.add(new Carte("Trois de " + couleur, 3));
        cartesDeLaCouleur.add(new Carte("Quatre de " + couleur, 4));
        cartesDeLaCouleur.add(new Carte("Cinq de " + couleur, 5));
        cartesDeLaCouleur.add(new Carte("Six de " + couleur, 6));
        cartesDeLaCouleur.add(new Carte("Sept de " + couleur, 7));
        cartesDeLaCouleur.add(new Carte("Huit de " + couleur, 8));
        cartesDeLaCouleur.add(new Carte("Neuf de " + couleur, 9));
        cartesDeLaCouleur.add(new Carte("Dix de " + couleur, 10));
        cartesDeLaCouleur.add(new Carte("Valet de " + couleur, 11));
        cartesDeLaCouleur.add(new Carte("Dame de " + couleur, 12));
        cartesDeLaCouleur.add(new Carte("Roi de " + couleur, 13));

        return cartesDeLaCouleur;
    }

    public boolean aDesCartes() {
        return cartes.size() > 0;
    }

    public Carte tirerUneCarteAuHasard() {
        int index = (int) (Math.random() * cartes.size());
        Carte laCartePioche = cartes.get(index);
        cartes.remove(laCartePioche);
        return laCartePioche;
    }

    // methode ajoutée pour le test :
    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    public void ajouterUnCarteAuDessus(Carte carte) {
        cartes.add(0, carte);
    }

    public Carte carteDuDessus() {
        return cartes.get(0);
    }

    public void ajouterAuDessous(Carte carte) {
        cartes.add(carte);
    }

    public void retirer(Carte carte) {
        cartes.remove(carte);
    }

    public void mettreEnDessous(Carte carte) {
        cartes.remove(carte);
        cartes.add(carte);
    }
}
