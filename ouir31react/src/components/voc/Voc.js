import React, { useCallback, useEffect,useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Voc.scss";
import moment from "moment/moment";
import axios from "axios";
import TableRow from "../etc/TableRow";
import TableColumn from "../etc/TableColumn";
import Table from "../etc/Table";
import Paging from "../etc/Paging";
import Button from "../etc/Button";
import "../etc/Button.scss";



const date = (date) => moment(date).format("YYYY-MM-DD HH:mm:ss");

const Voc = () => {
    const navigate = useNavigate();

    // const uid = sessionStorage.getItem("uid");
    // const aid = sessionStorage.getItem("aid");

    let pnum = sessionStorage.getItem("pageNum");
    const [voc, setVoc] = useState({});
    const [page, setPage] = useState({
        totalPage: 0,
        pageNum: 1
    });

    //공지 목록을 서버에서 가져오기
    const getNoticeList = (pnum) => {
        axios
            .get("/voc", { param: { pageNum: pnum } })
            .then((res) => {
                const { nList, totalPage, pageNum } = res.data;
                setPage({ totalPage: totalPage, pageNum: pageNum });
                setVoc(nList);
                sessionStorage.setItem("pageNum", pageNum);
            })
            .catch((error) => console.log(error));
    }

    const getVoc = useCallback((vocno) => {
        localStorage.setItem("vocno", vocno);
        navigate("/voc");
    }, []);

    //notice 페이지에 서버에서 가져온 공지 목록 보여주기
    useEffect(() => {
        pnum !== null ? getNoticeList(pnum) : getNoticeList(1);
    }, []);

    //공지 목록 작성
    let list = null;
    if (voc.length === 0) {
        list = (
            <TableRow key={0}>
                <TableColumn span={4}>게시글이 존재하지 않습니다.</TableColumn>
            </TableRow>
        );
    } else {
        list = Object.values(voc).map((item) => (
            <TableRow key={item.vocno}>
                <TableColumn wd="w-10">{item.vocno}</TableColumn>
                <TableColumn wd="w-40">
                    <div onClick={() => getVoc(item.vocno)}>{item.voctitle}</div>
                </TableColumn>
                <TableColumn wd="w-20">{item.vocuid}</TableColumn>
                <TableColumn wd="w-30">{date(item.rdate)}</TableColumn>
            </TableRow>
        ));
    }

    //공지 작성
    const vocWrite = () => {
        navigate("/voc/write");
    }

    return (
        <div className="vocMain">
            <div className="Content">
                <h2 className="main_title">고객의 소리</h2>
                <Table hName={["글번호", "제목", "작성자", "작성일"]}>{list}</Table>
                <Paging page={page} getNoticeList={getNoticeList} />
            </div>
            <div className="Buttons">
                <Button color="ouir1" wsize="s-10" onClick={() => navigate("/voc/write")}>
                    문의등록
                </Button>
            </div>
        </div>
    );
}

export default Voc;