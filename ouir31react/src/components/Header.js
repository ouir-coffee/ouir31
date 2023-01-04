import React from "react";
import "./Header.scss";
import { useState } from "react";

const Header = () => {
  const [hide,setHide] = useState(false); //메뉴 접기
  

    return(

      <div>
        <div className="Header">
      <div className="Content">
        <div className="Logo"><img src="img/logo.png" className="LogoStyle"></img></div>
        <div className="Title"><img src="/img/ouircoffee.jpg"></img></div>
        <div className="Menu">
          <div className="Item">
            로긴정보
          </div>
          <div className="Item">
            로그인/가입
          </div>
        </div>
      </div>
    </div>
    <nav className={hide ? "active" : ""}>
    <div className="Menubar">
    <ul className="Menulist" onMouseEnter={() => {setHide(true)}} onMouseLeave={() => {setHide(false)}}>
      <li>ABOUT OUIR
        <div>위어커피</div>
      </li>
      <li>MENU
        <div>커피ㅇㅇ</div>
        <div>커피ㄴㄴ</div>
        <div>커피안주</div>
      </li>
      <li>NOTICE
        <div>공지사항</div>
      </li>
      <li>VOC
        <div>문의사항</div>
      </li>
    </ul>
    </div>
    </nav>
    </div>
    );
};

export default Header;