package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.modell.felles.medlemskap;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonPropertyOrder({ "arbeidSiste12", "utenlandsopphold", "framtidigUtenlandsopphold" })
public class Medlemsskap {

    private ArbeidsInformasjon arbeidSiste12;
    private List<Utenlandsopphold> utenlandsopphold;
    private List<Utenlandsopphold> framtidigUtenlandsopphold;

}
