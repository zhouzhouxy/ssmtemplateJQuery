$(function(){
    var url="/main/queryData"
       $.post(url,null,function(data){
           // console.log(JSON.parse(data.menus))
           console.log(data)
           var json=JSON.parse(data.menus)

           $(".uname").text(data.employee.employeeName);
           var uls=$("#leftul");
           $.each(json,function(i,menu){
               var menuName=menu.menuName;
               var menuUrl=menu.menuUrl;
               var liNode= uls.children().first().clone();
               var aNode=liNode.children();
               aNode.text(menuName);
               // aNode.attr("href","/taskController"+menuUrl);
               // aNode.prop("href","/taskController"+menuUrl);
               $(aNode).on("click",function(){
                   // window.location.href="/taskController"+menuUrl;
                    //aNode.prop("href","/taskController"+menuUrl);
                   //获取iframe设置src的值
                   $("#iframe").prop("src","/taskController"+menuUrl)
                   //  aNode.attr("href","/taskController"+menuUrl);
               })
               uls.append(liNode);
           })
           // var uls=$("#leftul");
           // // uls.remove();
           // var i=0
           // for(var i=0,l=json.length;i<l;i++){
           //     var s= uls.find(".treeview").clone(true)
           //     for(var key in json[i]){
           //         // if(json[i][key]=="menuUrl"){
           //         //
           //         // }
           //         console.log(key)
           //     }
           // }
            // $.each(json,function (i,field) {
            //      var s= uls.find(".treeview").clone(true)
            //     $.each(field,function(name,value){
            //         // // console.log(name)
            //         if(name=="menuUrl"){
            //             s.find("a").prop("href",value);
            //         }
            //         if(name=="menuName"){
            //             console.log(value)
            //             s.find("span").text(value);
            //             uls.append(s)
            //         }
            //
            //     })
            //
            // })

           // console.log(uls.find(".treeview").clone());
       },"json")

});
