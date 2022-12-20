package com.ouir.ouir31.repository;

import com.ouir.ouir31.entity.Notice;
import com.ouir.ouir31.entity.NoticeFile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeFileRepository extends CrudRepository<NoticeFile, Long> {

    List<NoticeFile> findByNfnid(Notice notice);
    void deleteByNfnid(Notice notice);
}
