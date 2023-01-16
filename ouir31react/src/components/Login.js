import axios from "axios";
import React, { useCallback, useState } from "react";
import { useNavigate } from "react-router-dom";
import Button from "./Button";
import "./Login.scss";

const Login = ({ sucLogin }) => {
  const nav = useNavigate();
  const [form, setForm] = useState({
    uid: "",
    upwd: "",
  });
  const { uid, upwd } = form;

  const sendLogin = (e) => {
    e.preventDefault();

    axios
      .post("/user/login", form)
      .then((res) => {
        if (res.data.success === true) {
          const uid = res.data.uid;
          sucLogin(uid);
          //로그인 상태 유지(세션)
          sessionStorage.setItem("uid", uid);
          nav("/home");
        } else {
          alert("아이디나 비밀번호가 틀립니다.");
          const formObj = {
            uid: "",
            upwd: "",
          };
          setForm(formObj);
        }
      })
      .catch((err) => console.log(err));
  };

  const onChange = useCallback(
    (e) => {
      const formObj = {
        ...form,
        [e.target.name]: e.target.value,
      };
      setForm(formObj);
    },
    [form]
  );

  return (
    <div className="Login">
      <form className="Content" onSubmit={sendLogin}>
        <h1>로그인</h1>
        <input
          className="Input"
          name="uid"
          value={uid}
          placeholder="아이디"
          onChange={onChange}
          autoFocus
          required
        />
        <input
          type="password"
          className="Input"
          name="upwd"
          value={upwd}
          placeholder="비밀번호"
          onChange={onChange}
          required
        />
        <Button type="submit" size="large">
          로그인
        </Button>
      </form>
    </div>
  );
};

export default Login;

