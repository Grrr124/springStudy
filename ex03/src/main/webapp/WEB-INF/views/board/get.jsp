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
                           		<div class="form-group">
                           			<label>Bno</label> <input class="form-control" name="bno"  
                           																						 value='<c:out value="${board.bno }"/>' 
                           																						 readonly="readonly">
                           		</div>
                           		
                           		<div class="form-group">
                           			<label>Title</label>
                           			<input class="form-control"  name='title'
                           															value='<c:out value="${board.title }" />'
                           															readonly="readonly">
                           		</div>
                           		
                           		<div class="form-group">
                           			<label>Text area</label> 
                           			<textarea rows="3" class="form-control" name='content' readonly="readonly"><c:out value="${board.content }"/></textarea>
                           		</div>
                           		
                           		<div class="form-group">
                           			<label>Writer</label> <input class="form-control" name='writer' 
                           																							 value='<c:out value="${board.writer }" />' 
                           																							 readonly="readonly">
                           		</div>
<%--                       직접 버튼에 링크를 처리하는 방식
								<button data-oper='modify' 
                           		class="btn btn-default" 
                           		onclick="location.href='/board/modify?bno=<c:out value="${board.bno }"/>'">Modify</button>
                           		<button data-oper='list' 
                           		class="btn btn-info"
                           		onclick="location.href='/board/list'">List</button> --%>
                           		
                           		<button data-oper='modify' class="btn btn-default" >Modify</button>
                           		<button data-oper='list' class="btn btn-info">List</button>
                           		
                           		<form id='operForm' action="/board/modify" method="get">
                           			<input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno }" />'>
                           			<input type='hidden' name="pageNum" value='<c:out value="${cri.pageNum}" />'>
                           			<input type='hidden' name="amount" value='<c:out value="${cri.amount}" />'>
                           			<input type='hidden' name='type' value='<c:out value="${cri.type }" />'>
                           			<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }" />'>
                           		</form>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
             </div>
            </div>
            <!-- /.row -->
       		<div class='row'>
       			<div class="col-lg-12">
       				<div class="panel panel-default">
 <!--       			<div class="panel-heading">
       						<i class="fa fa-comments fa-fw"></i> Reply
       					</div> -->
       					
       					 <div class="panel-heading">
       						<i class="fa fa-comments fa-fw"></i> Reply
       						<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
       					</div>
       					
       					<div class="panel-body">
       						<ul class="chat">
       							<li class="left clearfix" data-rno='12'>
       								<div>
       									<div class="header">
       										<strong class="primary-font">user00</strong>
       										<small class="pull-right text-muted">2020-01-12</small>
       									</div>
       									<p>AAAAAAAAAAAAAAAAAAAAAA</p>
       								</div>
       							</li>
       						</ul>
       					</div>
       					<div class="panel-footer">
       					
       					</div>
         			</div>
       			</div>
       		</div>
<%@include file="../includes/footer.jsp" %>

 <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">REPLY Modal</h4>
                        </div>
                        <div class="modal-body">
                             <div class="form-group">
                             	<label>Reply</label>
                             	<input class="form-control" name='reply' value='New Reply!!!!'>
                             </div>
                             <div class="form-group">
                             	<label>Replyer</label>
                             	<input class="form-control" name='replyer' value='replyer'>
                             </div>
                             <div class="form-group">
                             	<label>Reply Date</label>
                             	<input class="form-control" name='replyDate' value=''>
                             </div>
                        </div>
                        <div class="modal-footer">
                            <button id="modalModBtn"  type="button" class="btn btn-warning" >Modify</button>
                            <button id="modalRemoveBtn"  type="button" class="btn btn-danger">Remove</button>
                            <button id="modalRegisterBtn"  type="button" class="btn btn-primary">Register</button>
                            <button id="modalCloseBtn"  type="button" class="btn btn-default">Close</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
         <!-- /.modal -->
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script type="text/javascript">
//http://localhost:8090/board/get?bno=1
$(document).ready(function(){

	console.log("=================");
	console.log("JS TEST")

	var bnoValue = '<c:out value="${board.bno}"/>';
 	var replyUL = $(".chat");

	showList(1);

	function showList(page) {

		replyService.getList({bno:bnoValue, page: page || 1}, function(replyCnt, list) {

			console.log("replyCnt: " + replyCnt);
			console.log("list: " + list);
			console.log(list);
			console.log("page: " + page);
			//사용자가 modalRegisterBtn을 이용해 댓글을 추가하면 메서드에 있는 showList(-1)로 다시 호출하여 다시 뿌려주는 방식.
			//if page가 -1이라면, 다시 전체 댓글의 숫자를 파악하는 pageNum = Math.ceil(replyCnt/10.0); 을 이용해서
			//showList 파라미터로 리턴시킨다.
			if(page == -1){
				pageNum = Math.ceil(replyCnt/10.0);
				showList(pageNum);
				return;
				}
			
			var str="";
			
		 	if(list == null || list.length == 0) {
				return;
			 	}
		 	//.rno을 쓸수 있는 이유는 
		 	//http://localhost:8090/replies/pages/bno/page에서 json타입의 데이터를 가져오기 때문이다.
		 		for(var i = 0, len = list.length || 0; i < len; i++) { 
					str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
					str +="<div><div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
					str +="<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
					str +="<p>"+list[i].reply+"</p></div></li>"
				}

			replyUL.html(str)
			console.log(bnoValue);
			showReplyPage(replyCnt);
			}); //end function
		}	//end showList 

		var modal = $(".modal");
		var modalInputReply = modal.find("input[name='reply']");
		var modalInputReplyer = modal.find("input[name='replyer']");
		var modalInputReplyDate = modal.find("input[name='replyDate']");

		var modalModBtn = $("#modalModBtn");
		var modalRemoveBtn = $("#modalRemoveBtn");
		var modalRegisterBtn = $("#modalRegisterBtn");
		var modalCloseBtn = $("#modalCloseBtn");
		$("#addReplyBtn").on("click", function(e){

			//modal안 input을 찾아서 value값의 ""을 부여함
			modal.find("input").val("");
			//replyDate div를 숨김
			modalInputReplyDate.closest("div").hide();
			//input id가 close가 아닐시 버튼을 숨김
			modal.find("button[id !='modalCloseBtn']").hide();
			//다시 Register 보여줌
			modalRegisterBtn.show();

			//modal의 id를 가진 modal을 불러오는데 옵션으로 show의 의미는 초기화할 때 모달을 보여준다.
			$(".modal").modal("show");

			});

		modalRegisterBtn.on("click", function(e){

			var reply = {
						reply: modalInputReply.val(),
						replyer:modalInputReplyer.val(),
						bno:bnoValue
					};
			//modalInput의 내용들을 함수 add(reply객체에 담아서 ajax로 전송해버린다. result는 callback)
			replyService.add(reply, function(result){
				alert(result);

			//modal창을 닫고 showList로 다시 리스트를 뿌려준다.
				modal.find("input").val("");
				modal.modal("hide");
				//showList(1);
				showList(-1);
				});
			}); //end modalRegisterBtn

		//ul태그의 chat 클래스를 이용해서 이벤트를 걸고 실제 이벤트의 대상인 li를 태그가 되도록 세팅
		//data-rno='"+list[i].rno+"'에 담긴 rno를 jQuery의 .data를 통해서 저장된 데이터를 가져온다. 
		//(key)값이 없으면 JSON형식으로 리턴.. HTML5 경우 data-로 저장한경우엔 key를 이용하여 data를 읽어옴
		//attr("readonly", "readonly"); 선택한 요소에 속성을 추가, displayTime로 변환된 요소에 readonly 속성을 추가하고 속성의 값은 readonly로 추가
		//.data("rno", reply.rno); rno에 get 함수의 json 데이터 콜백데이터인 reply.rno을 밸류값으로 가져와서 data "rno" 키값에 저장한다.
		$(".chat").on("click", "li", function(e){
			
			var rno = $(this).data("rno");

			replyService.get(rno, function(reply){

				modalInputReply.val(reply.reply);
				modalInputReplyer.val(reply.replyer);
				modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
				modal.data("rno", reply.rno);

				modal.find("button[id !='modalCloseBtn']").hide();
				modalModBtn.show();
				modalRemoveBtn.show();

				$(".modal").modal("show");
				
				});
			console.log(rno);
			});
		
		modalModBtn.on("click", function(e){
			var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};

			replyService.update(reply, function(result){

				alert(result);
				modal.modal("hide");
				showList(pageNum);
				});

			});
		
		modalRemoveBtn.on("click", function(e){
			var rno = modal.data("rno");
			replyService.remove(rno, function(result){

				alert(result);
				modal.modal("hide");
				showList(pageNum);
				});
			});

		modalCloseBtn.on("click", function(e){
			modal.modal("hide");
			showList(pageNum);
			});


		//댓글 페이지
		var pageNum = 1;
		var replyPageFooter = $(".panel-footer");

		function showReplyPage(replyCnt) {

			var endNum = Math.ceil(pageNum / 10.0) * 10;
			var startNum = endNum - 9;

			var prev = startNum != 1;
			var next = false;

			if(endNum * 10 >= replyCnt) {
				endNum = Math.ceil(replyCnt / 10.0);
				}

			if(endNum * 10 < replyCnt) {
				next = true;
				}

			var str = "<ul class='pagination pull-right'>";

			if(prev) {
				str+="<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'>Previous</a></li>";
				}

			for(var i = startNum ; i <= endNum; i++){

				var active = pageNum == i? "active":"";

				str+="<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
				}

			if(next){
				str+="<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
				}

				str += "</ul></div>"

				console.log(str);

				replyPageFooter.html(str);
			}
		//페이지 번호 클릭시 새로운 댓글 가져오기
		//댓글의 페이지번호는 a태그 내의 존재하므로 a태그 기본동작을 제한하고
		//댓글 페이지 번호를 변경 후 가져온다.
		replyPageFooter.on("click", "li a", function(e){
			e.preventDefault();
			console.log("page click");

			var targetPageNum = $(this).attr("href");

			console.log("targetPageNum: " + targetPageNum);
			pageNum = targetPageNum;

			showList(pageNum);
			});
/* 	replyService.getList( 
		{bno:bnoValue, page:1} //function getList('param', callback, error)에서 param값의 해당하는 부분
		, 
		function(list) { //callback의 해당하는 부분
			for(var i = 0, len = list.length || 0; i < len; i++) {
				console.log(list[i]);
			}
		}); */
	
	//for replyService add test
/*  	replyService.add( 
		{reply:"JS TEST", replyer:"tester", bno:bnoValue} //function add(reply, callback, error)에서 reply의 해당하는 부분
		,
		function(result){ //callback의 해당하는 부분
			alert("RESULT: " + result);
		});   */

/* 	replyService.remove(12, function(count){
		console.log(count);

		if(count === "success") {
			alert("REMOVED");
			}
		}, function(er) {
			alert("ERROR.....")
		});

	replyService.update({
		rno : 22,
		bno : bnoValue,
		reply : "Modified Reply...."},
		function(result) {
			alert("수정 완료...");
		}); */

/* 	replyService.get(10, function(data){
		console.log(data);
		}); */
	
});
</script>

<script type="text/javascript">

$(document).ready(function() {

	var operForm = $("#operForm");

	$("button[data-oper='modify']").on("click", function(e){

		operForm.attr("action", "/board/modify").submit();
		
		});

	$("button[data-oper='list']").on("click", function(e) {

		operForm.find("#bno").remove();
		operForm.attr("action", "/board/list")
		operForm.submit();
		
		});
});

</script>            
</body>
</html>