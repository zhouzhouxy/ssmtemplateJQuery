$(function(){
    //获取模板行
    tr_template=$("#tr_template").children();
    tr_cln=$("#tr_cln");

    //实施计划的模板行
    tr_template2=$("#plan_template").children();
    planInfo=$("#plan_info");
    //定义分页参数
    var pageParam={"pageNum":1,"pageSize":5,"keyWord":'task_name'};
    //定义第一次加载时的url
    url="/taskController/trackTask";
    //加载表格
    initTable(pageParam);
    //为分页页码绑定事件
    $("#pager").on('click',"a",function(){
        var pageNum=$(this).data("page");
        //获取下拉框中的值
        var pageSize=$("#pageSize option:selected").val();
        //变为json数据
        var params={'pageNum':pageNum,'pageSize':pageSize};
        initTable(params);
    })


})
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
                new_tr.find("[name=planRatio]").val(value.taskId);
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

//获取选中的单选按钮
$("#planInfo").on("click",function(){
    //获取选中的单选按钮的值
    taskId= $("[name=planRatio]:checked").val();
    if(taskId==null){
        alert("请选中你要查看的计划")
        return false;
    }
    var url="queryDescTask";
    var params={"taskId":taskId};
    $.post(url,params,function(data){
        console.log(data);
        var taskDesc=data.taskDesc;
        var employee=data.employee;
        //根据数据绑定到任务详细信息中
        $("#taskName").text(taskDesc.taskName);
        $("#taskDesc").text(taskDesc.taskDesc);
        $("#beginDate").text(taskDesc.beginDate)
        $("#endDate").text(taskDesc.endDate);
        $("#real_beginDate").text(taskDesc.realBeginDate);
        $("#real_endDate").text(taskDesc.realEndDate);
        $("#employeeName").text(employee.employeeName)
        $("#taskStatus").text(taskDesc.status);
    },"json");
    $.post("/plan/queryPlan",params,function(data){
        plans=data.plans;
        //循环生成表格
        //1.先清空表格
        planInfo.empty();
        $.each(data.plans,function(i,value){
            // console.log(value)
            //复制行
            var new_trs=tr_template2.clone();
            $.each(value,function(name,val){
                // console.log("name="+name+"value"+val)
                //设置值
                new_trs.find("."+name).text(val);
                new_trs.find("[name=plan_ratio]").val(value.planId)
            })
            planInfo.append(new_trs);
        })
    },"json");
    $("#trakTask").css("display","none")
    $("#taskDetail").css("display","block")
})

//点击修改按钮
$("#updateTaskInfo").on("click",function(){
    //获取选中的任务状态
  var status=$("#taskStaus > option:selected").val();
  if(status=="实施中"){
      alert("没有修改")
      return false;
  }
    //提交修改
    $.post("updateTaskInfo",{"taskId":taskId},function(data){
        if(data.message=="yes"){
            alert("修改成功")
            //重新查询表格
            //定义分页参数
            var pageParam={"pageNum":1,"pageSize":5,"keyWord":'task_name'};
            initTable(pageParam);
            //隐藏div
            $("#taskDetail").css("display","none");
            //显示trakTask div
            $("#trakTask").css("display","block")
        }
    },"json")
})

//点击反馈信息
$("#feedBackInfo").on("click",function(){
    //清空所有的新添加的行
    $(".inserRow").remove()
    //获取所有选中的复选框的值
    var taskIds=new Array();
    $.each($("#plan_info [name=plan_ratio]:checked"),function(i,value){
        taskIds.push(value.value);
    })
    console.log(plans)
    $.each(plans,function(i,e){
        if(e.feedbackInfo!=null){
            for(var i=0;i<taskIds.length;i++){
                if(e.planId==taskIds[i]){
                    // console.log(taskIds[i])
                    // console.log(e.feedbackInfo)
                    //循环所有的checkbox
                    $.each($("#plan_info [name=plan_ratio]:checked"),function(j,value){
                        // console.log(value.value)
                        if(value.value==taskIds[i]){
                            var trs="<tr bgcolor='#f5f5dc' class='inserRow'><td colspan='6'>"+e.feedbackInfo+"</td></tr>"
                            //添加
                            $(this).parents("tr").after(trs)
                        }
                    })
                }
            }
        }
    })
    console.log(taskIds)
})
