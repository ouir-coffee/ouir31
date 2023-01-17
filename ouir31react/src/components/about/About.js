import React from "react";
import "./About.scss";

const About = () => {
    return (
        <div className="bgimg" style={{ backgroundImage: "url(/img/tea11.png)" }}>
            <div className="aboutMain">
                <div className="Content">
                    <h2 className="main_title">위어커피</h2>
                    <p>
                        we와 your가 합쳐져 우리가 만들어 가는 너의 카페입니다!<br></br>모두모두 환영합니다!
                    </p>
                </div>
            </div>
        </div>
    )
};

export default About;