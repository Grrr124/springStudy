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
                                    	<td><c:out value="${board.title }" /></td>
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

	checkModal(result);
	/* checkModal() 함수는 파라미터에 따라서 모달창을 보여주거나 내용을 수정한 뒤 보이도록 작성,
	새로운 게시글이 작성되는 경우 RedirectAttributes로 게시물의 번호가 전송되므로 이를 이용해서 모달창의 내용을 수정,
	$("#myModal").modal("show");를 호출해서 모달창이 나타난다. */
	function checkModal(result) {

		if(result === ' '){
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
