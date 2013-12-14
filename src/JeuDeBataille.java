
public class JeuDeBataille {

    private Joueur raoul;
    private Joueur toto;

    private Paquet pioche;

    // preparer le jeu
    public JeuDeBataille() {

        // creer les joueurs
        raoul = new Joueur("Raoul");
        toto = new Joueur("Toto");

        // creer les cartes
        pioche = new Paquet("depart");

        // distribuer les cartes ( on distribue pas : c'est le joueur qui tire les cartes)

        while (pioche.aDesCartes()) {

            Carte c = pioche.tirerUneCarteAuHasard();
            raoul.prends(c);

            toto.prends(pioche.tirerUneCarteAuHasard());
        }

        // on affiche la distribution des cartes pour verifier que tout est en ordre
        System.out.println("pioche : " + pioche.getCartes().size());
        System.out.println("Raoul : " + raoul.nombreDeCartes());
        System.out.println("Toto : " + toto.nombreDeCartes());

    }


    // jouer une partie
    public void jouerUnePartie() {

        int i = 0 ;

        while (raoul.aDesCartes() && toto.aDesCartes() && (i++ < 200)) {
            jouerUnTour();
        }

    }

    // joueur un tour
    private void jouerUnTour() {

        Carte carteDeRaoul = raoul.tirerUneCarte();
        Carte carteDeToto = toto.tirerUneCarte();

        System.out.println("Raoul : " + carteDeRaoul.getTitre() + " Vs " + "Toto : " + carteDeToto.getTitre());

        if (carteDeRaoul.estPlusForteQue(carteDeToto)) {
            raoul.gagne(carteDeToto);
            toto.perdre(carteDeToto);
            raoul.mettreEnDessous(carteDeRaoul);
        }

        // si battaile ?
            // on parie une carte supplementaire


        if (carteDeToto.estPlusForteQue(carteDeRaoul)) {
            toto.gagne(carteDeRaoul);
            raoul.perdre(carteDeRaoul);
            toto.mettreEnDessous(carteDeToto);
        }

        System.out.println("Raoul : " + raoul.nombreDeCartes() + " / " + "Toto : " + toto.nombreDeCartes() + " : " + (raoul.nombreDeCartes() + toto.nombreDeCartes()));

    }


}
