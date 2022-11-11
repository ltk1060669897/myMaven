$(function () {
    $.get("/user/allUserList",function (data) {
        if(data != null){
            console.log(data);
        }
    })
});