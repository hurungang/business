<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set name="errorKey" value="%{@com.runtech.web.util.Constant@EXCEPTION}"/>
			<div class="notification error png_bg">
				<a href="#"
					class="close"><img src="<%=request.getContextPath()%>/images/cross_grey_small.png"
					title="Close this notification" alt="close"></a>
				<div>${requestScope[errorKey]}</div>
			</div>
