package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.erketyper;


import no.nav.foreldrepenger.common.domain.foreldrepenger.Rettigheter;

public final class RettigheterErketyper {

    private RettigheterErketyper() {
    }

    public static Rettigheter beggeForeldreRettIkkeAleneomsorg() {
        return new Rettigheter(
                true,
                false,
                false);
    }

    // NB! Default verdi hvis annenpart er "ukjent foreldre"!
    public static Rettigheter harAleneOmsorgOgEnerett() {
        return new Rettigheter(
                false,
                true,
                false);
    }

    public static Rettigheter harIkkeAleneomsorgOgAnnenpartIkkeRett() {
        return new Rettigheter(
                false,
                false,
                false);
    }

    public static Rettigheter annenpartIkkeRettOgMorHarUføretrygd() {
        return new Rettigheter(
                false,
                false,
                true);
    }
}
