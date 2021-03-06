<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${pageContext.request.contextPath}/">
<title>Insert title here</title>
<link rel="stylesheet" href="css/common.css"/>
<link rel="stylesheet" href="css/personal.css"/>
<script src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">
	let $newsNavLi = $('.news-nav li');
		  $newsNavLi.on('click',function () {
		    let nowIndex = $newsNavLi.index(this);
		    $newsNavLi.removeClass('news-li-active').eq(nowIndex).addClass('news-li-active');
		  });
		
		  let $newsNav = $('.news-nav');
		  let $navNewsZhanWei = $('.news-nav-zhanwei');
		  let newsNavTop = $newsNav.offset().top + 50;
		  let scrollHeight;

		  $(document).scroll(function () {
		        scrollHeight = $(window).scrollTop();
		        if(scrollHeight >= newsNavTop){
		          $newsNav.css({'position':'fixed', 'top':0});
		          $navNewsZhanWei.css('display','block');
		        }else{
		          $newsNav.css({'position':'relative'});
		          $navNewsZhanWei.css('display','none');
		        }
		    });
</script>
</head>
<body>
<div class="g-container">
    <header title="头部导航条" class="g-minwidth" id="header" name="header">
        <ul>
        <li class="h-tianqi">
               <a class="a-header">
                    <iframe width="150" scrolling="no" height="70" frameborder="0" allowtransparency="true" style="margin-top: 5px;"
	    			src="http://i.tianqi.com/index.php?c=code&id=11&color=%230070C0&icon=1&site=15">
	    		</iframe>
                </a>
            </li>
        </ul>
        <ul class="h-right g-c">
            <li style="margin-right: 14px;" class="g-active">
                <div class="h-login">
			<c:choose>
				<c:when test="${empty currUser }">
					<a href="login.jsp">登录</a>
				</c:when>
				<c:otherwise>
					<c:if test="${empty currUser.headImg }">
						<a href="personal.jsp"><img src="headImg/headimg02.png" class="head-img"/><span class="s-name">${currUser.nickName }</span></a>
					</c:if>
					<c:if test="${not empty currUser.headImg }">
						<a href="personal.jsp"><img src="${currUser.headImg }" class="head-img"/><span class="s-name">${currUser.nickName }</span></a>
					</c:if>
				</c:otherwise>
			</c:choose>       
                </div>
            </li>
            <li><a>联系我们</a></li>
            <li class="a-header3"><a>退出</a></li>
        </ul>
       
    </header>
    </div>
	    <div id="xz">
	    	<a href="index.jsp"><p style="font-weight:800;font-size: 35px;color: #ED4040;margin-left: 0px;">先知</p></a>
	    </div>
	    
	    <div id="x-down">
	    	<div id="x-down-1">
	    	
	    	<c:choose>
				<c:when test="${empty otherUser }">
					<a href="login.jsp">加载失败</a>
				</c:when>
				<c:otherwise>
					<c:if test="${empty otherUser.headImg }">
						<a href="personal.jsp"><img src="headImg/headimg02.png" class="a-img"/><span class="nc">${currUser.nickName }</span></a>
					</c:if>
					<c:if test="${not empty otherUser.headImg }">
						<a href="personal.jsp"><img src="${otherUser.headImg }" class="a-img"/><span class="nc">${currUser.nickName }</span></a>
					</c:if>
				</c:otherwise>
			</c:choose>
			  
	    		<div>
	    			<iframe src="danmu.jsp" style="margin-left: 10px;margin-top: -260px;width: 70%;height: 600px;"></iframe>
                </div> 
                
	    		<!-- <a href="xiaobian.jsp"><input type="button" id="tian" value="发布文章"/></a>
	    		<input type="button" id="tian1" value="发布视频"/> -->
	    	</div>
	    	
	    	<!-- <div id="x-down-2"> -->
	    		 <div class="news-nav" style="margin-left:138px;">
                    <ul class="g-c">
                        <li class="news-li-active"><a href="frame/shoucang.jsp">收藏</a></li>
                        <li><a href="loadDiscuss_UserCenterAction">动态</a></li>
                      <!--   <li><a href="loadget">私信</a></li> -->
                        
                    </ul>
                </div>        
	    	<!-- </div> -->
	    	
	    	<div id="x-down-3">
	    		<div>	    	
		    		<a href="loadFollowUser_otherUserAction?user.id=${currUser.id }" target="userZone"><li style="font-size: 22px;font-weight: bold;margin-left: 40px;">2</li>
		    		<span style="color: gray;margin-top: -25px;margin-left: 75px;display: block;">关注</span></a>
	    		</div>
	    		<hr style="width: 1px;height: 30px;margin-left: 170px;margin-top: -25px;" />	    		
	    		<div>
		    		<a href="loadFans_otherUserAction?user.id=${currUser.id }" target="userZone"><li style="font-size: 22px;font-weight: bold;margin-left: 200px;margin-top: -30px;">2</li>
		    		<span style="color: gray;margin-top: -25px;margin-left: 240px;display: block;">粉丝</span></a>
	    		</div>
	    	</div>	    	
	    	<div id="x-down-4">
	    		<iframe name="userZone" src="frames/shoucang.jsp" style="width:688px;height:600px;" ></iframe>
	    	</div>
	    </div>
</div>
</body>
</html>
