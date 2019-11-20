$(function(){

    $("#addEmp").validate({
        rules:{
            password:{
                required:true,
                minlength:2
            },
            confirm_password:{
                required:true,
                minlength:5,
                equalTo:"#password"
            }

        }
    });


    tr_template=$("#tr_template").children();
    tr_cln=$("#tr_cln");

    //分页参数
    var params={"pageSize":5,"pageNum":1,"keyWord":"employee_id"};
    url="/personController/queryAllEmployee";

    initTable(params);

    //为分页页码绑定事件
    $("#pager").on('click',"a",function(){
        var pageNum=$(this).data("page");
        //获取下拉框中的值
        var pageSize=$("#pageSize option:selected").val();
        //变为json数据
        var params={'pageNum':pageNum,'pageSize':pageSize,"keyWord":"employee_id"};
        initTable(params);
    })

})


function initTable(params){
    $.post(url,params,function(data){
        pageData=data.pageInfo;
        role=data.role;
        loadPage();
        //先清空表格
        tr_cln.empty();
        $.each(pageData.list,function(i,e){
            //克隆
            var new_tr=tr_template.clone();
            $.each(e,function(name,value){
                new_tr.find("."+name).text(value);

                if(new_tr.find(".parentName")){
                    $.each(pageData.list,function(j,s){
                        if(s.employeeId==e.parentId){
                            new_tr.find(".parentName").text(s.employeeName);
                        }
                    })
                }
                if(new_tr.find(".roleName")){
                    $.each(role,function(j,s){
                        if(s.roleId==e.roleId){
                            new_tr.find(".roleName").text(s.roleName);
                        }
                    })
                }
            })
            tr_cln.append(new_tr);
        })
    },"json")
}
//加载分页栏
var loadPage=function(){
    //保留首页 尾页，中间的数字全部删除
    $("#pager > a:not(:first):not(:last)").remove();
    //显示总共多少条数据
    $("#totalSize").text(pageData.total)
    //输入框中的值为当前页
    var pageNum=pageData.pageNum
    $("#pageNum").val(pageNum);
    //显示总共多少页
    var totalPage=pageData.navigateLastPage;
    //最开始页码为1
    var beginLabel=1;
    var endLabel=totalPage;
    if(totalPage>5){    //如果页码大于5
        if(pageNum<=3){  //如果当前页面小于等于3 则显示前五页
            endLabel=5;
        }else if(pageNum>=totalPage-2){
            beginLabel=totalPage-4;
        }else{
            beginLabel=pageNum-2;
            endLabel=pageNum+2;
        }
    }
    //获取尾页元素
    var a_last=$("#pager>a:last");
    a_last.data("page",totalPage);
    //动态加载页面
    for(var i=beginLabel;i<=endLabel;i++){
        var a_node=$("<a data-page='"+i+"'>"+i+"</a>");
        //给当前页设置样式
        if(i==pageNum){
            a_node.addClass("active");
        }
        a_last.before(a_node);
    }
}
//下拉框发生改变
$("#pageSize").on("change",function(){
    //获取下拉框中选中的值
    var pageSize=$("#pageSize option:selected").val();
    //输入框中的值为当前页
    var pageNum=pageData.pageNum;
    //变为json数据
    var params={'pageNum':pageNum,'pageSize':pageSize,"keyWord":"employee_id"};
    initTable(params);
})

//焦点失去触发输入框查询指定页面
$("#pageNum").on("blur",function(){
    //获取下拉框中选中的值
    var pageSize=$("#pageSize option:selected").val();
    //输入框中的值为当前页
    var pageNum=  $("#pageNum").val();
    if(pageNum>pageData.navigateLastPage){
        alert("输入的页码"+pageNum+";总共的页数"+pageData.navigateLastPage)
        alert("没有这一页，请重新输入");
        return;
    }
    //变为json数据
    var params={'pageNum':pageNum,'pageSize':pageSize,"keyWord":"employee_id"};
    initTable(params);
})

//选择角色发生改变时
$("#role").on("change",function(){
    //获取到选中的
    var roleId=$("#role :selected").val();
    //生成领导的下拉列表
    $.post("/personController/queryLeaderShip",{"roleId":roleId},function(data){
        if(data.leader!=null){
            $.each(data.leader,function(i,e){
                $("#parent").append("<option value="+e.employeeId+">"+e.employeeName+"</option>");
            })
        }
    },"json")
})

//添加员工
$("#add").on("click",function(){
    //隐藏显示数据dic
    $("#addUser").css("display","none");
    $("#addEmployee").css("display","block");

})

//提交表单
$.validator.setDefaults({
    submitHandler: function() {
        console.log($("#addEmp").serializeArray())
        var val=$("#addEmp").serializeArray();
        //提交
        $.post("/personController/addEmp",val,function(data){
            if(data.message=="yes"){
                alert("删除成功");
                //清空输入表单
                $("#addEmp")[0].reset();
                //重新查询表格
                var params={"pageSize":5,"pageNum":1,"keyWord":"employee_id"}
                initTable()
                // //隐藏添加员工
                $("#addEmployee").css("display","none");
                $("#addUser").css("display","block");
            }else{
                alert("添加失败")
            }
        },"json")
    }
});
