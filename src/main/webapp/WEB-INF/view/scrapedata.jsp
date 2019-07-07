<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


</head>
<body>

		<h1>Scrape Data</h1>
		<table class="table table-bordered table-responsive table-hover">
			<tr>
				<th>Sender </th>
				<th>Message </th>
			</tr>
			<c:forEach var="listValue" items="${scrapedata}">
				<tr>
					<td>${listValue.sender}</td>
					<td>${listValue.subject}</td>
				</tr>
			</c:forEach>
		</table>


</body>

</html>