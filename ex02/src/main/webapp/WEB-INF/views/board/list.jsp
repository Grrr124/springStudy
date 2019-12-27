<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp" %>
<!DOCTYPE html>
<html>

<body>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board List Page
                            <button id='regBtn' type="button" class="btn btn-xs pull-right">Register New Board</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" >
                                    <thead>
                                        <tr>
                                            <th>#번호</th>
                                            <th>제목</th>
                                            <th>작성자</th>
                                            <th>작성일</th>
                                            <th>수정일</th>
                                        </tr>
                                    </thead>
                                    
                                    <c:forEach items="${list }" var="board">
                                    <tr>
                                    	<td><c:out value="${board.bno }" /></td>
                                    	<!-- 만일 새창을 통해 보고 싶으면 target='_blank'를 지정, 
                                    	a태그와 form 태그에는 target 속성을 정할 수 있는데 '_blank'는 새로운 창에서 처리 된다. -->
                                    	<td><a href='/board/get?bno=<c:out value="${board.bno}" />'>
                                    	<c:out value="${board.title }" /></a></td>
                                    	<td><c:out value="${board.writer }" /></td>
                                    	<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate }"/></td>
                           				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate }"/></td>
                           			</tr>
                                    </c:forEach>
                                </table>
                                
                        <!-- Modal 추가 -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                        </div>
                                        <div class="modal-body">
                                        처리가 완료되었습니다.
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                            <button type="button" class="btn btn-primary">Save changes</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                           <!-- /.modal -->
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
             </div>
            </div>
           </div>
            <!-- /.row -->            
<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">
$(document).ready(function(){

	var result = '<c:out value="${result}"/>';	

	/* checkModal() 함수는 파라미터에 따라서 모달창을 보여주거나 내용을 수정한 뒤 보이도록 작성,
	새로운 게시글이 작성되는 경우 RedirectAttributes로 게시물의 번호가 전송되므로 이를 이용해서 모달창의 내용을 수정,
	$("#myModal").modal("show");를 호출해서 모달창이 나타난다. */
	checkModal(result);

	/* history API 객체 모델은 브라우징 히스토리를 문서와 문서 상태 목록으로 저장하는데.. 
	뒤로가기, 앞으로가기는 목록 안에서 이동하는 것,
	replaceState() 현재의 히스토리 엔트리를 변경한다
	state 객체에 데이터를 저장 
	사용자의 동작에 따라 현재 히스토리 엔트리의 URL을 업데이트 할때 사용
	첫번째 인자는 저장할 데이터 빈객체, 두,세번째 인자는 바꿀 값(값이나 주소를 추가함)
	pushState도 있는데 주소목록에 이전주소를 두고, 새 주소를 추가한 차이점이다. 
	즉 이전 주소가 남아있기 때문에 뒤로가기로 되돌아 갈수 있고
	replaceState는 이전 주소를 없애고 바꿀 주소를 넣는다. 
	프레임워크 없이 싱글페이지 애플리케이션을 만들 수 있다. */
	history.replaceState({},null,null);
	
	function checkModal(result) {

	/* bno의 값이 널 값이거나 또는 state의 객체에 데이터 값이 null이면 값을 반환한다. 
		즉 첫 브라우저 list창 스택에서는, replaceState를 통하여 모달창이 필요없는 표시가 되어진다.
		history.state통해서 스택에 이미 null이 체크가 되었으므로 register에서 list로 뒤로가기를 눌러도 모달창이 뜨지 않는다.
		반면에 register에서 새로운 게시물 작성했을 경우에는 null 아니므로 paresint(result) > 0 이 실행된다.*/
		if(result === ' ' || history.state){
			return;
			}
		
		if(parseInt(result) > 0) {
			$(".modal-body").html("게시글 " + parseInt(result) + "번이 등록되었습니다.");
			}

			$("#myModal").modal("show");
		}
	/* 게시물의 등록페이지로 이동하는 함수 */
	$("#regBtn").on("click", function(){
		self.location = "/board/register";
		});
});
</script>

</body>

</html>
