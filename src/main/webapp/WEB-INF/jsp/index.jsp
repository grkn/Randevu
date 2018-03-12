<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="UTF-8"/>
		<script src="https://unpkg.com/vue@2.5.12/dist/vue.min.js"></script>
		<script
		  src="https://code.jquery.com/jquery-3.3.1.js"
		  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		  crossorigin="anonymous">
		</script>
		<script src="https://unpkg.com/vue-router@2.0.0/dist/vue-router.js"></script>
		<script src="https://unpkg.com/vue-i18n/dist/vue-i18n.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/vue-resource/0.1.13/vue-resource.min.js"></script>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
		<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet"/>
		<link href="${pageContext.servletContext.contextPath}/lib/css/emoji.css" rel="stylesheet"/>
		<link href="${pageContext.servletContext.contextPath}/resources/css/index.css" rel="stylesheet"/>
		<!-- Optional theme 
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"/>
		-->
		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
		<script type="text/javascript">
			var contextPath = "${pageContext.servletContext.contextPath}";
			localStorage.setItem('id_token', "${authToken}");
		</script>
	</head>
	<body>
		<div id="app">
			<router-view></router-view>
		</div>
		<script type="text/javascript">
			Vue.http.headers.common['Authorization'] = "Bearer " + localStorage.getItem('id_token');
		</script>
		<script src="${pageContext.servletContext.contextPath}/lib/js/config.js"></script>
		<script src="${pageContext.servletContext.contextPath}/lib/js/util.js"></script>
		<script src="${pageContext.servletContext.contextPath}/lib/js/jquery.emojiarea.js"></script>
		<script src="${pageContext.servletContext.contextPath}/lib/js/emoji-picker.js"></script>
		<script src="${pageContext.servletContext.contextPath}/resources/asset/js/messages.js"></script>
		<script src="${pageContext.servletContext.contextPath}/resources/asset/js/index.js"></script>
	</body>
</html>