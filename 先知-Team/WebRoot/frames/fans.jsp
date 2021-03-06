<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
 <base href="${pageContext.request.contextPath}/">
<title>UserZone</title>	
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery-2.1.0.min.js"></script>
<style type="text/css">
.goZone {
	font-size: 15px;
}

.goZone:hover {
	text-decoration: none;
}

.btn-primary {
	float: right;
	margin-top: 5px;
}

.aa {
	background: #5bc0de;
	margin-right: 30px;
	font-size: 14px;
	color: #fff;
	width:60px;
	height:30px;
	text-align:center;
	float:right;

}

.aa {
	border: 1px solid transparent;
	display: inline-block;
	border-color: #1b6d85;
}
</style>
<script>
/*  	  $(document).ready(function(){
        var onOff=true;
        var oaa=$(".aa");
        oaa.click(function(){        
                if (oaa.onOff) {
                  $(this).val("加关注");
                  $(this).css({"background":'#5bc0de',"border-color":'#1b6d85'});
                  $(this).onOff = false;
                } else {
                    $(this).css({"background":'#C0C0C0',"border-color":'#000'});
                     $(this).val("已关注");
                    $(this).onOff = true;
                }
            });
      });  */
     /*  function change(fansId){
      	var btn = $(".aa");
      	this.bind("click",function(){
			alert(fansId);      
      		$.ajax({
      			type:"post",
      			url:"follow_excuteAjaxJsonAction",
      			date:{
      				fansId:fansId
      			},
      			dateType:"json",
      			success:function(data){
      				alert('关注成功')
      			}
      			
      			
      		})
      	})
      } */
</script>
</head>
<body>
<ul class="list-group" style="width:720px;">
		 	<c:choose>
				<c:when test="${ empty fansSet }">
					<li class="list-group-item">空空如也</li>
				</c:when>
				<c:otherwise>
					<c:forEach items="${fansSet }" var="fans">
						<li class="list-group-item">
						<a href="loadCollectNews_UserCenterAction?user.id=${fans.byFollow.id }" target="_blank" class="goZone" >
							<c:if test="${ empty fans.byFollow.headImg }">
								<img src="headImg/headimg01.png" class="img-circle" alt="Cinque Terre" width="50px" height="50px">
							</c:if>
							<c:if test="${ not empty fans.byFollow.headImg }">
								<img src="${fans.byFollow.headImg }" class="img-circle" alt="Cinque Terre" width="50px" height="50px">
							</c:if>
							<font style="margin-left:15px;">${fans.byFollow.nickName }</font>
							</a>
							<input type="button"  value="加关注" class="aa" onclick="change(${fans.byFollow.id})">
						</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
</ul>
</body>
</html>

