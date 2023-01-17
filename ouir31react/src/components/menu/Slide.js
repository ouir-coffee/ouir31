import React, { useState } from "react";
import Item from "./Item";
import NextBtn from "./NextBtn";
import PrevBtn from "./PrevBtn";
import "./Slide.scss";

const Slide = (props) => {
  const { best } = props;
  const length = best.length;
  const [slide, setSlide] = useState(0);
  const [count, setCount] = useState(3);

  const prev = () => {
    setSlide(slide + 350);
    setCount(count - 1);
  };

  const next = () => {
    setSlide(slide - 350);
    setCount(count + 1);
  };

  const [active, setActive] = useState(-1);

  const itemList = Object.values(best).map((value, index) => {
    const imgPath = "upload/" + best[index].mfList.mfsysname;
    return (
      <Item
        key={index}
        imgUrl={imgPath}
        title={best[index].mitem}
        title2={best[index].mname}
        contents={best[index].mcontents}
        index={index}
        active={active}
        onClick={() => {
          active === index ? setActive(-1) : setActive(index);
        }}
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
      <NextBtn className={length <= count ? "disabled" : ""} onClick={next} />
    </div>
  );
};

export default Slide;
