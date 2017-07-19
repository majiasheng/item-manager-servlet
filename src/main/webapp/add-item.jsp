<html>
<head>
<title>Add Item</title> </head>
<body>
<h3>Add Item</h3>

<form method="POST" action="/additem" id="additem-form">
	<input type="hidden" name="command" value="ADD" />
	Name: <input type="text" name="name" required="required"/> <br/>
	
	Type: 
	<select name="type" required="required">
		<option value="other">other</option>
		<option value="appliance">Appliance</option>
		<option value="book">Book</option>
		<option value="appliance">Clothing/Wearable</option>
		<option value="electronic">Electronic</option>
		
	</select><br/>
	Price: <input type="text" name="price" /> <br/>
	Date acquired: 
	<select name="month" required="required">
		<option value="month">Month</option>
		<option value="0">January</option>
		<option value="1">February</option>
		<option value="2">March</option>
		<option value="3">April</option>
		<option value="4">May</option>
		<option value="5">June</option>
		<option value="6">July</option>
		<option value="7">August</option>
		<option value="8">September</option>
		<option value="9">October</option>
		<option value="10">November</option>
		<option value="11">December</option>
	</select>
	<select name="day" required="required">
		<option value="day">Day</option>
		<%
			for(int i = 1; i <= 31; i++) {
				int val = i - 1;
				out.println("<option value=" + val + ">" + i + "</option>");
			}
		%>
	</select>

	<select name="year" required="required">
		<option value="year">Year</option>
		<%
			
			for(int i = 2017; i >= 1900; i--) {
				int val = i - 1900;
				out.println("<option value=" + val + ">" + i + "</option>");
			}
		%>
	</select>
	<br/>
	<p>
		<input type="submit" name="Submit" value="Submit" />
	</p>
</form>
</body>
</html>