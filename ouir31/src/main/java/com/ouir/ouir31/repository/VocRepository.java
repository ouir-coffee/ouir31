package com.ouir.ouir31.repository;

import com.ouir.ouir31.entity.Voc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocRepository extends CrudRepository<Voc,Long> {

    Page<Voc> findByVocnoGreaterThan(long vocno, Pageable pb);

    Voc findById(long vocno);

}
