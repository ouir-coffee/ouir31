package com.ouir.ouir31.repository;

import com.ouir.ouir31.entity.Notice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice, Long> {

    List<Notice> findAll();
}
