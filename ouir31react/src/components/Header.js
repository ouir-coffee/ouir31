import React, { useState } from "react";
import "./Header.scss";
import { Link } from "react-router-dom";

const Header = ({ lstate, onLogout }) => {
  const [view, setView] = useState(false); //메뉴 접기
  const mouseOver = () => {
    setView(true);
  };

  const mouseOut = () => {
    setView(false);
  };
  const home = "/";
  const about = "/about";
  const menu = "/menu";
  const notice = "/notice";
  const voc = "/voc";
  const myPage = "/mypage";
  //로그인 정의
  const { logid, flink } = lstate;
  //로고 클릭(로그인 후 main, 로그인 전 home)
  // console.log(JSON.parse(logid))
  // console.log(JSON.stringify(logid))

  return (
    <div className="Header">
      <div className={view ? "dropDown active" : "dropDown out"}></div>
      <div className="Content">
        <Link to={home}>
          <img src="/img/ouircoffee.jpg" className="Logostyle"></img>
        </Link>
        <nav>
          <div className="Menubar">
            <ul
              className="Menulist"
              onMouseOver={mouseOver}
              onMouseOut={mouseOut}
            >
              <Link to={about}>
                <li>ABOUT OUIR</li>
                <ul className={view ? "drop_block" : "drop_none"}>
                  <li>1</li>
                  <li>2</li>
                  <li>3</li>
                </ul>
              </Link>
              <Link to={menu}>
                <li>MENU</li>
                <ul className={view ? "drop_block" : "drop_none"}>
                  <li>1</li>
                  <li>2</li>
                  <li>3</li>
                </ul>
              </Link>
              <Link to={notice}>
                <li>NOTICE</li>
                <ul className={view ? "drop_block" : "drop_none"}>
                  <li>1</li>
                  <li>2</li>
                  <li>3</li>
                </ul>
              </Link>
              <Link to={voc}>
                <li>VOC</li>
                <ul className={view ? "drop_block" : "drop_none"}>
                  <li>1</li>
                  <li>2</li>
                  <li>3</li>
                </ul>
              </Link>
            </ul>
          </div>
        </nav>
        <div className="Menu">
          <div className="Item">
            <Link to={flink}>{logid !== "" ? `${logid}님` : "로그인"}</Link>
          </div>
          <div className="Item">
            <Link to={myPage}>{logid !== "" ? "마이페이지" : ""}</Link>
          </div>
          <div className="Item">
            {logid !== "" ? (
              <span onClick={onLogout}>로그아웃</span>
            ) : (
              <Link to="/join">회원가입</Link>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Header;
