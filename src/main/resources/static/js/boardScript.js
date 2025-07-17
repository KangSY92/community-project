  function boardCreate() {
    const boardTitle = document.getElementById('postTitle');
    const boardContent = document.getElementById('postContent');

    if(boardTitle.value.trim() !== "" && boardContent.value.trim() !== "") {
        alert('작성이 완료되었습니다.');
    } else {
        alert('게시글 제목과 내용을 입력해주세요');
    }
}