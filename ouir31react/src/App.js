import logo from "./logo.svg";
import "./App.css";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Home from "./components/Home";
import { Route, Routes } from "react-router-dom";
import Menu from "./components/menu/Menu";
import About from "./components/About";
import Notice from "./components/Notice";
import NoticeWrite from "./components/NoticeWrite";

function App() {
  return (
    <div className="App">
      <Header/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/menu" element={<Menu />} />
        <Route path="/about" element={<About/>} />
        <Route path="/notice" element={<Notice />} />
        <Route path="/notice/write" element={<NoticeWrite/>} />
      </Routes>
      <Footer/>
    </div>
  );
}

export default App;
