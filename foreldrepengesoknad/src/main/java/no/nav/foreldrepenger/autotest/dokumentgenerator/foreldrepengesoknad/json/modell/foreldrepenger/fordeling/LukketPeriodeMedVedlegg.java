package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.foreldrepenger.fordeling;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static java.util.Collections.emptyList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "vedlegg" })
@ToString(exclude = { "vedlegg" })
@JsonPropertyOrder({ "fom", "tom" })
@JsonTypeInfo(use = NAME, include = PROPERTY, property = "type")
@JsonSubTypes({
        @Type(value = OverføringsPeriode.class, name = "overføring"),
        @Type(value = UttaksPeriode.class, name = "uttak"),
        @Type(value = OppholdsPeriode.class, name = "opphold"),
        @Type(value = UtsettelsesPeriode.class, name = "utsettelse")
})
public abstract class LukketPeriodeMedVedlegg implements Comparable<LukketPeriodeMedVedlegg> {

    @NotNull
    protected LocalDate fom;
    @NotNull
    protected LocalDate tom;
    protected List<String> vedlegg;


    @JsonCreator
    protected LukketPeriodeMedVedlegg(@JsonProperty("fom") LocalDate fom, @JsonProperty("tom") LocalDate tom,
            @JsonProperty("vedlegg") List<String> vedlegg) {
        this.fom = fom;
        this.tom = tom;
        this.vedlegg = Optional.ofNullable(vedlegg).orElse(emptyList());
    }

    @Override
    public int compareTo(LukketPeriodeMedVedlegg other) {
        return getFom().compareTo(other.getFom());
    }

}
