package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.builders;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.SøkersRolle;
import no.nav.vedtak.felles.xml.soeknad.engangsstoenad.v3.Engangsstønad;
import no.nav.vedtak.felles.xml.soeknad.engangsstoenad.v3.ObjectFactory;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.*;
import no.nav.vedtak.felles.xml.soeknad.v3.Soeknad;

public class EngangstønadBuilder extends SøknadBuilder<EngangstønadBuilder>{
    private Engangsstønad engangsstønadKladd;

    public EngangstønadBuilder(String aktoerId, SøkersRolle søkerRolle) {
        engangsstønadKladd = new Engangsstønad();
        medSøker(aktoerId, søkerRolle);
    }
    @Override
    protected EngangstønadBuilder medYtelse(Ytelse ytelse) {
        søknadKladd.setOmYtelse(setOmYtelseJAXBElement((new ObjectFactory()).createEngangsstønad((Engangsstønad) ytelse)));
        return this;
    }
    @Override
    protected EngangstønadBuilder self(){
        return this;
    }

    public EngangstønadBuilder medSoekersRelasjonTilBarnet(SoekersRelasjonTilBarnet soekersRelasjonTilBarnet) {
        engangsstønadKladd.setSoekersRelasjonTilBarnet(soekersRelasjonTilBarnet);
        return this;
    }
    public EngangstønadBuilder medMedlemskap(Medlemskap medlemskap) {
        engangsstønadKladd.setMedlemskap(medlemskap);
        return this;
    }
    public EngangstønadBuilder medAnnenForelder(String annenForelderAktørId) {
        engangsstønadKladd.setAnnenForelder(standardAnnenForelder(annenForelderAktørId));
        return this;
    }

    public Soeknad build() {
        medYtelse(engangsstønadKladd);
        return super.build();

    }


}
