  function boardCreate() {
    const boardTitle = document.getElementById('postTitle');
    const boardContent = document.getElementById('postContent');

    if(boardTitle.value.trim() !== "" && boardContent.value.trim() !== "") {
        alert('작성이 완료되었습니다.');
    } else {
        alert('게시글 제목과 내용을 입력해주세요');
    }
}

function filedel() {
  const filediv = document.getElementById('currentfilsediv');
  const filedelete = document.getElementById('fileDelete');
  const updateFile = document.getElementById('postFiles');
  const updateP = document.getElementById('updateFileP');
  if (confirm("삭제하시겠습니까")) {
    filediv.style.display = 'none';
	filedelete.value = 'true';
	updateFile.disabled = false;
	updateP.style.display = 'none';
	
  } else {
    alert("취소되었습니다")
  }
}