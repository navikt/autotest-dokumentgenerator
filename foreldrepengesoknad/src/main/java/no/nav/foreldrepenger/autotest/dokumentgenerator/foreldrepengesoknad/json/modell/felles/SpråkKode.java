package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles;

public enum SpråkKode {
    NN, NB, EN;

    @SuppressWarnings("SameReturnValue")
    public static SpråkKode defaultSpråk() {
        return NB;
    }
}
