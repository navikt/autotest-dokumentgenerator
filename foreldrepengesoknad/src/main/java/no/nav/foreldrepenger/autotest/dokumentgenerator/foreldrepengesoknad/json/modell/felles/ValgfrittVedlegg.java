package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ValgfrittVedlegg extends Vedlegg {

    @JsonCreator
    public ValgfrittVedlegg(@JsonProperty("metadata") VedleggMetaData metadata,
            @JsonProperty("vedlegg") byte[] vedlegg) {
        super(metadata, vedlegg);
    }

}
