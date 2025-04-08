package school.hei.patrimoine.cas.pro3;

import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.Set;

public class BakoCas extends Cas {
    public BakoCas(LocalDate ajd, LocalDate finSimulation, Map<Personne, Double> possesseurs) {
        super(ajd, finSimulation, possesseurs);
    }

    @Override
    protected Devise devise() {
        return Devise.MGA;
    }

    @Override
    protected String nom() {
        return "Cas de Bako";
    }

    @Override
    protected void init() {

    }

    @Override
    protected void suivi() {

    }

    @Override
    public Set<Possession> possessions() {
        var bniBako = new Compte(
                "Un compte courant", LocalDate.of(2025, Month.APRIL, 1),new Argent(2_000_000, Devise.MGA)
        );

        var bmoiBako = new Compte("Un compte épargne ", LocalDate.of(2025, Month.APRIL, 1),new Argent( 625_000, Devise.MGA));

        var coffre = new Compte("Un coffre", LocalDate.of(2025, Month.APRIL, 1),new Argent( 1_750_000, Devise.MGA));

        var dateDebut = LocalDate.of(2025, Month.APRIL, 8);
        var dateFin = LocalDate.of(2025, Month.DECEMBER, 31);
        new FluxArgent("Salaire mensuelle", bniBako, dateDebut, dateFin, 2, Argent.ariary(2_125_000));

        new FluxArgent("Virement bancaire", bmoiBako, dateDebut, dateFin, 3, Argent.ariary(200_000));

        new FluxArgent("loyer", bniBako, dateDebut, dateFin, 26, Argent.ariary(-600_000));

        new FluxArgent("vivre", coffre, dateDebut, dateFin, 1, Argent.ariary(-700_000));

        var ordinateur = new Materiel("ordinateur", dateDebut, dateDebut, Argent.ariary(3_000_000), 0.12);
        return Set.of(bniBako, bmoiBako, coffre, ordinateur);
    }
}
