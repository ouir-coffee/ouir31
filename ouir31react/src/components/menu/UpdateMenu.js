import React, { useState, useCallback, useEffect } from "react";
import axios from "axios";
import Modal from "../Modal/Modal";
import "./MenuModal.scss";

const UpdateMenu = (props) => {
  const { open, close, search } = props;

  const [changeView, setChange] = useState(false);
  const [selectMenu, setSelect] = useState("");
  const [choiceMenu, setChoice] = useState({});
  const { mitem, mname, mcontents, mprice, mcate, mbest } = choiceMenu;
  const [fileName, setFileName] = useState("");
  const [file, setFile] = useState();
  const [formData] = useState(new FormData());

  // 수정할 메뉴 선택
  const onClick = useCallback(
    (e) => {
      e.preventDefault();

      axios
        .get("/menu/search", { params: { mitem: selectMenu } })
        .then((res) => {
          console.log(res.data);
          setChoice(res.data);
          setFileName(res.data.mfList.mforiname);
          setChange(true);
        })
        .catch((error) => error);
    },
    [selectMenu]
  );

  //수정시 발생하는 이벤트
  const onChange = useCallback(
    (e) => {
      const dataObj = {
        ...choiceMenu,
        [e.target.name]: e.target.value,
      };
      setChoice(dataObj);
    },
    [choiceMenu]
  );

  //수정사항 업데이트
  const update_menu = useCallback(
    (e) => {
      e.preventDefault();

      formData.append(
        "data",
        new Blob([JSON.stringify(choiceMenu)], { type: "application/json" })
      );

      formData.append("files", file);
      console.log(file);

      axios
        .post("/menu", formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          if (res.data.flag === true) {
            console.log(res);
            alert("작성 성공");
            window.location.replace("/menu");
          } else {
            alert("작성 실패");
            console.log(res);
          }
        })
        .catch((error) => console.log(error));
    },
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [choiceMenu, file]
  );

  //파일업로드 관련
  const fileUpload = useCallback((e) => {
    setFile(e.target.files[0]);
    setFileName(e.target.files[0].name);
  }, []);

  // 모달창 open, close 할때 리셋
  useEffect(() => {
    reset();
  }, [open]);

  // 리셋 함수.
  const reset = () => {
    setSelect("");
    setChoice({});
    setChange(false);
  };

  return (
    <Modal open={open} close={close} header="메뉴 수정">
      <div className="MenuStyle">
        <div className={!changeView ? "select_menu view" : "hide"}>
          <div>
            <select
              onChange={(e) => {
                setSelect(e.target.value);
              }}
            >
              <option value="">메뉴 선택</option>
              {search &&
                search.map((v, i) => {
                  return (
                    <option key={i} value={v}>
                      {v}
                    </option>
                  );
                })}
            </select>
            <button type="button" onClick={onClick}>
              Search
            </button>
          </div>
        </div>
        <form
          className={changeView ? "Content view" : "hide"}
          onSubmit={update_menu}
        >
          <span>상품명(한글)</span>
          <input
            name="mitem"
            autoFocus
            defaultValue={mitem || ""}
            onChange={onChange}
          />

          <span>상품명(ENG)</span>
          <input name="mname" defaultValue={mname || ""} onChange={onChange} />
          <span>상품소개</span>
          <input
            name="mcontents"
            defaultValue={mcontents || ""}
            onChange={onChange}
          />

          <span>상품가격</span>
          <input
            type="number"
            name="mprice"
            defaultValue={mprice || ""}
            onChange={onChange}
          />
          <span>추천여부</span>
          <select name="mbest" value={mbest} onChange={onChange}>
            <option value="">추천여부를 선택해주세요.</option>
            <option value="true">추천</option>
            <option value="false">기본</option>
          </select>
          <span>카테고리</span>
          <select name="mcate" value={mcate} onChange={onChange}>
            <option value="">카테고리를 선택해주세요.</option>
            <option value="Coffee">Coffee</option>
            <option value="Cold Blew">Cold Blew</option>
            <option value="Beverage">Beverage</option>
            <option value="Organic Tea">Organic Tea</option>
            <option value="Financier">Financier</option>
            <option value="Scone">Scone</option>
            <option value="Butter Bar">Butter Bar</option>
            <option value="Sandwich">Sandwich</option>
          </select>
          <div className="filebox">
            <input className="upload-name" defaultValue={fileName} disabled />
            <label htmlFor="file">Upload</label>
            <input type="file" id="file" onChange={fileUpload} />
          </div>
          <div className="submit_section">
            <button className="submit_btn" type="submit">
              Submit
            </button>
          </div>
        </form>
      </div>
    </Modal>
  );
};

export default UpdateMenu;
