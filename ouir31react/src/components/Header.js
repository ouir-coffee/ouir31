import React from "react";
import "./Header.scss";
import { Link } from "react-router-dom";

const Header = () => {
  // const [view, setView] = useState(false); //메뉴 접기
  const home = "/";
  const about = "/about";
  const menu = "/menu";
  const notice = "/notice";
  const voc = "/voc";

  return (
    <div className="Header">
      <div className="Content">
        <Link to={home}>
          <img src="/img/ouircoffee.jpg" className="Logostyle"></img></Link>
        <nav>
          <div className="Menubar">
            <ul className="Menulist">
              <Link to={about}><li>ABOUT OUIR</li></Link>
              <Link to={menu}><li>MENU</li></Link>
              <Link to={notice}><li>NOTICE</li></Link>
              <Link to={voc}><li>VOC</li></Link>
            </ul>
          </div>
        </nav>
        <div className="Menu">
          <div className="Item">
            <Link>로그인정보</Link>
          </div>
          <div className="Item">
            <Link>회원가입</Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Header;