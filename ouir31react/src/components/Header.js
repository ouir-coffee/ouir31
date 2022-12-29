import React from "react";
// import "./Header.scss";

const Header = ({lstate,onLogout}) => {
    // const { logid, flink } = lstate;
    // const homeLink = logid === "" ? "/" : "/main";

    return(
        <div className="Header">
      <div className="Content">
          <img src="img/logo.png" className="LogoStyle"></img>
        <div className="Title"><img src="/img/ouircoffee.jpg"></img></div>
        <div className="Menu">
          <div className="Item">
          </div>
          <div className="Item">
            <span>login</span>:
            <span>register</span>
          </div>
        </div>
      </div>
    </div>
    );
};

export default Header;