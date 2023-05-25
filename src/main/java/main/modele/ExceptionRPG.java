package main.modele;

class ExceptionRPG extends Exception {
    private int codeErreur;

    /**
     * Constructeur de la classe ExceptionRPG
     * @param parCodeErreur int, le code d'erreur
     */
    public ExceptionRPG(int parCodeErreur) {
        super();
        this.codeErreur = parCodeErreur;
    }

    /**
     * Methode qui retourne le code d'erreur
     * @return le code d'erreur
     */
    public int getCodeErreur() {
        return codeErreur;
    }
}
