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
		#searchresults{
                 position:relative;
                text-align: center;
                float:center;
                border-style:solid;
                height: 700px;
                width:70%;
                margin-top:-42%;
                left: 15%;
                font-size:200%;
                background-color: #e6e6e6;
            }
		 #pollboxThree{
                
	        border:3px solid #00;		
                position:absolute ;
                text-align: center;
                border-style:solid;
		z-index:4;
		left: 5%;
		bottom:10%;            
		height: 25%;
                width: 90%;
                font-size:100%;
                background-color: #0099ff;
            }
		 #pollboxTwo{
                
	        border:3px solid #00;		
                position:absolute ;
                text-align: center;
                border-style:solid;
		z-index:4;
		left: 5%;
		bottom: 40%;              
		height: 25%;
                width: 90%;
                font-size:100%;
                background-color:#0099ff;
            }
		#pollboxOne{
                
	        border:3px solid #00;		
                position:absolute ;
                text-align: center;
                border-style:solid;
		z-index:4;
		left: 5%;
		bottom: 69%;             
		height: 25%;
                width: 90%;
                font-size:100%;
                background-color: #0099ff;
            }
	#searchname{
		
                float:center;
                position: relative;
                font-size: 10;
            	color: #00000;
		}
}

.buttonone{
     position: absoloute;
    
    border: none;
    color: white;
  
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    left: 20%;
}
.buttontwo{
     position: absoloute;
   
    top:-15%    
   margin-left: 80%;
}
#buttonboxone{
                
                position:relative;
                z-index:3;
                text-align: center;
                float:center;
               
                height: 30px;
                width:5%;
                margin-top:0;
                left: 45%;
                font-size:200%;
                
            }
#buttonboxtwo{
                
                position:relative;
                z-index:3;
                text-align: center;
                float:center;
                
                height: 30px;
                width:100%;
                margin-top:-76%;
                left: 100%;
                font-size:200%;
                
            }
</style>
  </style>
    </head>

    <body>

<div id = "entirety">
    
<div id="main">
<div id ="searchbox">
      	
<section class="searchbar">
	<form action="" method="">		    
		<input type="search" placeholder="Search Polls...">		    	
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
  
                 <div id = "searchresults">
	 	        
		 Search Results 

<div id = "pollboxOne">
		    <div id= "pollOne">
		        <a href="" class="pollOne">Poll</a>
				<p>${fullname1}</p>
                <p>${info1}</p>
				
</div>
</div>
<div id = "pollboxTwo">
		    <div id= "pollTwo">
		        <a href="" class="pollTwo"> Poll </a>
				<p>${fullname2}</p>
                <p>${info2}</p>
</div>

</div>
<div id = "pollboxThree">
		    <div id= "pollThree">
		        <a href="" class="pollThree">Poll </a>
				<p>${fullname3}</p>
                <p>${info3}</p>
</div>

</div>
</div>	
   <div>  	
 <div id ="buttonboxone">
 <form method="post">
<input type="button" class="buttonone" value="Back">
</form>
 <div>
<div id ="buttonboxtwo">
 <form method="post">
<input type="button" class="buttontwo" value="Next">
</form>
 <div> 
</div>
</div>

</body>
</html>
