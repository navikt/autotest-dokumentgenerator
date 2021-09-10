package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.medlemskap;

import static java.util.Collections.emptyList;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FramtidigOppholdsInformasjon {

    @Valid
    private final List<Utenlandsopphold> utenlandsOpphold;

    @JsonCreator
    public FramtidigOppholdsInformasjon(@JsonProperty("utenlandsOpphold") List<Utenlandsopphold> utenlandsOpphold) {
        this.utenlandsOpphold = Optional.ofNullable(utenlandsOpphold).orElse(emptyList());
    }

    @JsonIgnore
    public boolean isNorgeNeste12() {
        return utenlandsOpphold.isEmpty();
    }
}
