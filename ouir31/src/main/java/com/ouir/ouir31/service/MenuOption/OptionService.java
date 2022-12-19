package com.ouir.ouir31.service.MenuOption;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.MenuOption.Menu;
import com.ouir.ouir31.entity.MenuOption.Option;
import com.ouir.ouir31.entity.MenuOption.OptionCategories;
import com.ouir.ouir31.repository.MenuOption.OptionCateRepository;
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
    private final OptionCateRepository ocRepo;
    private final ReturnMsg rm = new ReturnMsg();

    public ReturnMsg optionInsert(Option option, int oc_code) {
        log.info("optionInsert()");
        rm.setFlag(false);
        try{
            option.setOptionCategories(ocRepo.findById(oc_code).get());
            oRepo.save(option);
            rm.setFlag(true);
            rm.setMsg("Option 작성 완료");
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
    public Option optionSearch(int ono){
        log.info("optionSearch()");
        return oRepo.findById(ono);
    }

    // Option Update ( 옵션 수정 )
    public ReturnMsg optionUpdate(Option option){
        log.info("optionUpdate()");
        rm.setFlag(false);
        try{
            Option op = oRepo.findById(option.getOno());
            if(op == null){
                rm.setFlag(false);
                rm.setMsg("데이터가 없습니다.");
                return rm;
            }
            if(option.getOitem() == null){
                op.setOitem(oRepo.findById(option.getOno()).getOitem());
            }else{
                op.setOitem(option.getOitem());
            }
            if(option.getOprice() == 0){
                op.setOprice(oRepo.findById(option.getOno()).getOprice());
            }else{
                op.setOprice(option.getOprice());
            }

            oRepo.save(op);
            rm.setFlag(true);
            rm.setMsg("수정 성공하였습니다.");
        }catch(Exception e){
            e.printStackTrace();
            rm.setFlag(false);
            rm.setMsg("수정 실패하였습니다.");
        }
        return rm;
    }

    // Option Delete ( 옵션 삭제 )
    public ReturnMsg optionDelete(int ono){
        log.info("optionDelete()");
        rm.setFlag(false);

        try{
            Option option = oRepo.findById(ono);
            if(option == null){
                rm.setMsg("데이터가 없습니다.");
                rm.setFlag(false);
                return rm;
            }
            oRepo.deleteById(ono);
            rm.setMsg(ono + " 번이 삭제되었습니다.");
            rm.setFlag(true);
        }catch(Exception e){
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;
    }


    // 옵션 카테고리 Insert
    public boolean optionCategories(OptionCategories optionCategories){
        log.info("optionCategories()");
        boolean result = false;
        try{
            ocRepo.save(optionCategories);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
