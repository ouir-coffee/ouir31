import React, { useState } from "react";
import CreateMenu from "./CreateMenu";
import DeleteMenu from "./DeleteMenu";
import Item from "./Item";
import "./Menu.scss";
import Slide from "./Slide";
import UpdateMenu from "./UpdateMenu";

const Menu = () => {
  const [createModal, setCreate] = useState(false);

  const open1 = () => {
    setCreate(true);
  };

  const close1 = () => {
    setCreate(false);
  };

  const submit1 = () => {
    close1();
  };

  const [updateModal, setUpdate] = useState(false);

  const open2 = () => {
    setUpdate(true);
  };

  const close2 = () => {
    setUpdate(false);
  };

  const submit2 = () => {
    close2();
  };

  const [deleteModal, setDelete] = useState(false);

  const open3 = () => {
    setDelete(true);
  };

  const close3 = () => {
    setDelete(false);
  };

  const submit3 = () => {
    close3();
  };

  return (
    <>
      <CreateMenu open={createModal} close={close1} submit={submit1} />
      <UpdateMenu open={updateModal} close={close2} submit={submit2} />
      <DeleteMenu open={deleteModal} close={close3} submit={submit3} />
      <div className="menu_section">
        <div className="container">
          <h2 className="main_title">추천메뉴</h2>
          <Slide />
          <ul className="menu_update">
            <li onClick={open1}>[메뉴 생성]</li>
            <li onClick={open2}>[메뉴 수정]</li>
            <li onClick={open3}>[메뉴 삭제]</li>
          </ul>
          <div className="menu_list">
            <ul>
              <Item
                title="더블에스프레소"
                title2="DOUBLE ESPROSSO"
                contents="저로 말할 것 같으면 저는 더블에스프레소라는 커피입니다."
              />
              <Item title="아메리카노" />
              <Item title="아메리카노" />
              <Item title="아메리카노" />
              <Item title="아메리카노" />
              <Item title="아메리카노" />
              <Item title="아메리카노" />
              <Item title="아메리카노" />
            </ul>
          </div>
        </div>
      </div>
    </>
  );
};

export default Menu;
