import React from "react";
import "./SlideBtn.scss";

const NextBtn = () => {
  const nextImg = "img/next-ico.png";

  return (
    <div className="slide_btn next_btn">
      <img src={nextImg} alt="next" />
    </div>
  );
};

export default NextBtn;
