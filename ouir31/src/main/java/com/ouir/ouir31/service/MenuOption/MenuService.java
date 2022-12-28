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

import javax.servlet.http.HttpSession;
import java.io.File;
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

        String realPath = session.getServletContext().getRealPath("/");
        log.info("realPath : " + realPath);
        realPath += "upload/";
        File folder = new File(realPath);
        if(!folder.isDirectory()){
            folder.mkdir();
        }
        for(MultipartFile file : files) {
            String orname = file.getOriginalFilename();
            if(orname.equals("")){
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

    // 메뉴 전체 출력
    public List<Menu> menuList() {
        log.info("menuList()");
        return mRepo.findAll();
    }

    // 메뉴 개별 출력
    public Menu menuSearch(String mitem) {
        log.info("menuSearch()");

        Menu menu = mRepo.findByMitem(mitem);
        List<MenuFiles> mfList = mfRepo.findByMfmitem(menu.getMitem());
        menu.setMfList(mfList);
        return mRepo.findByMitem(mitem);
    }

    // 메뉴 업데이트
    public ReturnMsg menuUpdate(Menu menu) {
        log.info("menuUpdate()");
        try{
            mRepo.save(menu);
            rm.setFlag(true);
        }catch(Exception e){
            e.printStackTrace();
            rm.setFlag(false);
        }
        return rm;
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
