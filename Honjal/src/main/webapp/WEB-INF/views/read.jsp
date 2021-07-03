<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<div class="main_box">
      <div class="ud_box">
        <button class="btn_update">수정</button>
        <button class="btn_delete">삭제</button>
      </div>
      <section class="writing_box">
        <h3 class="writing_title">전대 정문 고기 찐맛집 '술돼지' 리뷰 ~!</h3>
        <div class="writing_good">
          <img src="images/good.png" />
          <p>729</p>
        </div>
        <div>
          <p align="right" class="writing_time">2021-06-14 09:45</p>
          <div class="writing_user">
            <p class="writing_name">서녕</p>
            <img src="images/user.png" class="user_img" />
          </div>
        </div>
        <div class="writing_content">
          <p>광주광역시 북구 전남대학교 정문쪽에 위치한 고깃집 술돼지 ,,</p>
          <img src="images/sample_meat.jpg" />
        </div>
        <div class="writing_bottom">
          <div class="good_box">
            <img src="images/good.png" class="good" />
          </div>
          <button>스크랩</button>
        </div>
      </section>
      <section class="comment_box">
        <p>댓글 2</p>
        <hr />
        <table id="tb_comment_list">
          <tr>
            <td width="5%" class="cm_img"><img src="images/user.png" /></td>
            <td width="10%" class="cm_name">혜승</td>
            <td width="70%" class="cm_cm">
              우와ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ
              <span>2021-06-14 09:46</span>
            </td>
            <td width="7%" class="cm_btn">
              <button class="update">수정</button>
            </td>
            <td width="7%" class="cm_btn">
              <button class="delete">삭제</button>
            </td>
          </tr>
          <tr>
            <td width="5%" class="cm_img"><img src="images/user.png" /></td>
            <td width="10%" class="cm_name">창준</td>
            <td width="70%" class="cm_cm">
              와우 <span>2021-06-14 09:46</span>
            </td>
            <td width="7%" class="cm_btn">
              <button class="update">수정</button>
            </td>
            <td width="7%" class="cm_btn">
              <button class="delete">삭제</button>
            </td>
          </tr>
        </table>
        <table id="tb_comment">
          <tr>
            <td width="5%" class="cm_img"><img src="images/user.png" /></td>
            <td width="10%" class="cm_name">서녕</td>
            <td width="70%">
              <textarea placeholder="댓글 내용을 입력하세요"></textarea>
            </td>
            <td width="15%" class="cm_btn">
              <button class="insert">등록</button>
            </td>
          </tr>
        </table>
      </section>
    </div>