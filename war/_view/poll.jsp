
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
                 margin-left:10%;
                margin-right:10%;
                position:relative;
                z-index:0;
            }
	body {
  background: url(https://dl.dropboxusercontent.com/u/23299152/Wallpapers/wallpaper-22705.jpg) no-repeat center center fixed; 
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  font-family: 'Roboto', sans-serif;
}
		#name {
                float: left;
                font-size: 300%;
           	color: #0099ff;
		 }
            #fullName{
                float:left;
                margin-top:4%;
                margin-left:-12%;
                font-size: 150%;
                color: #0099ff;
		}
		#PollTitleBox{
		border-radius: 25px;
                position:relative;
                z-index:3;
                text-align: center;
                float:center;
                border-style:solid;
                height: 100px;
                width:30%;
                margin-top:-18%;
                left: 30%;
                font-size:300%;
                background-color: #0099ff;
            }
		#descriptionbox{
                border-radius: 25px;
		position:relative;
                z-index:3;
                text-align: center;
                float:center;
                border-style:solid;
                height:500px;
                width: 30%;
                margin-top:2%;
                left: 52%;
                font-size:300%;
                background-color: #0099ff;
            }
		#totalanswerbox{
		border-radius: 25px;               
		 position:relative;
                z-index:3;
                text-align: center;
                float:center;
                border-style:solid;
                height: 200px;
                width: 35%;
                margin-top:-26.5%;
                left: 10%;
                font-size:150%;
                background-color: #0099ff;
            }
		#answerbox{
		border-radius: 25px;               
		 position:absolute;
                z-index:3;
                text-align: center;
                float:center;
                border-style:solid;
                height: 200px;
                width: 25%;
                top: 0;
                right: -.5%;
                font-size:300%;
                background-color: #999966;
            }
.totalyescount{
	position: absolute;
	top: 10%;
	left: 5%;
}
.totalnocount{
	position: absolute;
	bottom: 10%;
	left: 5%;
}
.yesbutton {
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
.nobutton {
    position:absolute;
    background-color: #f44336;
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
    bottom: 5%;
    left: 5%;
}
		
		#profileinfo{
		position:relative;
                z-index:3;
                text-align: center;
                float:center;
                border-style:solid;
                height: 120px;
                width: 30%;
                margin-top:4%;
                left: 15%;
                font-size:100%;
                background-color: #0099ff;
            }	
		#picture{
		position:absolute;
		left: 0;
		top:0%;
		}	
		#author{
		font-size:150%;
		top:0;
		
		}
		#username{
		font-size:100%;
		float:center;
		bottom: 0;
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
	<div id= "PollTitleBox">
	<title="polltitle"> Poll Title</title>
	</div>
  <div id="descriptionbox">
<summary="pollsummary"> Summary of poll</summary>
</div>
 <div id="totalanswerbox">
	<div class="totalyescount">
		Total Votes for agree:
</div>
<div class="totalnocount">
	        Total Votes for disagree:
</div>
<div id="answerbox">
<name="agrees"></name>
<name="disagrees"></name>	
<form method="post">
<input type="button" class="yesbutton" value="Agree">
</form>
<form method="post">
<input type="button" class="nobutton" value="Disagree">
</form>
</div>
</div>
<div id="profileinfo">
	<div id="picture">
		<img src="http://www.ycp.edu/media/t4_1714435898215170617-270x270.jpg" alt="None"
		 width="120" height="120"  >
		</div>
		<div id="author">	
		Author Info
		</div>
		<div id="username">
		<profilename="profilename">Author username:Professor Hake </name>
</div>

</body>
</html>

