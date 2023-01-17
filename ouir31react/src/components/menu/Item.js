import React from "react";
import "./Item.scss";

const Item = (props) => {
  const { imgUrl, title, title2, contents, index, active, onClick } = props;

  return (
    <li className="list_item" onClick={onClick}>
      <div className="thumb">
        <img src={imgUrl} alt="img" />
      </div>
      <p className="menu_title">{title}</p>
      <div
        className={
          index === active ? "active_view fade-in" : "active_view fade-out"
        }
      >
        <h3 className="font-b1">{title}</h3>
        <div className="menu_tit2">{title2}</div>
        <p className="txt">{contents}</p>
        <p className="add_cart_css">
          <button className="add_cart_btn" type="button">
            장바구니
          </button>
        </p>
      </div>
    </li>
  );
};

export default Item;
