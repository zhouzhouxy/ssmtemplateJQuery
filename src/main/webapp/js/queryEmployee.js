$(function(){
    //获取模板行
    tr_template=$("#tr_template").children();
    tr_cln=$("#tr_cln");


    //循环生成下拉框查询所有的任务
    initSelect();
    //初始化数据
    // var params={"pageSize":5,"pageNum":1};
    //
    // url="/plan/queryAllPlan";
    //
    // initTable(params);

    var planName=$("#myForm [name=planName]").val();
    var tId=$("#sel :selected").val();
    var beginDate1=$("#myForm  [name=beginDate1]").val();
    var beginDate2=$("#myForm  [name=beginDate2]").val();
    var endDate=$("#myForm  [name=endDate]").val();
    var endDate2=$("#myForm  [name=endDate2]").val();
    var feedBackInfo=$("#myForm  [name=feedBackInfo]").val();

    var params={"pageNum":1,"pageSize":5,"planName":planName,"taskId":tId,"beginDate1":beginDate1,
    "beginDate2":beginDate2,"endDate":endDate,"endDate2":endDate2,
    "feedBackInfo":feedBackInfo};

     url="/plan/queryPlanByCondition";
    queryPlan(params);
    //为分页页码绑定事件
    $("#pager").on('click',"a",function(){
        var pageNum=$(this).data("page");
        //获取下拉框中的值
        var pageSize=$("#pageSize option:selected").val();
        //变为json数据
        var planName=$("#myForm [name=planName]").val();
        var tId=$("#sel :selected").val();
        var beginDate1=$("#myForm  [name=beginDate1]").val();
        var beginDate2=$("#myForm  [name=beginDate2]").val();
        var endDate=$("#myForm  [name=endDate]").val();
        var endDate2=$("#myForm  [name=endDate2]").val();
        var feedBackInfo=$("#myForm  [name=feedBackInfo]").val();

        var params={"pageNum":pageNum,"pageSize":pageSize,"planName":planName,"taskId":tId,"beginDate1":beginDate1,
            "beginDate2":beginDate2,"endDate":endDate,"endDate2":endDate2,
            "feedBackInfo":feedBackInfo};
        queryPlan(params);
    })

})
//初始化加载表格
function initTable(params){
    $.post(url,params,function(data){
        pageData=data.pageInfo;
        loadPage();
        //先清空表格
        tr_cln.empty();
        //循环生成表格
        console.log(allTasks)
        $.each(pageData.list,function(i,e){
            var new_tr=tr_template.clone();
            $.each(e,function(name,value){
                new_tr.find("."+name).text(value)
                if(new_tr.find(".taskName")){
                    $.each(allTasks,function(i,s){
                        if(e.taskId==s.taskId){
                            new_tr.find(".taskName").text(s.taskName);
                        }
                    })
                }
            })
            tr_cln.append(new_tr);
        })
    },"json")
}
//初始化查询所有的任务添加到下拉框中
function initSelect(){
    $.ajaxSettings.async = false;
    $.post("/taskController/queryAllTask",null,function (data) {
        //获取所有的任务
        allTasks=data.tasks;
        $.each(data.tasks,function (i,e) {
            var options="<option value="+e.taskId+">"+e.taskName+"</option>"
            $("#sel").append(options);
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

    var planName=$("#myForm [name=planName]").val();
    var tId=$("#sel :selected").val();
    var beginDate1=$("#myForm  [name=beginDate1]").val();
    var beginDate2=$("#myForm  [name=beginDate2]").val();
    var endDate=$("#myForm  [name=endDate]").val();
    var endDate2=$("#myForm  [name=endDate2]").val();
    var feedBackInfo=$("#myForm  [name=feedBackInfo]").val();

    var params={"pageNum":pageNum,"pageSize":pageSize,"planName":planName,"taskId":tId,"beginDate1":beginDate1,
        "beginDate2":beginDate2,"endDate":endDate,"endDate2":endDate2,
        "feedBackInfo":feedBackInfo};
    queryPlan(params);

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
    var planName=$("#myForm [name=planName]").val();
    var tId=$("#sel :selected").val();
    var beginDate1=$("#myForm  [name=beginDate1]").val();
    var beginDate2=$("#myForm  [name=beginDate2]").val();
    var endDate=$("#myForm  [name=endDate]").val();
    var endDate2=$("#myForm  [name=endDate2]").val();
    var feedBackInfo=$("#myForm  [name=feedBackInfo]").val();

    var params={"pageNum":pageNum,"pageSize":pageSize,"planName":planName,"taskId":tId,"beginDate1":beginDate1,
        "beginDate2":beginDate2,"endDate":endDate,"endDate2":endDate2,
        "feedBackInfo":feedBackInfo};
    queryPlan(params);
})

//点击查询
$("#queryPlan").on("click",function(){
    //获取下拉框中选中的值
    var pageSize=$("#pageSize option:selected").val();
    //输入框中的值为当前页
    var pageNum=  $("#pageNum").val();
    if(pageNum>pageData.navigateLastPage){
        alert("输入的页码"+pageNum+";总共的页数"+pageData.navigateLastPage)
        alert("没有这一页，请重新输入");
        return;
    }

    var planName=$("#myForm [name=planName]").val();
    var tId=$("#sel :selected").val();
    var beginDate1=$("#myForm  [name=beginDate1]").val();
    var beginDate2=$("#myForm  [name=beginDate2]").val();
    var endDate=$("#myForm  [name=endDate]").val();
    var endDate2=$("#myForm  [name=endDate2]").val();
    var feedBackInfo=$("#myForm  [name=feedBackInfo]").val();

    var params={"pageNum":pageNum,"pageSize":pageSize,"planName":planName,"taskId":tId,"beginDate1":beginDate1,
                "beginDate2":beginDate2,"endDate":endDate,"endDate2":endDate2,
                "feedBackInfo":feedBackInfo};

    queryPlan(params);
})

function queryPlan(params){
    $.post(url,params,function (data) {
        console.log(data)
        pageData=data.pageInfo;
        loadPage();
        // 先清空表格
        tr_cln.empty();
        console.log(allTasks)
        //循环生成表格
        $.each(pageData.list,function(i,e){
            var new_tr=tr_template.clone();
            $.each(e,function(name,value){
                new_tr.find("."+name).text(value)
                if( new_tr.find(".taskName")){
                    $.each(allTasks,function(i,s){
                        if(e.taskId==s.taskId){
                            new_tr.find(".taskName").text(s.taskName);
                        }
                    })
                }
            })
            tr_cln.append(new_tr);
        })
    },"json")

}
