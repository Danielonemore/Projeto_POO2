package br.com.clinicavt.repositories;

import br.com.clinicavt.models.consult.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, UUID> {

    @Query("SELECT c FROM Consult c WHERE c.date = :date AND ABS(HOUR(c.time) - :hour) < 1")
    Optional<Consult> findNearbyConsult(@Param("date") LocalDate date, @Param("hour") int hour);


    @Query("SELECT MIN(c.time) FROM Consult c WHERE c.date = :date AND c.time > :time")
    Optional<LocalTime> findNextAvailableTime(@Param("date") LocalDate date, @Param("time") LocalTime time);

    @Query("SELECT MIN(c.date) FROM Consult c WHERE c.date > :date")
    Optional<LocalDate> findNextAvailableDay(@Param("date") LocalDate date);
}
