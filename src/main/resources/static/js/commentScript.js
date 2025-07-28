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
	const commentDel = document.getElementsByClassName('commentDelete');
	if (confirm("삭제하시겠습니까")) {
		alert("삭제되었습니다")
		return true;
	} else {
		alert("취소되었습니다")
		commentDel.type = 'button'
		return false;
	}
}

function commentedit(button) {
	const commentDiv = button.closest('.comment')
	const commentcontent = commentDiv.querySelector('.comment-content')
	const commentedit = commentDiv.querySelector('.comment-Edit')
	commentcontent.style.display = 'none';
	commentedit.style.display = 'block';
}

function commentEdetCancel(button) {
	const commentDiv = button.closest('.comment')
	const commentcontent = commentDiv.querySelector('.comment-content')
	const commentedit = commentDiv.querySelector('.comment-Edit')
	commentcontent.style.display = 'block';
	commentedit.style.display = 'none';
}

function commentEditUpdate(button) {

	const commentDiv = button.closest('.comment')
	const edittextarea = commentDiv.querySelector('.editTextarea');

	if (edittextarea.value.trim() === "") {
		alert('댓글을 입력해 주세요');
	} else {
		button.type = 'submit';
		const commentcontent = commentDiv.querySelector('.comment-content')
		const commentedit = commentDiv.querySelector('.comment-Edit')
		commentcontent.style.display = 'block';
		commentedit.style.display = 'none';

		alert('수정되었습니다.');
	}

}