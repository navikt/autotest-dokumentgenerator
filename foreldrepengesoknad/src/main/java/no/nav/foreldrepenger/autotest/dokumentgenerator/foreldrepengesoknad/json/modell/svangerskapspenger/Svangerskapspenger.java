package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.Ytelse;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.medlemskap.Medlemsskap;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.opptjening.Opptjening;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger.tilrettelegging.Tilrettelegging;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
public class Svangerskapspenger extends Ytelse {

    @NotNull
    private LocalDate termindato;
    private LocalDate fødselsdato;
    @Valid
    private Medlemsskap medlemsskap;
    @Valid
    private Opptjening opptjening;
    @Valid
    private List<Tilrettelegging> tilrettelegging;

    @JsonCreator
    public Svangerskapspenger(@JsonProperty("termindato") LocalDate termindato,
            @JsonProperty("fødselsdato") LocalDate fødselsdato,
            @JsonProperty("medlemsskap") Medlemsskap medlemsskap,
            @JsonProperty("opptjening") Opptjening opptjening,
            @JsonProperty("tilrettelegging") List<Tilrettelegging> tilrettelegging) {
        this.termindato = termindato;
        this.fødselsdato = fødselsdato;
        this.medlemsskap = medlemsskap;
        this.opptjening = opptjening;
        this.tilrettelegging = tilrettelegging;
    }

}
