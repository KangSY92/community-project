// DOM 요소 로드 후 실행
document.addEventListener('DOMContentLoaded', function() {
	// 모달 관련 요소
	const loginBtn = document.getElementById('loginBtn');
	const loginModal = document.getElementById('loginModal');
	const showLoginBtn = document.getElementById('showLoginBtn');
	const closeButtons = document.querySelectorAll('.close');

	// 로그인 모달 표시
	if (loginBtn) {
		loginBtn.addEventListener('click', function(e) {
			e.preventDefault();
			loginModal.style.display = 'flex';
		});
	}

	// 회원가입 페이지에서 로그인 버튼
	if (showLoginBtn) {
		showLoginBtn.addEventListener('click', function(e) {
			e.preventDefault();
			loginModal.style.display = 'flex';
		});
	}

	// 모달 닫기 버튼
	closeButtons.forEach(function(button) {
		button.addEventListener('click', function() {
			const modal = button.closest('.modal');
			if (modal) {
				modal.style.display = 'none';
			}
		});
	});

	// 모달 외부 클릭시 닫기
	window.addEventListener('click', function(e) {
		if (e.target.classList.contains('modal')) {
			e.target.style.display = 'none';
		}
	});

	// 프로필 이미지 미리보기
	const profileImage = document.getElementById('profileImage');
	const uploadProfileBtn = document.getElementById('uploadProfileBtn');
	if (profileImage && uploadProfileBtn) {
		uploadProfileBtn.addEventListener('click', function() {
			profileImage.click();
		});

		profileImage.addEventListener('change', function(e) {
			const file = e.target.files[0];
			if (file) {
				const reader = new FileReader();
				reader.onload = function(e) {
					const profilePreview = document.getElementById('profilePreview');
					if (profilePreview) {
						profilePreview.querySelector('img').src = e.target.result;
					}
				};
				reader.readAsDataURL(file);
			}
		});
	}

	// 게시글 삭제
	const deletePostBtn = document.getElementById('deletePostBtn');
	const deleteModal = document.getElementById('deleteModal');
	const cancelDelete = document.getElementById('cancelDelete');

	if (deletePostBtn && deleteModal) {
		deletePostBtn.addEventListener('click', function() {
			deleteModal.style.display = 'flex';
		});

		if (cancelDelete) {
			cancelDelete.addEventListener('click', function() {
				deleteModal.style.display = 'none';
			});
		}

	}

	// 파일 업로드 드롭존
	const uploadDropzone = document.getElementById('uploadDropzone');
	const fileInput = document.getElementById('fileInput');
	const uploadFiles = document.getElementById('uploadFiles');
	const fileCount = document.getElementById('fileCount');
	const uploadButton = document.getElementById('uploadButton');
	const cancelUpload = document.getElementById('cancelUpload');

	if (uploadDropzone && fileInput) {
		uploadDropzone.addEventListener('click', function() {
			fileInput.click();
		});

		uploadDropzone.addEventListener('dragover', function(e) {
			e.preventDefault();
			uploadDropzone.classList.add('active');
		});

		uploadDropzone.addEventListener('dragleave', function() {
			uploadDropzone.classList.remove('active');
		});

		uploadDropzone.addEventListener('drop', function(e) {
			e.preventDefault();
			uploadDropzone.classList.remove('active');

			if (e.dataTransfer.files.length > 0) {
				handleFiles(e.dataTransfer.files);
			}
		});

		fileInput.addEventListener('change', function() {
			if (fileInput.files.length > 0) {
				handleFiles(fileInput.files);
			}
		});

		// 파일 처리 함수
		function handleFiles(files) {
			if (uploadFiles && fileCount && uploadButton && cancelUpload) {
				// 업로드 버튼 활성화
				uploadButton.disabled = false;
				cancelUpload.disabled = false;

				// 파일 카운트 업데이트
				fileCount.textContent = `(${files.length})`;

				// 파일 목록 초기화
				uploadFiles.innerHTML = '';

				// 각 파일 미리보기 생성
				Array.from(files).forEach(function(file, index) {
					const fileItem = document.createElement('div');
					fileItem.classList.add('upload-file-item');

					// 파일 타입에 따른 미리보기
					const isImage = file.type.startsWith('image/');

					fileItem.innerHTML = `
            <div class="upload-file-info">
              <span class="upload-file-name">${file.name}</span>
              <span class="upload-file-size">${formatFileSize(file.size)}</span>
            </div>
            <button class="btn-icon remove-file" data-index="${index}">
              <span class="icon">×</span>
            </button>
          `;

					uploadFiles.appendChild(fileItem);
				});

				// 파일 제거 버튼 이벤트
				document.querySelectorAll('.remove-file').forEach(function(button) {
					button.addEventListener('click', function() {
						const index = parseInt(button.dataset.index);
						// 실제 구현에서는 FileList를 수정할 수 없으므로
						// 유지할 파일들의 새 배열을 만들어 다시 처리해야 함
						button.closest('.upload-file-item').remove();

						// 남은 파일 카운트 업데이트
						const remainingFiles = document.querySelectorAll('.upload-file-item').length;
						fileCount.textContent = `(${remainingFiles})`;

						if (remainingFiles === 0) {
							uploadButton.disabled = true;
							cancelUpload.disabled = true;
						}
					});
				});
			}
		}

		// 업로드 버튼 이벤트
		if (uploadButton) {
			uploadButton.addEventListener('click', function() {
				// 여기에 실제 파일 업로드 처리 코드 추가
				alert('파일 업로드가 완료되었습니다.');

				// 업로드 후 상태 초기화
				uploadFiles.innerHTML = '';
				fileCount.textContent = '(0)';
				uploadButton.disabled = true;
				cancelUpload.disabled = true;
			});
		}

		// 업로드 취소 버튼 이벤트
		if (cancelUpload) {
			cancelUpload.addEventListener('click', function() {
				uploadFiles.innerHTML = '';
				fileCount.textContent = '(0)';
				uploadButton.disabled = true;
				cancelUpload.disabled = true;
			});
		}
	}

	// 게시글 작성 폼
	const postForm = document.getElementById('postForm');
	const postFiles = document.getElementById('postFiles');
	const fileList = document.getElementById('fileList');

	if (postForm && postFiles) {
		postFiles.addEventListener('change', function() {
			if (fileList) {
				// 파일 목록 초기화
				fileList.innerHTML = '';

				// 각 파일 목록 표시
				Array.from(postFiles.files).forEach(function(file) {
					const fileItem = document.createElement('div');
					fileItem.classList.add('file-item-preview');

					fileItem.innerHTML = `
            <span class="file-name">${file.name}</span>
            <span class="file-size">${formatFileSize(file.size)}</span>
          `;

					fileList.appendChild(fileItem);
				});
			}
		});

		/*    if (postForm) {
			  postForm.addEventListener('submit', function(e) {
				e.preventDefault();
				const title = document.getElementById('postTitle').value;
				const content = document.getElementById('postContent').value;
			    
				// 제목과 내용 검증
				if (!title || !content) {
				  alert('제목과 내용을 모두 입력해주세요.');
				  return;
				}
			    
				// 여기에 실제 게시글 등록 처리 코드 추가
				alert('게시글이 등록되었습니다.');
				window.location.href = 'board.html';
			  });
			}*/
	}

	// 로그인 상태 변경 함수
	function updateLoginStatus(username) {
		const loginBtn = document.getElementById('loginBtn');
		if (loginBtn) {
			loginBtn.textContent = `${username} 님`;
			loginBtn.href = '#';

			// 로그아웃 기능 추가 (클릭시)
			loginBtn.addEventListener('click', function(e) {
				e.preventDefault();

				// 로그아웃 확인
				if (confirm('로그아웃 하시겠습니까?')) {
					// 로그아웃 처리
					loginBtn.textContent = '로그인';
					alert('로그아웃되었습니다.');

					// 이벤트 리스너 제거 및 재설정
					loginBtn.removeEventListener('click', arguments.callee);
					loginBtn.addEventListener('click', function(e) {
						e.preventDefault();
						loginModal.style.display = 'flex';
					});
				}
			});
		}
	}

	// 파일 크기 포맷 함수
	function formatFileSize(bytes) {
		if (bytes === 0) return '0 Bytes';

		const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
		const i = Math.floor(Math.log(bytes) / Math.log(1024));

		return parseFloat((bytes / Math.pow(1024, i)).toFixed(2)) + ' ' + sizes[i];
	}
});

// CSS 클래스 토글 헬퍼 함수
function toggleClass(element, className) {
	if (element.classList.contains(className)) {
		element.classList.remove(className);
	} else {
		element.classList.add(className);
	}
}