<%@ page contentType="text/html"%>
<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>Accounting form</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
	<div style="padding-left: 10px;">
		<form action="accountservice/operationclient" method="POST">
			<div style="width: 250px; float: left; text-align: center; border: 1px solid black">
				<h4>Operations with money</h4>
				<div>
					Client name:</br> <input name="nameFrom" />
				</div>
				<div>
					Client name to (if transfer):</br> <input name="nameTo" />
				</div>
				<div>
					Amount:</br> <input name="amount" />
				</div>
				<div style="margin: 12px;">
					<input type="radio" name="param" value="put" checked /> put <input
						type="radio" name="param" value="transfer"> transfer <input
						type="radio" name="param" value="withdraw" /> withdraw
				</div>
				<input type="submit" value="Perform" style="margin: 12px;" />
				<div style="color: red;">
				<%
				    if (request.getParameter("errors") != null) {
				        out.println(request.getParameter("errors"));
				    }
				%>
				</div>
			</div>
		</form>
		<div style="width: 200px; float: left; text-align: center;">
			<h4>Accounts balances</h4>
		<%
		    if (request.getParameter("accounts") != null) {
		        out.println(request.getParameter("accounts"));
		    }
		%>
		</div>
	</div>
</body>
</html>