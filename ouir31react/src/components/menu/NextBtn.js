import React from "react";
import "./SlideBtn.scss";

const NextBtn = (props) => {
  const nextImg = "img/next-ico.png";

  return (
    <div className={"slide_btn next_btn " + props.className}>
      <img src={nextImg} alt="next" onClick={props.onClick} />
    </div>
  );
};

export default NextBtn;
