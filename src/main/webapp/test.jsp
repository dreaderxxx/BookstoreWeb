<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3>Testing JSP</h3>

<%!
public int add (int a, int b) {
	return a+b;
}
%>


<%
int i = 3;
%>
<br>
Value of 1+2 is <%=1+2%>
<br>
Value of i is <%=i%>
<br>
Value of adding 123+321 = <%=add(123,321)%>
</body>
</html>