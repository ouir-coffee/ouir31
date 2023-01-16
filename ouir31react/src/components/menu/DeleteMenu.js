import React from "react";
import Modal from "../Modal/Modal";

const DeleteMenu = (props) => {
  const { open, close, submit } = props;
  return (
    <Modal open={open} close={close} submit={submit} header="Delete">
      <div className="MenuStyle">
        <form className="Content">
          <span>상품명(한글)</span>
          <input name="mitem" autoFocus />
          <span>상품명(ENG)</span>
          <input name="mname" />
          <span>상품소개</span>
          <input name="mcontents" />
          <span>상품가격</span>
          <input name="mprice" />
          <span>추천여부</span>
          <select name="mbest">
            <option value="false">기본상품</option>
            <option value="true">추천상품</option>
          </select>
          <span>카테고리</span>
          <select>
            <option value="Coffee">Coffee</option>
            <option value="Cold Blew">Cold Blew</option>
            <option value="Beverage">Beverage</option>
            <option value="Organic Tea">Organic Tea</option>
            <option value="Financier">Financier</option>
            <option value="Scone">Scone</option>
            <option value="Butter Bar">Butter Bar</option>
            <option value="Sandwich">Sandwich</option>
          </select>
        </form>
      </div>
    </Modal>
  );
};

export default DeleteMenu;
