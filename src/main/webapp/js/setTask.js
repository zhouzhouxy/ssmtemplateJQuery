$(function () {
    //系统显示“新建任务”界面（如图1－1所示），在“实施人”下拉列表中显示该主管下属的员工用户名。
    var url="/taskController/queryEmp";
    $.post(url,null,function(data){
        //循环数据生成下拉列表
        // var json=JSON.parse(data);
        console.log(data.employee.length);
        for(var i=0;i<data.employee.length;i++){
            // console.log(data.employee[i].employeeName)
            $("#setEmployee").append("<option value="+data.employee[i].employeeId+">"+data.employee[i].employeeName+"</option>")
        }

        // $.each(data,function(i,menu){
        //     // $("#setEmployee").append("<option value="+menu.employeeId+">"+menu.employeeName+"</option>")
        //     console.log(menu)
        //     $(menu,function(i,field){
        //         console.log(i+"====="+field)
        //     })
        // })
        // for(var i=0;i<data.length;)
    },"json");
})
//提交表单
$("#sub").on('click',function(){
    var url="/taskController/subTask"
    var params= $("#forms").serializeArray();
    console.log(params)
    $.post(url,params,function(data){
       if(data.message=="yes"){
           window.location.href="/main/main"
       }else{
           alert("添加失败")
       }
    },"json")
})
