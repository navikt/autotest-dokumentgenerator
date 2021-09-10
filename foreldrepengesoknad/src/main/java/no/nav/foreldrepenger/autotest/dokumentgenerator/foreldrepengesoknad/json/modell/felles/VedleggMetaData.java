package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class VedleggMetaData {

    private final String beskrivelse;
    private final String id;
    private final InnsendingsType innsendingsType;
    private final DokumentType dokumentType;

    public VedleggMetaData(String id, InnsendingsType innsendingsType, DokumentType dokumentType) {
        this(dokumentType.getBeskrivelse(), id, innsendingsType, dokumentType);
    }

    @JsonCreator
    public VedleggMetaData(@JsonProperty("beskrivelse") String beskrivelse, @JsonProperty("id") String id,
            @JsonProperty("innsendingsType") InnsendingsType innsendingType,
            @JsonProperty("dokumentType") DokumentType dokumentType) {
        this.beskrivelse = Optional.ofNullable(beskrivelse)
                .orElse(dokumentType.getBeskrivelse());
        this.id = id;
        this.innsendingsType = innsendingType;
        this.dokumentType = dokumentType;
    }
}
