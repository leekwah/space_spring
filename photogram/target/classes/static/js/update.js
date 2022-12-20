// (1) 회원정보 수정
function update(userId) {
    // profileUpdate 는 form 의  id 값을 의미한다.
    // serialize() 를 하면, 모든 값들이 데이터화 된다.
    let data = $("#profileUpdate").serialize();

    $.ajax({
        type:"put",
        url:`/api/user/${userId}`,
        data:data,
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"
    }).done(res => {
        console.log("update 성공");
    }).fail(error => {
        console.log("update 실패");
    })
}