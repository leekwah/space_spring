// (1) 회원정보 수정
function update(userId, event) {
    event.preventDefault(); // 폼 태그 액션을 막는 것

    // profileUpdate 는 form 의  id 값을 의미한다.
    // serialize() 를 하면, 모든 값들이 데이터화 된다.
    let data = $("#profileUpdate").serialize(); // key = value

    console.log(data);

    $.ajax({
        type:"put",
        url:`/api/user/${userId}`,
        data:data,
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"
    }).done(res => { // HttpStatus 상태코드 200번대면 성공
        console.log("update 성공", res);
        location.href=`/user/${userId}` // 성공시에 페이지 이동
    }).fail(error => { // HttpStatus 상태코드 200번대가 아닐 때
        // errormap 에 data 가 null 이면 오류가 뜰 수 있다.
        if (error.data == null) { // 그렇기 때문에, if 문으로 설정한다.
            alert(error.responseJSON.message);
        } else {
            alert(JSON.stringify(error.responseJSON.data)); // Object 를 문자열로 바꾼 뒤에, alert 로 보낸다.
        }
    });
}