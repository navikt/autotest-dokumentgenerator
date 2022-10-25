package no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.util;

import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.util.VirkedagUtil.helgejustertTilFredag;
import static no.nav.foreldrepenger.autotest.dokumentgenerator.foreldrepengesoknad.json.util.VirkedagUtil.helgejustertTilMandag;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class VirkedagUtilTest {

    @Test
    void fomTilVirkedag() {
        // Onsdag -> Onsdag
        assertThat(helgejustertTilMandag(LocalDate.of(2022, 10, 26))).isEqualTo(LocalDate.of(2022, 10, 26));
        // Fredag -> Fredag
        assertThat(helgejustertTilMandag(LocalDate.of(2022, 10, 28))).isEqualTo(LocalDate.of(2022, 10, 28));
        // Lørdag -> Mandag
        assertThat(helgejustertTilMandag(LocalDate.of(2022, 10, 29))).isEqualTo(LocalDate.of(2022, 10, 31));
        // Søndag -> Mandag
        assertThat(helgejustertTilMandag(LocalDate.of(2022, 10, 29))).isEqualTo(LocalDate.of(2022, 10, 31));
        // Mandag -> Mandag
        assertThat(helgejustertTilMandag(LocalDate.of(2022, 10, 31))).isEqualTo(LocalDate.of(2022, 10, 31));
    }

    @Test
    void tomTilVirkedag() {
        // Onsdag
        assertThat(helgejustertTilFredag(LocalDate.of(2022, 10, 26))).isEqualTo(LocalDate.of(2022, 10, 26));
        // Fredag
        assertThat(helgejustertTilFredag(LocalDate.of(2022, 10, 28))).isEqualTo(LocalDate.of(2022, 10, 28));
        // Lørdag -> Fredag
        assertThat(helgejustertTilFredag(LocalDate.of(2022, 10, 29))).isEqualTo(LocalDate.of(2022, 10, 28));
        // Søndag -> Fredag
        assertThat(helgejustertTilFredag(LocalDate.of(2022, 10, 29))).isEqualTo(LocalDate.of(2022, 10, 28));
    }


}
