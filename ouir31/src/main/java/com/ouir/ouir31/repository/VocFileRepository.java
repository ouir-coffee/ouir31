package com.ouir.ouir31.repository;

import com.ouir.ouir31.entity.Voc;
import com.ouir.ouir31.entity.VocFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocFileRepository extends CrudRepository<VocFile, Long> {
    List<VocFile> findByvfid(Voc voc);


    void deleteByvfid(Voc voc);
}
