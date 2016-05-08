<!DOCTYPE html>

<html>
    <head>
        <title>D.E.R.P.</title>
        <!--<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'> this is a font i like please check out-->
        <style type="text/css">
       
 @import url(http://fonts.googleapis.com/css?family=Roboto:400,100);   
		body {
  background: url(https://dl.dropboxusercontent.com/u/23299152/Wallpapers/wallpaper-22705.jpg) no-repeat center center fixed; 
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
font-family: 'Roboto', sans-serif;
}
		 #main{
                float:center;
                height: 1000px;
                border-left-style: solid;
                border-right-style: solid;
                border-right-color: #000000;
                border-left-color: #000000;
                position:relative;
                z-index:1;
            }
		#Profilepicture{
		position:absolute;
		left: 15%;
		top:30%;
		}

		#buttonBar{
                border-radius: 25px;
                position:relative;
                z-index:3;
                text-align: center;
                float:center;
                border-style:solid;
                height: 70px;
                width:85%;
                margin-top:-900px;
                left: 7.5%;
                font-size:200%;
                background-color: #0099ff;
            }
  
            #Personalinfo{
 	        border-radius: 25px;
	        border:3px solid #00;		
	        position: relative;
	        float:right;
	        text-align:center;
	        border-style:solid;
                height: 250px;
                width: 30%;
                margin-top: 80px;
                left: -20%;
                font-size:200%;
                background-color: #0099ff;
            }
		 #yourpollsbox{
                border-radius: 25px;
	         border:3px solid #00;		
                position: relative;
                text-align: center;
                float:right;
                border-style:solid;
                height: 400px;
                width:65%;
                margin-top: 400px;
                left: 15%;
                font-size:200%;
                background-color: #0099ff;
            }
	
		 #pollboxOne{
                
	        border:3px solid #00;		
                position:absolute ;
                text-align: center;
                border-style:solid;
		left: 2%;
		bottom: -1%;                
		height: 80%;
                width: 30%;
		z-index:4;
                font-size:100%;
                background-color: #e6e6e6;
            }
		#pollboxTwo{
                
	        border:3px solid #00;		
                position:absolute ;
                text-align: center;
                border-style:solid;
		left: 110%;
		z-index:4;
		bottom: -1%;                
		height: 100%;
                width: 100%;
                font-size:100%;
                background-color: #e6e6e6;
            }
		#pollboxThree{
                
	       border:3px solid #00;		
                position:absolute ;
                text-align: center;
                border-3style:solid;
		z-index:4;
		left: 110%;
		bottom: 0;                
		height: 100%;
                width: 100%;
                
                font-size:100%;
                background-color: #e6e6e6;
            } 
		
		h1{
	font-size: 350%;	
         color: #0099ff;
	}  
	h2{
	font-size: 250%;	
         color: #0099ff;
	} 
            #YourPolls{
                 z-index: 4;
		position: absolute;
		left: 10%;
                top: 10px;
            	color: #000000;
	}
            #home{
                z-index: 4;
		position: absolute;
		left: 10%;
                top: 10px;
            	color: #000000;
	}
            #Newpoll{
                z-index: 4;
		position: absolute;
		right: 10%;
                top: 10px;
           	color: #000000;
	 }
		
		
	
		
	  .Profilepicture{
                display: block;
                
            }
 </style>
    </head>

    <body>
        <div id = "entirety">
            <div id = "main">
               <h1>D.E.R.P</h1>
		
            </div>
       </div>
<div id="Profilepicture">
		<img src="http://www.ycp.edu/media/t4_1714435898215170617-270x270.jpg" alt="None"
		 width="300" height="250"  >
		</div>

<div id = "buttonBar">
                <div id = "Yourpolls">
                    <a href="/derp/userHome" class="YourPolls">HomePage</a>
                </div>
                <div id = "home">
                    <a href="/derp/createPoll" class="home">New Poll</a>
                </div>
                <div id = "Newpoll">
                    <a href="profile" class="Newpoll">Loop</a>
                </div>
            </div>
        </div>
	    <div id = "Personalinfo">
				<a class="Personalinfo">Personal Information</a>
            			<p>${fullname} (${username})</p>
				<p>${email} </p>
				<p>${Inst} </p>
		</div>
		
		<div id = "yourpollsbox">
		    <div id= "yourpolls">
		        <a class="yourpolls">Your Polls</a>
		 <div id = "pollboxOne">
		    <div id= "pollOne">
		        <a href="/derp/poll?user=${poll1user}&title=${fullname1}" class="pollOne">Example Poll one</a>
		<p>${fullname1} </p>
				<p>${info1} </p>
		<div id = "pollboxTwo">
		    <div id= "pollTwo">
		        <a href="/derp/poll?user=${poll2user}&title=${fullname2}" class="pollTwo">Example Poll Two</a>
				<p>${fullname2} </p>
				<p>${info2} </p>
	<div id = "pollboxThree">
		    <div id= "pollThree">
		        <a href="/derp/poll?user=${poll3user}&title=${fullname3}" class="pollThree">Example Poll Three</a>
				<p>${fullname3} </p>
				<p>${info3} </p>
</body>		
</html>
