<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<div class="main_box read_box">

      <div class="ud_box">
        <button class="btn_update">수정</button>
        <button class="btn_delete">삭제</button>
      </div>
      
      <section class="content_box">
        <h3 class="content_title">${R.content_title}</h3>
        <div class="content_good_box">
          <img src="${rootPath}/static/images/good.png" />
          <p>${R.content_good}</p>
        </div>
        <div class="content_w_box">
          <p class="content_date">${R.content_date}</p>
          <div class="content_member">
            <p>${R.member_nname}</p>
            <img src="${rootPath}/static/images/user.png" class="member_img" />
          </div>
        </div>
        <div class="content_text">
          <p>${R.content_text}</p>
        </div>
        <div class="content_bottom">
          <img src="${rootPath}/static/images/good.png" class="good" />
          <button>스크랩</button>
        </div>
      </section>
      
      <section class="comment_box">
        <p>댓글 2</p>
        <hr />
        <table id="tb_comment_list">
          <tr>
            <td width="5%" class="cm_img"><img src="${rootPath}/static/images/user.png" /></td>
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
            <td width="5%" class="cm_img"><img src="${rootPath}/static/images/user.png" /></td>
            <td width="10%" class="cm_name">창준</td>
            <td width="70%" class="cm_cm">와우 <span>2021-06-14 09:46</span></td>
            <td width="7%" class="cm_btn">
              <button>수정</button>
            </td>
            <td width="7%" class="cm_btn">
              <button>삭제</button>
            </td>
          </tr>
        </table>
        <table id="tb_comment">
          <tr>
            <td width="5%" class="cm_img"><img src="${rootPath}/static/images/user.png" /></td>
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

<script>

let update_button = document.querySelector(".btn_update")
let delete_button = document.querySelector(".btn_delete")

update_button.addEventListener("click",()=>{
	location.href = "${rootPath}/tip/update" + "?content_num=${R.content_num}"
})

delete_button.addEventListener("click",()=>{
	if(confirm("글을 삭제하시겠습니까?")) {
		
		location.replace("${rootPath}/delete"
				+ "?g_seq=${GALLERY.g_seq}")
		
	}
})

</script>