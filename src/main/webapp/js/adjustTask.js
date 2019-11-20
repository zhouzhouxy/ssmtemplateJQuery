$(function(){
    //获取模板行
     tr_template=$("#tr_template").children();
    //获取要生成的tbody
     tr_cln=$("#tr_cln");

    //定义分页参数
    var pageParam={"pageNum":1,"pageSize":5,"keyWord":'task_name'};
    //     //定义第一次加载时的url
    url="/taskController/getAdjustTaskInfo";
    //加载表格
    //$.ajaxSettings.async = false; //关闭异步
    initTable(pageParam);
    //$.ajaxSettings.async = true; //关闭异步
    //为分页页码绑定事件
    $("#pager").on('click',"a",function(){
        var pageNum=$(this).data("page");
        //获取下拉框中的值
        var pageSize=$("#pageSize option:selected").val();
        //变为json数据
        var params={'pageNum':pageNum,'pageSize':pageSize};
        initTable(params);
    })
    //为生成的任务名称中a标签添加事件
    // $(".taskName").on('click',function(){//当页面点击其它按钮以后就会失效只能重新刷新
        $("#tr_cln").on('click',"a",function(){
        var  taskId=$(this).parents("tr").find("[name=delRatio]").val();
        //隐藏未实施任务信息div
        $("#unimplmented").css("display","none");

      // console.log($.trim($("#employeeName").val()));
      //   console.log($("#employeeName").get(0).selectedIndex
        //通过任务id查询该任务信息，并做出调整
        $.post("queryDescTask",{"taskId":taskId},function(data){
            taskDesc=data.taskDesc
            select();
        },"json");
        $("#taskInfo").css("display","block")
    });

    //循环生成下拉框
    $.post("/taskController/queryEmp",null,function(data){
        //console.log(data.employee)
        for(var i=0;i<data.employee.length;i++){
            // console.log(data.employee[i].employeeName)
            $("#employeeName").append("<option value="+data.employee[i].employeeId+">"+data.employee[i].employeeName+"</option>")
        }
    },"json")
});
function select(){
    $.each(taskDesc,function(name,value){
        $("#taskInfo").find("#"+name).val(value);
    });
    //选中下拉框
    for(var i=0;i<$("#employeeName option").length;i++){
        if($("#employeeName option").eq(i).val()==taskDesc.implementorId){
            $("#employeeName option").eq(i).prop("selected","selected")
        }
    }
}
//加载表格函数
function initTable(pageParam){
    $.ajaxSettings.async = false;
    $.post(url,pageParam,function(data){
        console.log(data)
        //加载分页
        pageData=data.pageInfo;
        //循环数据
        var task=pageData.list;
        loadPage();
        //循环前先清空表格
        tr_cln.empty();
        //循环生成表格
        $.each(task,function(i,value){
            //新建行
            var new_tr=tr_template.clone();

            $.each(value,function(name,val){
                new_tr.find("."+name).text(val);
                new_tr.find("[name=delRatio]").val(value.taskId);
                if(name="implementor_id") {
                    //将实现者id变为员工名称
                    $.each(data.employees, function (j, employee) {
                        // console.log(employee.employeeId==val)
                        if (employee.employeeId == val) {
                            new_tr.find("." + name).text(employee.employeeName)
                        }
                    })
                }
            })
            //添加
            tr_cln.append(new_tr);
        })
    },"json")
    $.ajaxSettings.async = true;
}
//点击表头排序
function desc(keyWord){
     var pageParam={"pageNum":pageData.pageNum,"pageSize":pageData.pageSize,"keyWord":'task_name'};
    //加载表格
    initTable(pageParam);
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
    var params={'pageNum':pageNum,'pageSize':pageSize};
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
    var params={'pageNum':pageNum,'pageSize':pageSize};
    initTable(params);
})

//点击删除按钮
$("#delBtn").on("click",function(){
    //获取选中的按钮
    // var taskId=$("[name=delRatio]:checked").val();
    //获取所有选中的复选框的值
    var taskIds=new Array();
    $.each($("#tr_cln [name=delRatio]:checked"),function(i,value){
        taskIds.push(value.value);
    })
    var ids=taskIds.join(",");
    console.log(taskIds)

    if(taskIds.length==0){
        alert("请选择任务")
        return false;
    }
    $.post("delTask?ids="+ids,null,function(data){
        console.log(data)
        if(data.message=='yes'){
            alert("删除成功")
            //获取下拉框中选中的值
            var pageSize=$("#pageSize option:selected").val();
            //输入框中的值为当前页
            var pageNum=pageData.pageNum;
            //变为json数据
            var params={'pageNum':pageNum,'pageSize':pageSize};
            initTable(params);
        }else{
            alert("删除失败");
        }
    },"json")
});
//点击重置按钮
$("#reset").on("click",function(){
    $.each(taskDesc,function(name,value){
        $("#taskInfo").find("#"+name).val(value);
    });
    //选中下拉框
    for(var i=0;i<$("#employeeName option").length;i++){
        if($("#employeeName option").eq(i).val()==taskDesc.implementorId){
            $("#employeeName option").eq(i).prop("selected","selected")
        }
    }
})
//点击提交按钮
$("#sub").on("click",function(){
    var params=$("form").serializeArray();
    console.log(params)
    $.post("updateTask",params,function(data){
        if(data.message=="yes"){
            alert("修改成功")
            //隐藏taskInfodiv
            $("#taskInfo").css("display","none");
            //重新查询
            //定义分页参数
            var pageParam={"pageNum":1,"pageSize":5,"keyWord":'task_name'};
            initTable(pageParam);
            //显示unimplmented
            $("#unimplmented").css("display","block")
        }
    })
    return false;
})

//点击复选框全选全不选
$("#all").on("click",function(){
    console.log($("#all").is(":checked"))
    //定义一个数组
    taskIds=[]
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
