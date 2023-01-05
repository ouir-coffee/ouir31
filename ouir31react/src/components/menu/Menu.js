import React from "react";
import Item from "./Item";
import PrevBtn from "./PrevBtn";
import NextBtn from "./NextBtn";
import "./Menu.scss";

const Menu = () => {
  return (
    <div className="menu_section">
      <div className="container">
        <h2 className="main_title">추천메뉴</h2>
        <div className="menu_slider">
          <div className="best_item" style={{ transform: "translateX(0px)" }}>
            <Item title="아메리카노" />
            <Item title="아메리카노" />
            <Item title="아메리카노" />
            <Item title="아메리카노" />
            <Item title="아메리카노" />
            <Item title="아메리카노" />
          </div>
          <PrevBtn />
          <NextBtn />
        </div>
        <div className="menu_list">
          <ul>
            <Item
              title="더블에스프레소"
              title2="DOUBLE ESPROSSO"
              contents="저로 말할 것 같으면 저는 더블에스프레소라는 커피입니다."
            />
            <Item title="아메리카노" />
          </ul>
        </div>
      </div>
    </div>
  );
};

export default Menu;
