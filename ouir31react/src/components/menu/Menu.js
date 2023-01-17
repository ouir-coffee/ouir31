import React, { useState, useEffect } from "react";
import axios from "axios";
import CreateMenu from "./CreateMenu";
import DeleteMenu from "./DeleteMenu";
import Item from "./Item";
import "./Menu.scss";
import Slide from "./Slide";
import UpdateMenu from "./UpdateMenu";

const Menu = () => {
  const uid = sessionStorage.getItem("uid");

  const [adminLogin, setAdmin] = useState(false);

  useEffect(() => {
    if (uid === "Admin") {
      setAdmin(true);
    }
  }, [uid]);
  //모달 관련 useState
  const [createModal, setCreate] = useState(false);

  const open1 = () => {
    setCreate(true);
  };

  const close1 = () => {
    setCreate(false);
  };

  const [updateModal, setUpdate] = useState(false);

  const open2 = () => {
    setUpdate(true);
  };

  const close2 = () => {
    setUpdate(false);
  };

  const [deleteModal, setDelete] = useState(false);

  const open3 = () => {
    setDelete(true);
  };

  const close3 = () => {
    setDelete(false);
  };

  const [data, setData] = useState({});
  const [searchMenu, setMenu] = useState([]);
  const [bestItem, setBest] = useState({});
  useEffect(() => {
    axios
      .get("/menu")
      .then((res) => {
        setData(res.data);
        const dataList = [];
        for (let i = 0; i < res.data.length; i++) {
          const mItem = {
            ...res.data[i],
          };
          dataList.push(mItem.mitem);
        }
        setMenu(dataList);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const [active, setActive] = useState(-1);

  const itemList = Object.values(data).map(
    (value, index) => {
      const imgPath = "upload/" + data[index].mfList.mfsysname;
      return (
        <Item
          key={index}
          imgUrl={imgPath || ""}
          title={data[index].mitem}
          title2={data[index].mname}
          contents={data[index].mcontents}
          index={index}
          active={active}
          onClick={() => {
            active === index ? setActive(-1) : setActive(index);
          }}
        />
      );
    },
    [active]
  );

  useEffect(() => {
    const dataList = [];
    for (let i = 0; i < data.length; i++) {
      if (data[i].mbest === true) {
        const dataSet = {
          ...data[i],
        };
        dataList.push(dataSet);
      }
    }
    setBest(dataList);
  }, [data]);

  return (
    <>
      <CreateMenu open={createModal} close={close1} />
      <UpdateMenu open={updateModal} close={close2} search={searchMenu} />
      <DeleteMenu open={deleteModal} close={close3} search={searchMenu} />
      <div className="menu_section">
        <div className="container">
          <h2 className="main_title">추천메뉴</h2>
          {bestItem.length === 0 ? null : <Slide best={bestItem} />}

          <ul className={adminLogin ? "menu_update show" : "menu_update hide"}>
            <li onClick={open1}>[메뉴 생성]</li>
            <li onClick={open2}>[메뉴 수정]</li>
            <li onClick={open3}>[메뉴 삭제]</li>
          </ul>
          <div className="menu_list">
            <ul>{itemList && itemList}</ul>
          </div>
        </div>
      </div>
    </>
  );
};

export default Menu;
