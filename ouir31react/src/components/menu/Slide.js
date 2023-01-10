import React, { useState } from "react";
import Item from "./Item";
import NextBtn from "./NextBtn";
import PrevBtn from "./PrevBtn";
import "./Slide.scss";

const Slide = (props) => {
  const [slide, setSlide] = useState(0);

  const prev = () => {
    if (slide !== 0) {
      setSlide(slide + 350);
    }
  };

  const next = () => {
    if (slide !== -1050) {
      setSlide(slide - 350);
    }
  };

  return (
    <div className="menu_slider">
      <div
        className="best_item"
        style={{ transform: `translateX(${slide}px)` }}
      >
        <Item title="아메리카노" />
        <Item title="1" />
        <Item title="2" />
        <Item title="3" />
        <Item title="4" />
        <Item title="5" />
      </div>
      <PrevBtn className={slide === 0 ? "disabled" : ""} onClick={prev} />
      <NextBtn className={slide === -1050 ? "disabled" : ""} onClick={next} />
    </div>
  );
};

export default Slide;
