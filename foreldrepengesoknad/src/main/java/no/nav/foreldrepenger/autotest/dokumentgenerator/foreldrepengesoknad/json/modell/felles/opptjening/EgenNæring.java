package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.opptjening;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Collections.emptyList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.ProsentAndel;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.ÅpenPeriode;

@Data
@NoArgsConstructor
@JsonTypeInfo(use = NAME, include = PROPERTY, property = "type")
@JsonSubTypes({
        @Type(value = NorskOrganisasjon.class, name = "norsk"),
        @Type(value = UtenlandskOrganisasjon.class, name = "utenlandsk")
})
@ToString(exclude = "vedlegg")
@EqualsAndHashCode(exclude = "vedlegg")
public abstract class EgenNæring {

    private List<Virksomhetstype> virksomhetsTyper;
    private ÅpenPeriode periode;
    private boolean nærRelasjon;
    private List<Regnskapsfører> regnskapsførere;
    private boolean erNyOpprettet;
    private boolean erVarigEndring;
    private boolean erNyIArbeidslivet;
    private long næringsinntektBrutto;
    private LocalDate endringsDato;
    private LocalDate oppstartsDato;
    private String beskrivelseEndring;
    private ProsentAndel stillingsprosent;
    private List<String> vedlegg;

    @JsonCreator
    protected EgenNæring(
            @JsonProperty("virksomhetsType") List<Virksomhetstype> virksomhetsTyper,
            @JsonProperty("periode") ÅpenPeriode periode,
            @JsonProperty("nærRelasjon") boolean nærRelasjon,
            @JsonProperty("regnskapsførere") List<Regnskapsfører> regnskapsførere,
            @JsonProperty("erNyOpprettet") boolean erNyOpprettet,
            @JsonProperty("erVarigEndring") boolean erVarigEndring,
            @JsonProperty("erNyIArbeidslivet") boolean erNyIArbeidslivet,
            @JsonProperty("næringsinntektBrutto") long næringsinntektBrutto,
            @JsonProperty("endringsDato") LocalDate endringsDato,
            @JsonProperty("oppstartsDato") LocalDate oppstartsDato,
            @JsonProperty("beskrivelseEndring") String beskrivelseEndring,
            @JsonProperty("stillingsprosent") ProsentAndel stillingsprosent,
            @JsonProperty("vedlegg") List<String> vedlegg) {
        this.virksomhetsTyper = virksomhetsTyper;
        this.periode = periode;
        this.nærRelasjon = nærRelasjon;
        this.regnskapsførere = regnskapsførere;
        this.erNyOpprettet = erNyOpprettet;
        this.erNyIArbeidslivet = erNyIArbeidslivet;
        this.erVarigEndring = erVarigEndring;
        this.næringsinntektBrutto = næringsinntektBrutto;
        this.endringsDato = endringsDato;
        this.oppstartsDato = oppstartsDato;
        this.beskrivelseEndring = beskrivelseEndring;
        this.stillingsprosent = stillingsprosent;
        this.vedlegg = Optional.ofNullable(vedlegg).orElse(emptyList());
    }
}
