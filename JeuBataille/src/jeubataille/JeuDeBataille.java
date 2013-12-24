package jeubataille;

import java.util.ArrayList;
import java.util.Scanner;

public class JeuDeBataille {

    private Paquet pioche;
    private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    private ArrayList<Carte> cartesTour = new ArrayList<Carte>();
    private int nbreJoueurs;
    private int positionCartes;

    // preparer le jeu
    public JeuDeBataille() {

        // creer les joueurs
        creationJoueur();

        // creer les cartes
        creationPioche();
    }

    private void creationJoueur() {
        nbreJoueurs = 2;
        for (int i = 1; i <= nbreJoueurs; i++) {
            Scanner input = new Scanner(System.in);                             // Saisie des noms des joueurs
            System.out.println("Quel est le nom du joueur n°" + i + " ?");
            joueurs.add(new Joueur(input.nextLine()));
        }
    }

    private void creationPioche() {
        pioche = new Paquet("depart");

        // distribuer les cartes ( on distribue pas : c'est le joueur qui tire les cartes)
        while (pioche.aDesCartes()) {
            for (Joueur j : joueurs) {
                Carte c = pioche.tirerUneCarteAuHasard();
                j.prends(c);
            }
        }
        System.out.println("pioche : " + pioche.getCartes().size());
        for (Joueur j : joueurs) {
            System.out.println(j.getNom() + " : " + j.nombreDeCartes());
        }

    }

    // jouer une partie
    public void jouerUnePartie() {
        while (cartesEnMain()) {
            jouerUnTour();
        }

        finDePartie();
    }

    // joueur un tour
    private void jouerUnTour() {
        /* Les cartes tirées par les joueurs sont stockées dans une liste
        /* transitoire qui s'appelle cartesTour. Les manipulations de comparaison se font sur cette liste.
        /* la variable positionCartes est un index qui a toute son utilité en cas de batailles ou de bataille de batailles.
        */

        positionCartes = 0;
        tirerCartes();
        comparerCartes(positionCartes, cartesTour);
    }

    private void tirerCartes() {

        for (Joueur j : joueurs) {
            cartesTour.add(j.tirerUneCarte());
            j.perdre(j.tirerUneCarte());                //la carte est retirée du paquet du joueur
        }
    }

    private void comparerCartes(int i, ArrayList<Carte> cartesTour) {

        String j1 = joueurs.get(0).getNom();
        String j2 = joueurs.get(1).getNom();

        System.out.println("*****************");
        System.out.println(j1 + " : " + cartesTour.get(i).getTitre() + " <- Vs -> "
                + j2 + " : " + cartesTour.get(i + 1).getTitre());

        if (cartesTour.get(i).estPlusForteQue(cartesTour.get(i + 1))) {
            for (int c = 0; c < cartesTour.size(); c++) {
                joueurs.get(0).gagne(cartesTour.get(c));
            }
        }

        if (cartesTour.get(i + 1).estPlusForteQue(cartesTour.get(i))) {

            for (int c = 0; c < cartesTour.size(); c++) {
                joueurs.get(1).gagne(cartesTour.get(c));
            }
        }

        if (cartesTour.get(i).estEgale(cartesTour.get(i + 1))) {
            if (assezCarteAvantBataille()) {
                bataille(cartesTour);
            }
        }
        System.out.println("Cartes restantes : "+j1 + " : " + joueurs.get(0).nombreDeCartes() + " / "
                + j2 + " : " + joueurs.get(1).nombreDeCartes() + " (total "
                + (joueurs.get(0).nombreDeCartes() + joueurs.get(1).nombreDeCartes()) + ")");
        cartesTour.clear();                                     // la liste cartesTour est remise à zéro avant le debut d'un nouveau tour
    }

    private boolean assezCarteAvantBataille() {
        for (Joueur j : joueurs) {
            if (j.nombreDeCartes() <= 1) {
                return false;
            }
        }
        return true;
    }

    private void bataille(ArrayList<Carte> cartesTour) {
        positionCartes += 4;
        System.out.println("---------------------------------- Début de la Bataille --------------------------------");
        tirerCartes();                          // Cartes tirées mais non retournées
        tirerCartes();                          // Cartes tirées et retournées pour comparaison
        if (positionCartes == 8) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DOUBLE BATAILLE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! DOUBLE BATAILLE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

//        System.out.println("Position carte dans le paquet = " + positionCartes);
        comparerCartes(positionCartes, cartesTour);
        System.out.println("---------------------------------- Fin de la Bataille ----------------------------------");
    }

    public boolean cartesEnMain() {
        for (Joueur j : joueurs) {
            if (!j.aDesCartes()) {
                return false;
            }
        }
        return true;
    }

    private void finDePartie() {
        for (Joueur j : joueurs) {
            if (j.nombreDeCartes() >= 50) {
                System.out.println("Bravo, " + j.getNom() + " ! Vous avez gagné !!");
            }
        }
    }
}
