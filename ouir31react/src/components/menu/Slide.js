import axios from "axios";
import React, { useState, useEffect } from "react";
import Item from "./Item";
import NextBtn from "./NextBtn";
import PrevBtn from "./PrevBtn";
import "./Slide.scss";

const Slide = (best, ...props) => {
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

  const Best = best.best;
  const itemList = Object.values(Best).map((value, index) => {
    const imgPath = "upload/" + Best[index].mfList.mfsysname;
    return (
      <Item
        key={index}
        imgUrl={imgPath}
        title={Best[index].mitem}
        title2={Best[index].mname}
        contents={Best[index].mcontents}
      />
    );
  });

  return (
    <div className="menu_slider">
      <div
        className="best_item"
        style={{ transform: `translateX(${slide}px)` }}
      >
        {itemList}
      </div>
      <PrevBtn className={slide === 0 ? "disabled" : ""} onClick={prev} />
      <NextBtn className={slide === -1050 ? "disabled" : ""} onClick={next} />
    </div>
  );
};

export default Slide;
