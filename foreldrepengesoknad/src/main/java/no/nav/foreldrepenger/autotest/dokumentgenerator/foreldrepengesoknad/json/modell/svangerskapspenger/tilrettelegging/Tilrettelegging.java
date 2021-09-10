package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger.tilrettelegging;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Collections.emptyList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.svangerskapspenger.tilrettelegging.arbeidsforhold.Arbeidsforhold;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "vedlegg" })
@ToString(exclude = { "vedlegg" })
@JsonTypeInfo(use = NAME, include = PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = HelTilrettelegging.class, name = "hel"),
        @JsonSubTypes.Type(value = DelvisTilrettelegging.class, name = "delvis"),
        @JsonSubTypes.Type(value = IngenTilrettelegging.class, name = "ingen")
})
public abstract class Tilrettelegging {

    private Arbeidsforhold arbeidsforhold;
    @NotNull
    private LocalDate behovForTilretteleggingFom;
    private List<String> vedlegg;

    protected Tilrettelegging(Arbeidsforhold arbeidsforhold, LocalDate behovForTilretteleggingFom, List<String> vedlegg) {
        this.arbeidsforhold = arbeidsforhold;
        this.behovForTilretteleggingFom = behovForTilretteleggingFom;
        this.vedlegg = Optional.ofNullable(vedlegg).orElse(emptyList());
    }
}
