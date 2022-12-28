package com.ouir.ouir31.service.MenuOption;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.MenuOption.Option;
import com.ouir.ouir31.repository.MenuOption.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
@RequiredArgsConstructor

public class OptionService {
    private final OptionRepository oRepo;
    private final ReturnMsg rm = new ReturnMsg();

    public ReturnMsg optionInsert(Option option) {
        log.info("optionInsert()");
        try{
            oRepo.save(option);
            rm.setFlag(true);
        }catch(Exception e){
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;
    }

    // Option List ( 옵션 전체 출력 )
    public List<Option> optionList() {
        log.info("optionList()");
        return oRepo.findAll();
    }

    // Option Search ( 옵션 개별 출력 )
    public Option optionSearch(String oitem){
        log.info("optionSearch()");
        return oRepo.findByOitem(oitem);
    }


    // Option Update ( 옵션 수정 )
    public ReturnMsg optionUpdate(Option option){
        log.info("optionUpdate()");
        try{
            oRepo.save(option);
            rm.setFlag(true);
        }catch(Exception e){
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;
    }

    // Option Delete ( 옵션 삭제 )
    public ReturnMsg optionDelete(String oitem){
        log.info("optionDelete()");
        try{
            oRepo.deleteById(oitem);
            rm.setFlag(true);
        }catch(Exception e){
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;
    }
}
