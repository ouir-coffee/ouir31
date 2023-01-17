import React, { useState, useCallback, useEffect } from "react";
import axios from "axios";
import Modal from "../Modal/Modal";

const DeleteMenu = (props) => {
  const { open, close, search } = props;
  const [choice, setChoice] = useState();

  const change = useCallback((e) => {
    setChoice(e.target.value);
  }, []);

  const deleteSubmit = useCallback(
    (e) => {
      e.preventDefault();

      axios
        .delete("/menu", { params: { mitem: choice } })
        .then((res) => {
          console.log(res.data);
          window.location.replace("/menu");
        })
        .catch((error) => console.log(error));
    },
    [choice]
  );

  useEffect(() => {
    setChoice();
  }, [open]);

  return (
    <Modal open={open} close={close} header="메뉴 수정">
      <div className="MenuStyle">
        <div className="select_menu">
          <form onSubmit={deleteSubmit}>
            <select onChange={change} value={choice}>
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
            <button type="submit">Delete</button>
          </form>
        </div>
      </div>
    </Modal>
  );
};

export default DeleteMenu;
