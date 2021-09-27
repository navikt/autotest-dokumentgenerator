package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.builder;

import java.time.LocalDate;
import java.util.List;

import no.nav.foreldrepenger.common.domain.BrukerRolle;
import no.nav.foreldrepenger.common.domain.Søknad;
import no.nav.foreldrepenger.common.domain.Ytelse;
import no.nav.foreldrepenger.common.domain.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.common.domain.felles.opptjening.Opptjening;
import no.nav.foreldrepenger.common.domain.svangerskapspenger.Svangerskapspenger;
import no.nav.foreldrepenger.common.domain.svangerskapspenger.tilrettelegging.Tilrettelegging;
import no.nav.foreldrepenger.common.oppslag.dkif.Målform;

public class SvangerskapspengerBuilder extends SøknadBuilder<SvangerskapspengerBuilder> {

    private final Svangerskapspenger.SvangerskapspengerBuilder svpBuilder = Svangerskapspenger.builder();
    private boolean opptjeningSatt = false;

    public SvangerskapspengerBuilder(BrukerRolle brukerRolle, List<Tilrettelegging> tilretteleggingListe) {
        this.medSøker(brukerRolle, Målform.standard());
        this.medTilrettelegging(tilretteleggingListe);
    }

    @Override
    protected SvangerskapspengerBuilder self() {
        return this;
    }

    @Override
    protected SvangerskapspengerBuilder medYtelse(Ytelse ytelse) {
        søknadKladd.ytelse(ytelse);
        return this;
    }

    public SvangerskapspengerBuilder medTermindato(LocalDate termindato) {
        svpBuilder.termindato(termindato);
        return this;
    }

    public SvangerskapspengerBuilder medFødselsdato(LocalDate fødselsdato) {
        svpBuilder.fødselsdato(fødselsdato);
        return this;
    }

    public SvangerskapspengerBuilder medMedlemsskap(Medlemsskap medlemsskap) {
        svpBuilder.medlemsskap(medlemsskap);
        return this;
    }

    public SvangerskapspengerBuilder medOpptjening(Opptjening opptjening) {
        svpBuilder.opptjening(opptjening);
        opptjeningSatt = true;
        return this;
    }

    public SvangerskapspengerBuilder medTilrettelegging(List<Tilrettelegging> tilrettelegginger) {
        svpBuilder.tilrettelegging(tilrettelegginger);
        return this;
    }

    @Override
    public Søknad build() {
        if (!opptjeningSatt) {
            this.medOpptjening(Opptjening.builder().build());
        }
        this.medYtelse(this.svpBuilder.build());
        return super.build();
    }
}
