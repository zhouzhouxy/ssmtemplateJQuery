$(function(){
    $("#sign").on("click",function(){
        var form=$("#loginForm").serializeArray();
        var url="/login/verify";
        $.post(url,form,function(data){
            console.log(data);
            if(data.message=='success'){
                window.location.href="/main/main"
            }else{
                alert("用户名或密码错误");
            }
        },"json")
    })
})
