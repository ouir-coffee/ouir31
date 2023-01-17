import axios from "axios";
import React from "react";
import { useCallback } from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../etc/Textarea.scss";
import "../etc/Input.scss";
import "./VocWrite.scss";
import Button from "../etc/Button";
import "../etc/Button.scss";

const VocWrite = () => {
    const navigate = useNavigate();
    
    const aid = sessionStorage.getItem("aid");
    const uid = sessionStorage.getItem("uid");

    const [data, setData] = useState({
        voctitle : "",
        voccontents: ""
    });

    //데이터와 파일을 담을 폼
    let formData = new FormData();
    const { voctitle, voccontents } = data;

    //문의 작성 내용 전송
    const vocWrite = useCallback(
        (e) => {
            e.preventDafault();
            formData.append(
                "data",
                new Blob([JSON.stringify(data)], { type: "application/json" })
            );
            axios
                .get("vocWrite", formData, {
                    headers: { "Content-Type": "multipart/form-data" }
                })
                .then((res) => {
                    if (res.data === "ok") {
                        alert("문의가 등록되었습니다.");
                        sessionStorage.removeItem("pageNum");
                        navigate("/voc");
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
        <div className="vocMain">
            <form className="Content" onSubmit={vocWrite}>
                <h2 className="main_title">문의사항</h2>
                <input
                    className="Input"
                    name="voctitle"
                    value={voctitle}
                    placeholder="제목을 입력해주세요."
                    onChange={onChange}
                    autoFocus
                    required
                />
                <textarea
                    className="Textarea"
                    name="voccontents"
                    onChange={onChange}
                    placeholder="문의 내용을 작성해주세요."
                    value={voccontents}
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


export default VocWrite;