package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger.tilrettelegging.arbeidsforhold;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.Orgnummer;

@Data
@EqualsAndHashCode(callSuper = false)
public class Virksomhet extends Arbeidsforhold {

    @NotNull
    public final Orgnummer orgnr;

    @JsonCreator
    public Virksomhet(Orgnummer orgnr) {
        this.orgnr = orgnr;
    }

}
