package com.example.cardservice.repository;

import com.example.cardservice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    Optional<Card> findByIdAndDeletedAtIsNull(Integer id);

    @Query(
            nativeQuery = true,
            value = "select * from carts"
    )
    List<Card> getAllCardByAndDeletedAtIsNull();

    List<Card> getAllByUserIdAndDeletedAtIsNull(Integer id);

}
