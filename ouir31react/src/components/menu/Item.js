import React, { useState } from "react";
import "./Item.scss";

const Item = (props) => {
  const imgUrl = "img/더블에스프레소-2-450x588.png";
  const [isActive, setIsActive] = useState(false);
  const showBlock = (event) => {
    setIsActive((current) => !current);
  };
  return (
    <li className="list_item" onClick={showBlock}>
      <div className="thumb">
        <img src={imgUrl} alt="img" />
      </div>
      <p className="menu_title">{props.title}</p>
      <div className={isActive ? "show_block" : "hide_block"}>
        <h3 className="font-b1">{props.title}</h3>
        <div className="menu_tit2">{props.title2}</div>
        <p className="txt">{props.contents}</p>
        <p className="add_cart_css">
          <button className="add_cart" type="button">
            장바구니
          </button>
        </p>
      </div>
    </li>
  );
};

export default Item;
