$(function(){
    tr_template=$("#tr_template").children();
    tr_cln=$("#tr_cln");

    //定义分页参数
    var params={"pageNum":1,"pageSize":5};
    url="/personController/queryPerson";

    //加载表格
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

});
//加载表格
function initTable(params){
    $.post(url,params,function(data){
        pageData=data.pageInfo;
        loadPage();
        //循环生成表格
        //循环之前先清空表格
        tr_cln.empty();
        $.each(pageData.list,function(i,e){
            //克隆
           var new_tr= tr_template.clone();
           $.each(e,function(name,values){
               new_tr.find("."+name).text(values)
               if(new_tr.find(".employeeIds")){
                   new_tr.find(".employeeIds").val(e.employeeId)
               }
           })
            //添加
            tr_cln.append(new_tr)
        })
        console.log(pageData);
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

//点击详细信息
$("#employeeInfo").on("click",function(){
    //获取选中的单选按钮的值
    var eid=$("[name=employeeId]:checked").val();
    //查询详细信息
    // var params={"employeeId":eid}

    //循环pageData.list
    $.each(pageData.list,function(i,e){
        if(e.employeeId==eid){
            //循环
            $.each(e,function(name,value){
                $("#descEmployee").find("."+name).text(value);
            })
        }
    })
    // $.post("/queryDetailEmployee",eid,function(data){
    //     var callbackData=data.employee;
    //
    // },"json");
    //隐藏showPerson.div
    $("#showPerson").css("display","none");
    //显示showPersonDetail div
    $("#showPersonDetail").css("display","block");
});

//点击返回
$("#return").on("click",function(){
    //隐藏showPersonDetail.div
    $("#showPersonDetail").css("display","none");
    //显示showPerson div
    $("#showPerson").css("display","block");
})
