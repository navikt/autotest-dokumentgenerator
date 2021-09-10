package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.foreldrepenger.fordeling;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.Orgnummer;
import no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.ProsentAndel;

@Data
@EqualsAndHashCode(callSuper = true, exclude = { "virksomhetsnummer" })
@ToString(callSuper = true, exclude = { "virksomhetsnummer" })
public class GradertUttaksPeriode extends UttaksPeriode {
    private final ProsentAndel arbeidstidProsent;
    private final boolean erArbeidstaker;
    private final List<Orgnummer> virksomhetsnummer;
    private final boolean arbeidsForholdSomskalGraderes;
    private final Boolean frilans;
    private final Boolean selvstendig;

    @Builder(builderMethodName = "GraderingBuilder")
    @JsonCreator
    public GradertUttaksPeriode(@JsonProperty("fom") LocalDate fom, @JsonProperty("tom") LocalDate tom,
            @JsonProperty("UttaksperiodeType") StønadskontoType uttaksperiodeType,
            @JsonProperty("ønskerSamtidigUttak") boolean ønskerSamtidigUttak,
            @JsonProperty("morsAktivitetsType") MorsAktivitet morsAktivitetsType,
            @JsonProperty("ønskerFlerbarnsdager") boolean ønskerFlerbarnsdager,
            @JsonProperty("samtidigUttakProsent") ProsentAndel samtidigUttakProsent,
            @JsonProperty("arbeidstidProsent") ProsentAndel arbeidstidProsent,
            @JsonProperty("erArbeidstaker") boolean erArbeidstaker,
            @JsonProperty("arbeidsForholdSomskalGraderes") boolean arbeidsForholdSomskalGraderes,
            @JsonProperty("virksomhetsnummer") List<Orgnummer> virksomhetsnummer,
            @JsonProperty("frilans") Boolean frilans,
            @JsonProperty("selvstendig") Boolean selvstendig,
            @JsonProperty("vedlegg") List<String> vedlegg) {
        super(fom, tom, uttaksperiodeType, ønskerSamtidigUttak, morsAktivitetsType, ønskerFlerbarnsdager,
                samtidigUttakProsent, vedlegg);
        this.arbeidstidProsent = arbeidstidProsent;
        this.erArbeidstaker = erArbeidstaker;
        this.virksomhetsnummer = virksomhetsnummer;
        this.arbeidsForholdSomskalGraderes = arbeidsForholdSomskalGraderes;
        this.frilans = frilans;
        this.selvstendig = selvstendig;
    }
}
