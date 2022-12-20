package com.ouir.ouir31.repository;

import com.ouir.ouir31.entity.Voc;
import com.ouir.ouir31.entity.VocReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VocReviewRepository extends CrudRepository<VocReview,Long> {

    List<VocReview> findByVvrno(Voc voc);
}
