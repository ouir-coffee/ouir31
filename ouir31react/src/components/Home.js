import React from "react";
import "./Home.scss";

const Home = () => {
  const uid = sessionStorage.getItem("uid");
  

  return (
    <div className="Home">
      <div className="Content">
        <div className="HomeMain"><img src="/img/A3.png"></img></div>
      </div>
    </div>
  );
};

export default Home;