package com.ouir.ouir31.util;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class PagingUtil {
    private int totalPage; // 전체 데이터의 개수(게시글 개수)
    private int pageNum; // 현재 보이고 있는 페이지의 번호
    private int pageCnt; // 보여질 페이지 번호의 개수
    private String listName; //게시판이 여러개일 경우 게시판을 구분하는 url 지정

    //페이징용 html 코드를 만드는 메소드
    public String makePaging() {
        String pageHtml = null;
        StringBuffer sb = new StringBuffer();


        // 1 . 현재 페이지가 속한 그룹 구하기
        // 한 페이지에 보일 페이지 번호가 5개 일 때
        // [이전] 6 7 8 9 10 [다음] - 6,7,8,9,10번이 한 그룹
        int curGroup = (pageNum % pageCnt) > 0 ?
                pageNum / pageCnt + 1 :
                pageNum / pageCnt;
        /* 예를들어 넘이 7이고 cnt는 5 고정(cnt를 5로 정했으니까)
         * 7%5 = 2나까 값은 true 나눈 결과에 +1해서 2페이지

         * 넘이 10일경우 10%5 = 0이니까 false 결과를 그냥 나누기(+안함)*/


        // 2. 현재 보이는 페이지 그룹의 시작 번호 구하기
        // 위의 예일 경우 6을 구함 (이전 페이지는 5)

        int start = (curGroup * pageCnt) - (pageCnt - 1);
        /* 그룹이 2일때 cnt5 5이고 2*5 = 15 - (5-1) = 11
         * 그룹2의 시작번호 = 11 */

        // 3. 현재 보이는 페이지 그룹의 마지막 번호 구하기
        // 위의 예일 경우 10을 구함 (다음페이지는 11)

        int end = (curGroup * pageCnt) >= totalPage ?
                totalPage : curGroup * pageCnt;
        /* 토탈페이지 미만이면 end는 totalpage
         * 토탈보다 크면 그룹*cnt(5)
         * 2*5 = 10번이 2번그룹의 마지막 번호 */

        // paging용 HTML 태그 작성
        // 1. 이전 버튼 처리

        if (start != 1) { //시작버튼이 1이 아닐경우(첫페이지에서는 [이전] 출력안함)
            sb.append("<a class='pno' href='/" + listName + "paneNum=" + (start - 1) + "'>");
            sb.append("&nbsp;이전&nbsp;</a>");
        }
        // <a class='pno' href='/?pageNum=5'> 이전 </a>

        // 2. 그룹 내 페이지번호 처리
        for (int i = start; i <= end; i++) {

            // 보여질 페이지 번호 처리(링크가 없는 페이지번호)
            if (pageNum == i) {
                sb.append("<font class='pno' style='color:red'>");
                sb.append("&nbsp;" + i + "&nbsp;</font>");
                // <font class='pno' style='color:red;'> 현재 페이지 </font>
            } else { // 링크가 붙는 페이지 번호
                sb.append("<a class='pno' href='/" + listName + "pageNum=" + i + "'>");
                sb.append("&nbsp;" + i + "&nbsp</a>");
                // <a class='pno' href='/?pageNum=1'> 페이지이동 링크 </a>
            }
        } // for 끝
        
        // 다음 버튼 처리

        if(end != totalPage){ //마지막번호가 토탈페이지랑 다를때
            sb.append("<a class='pno' href='/" + listName + "pageNum=" + (end + 1) + "'>");
            sb.append("&nbsp;다음&nbsp;</a>");
            // <a class='pno' href='/?pageNum=11'> 다음 </a>
        }

        //StringBuffer에서 작성한 문장을 문자열로 변환
        pageHtml = sb.toString();


        return pageHtml;
    } // 메소드 끝


} //클래스 끝
