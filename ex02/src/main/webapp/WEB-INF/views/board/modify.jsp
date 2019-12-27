<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board Read Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        	<form role="form" action="/board/modify" method="post">
                           		<div class="form-group">
                           			<label>Bno</label> <input class="form-control" name="bno"  
                           																						 value='<c:out value="${board.bno }"/>' 
                           																						 readonly="readonly">
                           		</div>
                           		
                           		<div class="form-group">
                           			<label>Title</label>
                           			<input class="form-control"  name='title'
                           															value='<c:out value="${board.title }" />'>
                           		</div>
                           		
                           		<div class="form-group">
                           			<label>Text area</label> 
                           			<textarea rows="3" class="form-control" name='content' ><c:out value="${board.content }"/></textarea>
                           		</div>
                           		
                           		<div class="form-group">
                           			<label>Writer</label> <input class="form-control" name='writer' 
                           																							 value='<c:out value="${board.writer }" />' 
                           																							 readonly="readonly">
                           		</div>
                           		
                           		<div class="form-group">
                           			<label>RegDate</label>
                           			<input class="form-control" name='regDate' value='<fmt:formatDate value="${board.regdate }" 
                           																					 pattern="yyyy/MM/dd" />' 
                           																					 readonly="readonly">
                           		</div>
                           		
                           		<div class="form-group">
                           			<label>Update Date</label>
                           			<input class="form-control" name='updateDate' value='<fmt:formatDate value="${board.updateDate }" 
                           																					 pattern="yyyy/MM/dd" />' 
                           																					 readonly="readonly">
                           		</div>
                           		<button type="submit" data-oper='modify' class="btn btn-default" >Modify</button>
                           		<button type="submit" data-oper='remove' class="btn btn-danger" >Remove</button>
                           		<button type="submit" data-oper='list' class="btn btn-info">List</button>
                           		
                           		</form>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
             </div>
            </div>
            <!-- /.row -->
<%@include file="../includes/footer.jsp" %>       

<script type="text/javascript">
$(document).ready(function() {

	var formObj = $("form");

	$('button').on("click", function(e) {

		//고유 동작을 중단시킴
		e.preventDefault();

		var operation = $(this).data("oper");

		console.log(operation);
		//remove 버튼을 클릭하면 form 태그의 action 값이 /borad/remove가 되고 데이터들이 전송 된다.
		if(operation === 'remove') {

			formObj.attr("action", "/board/remove");	

			}else if(operation === 'list'){
				//move to list
				self.location="/board/list";
				return;
			}
			formObj.submit();	
	});
});

</script>     
</body>
</html>