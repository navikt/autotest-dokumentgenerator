package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.erketyper;

import no.nav.vedtak.felles.xml.soeknad.felles.v3.Rettigheter;

public class RettigheterErketyper {

    private RettigheterErketyper() {
        // Skal ikke instansieres
    }

    public static Rettigheter beggeForeldreRettIkkeAleneomsorg() {
        var rettigheter = new Rettigheter();
        rettigheter.setHarAleneomsorgForBarnet(false);
        rettigheter.setHarAnnenForelderRett(true);
        rettigheter.setHarOmsorgForBarnetIPeriodene(true);

        return rettigheter;
    }

    public static Rettigheter harAleneOmsorgOgEnerett() {
        var rettigheter = new Rettigheter();
        rettigheter.setHarOmsorgForBarnetIPeriodene(true);
        rettigheter.setHarAnnenForelderRett(false);
        rettigheter.setHarAleneomsorgForBarnet(true);

        return rettigheter;
    }

    public static Rettigheter harIkkeAleneomsorgOgAnnenpartIkkeRett() {
        var rettigheter = new Rettigheter();
        rettigheter.setHarOmsorgForBarnetIPeriodene(true);
        rettigheter.setHarAnnenForelderRett(false);
        rettigheter.setHarAleneomsorgForBarnet(false);

        return rettigheter;
    }
}
