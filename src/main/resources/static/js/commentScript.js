//댓글 내용 빈값이면 서브밋 막기
function buttonbtn() {
	const commentbtn = document.getElementById('commentBtn');
	const commentcontent = document.getElementById('commentContent');
	if (commentcontent.value.trim() === "") {
		commentbtn.type = 'button';
		alert('댓글을 입력해 주세요');
	} else {
		commentbtn.type = 'submit';
	}
}

function commentdel() {
	document.getElementsByClassName
  const commentDel = document.getElementsByClassName('commentDelete')
  if(confirm("삭제하시겠습니까")) {
    alert("삭제되었습니다")
    commentDel.type = 'submit'
  } else {
    alert("취소되었습니다")
  }
}