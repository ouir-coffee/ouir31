import React from "react";
import Modal from "../Modal/Modal";
import "./MenuModal.scss";

const CreateMenu = (props) => {
  const { open, close, submit } = props;
  return (
    <Modal open={open} close={close} submit={submit} header="Create">
      <div className="MenuStyle">
        <form className="Content">
          <input name="mitem" placeholder="상품명(한글)" />
          <input name="mname" placeholder="상품명(영어)" />
          <input name="mcontents" placeholder="상품소개" />
          <input name="mprice" placeholder="상품가격" />
          <select name="mbest">
            <option value="false">기본</option>
            <option value="true">추천</option>
          </select>
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

export default CreateMenu;
