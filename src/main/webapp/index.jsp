<%@page import="dbm.ItemManager"%>
<%@page import="model.Item"%>
<%@page import="java.util.List"%>
<html>
<body>
<h2><a href="index.jsp">Items</a></h2>

<table style="border: 1px solid black;">
	<tr>
		<td style="border: 1px solid black;">Name</td>
		<td style="border: 1px solid black;">Type</td>
		<td style="border: 1px solid black;">Date Acquired</td>
		<td style="border: 1px solid black;">Price</td>		
	</tr>
	<%
	List<Item> items = ItemManager.getItems();
	for(Item item : items) {
		out.print("<tr>");
		out.print("<td style=\"border: 1px solid black;\">" + item.getName() + "</td>");
		out.print("<td style=\"border: 1px solid black;\">" + item.getType() + "</td>");
		out.print("<td style=\"border: 1px solid black;\">" + item.getDateAquired().toString() + "</td>");
		out.print("<td style=\"border: 1px solid black;\">" + item.getPrice() + "</td>");
		out.print("<td><form action=\"/edititem\" method=\"GET\"><input type=\"hidden\" name=\"id\" value=\"" 
			+ item.getId() 
			+ "\">"
			/* + "<input type=\"hidden\" name=\"command\" value=\"EDIT\" />" */
			+ "<input type=\"submit\" name=\"command\" value=\"REMOVE\" />"
			+ "<input type=\"submit\" name=\"command\" value=\"EDIT\"></form></td>");
		out.print("<td></td>");
		out.print("</tr>");
	}
	%>
	
</table>
Add Item <input type="button" value="+" onclick="document.location.href='/add-item.jsp';">
</body>
</html>
