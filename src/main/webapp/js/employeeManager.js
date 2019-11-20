$(function(){


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
});
function initTable(params){
    $.post(url,params,function(data){
        pageData=data.pageInfo;
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
                            console.log(s.employeeName)
                            new_tr.find(".parentName").text(s.employeeName);
                        }
                    })
                }
                if(new_tr.find(".roleName")){
                    $.each(data.role,function(j,s){
                        if(s.roleId==e.roleId){
                            new_tr.find(".roleName").text(s.roleName);

                        }
                    })
                }
                if(new_tr.find("[name=delRatio]")){
                    new_tr.find("[name=delRatio]").val(e.employeeId)
                }

            })
            new_tr.find(".viewDesc").on("click",function(){
                viewDesc(e.roleId,e.employeeId)
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

//点击删除按钮
$("#delBtn").on("click",function(){

    console.log(cn)
    //获取选中的按钮
    // var taskId=$("[name=delRatio]:checked").val();
    //获取所有选中的复选框的值
    var eIds=new Array();
    $.each($("#tr_cln [name=delRatio]:checked"),function(i,value){
        eIds.push(value.value);
    })
    var ids=eIds.join(",");
    console.log(eIds)

    if(eIds.length==0){
        alert("请选择人员")
        return false;
    }
    var cn=confirm("是否确认删除");
    if(!cn){
        return false;
    }
    console.log(eIds)
    $.post("/personController/delEmp?ids="+ids,null,function(data){
        console.log(data)
        if(data.message=='yes'){
            alert("删除成功")
            //获取下拉框中选中的值
            var pageSize=$("#pageSize option:selected").val();
            //输入框中的值为当前页
            var pageNum=pageData.pageNum;
            //变为json数据
            var params={'pageNum':pageNum,'pageSize':pageSize,"keyWord":"employee_id"};
            initTable(params);
        }else{
            alert("删除失败");
        }
    },"json")
});

//点击复选框全选全不选
$("#all").on("click",function(){
    //定义一个数组
    if($("#all").is(":checked")){
        $.each($("[name=delRatio]"),function(){
            $(this).prop("checked","checked")
        })
    }else{
        $.each($("[name=delRatio]"),function(){
            $(this).prop("checked","")
        })
    }
})

function viewDesc(roleId,eId){
    if(roleId!=3){
        alert("目前只能查看员工详情")
        return false;
    }
    //生成下拉框
    $.post("/personController/queryLeader",null,function(data){
        $.each(data.employees,function(i,e){
                $("#leader").append("<option value="+e.employeeId+">"+e.employeeName+"</option>")
        })
    },"json");
    $.each(pageData.list,function(i,e){
        if(e.employeeId==eId){
            $.each(e,function(name,value){
                $("#empForm").find("."+name).val(value);

            })
        }
    })
    //隐藏显示信息
    $("#showUser").css("display","none");
    $("#viewEmp").css("display","block");
}
$("#updateEmp").on("click",function(){
    //序列化
    var info=$("#empForm").serialize();
    console.log(info)
    //提交
    $.post("/personController/updateEmp?"+info,null,function(data){
        if(data.message=="yes"){
            alert("修改成功")
        }
        //分页参数
        var params={"pageSize":5,"pageNum":1,"keyWord":"employee_id"};
        initTable(params)
        //隐藏显示信息
        $("#viewEmp").css("display","none");
        $("#showUser").css("display","block");
    },"json")

})
