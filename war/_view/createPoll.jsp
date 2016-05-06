<!DOCTYPE html>

<html>
    <head>
        <title>D.E.R.P.</title>
        <!--<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'> this is a font i like please check out-->
        <style type="text/css">
 		#main{
                float:center;
                height: 500px;
                border-left-style: double;
                border-right-style: solid;
                position:relative;
                z-index:0;
            }
		#name {
                float: left;
                font-size: 300%;
           	color: #0099ff;
		 }
            #fullName{
                float:left;
                margin-top:4%;
                margin-left:-9%;
                font-size: 150%;
                color: #0099ff;
		}

		

@import url(http://fonts.googleapis.com/css?family=Roboto:400,100);

body {
  background: url(https://dl.dropboxusercontent.com/u/23299152/Wallpapers/wallpaper-22705.jpg) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  font-family: 'Roboto', sans-serif;
}

.poll-box {
  position:fixed;
  float: center;
  top: 250px;
  left: 20%;
  padding: 40px;
  width: 50%;
  height: 30%;
  background-color: #0099ff;
  margin: 0 auto 10px;
  border-radius: 2px;
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  overflow: hidden;
}

.poll-box h1 {
  font-weight: 100;
  text-align: center;
  font-size: 2.3em;
}

.poll-box input[type=submit] {
  width: 100%;
  display: block;
  margin-bottom: 10px;
  position: relative;
}

.poll-box input[type=text], input[type=text] {
top: 20px;  
height: 80px;
  font-size: 16px;
  width: 100%;
  margin-bottom: 10px;
  -webkit-appearance: none;
  background: #fff;
  border: 1px solid #d9d9d9;
  border-top: 1px solid #c0c0c0;
  /* border-radius: 2px; */
  padding: 0 8px;
  box-sizing: border-box;
  -moz-box-sizing: border-box;
}

.poll-box input[type=text]:hover, input[type=text]:hover {
  border: 1px solid #b9b9b9;
  border-top: 1px solid #a0a0a0;
  -moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
  box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
}

.Submit {
  text-align: center;
  font-size: 14px;
  font-family: 'Arial', sans-serif;
  font-weight: 700;
  height: 36px;
  padding: 0 8px;
/* border-radius: 3px; */
/* -webkit-user-select: none;
  user-select: none; */
}

.poll-submit {
  /* border: 1px solid #3079ed; */
  border: 5px;
  color: #000;
  text-shadow: 0 1px rgba(0,0,0,0.1); 
  background-color: #4d90fe;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
}

.poll-submit:hover {
  /* border: 1px solid #2f5bb7; */
  border: 0px;
  text-shadow: 0 1px rgba(0,0,0,0.3);
  background-color: #357ae8;
  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
}

.poll-box a {
  text-decoration: none;
  color: #666;
  font-weight: 400;
  text-align: center;
  display: inline-block;
  opacity: 0.6;
  transition: opacity ease 0.5s;
}
.createpoll{
font-size: 300%;
color: #00000;
text-align: center;
}

Submit {
     position:absolute;
    background-color: #4CAF50;
    border: none;
    color: white;
    border-radius: 12px;
    padding: 10px 15px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    left: 5%;
}		


 </style>
	
    </head>
	<body>
        

<div id = "entirety">
            <div id = "main">
                <div id = "name">
                    D.E.R.P.
                </div>
                <div id = "fullName">
                   <p> Dedicated Electronic Representative Pollings<p>
                </div>
            </div>
           
    <div class="poll-box">
    <div class= "createpoll">	
	Create Poll
   <form method="post">
    <input type="text" name="polltitle" placeholder="poll title">
    <input type="text" name="polldescription" placeholder="poll description">
<form method="post">
<input type="button" class="Submit" value="Submit">
</form>

    
</form>
      
  </div>

</div>
	 
</body>		
</html>
