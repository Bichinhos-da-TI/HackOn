package com.hackon.repositories;

import com.hackon.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e"
    + " WHERE" +
            " e.location = :location" +
            " AND (e.startDateTime BETWEEN :start and :end" +
            " OR e.endDateTime BETWEEN :start and :end" +
            " OR :start BETWEEN e.startDateTime and e.endDateTime"+
            " OR :end BETWEEN e.startDateTime and e.endDateTime)"
    )

    List<Event> findEventsBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("location") String location);


    @Query("SELECT e FROM Event e WHERE e.userId = :userId " +
            "AND EXTRACT(YEAR FROM e.startDateTime) = :year " +
            "AND EXTRACT(MONTH FROM e.startDateTime) = :month")
    List<Event> findAllByUserIdAndMonth(
            @Param("userId") Long userId,
            @Param("month") Integer month,
            @Param("year") Integer year);

}
