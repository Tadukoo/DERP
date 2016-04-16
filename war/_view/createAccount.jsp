<!DOCTYPE html>


<! File taken and slightly modified from CS320 Library Solution Lab 03 login.jsp !>

<html>
	<head>
		<title>Create a DERP Account</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
		
	</head>

	<body>
	<Tform>
		Please enter the following fields.
	</Tform>
		<form>
			
				User Name:
					<input type="text" id="username" name="username" size="12" value="${username}" />
				Password:
					<input type="text" id="password" name="password" size="12" value="${password}" />
				First Name:
					<input type="text" id="first" name="fist_name" size="12" value="${firstname}" />
				Last Name:
					<input type="text" id="last" name="last_name" size="12" value="${lastname}" />
				Email:
					<input type="text" id="email" name="email" size="12" value="${email}" />
				Institution:
					<input type="text" id="institution" name="institution" size="12" value="${institution}" />
					
			<button type="button" value="bttn" onClick="WriteToFile(this.form)">Sign Up</button>
		</form>
		<script type = "text/javascript">
		function WriteToFile(passForm)
		{
			var fso = new ActiveXObject("Scripting.FileSystemObject");
			var s = fso.CreateTextFile("C:\\users.csv", true);
			s.WriteLine(document.passForm.username.value + "," + document.passForm.password.value);
			s.Close();
		}
		</script>
	</body>
</html>