import React, { useState, useCallback, useEffect } from "react";
import axios from "axios";
import Modal from "../Modal/Modal";
import "./MenuModal.scss";

const CreateMenu = (props) => {
  const { open, close } = props;

  const [file, setFile] = useState();
  const [fileName, setFileName] = useState();

  const [data, setData] = useState({});
  const { mitem, mname, mcontents, mprice, mcate, mbest } = data;
  const [formData] = useState(new FormData());

  const create_menu = useCallback(
    (e) => {
      e.preventDefault();
      console.log(data);
      console.log(file);

      formData.append(
        "data",
        new Blob([JSON.stringify(data)], { type: "application/json" })
      );

      formData.append("files", file);

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
    [data, file, formData]
  );

  const onChange = useCallback(
    (e) => {
      const dataObj = {
        ...data,
        [e.target.name]: e.target.value,
      };
      console.log(dataObj);
      setData(dataObj);
    },
    [data]
  );

  //파일업로드 관련
  const fileUpload = useCallback((e) => {
    setFile(e.target.files[0]);
    setFileName(e.target.files[0].name);
  }, []);

  useEffect(() => {
    setFileName();
    setFile();
    setData({});
  }, [open]);

  return (
    <Modal open={open} close={close} header="메뉴 생성">
      <div className="MenuStyle">
        <form className="Content" onSubmit={create_menu}>
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

export default CreateMenu;
