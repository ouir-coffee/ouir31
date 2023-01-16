package com.ouir.ouir31.service.MenuOption;

import com.ouir.ouir31.dto.ReturnMsg;
import com.ouir.ouir31.entity.MenuOption.Menu;
import com.ouir.ouir31.entity.MenuOption.MenuFiles;
import com.ouir.ouir31.repository.MenuOption.MenuFilesRepository;
import com.ouir.ouir31.repository.MenuOption.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Log
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository mRepo;
    private final MenuFilesRepository mfRepo;
    private final ReturnMsg rm = new ReturnMsg();

    // 메뉴 저장
    public ReturnMsg menuInsert(Menu menu, List<MultipartFile> files, HttpSession session) {
        log.info("menuInsert()");
        try{
            mRepo.save(menu);
            if(files != null){
                fileUpload(files,session,menu.getMitem());
            }
            rm.setFlag(true);

        }catch(Exception e){
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;
    }

    private void fileUpload(List<MultipartFile> files, HttpSession session, String mitem) throws Exception{
        MenuFiles mfItem = mfRepo.findByMfmitem(mitem);
        String realPath = session.getServletContext().getRealPath("/");
        log.info("realPath : " + realPath);
        realPath += "upload/";
        File folder = new File(realPath);
        if(!folder.isDirectory()){
            folder.mkdir();
        }

        for(MultipartFile file : files) {
            String orname = file.getOriginalFilename();
            log.info("확인 : " + mfRepo.findByMforiname(orname));
            if(orname.equals("")){
                return;
            }
            if(mfItem != null){
                String delPath = realPath += mfItem.getMfsysname();
                File fileData = new File(delPath);

                if(fileData.exists()){
                    fileData.delete(); //파일이 있으면 삭제
                    realPath = session.getServletContext().getRealPath("/");
                    realPath += "upload/";
                }

                mfItem.setMfno(mfItem.getMfno());
                mfItem.setMfmitem(mitem);
                mfItem.setMforiname(orname);
                String sysname = System.currentTimeMillis() + orname.substring(orname.lastIndexOf("."));
                mfItem.setMfsysname(sysname);
                File saveFile = new File(realPath + sysname);
                file.transferTo(saveFile);
                mfRepo.save(mfItem);
                return;
            }
               MenuFiles mf = new MenuFiles();
                mf.setMfmitem(mitem);
                mf.setMforiname(orname);
                String sysname = System.currentTimeMillis() + orname.substring(orname.lastIndexOf("."));
                mf.setMfsysname(sysname);
                File saveFile = new File(realPath + sysname);
                file.transferTo(saveFile);
                mfRepo.save(mf);

        }

    }

    // 메뉴 카테고리별 출력
    public List<Menu> menuList(String mcate) {
        List<Menu> menuList = mRepo.findByMcate(mcate);
        int i = 0;
        for(Menu menu : menuList){
            MenuFiles menuFiles = mfRepo.findByMfmitem(menu.getMitem());
            menu.setMfList(menuFiles);
            menuList.set(i,menu);
            i++;

        }
        return menuList;
    }


    // 메뉴 개별 출력
    public Menu menuSearch(String mitem) {
        log.info("menuSearch()");

        Menu menu = mRepo.findByMitem(mitem);
        MenuFiles menuFiles = mfRepo.findByMfmitem(menu.getMitem());
        menu.setMfList(menuFiles);
        return menu;
    }



    // 메뉴 삭제
    public ReturnMsg menuDelete(String mitem) {
        log.info("menuDelete()");
        rm.setFlag(false);
        try{
            mRepo.deleteById(mitem);
            rm.setFlag(true);
        }catch (Exception e){
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;
    }
}
