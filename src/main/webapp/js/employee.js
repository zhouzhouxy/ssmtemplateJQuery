$(function(){
    //获取模板行
    tr_template=$("#tr_template").children();
    tr_cln=$("#tr_cln");

    //实施计划的模板行
    tr_template2=$("#plan_template").children();
    planInfo=$("#plan_info");
    //初始化数据
    var params={"pageSize":5,"pageNum":1,"keyWord":"task_name"};

    url="/personController/queryTask";

    initTable(params);

    //为分页页码绑定事件
    $("#pager").on('click',"a",function(){
        var pageNum=$(this).data("page");
        //获取下拉框中的值
        var pageSize=$("#pageSize option:selected").val();
        //变为json数据
        var params={'pageNum':pageNum,'pageSize':pageSize};
        initTable(params);
    })

    //反馈信息
    $(".feedBackInfo").on("click",function(){
        //获取当前行的计划Id
        var planId=$(this).parents("td").next().find("[name=plan_ratio]").val();
    })
})

function initTable(params){
    $.post(url,params,function(data){
        pageData=data.pageInfo;

        loadPage();
        //先清空表格
        tr_cln.empty();
        //循环
        $.each(pageData.list,function(i,e){
                var new_tr=tr_template.clone();
            $.each(e,function (name,value) {
                new_tr.find("."+name).text(value)
                //设置单选按钮中的值
                if(new_tr.find(".empId")){
                    new_tr.find(".empId").val(e.taskId)
                }
                //设置实施人的名字
                if(new_tr.find(".assign")){
                    $.each(data.employees,function(j,employee){
                        if(employee.employeeId==e.assignerId){
                            new_tr.find(".assign").text(employee.employeeName);
                        }
                    })
                }
            })
            //append
            tr_cln.append(new_tr);
        })
        console.log(data)
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


//制定计划
$("#setPlan").on("click",function(){
    //获取单选按钮选中的值
    taskId=$("[name=setPlan]:checked").val();
    if(taskId==null){
        alert("请选中任务")
        return
    }
    var url="/taskController/queryDescTask";
    var params={"taskId":taskId};
    $.post(url,params,function(data){
        // console.log(data);
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
            //复制行
            var new_trs=tr_template2.clone();
            $.each(value,function(name,val){
                // console.log("name="+name+"value"+val)
                //设置值
                new_trs.find("."+name).text(val);
                new_trs.find("[name=plan_ratio]").val(value.planId)

            })
            new_trs.find(".feedBackInfo").on("click",function(){    //点击反馈信息
                feedBackInfo(value.planId);
            })
            new_trs.find(".adjustPlan").on("click",function(){    //点击反馈信息
                adjustPlan(value.planId);
            })
            planInfo.append(new_trs);
        })
    },"json");
    $("#taskInfo").css("display","none")
    $("#taskDetail").css("display","block")
})
//点击排序
function desc(str){
    //获取下拉框中选中的值
    var pageSize=$("#pageSize option:selected").val();
    //输入框中的值为当前页
    var pageNum=  $("#pageNum").val();
    //变为json数据
    var params={'pageNum':pageNum,'pageSize':pageSize,'order':str};
    initTable(params);
}

//点击新建
$("#createPlan").on('click',function(){
    //隐藏taskDetail div
    $("#taskDetail").css("display","none");
    //显示输入新计划信息
    $("#createPlanInfo").css("display","block");
})

//提交新计划信息
$("#sub").on('click',function(){
    //序列化数据
  var planInfos=$("#planForm").serialize();
  console.log(planInfos)
  //提交
    $.post("/plan/subPlan?taskId="+taskId+"&"+planInfos,function(data){
        if(data.message=="yes"){
           alert("添加成功")
            //转到“员工计划”界面显示更改结果。
            var params={"taskId":taskId};
            $.post("/plan/queryPlan",params,function(data){
                console.log(data)
                plans=data.plans;
                //循环生成表格
                //1.先清空表格
                planInfo.empty();
                $.each(data.plans,function(i,value){
                    console.log(value)
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
            //清空表单里的数据
            $("#planForm")[0].reset();
            $("#createPlanInfo").css("display","none")
            $("#taskDetail ").css("display","block")
        }else{
            alert("添加失败")

        }
    },"json")
})

//点击删除按钮
$("#delBtn").on("click",function(){
    //获取选中的按钮
    // var taskId=$("[name=delRatio]:checked").val();
    //获取所有选中的复选框的值
    var taskIds=new Array();
    $.each($("#plan_info [name=plan_ratio]:checked"),function(i,value){
        taskIds.push(value.value);
    })
    var ids=taskIds.join(",");
    console.log(taskIds)

    if(taskIds.length==0){
        alert("请选择任务")
        return false;
    }
    $.post("/plan/delPlan?ids="+ids,null,function(data){
        console.log(data)
        if(data.message=='yes'){
            alert("删除成功")
            //转到“员工计划”界面显示更改结果。
            var params={"taskId":taskId};
            $.post("/plan/queryPlan",params,function(data){
                console.log(data)
                plans=data.plans;
                //循环生成表格
                //1.先清空表格
                planInfo.empty();
                $.each(data.plans,function(i,value){
                    console.log(value)
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
        }else{
            alert("删除失败");
        }
    },"json")
});

//点击复选框全选全不选
$("#all").on("click",function(){
    console.log($("#all").is(":checked"))
    if($("#all").is(":checked")){
        $.each($("[name=plan_ratio]"),function(){
            $(this).prop("checked","checked")
        })
    }else{
        $.each($("[name=plan_ratio]"),function(){
            $(this).prop("checked","")
        })
    }
})

//反馈信息
function feedBackInfo(pid){
    //查询该计划的反馈信息
    //循环所有的计划名称
    var backInfo;
    $.each(plans,function(i,e){
        $.each(e,function(name,value){
            if(value==pid){
                backInfo=e;
            }
        })
    })
    console.log(backInfo);
    planId=backInfo.planId;
    if(backInfo.isFeedback!="未反馈"){
        alert(backInfo.isFeedback)
        return false;
    }

    //循环生成反馈信息页面的值
    $.each(backInfo,function(name,value){
        $("#feedBackInfo").find("."+name).text(value);
    })

    $("#taskDetail").css("display","none");
    $("#feedBackInfo ").css("display","block");

}
//调整计划
function adjustPlan(pid){
    var planInfo;
    $.each(plans,function(i,e){
        $.each(e,function(name,value){
            if(value==pid){
                planInfo=e;
            }
        })
    })
    console.log(planInfo)
}

//点击取消按钮
$("#cancel").on("click",function () {
    //清空反馈信息
    $("#info").val('');
    //隐藏taskDetail
    $("#feedBackInfo").css("display","none");
    $("#taskDetail").css("display","block");

})

//点击提交按钮
$("#feedBackInfoSubmit").on("click",function () {
    //获取计划状态中的值
   var status= $("#palnStatus :selected").text();
    //获取是否反馈选中的值
   var feedBackInfos =$("#isFeedBackInfo :selected").text();
    //获取输入反馈信息的文本域中的值
    var info=$("#info").val();

    var params={"status":status,"feedBackInfo":feedBackInfos,"info":info,"planId":planId};
    //提交
    $.post("/plan/feedBackInfo",params,function(data){
        if(data.message=="yes"){
            alert("反馈成功");
            //重新查询计划信息
            var params={"taskId":taskId};
            $.post("/plan/queryPlan",params,function(data){
                plans=data.plans;
                //循环生成表格
                //1.先清空表格
                planInfo.empty();
                $.each(data.plans,function(i,value){
                    //复制行
                    var new_trs=tr_template2.clone();
                    $.each(value,function(name,val){
                        // console.log("name="+name+"value"+val)
                        //设置值
                        new_trs.find("."+name).text(val);
                        new_trs.find("[name=plan_ratio]").val(value.planId)

                    })
                    new_trs.find(".feedBackInfo").on("click",function(){    //点击反馈信息
                        feedBackInfo(value.planId);
                    })
                    new_trs.find(".adjustPlan").on("click",function(){    //点击反馈信息
                        adjustPlan(value.planId);
                    })
                    planInfo.append(new_trs);
                })
            },"json");
            //隐藏taskDetail
            //清空反馈信息
            $("#info").val('');
            $("#feedBackInfo").css("display","none");
            //重新查询计划信息
            $("#taskDetail").css("display","block");
        }
    },'json')
    console.log(params)
})
