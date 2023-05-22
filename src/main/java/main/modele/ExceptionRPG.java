package main.modele;

class ExceptionRPG extends Exception {
    private int codeErreur;

    public ExceptionRPG(int parCodeErreur) {
        super();
        this.codeErreur = parCodeErreur;
    }

    public int getCodeErreur() {
        return codeErreur;
    }
}
