$(function(){
    //模板行
    tr_template=$("#template").children();
    tblBook=$("#tblBook");
    //实施计划的模板行
    tr_template2=$("#plan_template").children();
    planInfo=$("#plan_info");

    //加载数据
    var pageSize=$("#pageSize option:selected").val();
    var params={'pageNum':1,'pageSize':pageSize};
    getTable(params);

    //为分页页码绑定事件
    $("#pager").on('click',"a",function(){
        var pageNum=$(this).data("page");
        //获取下拉框中的值
        var pageSize=$("#pageSize option:selected").val();
        //变为json数据
        var params={'pageNum':pageNum,'pageSize':pageSize};
        getTable(params);
    })
})
//查询分页
function getTable(params){
    var url="initTableData"
    console.log(params);
    $.post(url,params,function(data){
        console.log(data);
        //加载分页的页码
        pageData=data.pageInfo;
        loadPage();
        //重新加载数据之前要清空表格
        tblBook.empty();
        $.each(data.pageInfo.list,function(i,value){
            //复制行
            var new_tr=tr_template.clone();
            //设置每隔单元格的文本值
            $.each(value,function(name,val){
                //设置值
                new_tr.find("."+name).text(val);
                new_tr.find("[name=viewDesc").val(value.taskId) //给单选按钮设值
                if(name=="implementorId"){
                    // console.log(val)
                    $.each(data.employee,function(j,employee){
                        // console.log(employee.employeeId==val)
                        if(employee.employeeId==val){
                            new_tr.find("."+name).text(employee.employeeName)
                        }
                    })
                }
            })
            //将行添加到表格中
            tblBook.append(new_tr);
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
    var params={'pageNum':pageNum,'pageSize':pageSize};
    getTable(params);
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
    getTable(params);
})

//点击详细信息按钮获取选中的单选
$("#viewDetail").on("click",function(){
    var radioval=  $(".operation").find("[name=viewDesc]:checked").val();     //获取单选按钮中的值
    // console.log(radioval)
    if(radioval==null){
        alert("请选中")
        return;
    }
    //将这个表格div隐藏
    $("#cd").css("display","none")
    //显示详细信息div
    //像后台发送数据查询任务详细信息和实施计划
    var url="queryDescTask";
    var params={"taskId":radioval};
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
    //循环获取表格
    var parm={"taskId":radioval}
    $.post("/plan/queryPlan",parm,function(data){
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
        console.log(data.plans);
    },"json");
    $("#taskDetail").css("display","block")
})

//点击排序
function order(str){
    //获取下拉框中选中的值
    var pageSize=$("#pageSize option:selected").val();
    //输入框中的值为当前页
    var pageNum=  $("#pageNum").val();
    //变为json数据
    var params={'pageNum':pageNum,'pageSize':pageSize,'order':str};
    getTable(params);
}

//点击实施计划的详细按钮
$("#planInfoBtn").on('click',function(){
    //隐藏任务信息div
    $("#taskDetail").css("display","none");
    //获取选中的单选按钮
    // var planId=$(".planRatio").find("[name=plan_ratio]:checked").val();
    var planId=$(".planRatio [name=plan_ratio]:checked").val();

    //alert(planId)
    //异步查询计划详细
    var url="/plan/planDetail";
    var params={"planId":planId};
    $.post(url,params,function(data){
        $.each(data.plan,function(name,value){
            console.log("name="+name+"value="+value);
            $("#planDesc >p").find("."+name).val(value)
        })
        //所属任务的名称
        $("#planDesc >p").find(".taskName").val(data.taskName)
        // console.log(data)
    },"json");
    //根据planId查询所属任务的名称

    //计划详细div显示
    $("#planDetail").css("display","block")
})
//点击计划详细中返回按钮
$("#returnTask").on('click',function(){
   // 隐藏计划信息div
    $("#planDetail").css("display","none");
    //显示任务信息
    $("#cd").css("display","block");
});
