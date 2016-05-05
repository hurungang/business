<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title><s:property value="%{getText('text.platform.title')}"/> - <s:property value="%{currentMenu.title}"/></title>
	<!--                       CSS                       -->

	<!-- Reset Stylesheet -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/reset.css" type="text/css"
		media="screen">

		<!-- Main Stylesheet -->
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" type="text/css"
			media="screen">

		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery_datepicker.css" type="text/css" media="screen" >  
	 	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/wsyiwyg.css" type="text/css" media="screen" >  
	 
			<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
			<link rel="stylesheet" href="<%=request.getContextPath()%>/css/invalid.css" type="text/css"
				media="screen">



				<!-- Internet Explorer Fixes Stylesheet -->

				<!--[if lte IE 7]>
			<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ie.css" type="text/css" media="screen" >
		<![endif]-->

				<!--                       Javascripts                       -->



				<!-- jQuery -->
				<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
				<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.mb.browser.js"></script>
				<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.form.js"></script>

				<!-- jQuery Configuration -->
				<script type="text/javascript"
					src="<%=request.getContextPath()%>/js/simpla.jquery.configuration.js"></script>

				<!-- Facebox jQuery Plugin -->
				<script type="text/javascript" src="<%=request.getContextPath()%>/js/facebox.js"></script>

				<!-- jQuery WYSIWYG Plugin -->
				<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.wysiwyg.js"></script>

				<!-- jQuery Datepicker Plugin -->
				<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.10.3.custom.js"></script>
				

			<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath()%>/ckeditor/ckeditor.js"></script>
			
				<!--[if IE]><script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.bgiframe.js"></script><![endif]-->


				<!-- Internet Explorer .png-fix -->

				<!--[if IE 6]>
			<script type="text/javascript" src="<%=request.getContextPath()%>/js/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->
		
