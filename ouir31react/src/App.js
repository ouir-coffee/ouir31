import "./App.css";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Home from "./components/Home";
import { Route, Routes ,useNavigate } from "react-router-dom";
import Menu from "./components/menu/Menu";
import About from "./components/About";
import Notice from "./components/Notice";
import NoticeWrite from "./components/NoticeWrite";
import Join from "./components/Join";
import Login from "./components/Login";
import {useState, useEffect, useCallback} from "react";
import Mypage from "./components/Mypage";

function App() {
  const nav = useNavigate();

  //로그인 상태 저장
  const [lstate, setLstate] = useState({
    logid: "",
    flink: "/login",
  });

  useEffect(() => {
    //세션에 저장된 로그인 아이디를 가져옴(로그인 상태 유지)
    const uid = sessionStorage.getItem("uid");
    console.log(uid);
    if (uid !== null) {
      const newState = {
        logid: uid,
        flink: "/home",
      };
      setLstate(newState);
    }
  }, []);

  //로그인 성공 시 로그인 상태 변경 함수
  const sucLogin = useCallback((uid) => {
    const newState = {
      logid: uid,
      flink: "/home",
    };
    setLstate(newState);
  }, []);

  //로그아웃함수
  const onLogout = () => {
    alert("로그아웃");
    const newState = {
      logid: "",
      flink: "/login",
    };
    setLstate(newState);
    //로그아웃 시 로그인 상태 및 페이지번호 삭제
    sessionStorage.removeItem("uid");
    sessionStorage.removeItem("pageNum");
    nav("/"); //첫페이지로 돌아감.
  };
  
  return (
    <div className="App">
      <Header lstate={lstate} onLogout={onLogout}/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/menu" element={<Menu />} />
        <Route path="/about" element={<About/>} />
        <Route path="/notice" element={<Notice />} />
        <Route path="/notice/write" element={<NoticeWrite/>} />
        <Route path="/login" element={<Login sucLogin={sucLogin} />}/>
        <Route path="/join" element={<Join/>} />
        <Route path="/mypage" element={<Mypage/>}/>
      </Routes>
      <Footer/>
    </div>
  );
}

export default App;
