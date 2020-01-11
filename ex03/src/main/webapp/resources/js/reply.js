/**
 * xhr : XMLHttpRequest 오브젝트를 생성할 때 사용하는 함수
 * XMLHttpRequest : js 객체로 서버로부터 다양한 리소스를 가져오는 요청을 만들어주는 기능
 * JSON.stringify : JavaScript 값이나 객체를 JSON 문자열로 변환
 * success(요청 성공시) error(xhr,status,error) : 요청이 실패했을 때 실행되는 함수.. 세가지 파라미터까지 사용 가능하다. 
 */
console.log("Reply Module........");

var replyService = (function(){
	function add(reply, callback, error){
		console.log("add reply...............")
	
	$.ajax({
		type : 'post',
		url : '/replies/new',
		data : JSON.stringify(reply),
		contentType : "application/json; charset=utf-8",
		success : function(result, status, xhr) {
			if (callback) {
				callback(result);
			} //end if
		},//end success
		error : function(xhr, status, er) {
			if(error) {
				error(er);
			} //end if
		} //end error
	}) // end ajax
} //end add
	
	//getList는 param이라는 객체를 통해서 파라미터를 전달받아 JSON 목록을 호출
	//JSON 형태가 필요하므로 URL 호출시 확장자를 .json으로 요구
	function getList(param, callback, error)	{
		
		var bno = param.bno;
		//자바스크립트에서 || 논리연산자는 단순히 참,거짓을 판단하는 연산자가 아니라
		//피연산자 중 하나를 반환한다. 0이 아닌 정수는 true로 판단한다. 
		//Boolean으로 암묵적 형 변환이 이루어지기 때문이다.
		//이런 논리연산자의 특성중에 매개변수 디폴트 할당이 많이 쓰이는데 바로 아래와 같은 조건이다.
		//getList 함수를 호출할 때 (?) 매개변수를 전달하지 않는다면 param.page는 undefinde가 되고
		//이건 false로 판단되어 뒤의 값인 1이 기본으로 반환된다.
		//var AAA = { };
		//if (AAA) {
		//console.log("AAA");
		//}	객체가 제대로 할당되어 있으면 true로 형변환이 되고, 할당이 안되면 undefinde가 들어가며, false로 형변환 된다.
		var page = param.page || 1;
		
		//jQuery의 getJSON을 통해서 불러온 JSON형태의 URL을 data 함수에 담아서 콜백 시킨다.
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json", function(data){ 
			if(callback) {
				callback(data);
			} //end if
		}).fail(function(xhr, status, err) {
		if (error) {
			error();
		} //end if
	}); //end fail
}//end getList
	
	
	//삭제
	function remove(rno, callback, error) {
		$.ajax({
			type : 'delete',
			url : '/replies/' + rno,
			success : function(deleteResult, status, xhr) {
				if(callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if(error) {
					error(er);
				}
			}
		}); //end ajax
	} // end remove
	
	//업데이트
	function update(reply, callback, error) {
		
		console.log("RNO : " + reply.rno);
		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if(callback) {
					callback(result);
				}
			}, error : function(xhr, status, er) {
				if(error) {
					error(er);
				}
			}
		});
	}
	
	//조회
	function get(rno, callback, error) {
		$.get("/replies/" + rno + ".json", function(result) {
			
			if(callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err) {
			if(error) {
				error(err);
			}
		});
	}
 return {
		add : add,
		getList : getList,
		remove : remove,
		update : update,
		get : get
	};
})(); //end replyService