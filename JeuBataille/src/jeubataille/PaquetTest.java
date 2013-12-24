package jeubataille;


public class PaquetTest {


    public static void main(String[] args) {
//        affichageCartes();
//        compterLesCartes();
        verifieLeNombreDeCarteDansLePaquet();
    }

    private static void verifieLeNombreDeCarteDansLePaquet() {
        Paquet p = new Paquet("depart");
        if (p.getCartes().size() != 52) {
            System.out.println("ERROR (nombre de cartes))");
        }
    }

    private static void compterLesCartes() {

        Paquet p = new Paquet("depart");

        System.out.println("Il y a " + p.getCartes().size() + " cartes");
    }

    private static void affichageCartes() {
        Paquet p = new Paquet("depart");

        for (Carte carte : p.getCartes()) {
            System.out.println(carte.getTitre());
        }

    }




}
