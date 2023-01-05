import React from "react";
import "./SlideBtn.scss";

const PrevBtn = (...props) => {
  const prevImg = "img/prev-ico.png";
  return (
    <div className="slide_btn prev_btn">
      <img src={prevImg} alt="prev" />
    </div>
  );
};

export default PrevBtn;
