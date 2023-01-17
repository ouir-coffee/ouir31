import axios from "axios";
import React from "react";
import { useCallback } from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../etc/Textarea.scss";
import "../etc/Input.scss";
import "./NoticeWrite.scss";
import Button from "../etc/Button";
import "../etc/Button.scss";

const NoticeWrite = () => {
    const navigate = useNavigate();
    
    const aid = sessionStorage.getItem("aid");

    const [data, setData] = useState({
        ntitle: "",
        ncontents: ""
    });

    //데이터와 파일을 담을 폼
    let formData = new FormData();
    const { ntitle, ncontents } = data;

    //공지 작성 내용 전송
    const noticeWrite = useCallback(
        (e) => {
            e.preventDafault();
            formData.append(
                "data",
                new Blob([JSON.stringify(data)], { type: "application/json" })
            );
            axios
                .get("noticeWrite", formData, {
                    headers: { "Content-Type": "multipart/form-data" }
                })
                .then((res) => {
                    if (res.data === "ok") {
                        alert("공지가 등록되었습니다.");
                        sessionStorage.removeItem("pageNum");
                        navigate("/notice");
                    } else {
                        alert("예기치 않은 오류가 발생하였습니다. 다시 시도해주세요.");
                    }
                })
                .catch((error) => console.log(error));
        }, [data]
    );
    const onChange = useCallback(
        (e) => {
            const dataObject = {
                ...data, [e.target.name]: e.target.value
            };
            setData(dataObject)
        }, [data]
    );

    //파일 선택시 formdata에 파일 목록 추가(파일 여러개 넣기)
    const onFileChange = useCallback(
        (e) => {
            const files = e.target.files;
            for (let i = 0; i < files.length; i++) {
                formData.append("files", files[i]);
            }
        }, [formData]
    );

    return (
        <div className="Main">
            <form className="Content" onSubmit={noticeWrite}>
                <h2 className="main_title">공지 작성해보지아</h2>
                <input
                    className="Input"
                    name="ntitle"
                    value={ntitle}
                    placeholder="제목을 입력하세요."
                    onChange={onChange}
                    autoFocus
                    required
                />
                <textarea
                    className="Textarea"
                    name="ncontents"
                    onChange={onChange}
                    placeholder="내용을 입력하세요."
                    value={ncontents}
                ></textarea>
                <input type="file" name="files" onChange={onFileChange} multiple />
                <div className="Buttons">
                    <Button wsize="s-10" color="gray" outline onClick={() => navigate(-1)}>
                        뒤로
                    </Button>
                    <Button type="submit" color="ouir1" wsize="s-10">
                        작성
                    </Button>
                </div>
            </form>
        </div>
    )
}


export default NoticeWrite;