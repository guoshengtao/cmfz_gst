<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<script>
    $(function (){
        $("#user-show-table").jqGrid({
            url : '${pageContext.request.contextPath}/user/findUsersByStarId',
            datatype : "json",
            colNames : [ '编号', '用户名', '昵称', '头像','电话', '性别','地址','签名' ],
            colModel : [
                {name : "id",hidden:true,editable:false},
                {name : "username",editable:true},
                {name : "nickname",editable:true},
                {name : 'photo',editable:true,edittype:"file",formatter:function (value,option,rows) {
                        return "<img style='width:100px;height:70px' src='${pageContext.request.contextPath}/user/img/"+rows.photo+"'>";
                    }},
                {name : "phone",editable:true},
                {name : 'sex',editable:true,edittype:"select",editoptions:{value:"男:男;女:女"}},
                {name : "address",formatter:function (value,option,rows) {
                        return rows.province+rows.city;
                    }
                },
                {name : "sign",editable:true}
            ],
            height:250,
            autowidth:true,
            styleUI:"Bootstrap",
            rowNum : 3,
            rowList : [ 3, 5, 10 ],
            pager : '#user-page',
            sortname : 'id',
            viewrecords : true,
            caption : "用户列表",
            editurl : "${pageContext.request.contextPath}/user/edit"
        }).navGrid("#user-page", {edit : false,add : false,del : false,search:false},{
            //控制修改
            closeAfterEdit:true,
                beforeShowForm:function (fmt) {
                fmt.find("#photo").attr("disabled",true);
            }
        },{
            //控制添加
            closeAfterAdd:true,
            afterSubmit:function (data) {
                console.log(data);
                var status = data.responseJSON.status;
                var id = data.responseJSON.message;
                if(status){
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/user/upload",
                        type:"post",
                        fileElementId:"photo",
                        data:{id:id},
                        success:function (response) {
                            //自动刷新jqgrid表格
                            $("#user-show-table").trigger("reloadGrid");
                        }
                    });
                }
                return "123";
            }
        });
    })
</script>



<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">所有用户</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/user/export" >用户信息下载</a></li>
</ul>
<table id="user-show-table"></table>
<div id="user-page" style="height: 40px"></div>