package com.mhawi.slider.business.repo;

import com.mhawi.slider.business.model.Slider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SliderRepo extends JpaRepository<Slider, Long> {


    @Query("select s from Slider s order by s.ordered asc")
    List<Slider> findAllOrderByOrdered();

    @Query("select s from Slider s where s.status=true and true = " +
            "CASE " +
            " WHEN (s.fromDate is null and s.toDate is null) THEN true " +
            " WHEN (s.fromDate is not null and s.toDate is not null) THEN (CASE WHEN CURRENT_DATE between  s.fromDate and s.toDate THEN true  else false  END )" +
            " WHEN  s.fromDate is not null and s.toDate is null then ( CASE WHEN   CURRENT_DATE >= s.fromDate   THEN true ELSE false END )" +
            " WHEN  s.toDate is not null and s.fromDate is null then  ( CASE WHEN  CURRENT_DATE <= s.toDate   THEN true ELSE false END )" +
            " ELSE false END " +
            " order by s.ordered asc")
    List<Slider> findActive();

}
