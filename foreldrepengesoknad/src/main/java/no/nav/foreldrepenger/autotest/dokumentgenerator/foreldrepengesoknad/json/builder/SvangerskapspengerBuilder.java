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
    private LocalDate termindato;
    private LocalDate fødselsdato;
    private Medlemsskap medlemsskap;
    private Opptjening opptjening;
    private List<Tilrettelegging> tilrettelegging;

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
        this.ytelse = ytelse;
        return this;
    }

    public SvangerskapspengerBuilder medTermindato(LocalDate termindato) {
        this.termindato = termindato;
        return this;
    }

    public SvangerskapspengerBuilder medFødselsdato(LocalDate fødselsdato) {
        this.fødselsdato = fødselsdato;
        return this;
    }

    public SvangerskapspengerBuilder medMedlemsskap(Medlemsskap medlemsskap) {
        this.medlemsskap = medlemsskap;
        return this;
    }

    public SvangerskapspengerBuilder medOpptjening(Opptjening opptjening) {
        this.opptjening = opptjening;
        return this;
    }

    public SvangerskapspengerBuilder medTilrettelegging(List<Tilrettelegging> tilrettelegginger) {
        this.tilrettelegging = tilrettelegginger;
        return this;
    }

    @Override
    public Søknad build() {
        if (opptjening == null) this.opptjening = new Opptjening(null, null, null, null);
        this.medYtelse(new Svangerskapspenger(termindato, fødselsdato, medlemsskap, opptjening, tilrettelegging));
        return super.build();
    }
}
