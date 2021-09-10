package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.builders;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.JAXBElement;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.xml.SøkersRolle;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Medlemskap;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Vedlegg;
import no.nav.vedtak.felles.xml.soeknad.felles.v3.Ytelse;
import no.nav.vedtak.felles.xml.soeknad.foreldrepenger.v3.Opptjening;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.ObjectFactory;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.Svangerskapspenger;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.Tilrettelegging;
import no.nav.vedtak.felles.xml.soeknad.svangerskapspenger.v1.TilretteleggingListe;
import no.nav.vedtak.felles.xml.soeknad.v3.Soeknad;

public class SvangerskapspengerBuilder extends SøknadBuilder<SvangerskapspengerBuilder>{

    private Svangerskapspenger svangerskapspengerKladd;

    public SvangerskapspengerBuilder(String aktoerId, SøkersRolle søkerRolle, List<Tilrettelegging> tilretteleggingListe) {
        svangerskapspengerKladd = new Svangerskapspenger();
        medSøker(aktoerId, søkerRolle);
        medTilretteleggingListe(tilretteleggingListe);
    }
    @Override
    protected SvangerskapspengerBuilder medYtelse(Ytelse ytelse) {
        JAXBElement<? extends Ytelse> omYtelseJAXBElementKladd = (
                new ObjectFactory()).createSvangerskapspenger((Svangerskapspenger) ytelse);
        var svangerskapspenger = (Svangerskapspenger)omYtelseJAXBElementKladd.getValue();
        svangerskapspenger.getTilretteleggingListe().getTilrettelegging().forEach((tilrettelegging) -> {
            tilrettelegging.getVedlegg().forEach((vedlegg) -> {
                this.søknadKladd.getPaakrevdeVedlegg().add((Vedlegg)vedlegg.getValue());
            });
        });
        søknadKladd.setOmYtelse(setOmYtelseJAXBElement(omYtelseJAXBElementKladd));
        return this;
    }
    @Override
    protected SvangerskapspengerBuilder self() {
        return this;
    }

    public SvangerskapspengerBuilder medTermindato(LocalDate termindato) {
        svangerskapspengerKladd.setTermindato(termindato);
        return this;
    }
    public SvangerskapspengerBuilder medTilretteleggingListe(List<Tilrettelegging> tilretteleggingListe) {
        if(tilretteleggingListe != null) {
            var tl = new TilretteleggingListe();
            tl.getTilrettelegging().addAll(tilretteleggingListe);
            svangerskapspengerKladd.setTilretteleggingListe(tl);
        }
        return this;
    }
    public SvangerskapspengerBuilder medSpesiellOpptjening(Opptjening opptjening) {
        svangerskapspengerKladd.setOpptjening(opptjening);
        return this;
    }
    public SvangerskapspengerBuilder medMedlemskap(Medlemskap medlemskap) {
        svangerskapspengerKladd.setMedlemskap(medlemskap);
        return this;
    }
    public SvangerskapspengerBuilder medFødseldato(LocalDate fødselsdato) {
        svangerskapspengerKladd.setFødselsdato(fødselsdato);
        return this;
    }


    @Override
    public Soeknad build() {
        medYtelse(svangerskapspengerKladd);
        return super.build();

    }


}
