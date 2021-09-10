package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;

@Data
public class ProsentAndel {

    @JsonValue
    private final Double prosent;
}
