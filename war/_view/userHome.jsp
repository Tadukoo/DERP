<!DOCTYPE html>

<html>
    <head>
        <title>D.E.R.P.</title>
        <title>DDedicated Electronic Representative Pollings</title>


        <style type="text/css">
      

  @import url(http://fonts.googleapis.com/css?family=Cabin:400);
#searchbox{
		
	position: relative;	
	top: -17%;	
	width: 40%;	
	right: -65%;
	height: 2%;
	
}
.searchbar {	
	position:relative;
	height: 100%;
	text-align: center;
	width: 100%;
}

.searchbar:before,
.searchbar:after {
	content: '';
	
	
	left: 50%;
	margin: 0 0 0 -400px;
	position:relative;
	width: 800px;
}

.searchbar:before {
	background: #444;
	background: -webkit-linear-gradient(left, #151515, #444, #151515);
	background: -moz-linear-gradient(left, #151515, #444, #151515);
	background: -o-linear-gradient(left, #151515, #444, #151515);
	background: -ms-linear-gradient(left, #151515, #444, #151515);
	background: linear-gradient(left, #151515, #444, #151515);
	top: 192px;
}

.searchbar:after {
	background: #000;
	background: -webkit-linear-gradient(left, #151515, #000, #151515);	
	background: -moz-linear-gradient(left, #151515, #000, #151515);	
	background: -o-linear-gradient(left, #151515, #000, #151515);	
	background: -ms-linear-gradient(left, #151515, #000, #151515);	
	background: linear-gradient(left, #151515, #000, #151515);	
	top: 191px;
}

.searchbar form {
	background: #111;
	background: -webkit-linear-gradient(#1b1b1b, #111);
	background: -moz-linear-gradient(#1b1b1b, #111);
	background: -o-linear-gradient(#1b1b1b, #111);
	background: -ms-linear-gradient(#1b1b1b, #111);
	background: linear-gradient(#1b1b1b, #111);
	border: 1px solid #000;
	border-radius: 5px;
	box-shadow: inset 0 0 0 1px #272727;
	display: inline-block;
	font-size: 0px;
	margin: 150px auto 0;
	padding: 20px;
	position: relative;
	z-index: 1;
}

.searchbar input {
	background: #222;
	background: -webkit-linear-gradient(#333, #222);	
	background: -moz-linear-gradient(#333, #222);	
	background: -o-linear-gradient(#333, #222);	
	background: -ms-linear-gradient(#333, #222);	
	background: linear-gradient(#333, #222);	
	border: 1px solid #444;
	border-radius: 5px 0 0 5px;
	box-shadow: 0 2px 0 #000;
	color: #888;
	display: block;
	float: left;
	font-family: 'Cabin', helvetica, arial, sans-serif;
	font-size: 13px;
	font-weight: 400;
	height: 40px;
	margin: 0;
	padding: 0 10px;
	text-shadow: 0 -1px 0 #000;
	width: 200px;
}

.ie .searchbar input {
	line-height: 40px;
}

.searchbar input::-webkit-input-placeholder {
   color: #888;
}

.searchbar input:-moz-placeholder {
   color: #888;
}

.searchbar input:focus {
	-webkit-animation: glow 800ms ease-out infinite alternate;
	-moz-animation: glow 800ms ease-out infinite alternate;
	-o-animation: glow 800ms ease-out infinite alternate;
	-ms-animation: glow 800ms ease-out infinite alternate;
	animation: glow 800ms ease-out infinite alternate;
	background: #222922;
	background: -webkit-linear-gradient(#333933, #222922);
	background: -moz-linear-gradient(#333933, #222922);
	background: -o-linear-gradient(#333933, #222922);
	background: -ms-linear-gradient(#333933, #222922);
	background: linear-gradient(#333933, #222922);
	border-color: #393;
	box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
	color: #efe;
	outline: none;
}

.searchbar input:focus::-webkit-input-placeholder { 
	color: #efe;
}

.searchbar input:focus:-moz-placeholder {
	color: #efe;
}

.searchbar button {
	background: #222;
	background: -webkit-linear-gradient(#333, #222);
	background: -moz-linear-gradient(#333, #222);
	background: -o-linear-gradient(#333, #222);
	background: -ms-linear-gradient(#333, #222);
	background: linear-gradient(#333, #222);
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	-o-box-sizing: content-box;
	-ms-box-sizing: content-box;
	box-sizing: content-box;
	border: 1px solid #444;
	border-left-color: #000;
	border-radius: 0 5px 5px 0;
	box-shadow: 0 2px 0 #000;
	color: #0099ff;
	display: block;
	float: left;
	font-family: 'Cabin', helvetica, arial, sans-serif;
	font-size: 13px;
	font-weight: 400;
	height: 40px;
	line-height: 40px;
	margin: 0;
	padding: 0;
	position: relative;
	text-shadow: 0 -1px 0 #000;
	width: 80px;
}	

.searchbar button:hover,
.searchbar button:focus {
	background: #292929;
	background: -webkit-linear-gradient(#393939, #292929);	
	background: -moz-linear-gradient(#393939, #292929);	
	background: -o-linear-gradient(#393939, #292929);	
	background: -ms-linear-gradient(#393939, #292929);	
	background: linear-gradient(#393939, #292929);
	color: #0099ff;
	outline: none;
}

.searchbar button:active {
	background: #292929;
	background: -webkit-linear-gradient(#393939, #292929);
	background: -moz-linear-gradient(#393939, #292929);
	background: -o-linear-gradient(#393939, #292929);
	background: -ms-linear-gradient(#393939, #292929);
	background: linear-gradient(#393939, #292929);
	box-shadow: 0 1px 0 #000, inset 1px 0 1px #222;
	top: 1px;
}
	top: 1px;
}

@-webkit-keyframes glow {
    0% {
		border-color: #0099ff;
		box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
    }	
    100% {
		border-color: #0099ff;
		box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000;
    }
}

@-moz-keyframes glow {
    0% {
		border-color: #0099ff;
		box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
    }	
    100% {
		border-color: #0099ff;
		box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000;
    }
}

@-o-keyframes glow {
    0% {
		border-color: #0099ff;
		box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
    }	
    100% {
		border-color: #0099ff;
		box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000;
    }
}

@-ms-keyframes glow {
    0% {
		border-color: #0099ff;
		box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
    }	
    100% {
		border-color: #0099ff;
		box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000;
    }
     @keyframes glow {
    0% {
		border-color: #0099ff;
		box-shadow: 0 0 5px rgba(0,255,0,.2), inset 0 0 5px rgba(0,255,0,.1), 0 2px 0 #000;
    }	
    100% {
		border-color: #0099ff;
		box-shadow: 0 0 20px rgba(0,255,0,.6), inset 0 0 10px rgba(0,255,0,.4), 0 2px 0 #000;
    }
}
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
                font-size: 600%;
            	color: #0099ff;
		}
            #fullName{
                float:left;
                position: relative;
		margin-top: 5%;
                margin-left:-18.5%;
                font-size: 150%;
            	color: #0099ff;
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
		#buttonBar{
                border-radius: 25px;
                position:relative;
                z-index:3;
                text-align: center;
                float:center;
                border-style:solid;
                height: 80px;
                width:45%;
                margin-top:-45%;
                left: 55%;
                font-size:200%;
                background-color: #0099ff;
            }
		#Infobox{
		font-size: 150%;		
		border-radius: 25px;
	         border:3px solid #00;		
                position: relative;
                text-align: justify;
   		 text-justify: inter-word;
                float:right;
                border-style:solid;
                height: 350px;
                width:50%;
                margin-top: 10%;
                left: 0;
                font-size:200%;
                background-color: #0099ff;
            }
		
           
            .Signup{
                   text-decoration: underline;
		 z-index: 4;
		position: absolute;
		left: 10%;
                bottom: 10%;
            	color: #000000;
            }
            .Login{
                 text-decoration: underline;
		 z-index: 4;
		position: absolute;
		left: 40%;
                bottom: 10%;
            	color: #000000;
              }
		.home{
                z-index: 4;
		position: absolute;
		left: 70%;
                bottom: 10%;
            	color: #000000;
	}
	 
	h3{
	font-size: 150%;	
	} 
		 #hotpollsbox{
                border-radius: 25px;
	         border:3px solid #00;		
                position: relative;
                text-align: center;
                float:left;
                border-style:solid;
                height: 275%;
                width:65%;
                margin-top: -40%;
                left: -90%;
                font-size:100%;
                background-color: #0099ff;
            }
		 #pollboxThree{
                
	        border:3px solid #00;		
                position:absolute ;
                text-align: center;
                border-style:solid;
		left: 5%;
		z-index:4;
		bottom: 0;                
		height: 25%;
                width: 90%;
                font-size:100%;
                background-color: #e6e6e6;
            }
		 #pollboxTwo{
                
	        border:3px solid #00;		
                position:absolute ;
                text-align: center;
                border-style:solid;
		z-index:4;
		left: 5%;
		bottom: 30%;                
		height: 25%;
                width: 90%;
                font-size:100%;
                background-color: #e6e6e6;
            }
		#pollboxOne{
                
	        border:3px solid #00;		
                position:absolute ;
                text-align: center;
                border-style:solid;
		z-index:4;
		left: 5%;
		bottom: 60%;                
		height: 25%;
                width: 90%;
                font-size:100%;
                background-color: #e6e6e6;
            }
		
  </style>
    </head>

    <body>

<div id = "entirety">
     		<div id="main">
<div id ="searchbox">
      	
<section class="searchbar">
	<form action="" method="post">
		<input type="search" name="search" placeholder="Search Polls...">		    	
		<button>Search</button>
	</form>
</section>
</div>	 
            	    
 <div id = "name">
                    D.E.R.P.
                </div>
                <div id = "fullName">
                   <p> Dedicated Electronic Representative Pollings<p>
                </div>
            </div>
  
                 <div id = "buttonBar">
		<div id = "signup">                   
		 <a href="/derp/createPoll" class="Signup">Create Poll</a>
                	 </div>
			<div id = "Login">
                   <a href="/derp/profile" class="Login">Profile</a>
                    	 </div>	
			<div id = "home">
                    <a href="http://localhost:8081/derp/userHome" class="home">Home</a>
                </div>
</div>	
	</div>
 </div>		
 <div id = "Infobox"> 
<div>Why Derp?</div>
 <div>Derp is an online survey site. Users can post and vote on polls. However unlike most survey sites ours will be more secure verification of the ip address of registered users taking polls. This means the data collected by site will be far more reliable. </div>
<div id = "hotpollsbox"> 	 
<h3> Hot Polls</h3>
<div id = "pollboxOne">
		    <div id= "pollOne">
		        <a href="/derp/poll?user=${poll1user}&title=${fullname1}" class="pollOne">Poll </a>
                <p>${fullname1}</p>
                <p>${info1}</p>
</div>
</div>
<div id = "pollboxTwo">
		    <div id= "pollTwo">
		        <a href="/derp/poll?user=${poll2user}&title=${fullname2}" class="pollTwo">Poll </a>
                <p>${fullname2}</p>
                <p>${info2}</p>
</div>

</div>
<div id = "pollboxThree">
		    <div id= "pollThree">
		        <a href="/derp/poll?user=${poll3user}&title=${fullname3}" class="pollThree">Poll </a>
                <p>${fullname3}</p>
                <p>${info3}</p>
</div>

</div>
</div>


</body>
</html>
