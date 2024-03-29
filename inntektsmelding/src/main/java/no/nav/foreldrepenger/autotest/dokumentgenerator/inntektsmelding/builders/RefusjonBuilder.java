package no.nav.foreldrepenger.autotest.dokumentgenerator.inntektsmelding.builders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import no.seres.xsd.nav.inntektsmelding_m._20181211.EndringIRefusjon;
import no.seres.xsd.nav.inntektsmelding_m._20181211.ObjectFactory;
import no.seres.xsd.nav.inntektsmelding_m._20181211.Refusjon;


public class RefusjonBuilder {
    ObjectFactory objectFactory = new ObjectFactory();
    Refusjon refusjonKladd;

    RefusjonBuilder() {
        refusjonKladd = objectFactory.createRefusjon();
    }
    RefusjonBuilder medRefusjonsBelopPerMnd(BigDecimal refusjonsBelopPerMnd) {
        refusjonKladd.setRefusjonsbeloepPrMnd(
                objectFactory.createRefusjonRefusjonsbeloepPrMnd(refusjonsBelopPerMnd));
        return this;
    }
    RefusjonBuilder medEndringIRefusjonslist(List<EndringIRefusjon> endringIRefusjonList) {
        if (endringIRefusjonList != null && !endringIRefusjonList.isEmpty()) {
            var endringIRefusjonsListe = objectFactory.createEndringIRefusjonsListe();
            endringIRefusjonsListe.getEndringIRefusjon().addAll(endringIRefusjonList);
            refusjonKladd.setEndringIRefusjonListe(
                    objectFactory.createRefusjonEndringIRefusjonListe(endringIRefusjonsListe)
            );
        }
        return this;
    }
    RefusjonBuilder medEndringIRefusjonslist(Map<LocalDate, BigDecimal> endringRefusjonMap) {
       medEndringIRefusjonslist(endringRefusjonMap.entrySet().stream()
               .map(entry -> createEndringIRefusjon(entry.getKey(), entry.getValue()))
               .toList());
        return this;
    }
    RefusjonBuilder medRefusjonsOpphordato(LocalDate refusjonsOpphordato) {
        refusjonKladd.setRefusjonsopphoersdato(objectFactory.createRefusjonRefusjonsopphoersdato(refusjonsOpphordato));
        return this;
    }

    Refusjon build() {
        Objects.requireNonNull(refusjonKladd.getRefusjonsbeloepPrMnd(), "refusjonsBelopPerMnd kan ikke være null");
        return refusjonKladd;
    }

    private EndringIRefusjon createEndringIRefusjon(LocalDate endringsdato, BigDecimal refusjonsbeloepPrMnd) {
        var of = new ObjectFactory();
        var endringIRefusjon = of.createEndringIRefusjon();
        endringIRefusjon.setEndringsdato(of.createEndringIRefusjonEndringsdato(endringsdato));
        endringIRefusjon.setRefusjonsbeloepPrMnd(of.createEndringIRefusjonRefusjonsbeloepPrMnd(refusjonsbeloepPrMnd));
        return endringIRefusjon;
    }

}
