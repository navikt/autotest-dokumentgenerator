package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import java.time.LocalDate;
import java.util.List;

import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.BrukerRolle;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Søknad;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Ytelse;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.SpråkKode;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.opptjening.Opptjening;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger.Svangerskapspenger;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger.tilrettelegging.Tilrettelegging;

public class SvangerskapspengerBuilder extends SøknadBuilder<SvangerskapspengerBuilder> {

    private final Svangerskapspenger svangerskapspengerKladd = new Svangerskapspenger();

    public SvangerskapspengerBuilder(BrukerRolle brukerRolle, List<Tilrettelegging> tilretteleggingListe) {
        this.medSøker(brukerRolle, SpråkKode.NB);
        this.medTilrettelegging(tilretteleggingListe);
    }

    @Override
    protected SvangerskapspengerBuilder self() {
        return this;
    }

    @Override
    protected SvangerskapspengerBuilder medYtelse(Ytelse ytelse) {
        søknadKladd.setYtelse(ytelse);
        return this;
    }

    public SvangerskapspengerBuilder medTermindato(LocalDate termindato) {
        svangerskapspengerKladd.setTermindato(termindato);
        return this;
    }

    public SvangerskapspengerBuilder medFødselsdato(LocalDate fødselsdato) {
        svangerskapspengerKladd.setFødselsdato(fødselsdato);
        return this;
    }

    public SvangerskapspengerBuilder medMedlemsskap(Medlemsskap medlemsskap) {
        svangerskapspengerKladd.setMedlemsskap(medlemsskap);
        return this;
    }

    public SvangerskapspengerBuilder medOpptjening(Opptjening opptjening) {
        svangerskapspengerKladd.setOpptjening(opptjening);
        return this;
    }

    public SvangerskapspengerBuilder medTilrettelegging(List<Tilrettelegging> tilrettelegginger) {
        svangerskapspengerKladd.setTilrettelegging(tilrettelegginger);
        return this;
    }

    @Override
    public Søknad build() {
        if (this.svangerskapspengerKladd.getOpptjening() == null) {
            this.medOpptjening(new Opptjening());
        }

        this.medYtelse(this.svangerskapspengerKladd);

        return super.build();
    }
}
