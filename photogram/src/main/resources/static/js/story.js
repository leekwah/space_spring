/**
	2. 스토리 페이지
	(1) 스토리 로드하기
	(2) 스토리 스크롤 페이징하기
	(3) 좋아요, 안좋아요
	(4) 댓글쓰기
	(5) 댓글삭제
 */

// (0) 현재 로그인한 사용자 아이디
let principalId = $("#principalId").val();

// (1) 스토리 로드하기
let page = 0;

function storyLoad() {
	$.ajax({
		url : `api/image?page=${page}`,
		dataTypes : "JSON"
	}).done(res => {
		console.log(res);
		res.data.content.forEach((image) => {
			let storyItem = getStoryItem(image);
			$("#storyList").append(storyItem);
		});
	}).fail(error => {
		console.log("오류", error);
	});
}
storyLoad();


function getStoryItem(image) {
	let item = `<div class="story-list__item">
	<div class="sl__item__header">
		<div>
			<img class="profile-image" src="/upload/${image.user.profileImageUrl}" onerror="this.src='/images/person.jpeg'" />
		</div>
		<div>${image.user.username}</div>
		</div>
		<div class="sl__item__img">
			<img src="/upload/${image.postImageUrl}" />
		</div>
		
		<div class="sl__item__contents">
		<div class="sl__item__contents__icon">
			<button>`;

			if(image.likeState) {
				item += `<i class="fas fa-heart active" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
			} else {
				item += `<i class="far fa-heart" id="storyLikeIcon-${image.id}" onclick="toggleLike(${image.id})"></i>`;
			}

			item += `

			</button>
		</div>
		
		<span class="like"><b id="storyLikeCount-${image.id}">${image.likeCount} </b>likes</span>
		
		<div class="sl__item__contents__content">
			<p>${image.caption}</p>
		</div>
		
		<div id="storyCommentList-${image.id}">`;

			image.comments.forEach((comment) => {
				item += `<div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}"">
				<p>
					<b>${comment.user.username} :</b> ${comment.content}
				</p>`;

				if(principalId == comment.user.id) {
					item += `<button onclick="deleteComment(${comment.id})">
								<i class="fas fa-times"></i>
							</button>`;
				}

				item +=`
			</div>`;
			});

		item +=`
		</div>
		
		<div class="sl__item__input">
			<input type="text" placeholder="댓글 달기..." id="storyCommentInput-${image.id}" />
			<button type="button" onClick="addComment(${image.id})">게시</button>
		</div>
		
		</div>
</div>`;
	return item;
}

// (2) 스토리 스크롤 페이징하기
$(window).scroll(() => {
	let checkNum = $(window).scrollTop() - ( $(document).height() - $(window).height() );
	// console.log(checkNum)

	if (checkNum < 1 && checkNum > -1 ) {
		page++;
		storyLoad();

	}
});


// (3) 좋아요, 안좋아요
function toggleLike(imageId) {
	let likeIcon = $(`#storyLikeIcon-${imageId}`);

	if (likeIcon.hasClass("far")) { // 좋아요를 하겠다.

		$.ajax({
			type:"post",
			url:`/api/image/${imageId}/likes`,
			dataType:"JSON"
		}).done(res => {
			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			let likeCount = Number(likeCountStr) + 1; // 문자이기 때문에 캐스팅 후 + 1

			console.log("좋아요 카운트", likeCount);

			$(`#storyLikeCount-${imageId}`).text(likeCount);

			likeIcon.addClass("fas");
			likeIcon.addClass("active");
			likeIcon.removeClass("far");
		}).fail(error => {
			console.log("좋아요 토글실패", error);
		});


	} else { // 좋아요를 취소하겠다.
		$.ajax({
			type:"delete",
			url:`/api/image/${imageId}/likes`,
			dataType:"JSON"
		}).done(res => {

			let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
			let likeCount = Number(likeCountStr) - 1; // 문자이기 때문에 캐스팅 후 - 1

			console.log("좋아요 카운트", likeCount);

			$(`#storyLikeCount-${imageId}`).text(likeCount);

			likeIcon.removeClass("fas");
			likeIcon.removeClass("active");
			likeIcon.addClass("far");
		}).fail(error => {
			console.log("좋아요 토글실패", error);
		});


	}
}

// (4) 댓글쓰기
function addComment(imageId) { // 로그인한 사람의 정보도 변수에 넣어야하지만 세션을 통해서 찾기

	// addComment 를 하고 난 뒤에는 storyCommentList 를 찾아서 append 를 해야한다.

	let commentInput = $(`#storyCommentInput-${imageId}`);
	let commentList = $(`#storyCommentList-${imageId}`);

	let data = {
		imageId: imageId,
		content: commentInput.val()
	}

	// alert(data.content);

	// console.log(data); data 자체
	// console.log(JSON.stringify(data)); JSON 으로 변경한 것

	// return;

	if (data.content === "") {
		alert("댓글을 작성해주세요.");
		return;
	}

	$.ajax({
		type : "post",
		url : `/api/comment`,
		data : JSON.stringify(data),
		contentType : "application/json; charset=utf-8",
		dataType : "json"
	}).done(res => {
		console.log("댓글 작성 성공", res); // res 안에 데이터 내용들이 있음

		let comment = res.data;

		let content = `
			  <div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}"> 
			    <p>
			      <b>${comment.user.username} :</b>
			      ${comment.content}
			    </p>
			    
			    <button onclick="deleteComment(${comment.id})">
			    	<i class="fas fa-times"></i>
			    </button>
					
		
			  </div>
		`;
		commentList.prepend(content); // append 는 뒤에 넣는 것, prepend 는 앞에다가 넣음 (최신 댓글이 제일 위로)

	}).fail(error => {
		console.log("댓글 작성 실패", error);
		alert(error.responseJSON.data.content);
		// 프론트단에서 다 뚫고 들어왔을 경우에 한해서 작동 (서버단)
		// 어지간하면 앞에서 다 막힘
	});

	commentInput.val(""); // input 필드를 깨끗하게 비워주는 것
}

// (5) 댓글 삭제
function deleteComment(commentId) {

	$.ajax({
		type : "delete",
		url : `/api/comment/${commentId}`,
		dataType : "JSON"
	}).done(res => {
		console.log("댓글 삭제 성공", res);
		// 삭제를 했을 시에, 없애는 것
		$(`#storyCommentItem-${commentId}`).remove();
	}).fail(error => {
		console.log("댓글 삭제 실패", error);
	});

}







