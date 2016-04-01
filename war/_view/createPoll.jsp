<!DOCTYPE html>

<html>
    <head>
        <title>D.E.R.P.</title>
        <!--<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'> this is a font i like please check out-->
        <style type="text/css">
            #main{
                float:center;
                height: 1000px;
                border-left-style: solid;
                border-right-style: solid;
                border-right-color: #000000;
                border-left-color: #000000;
                margin-left:200px;
                margin-right:200px;
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
                height: 100px;
                width:85%;
                margin-top:-850px;
                margin-left:100px;
                font-size:300%;
                background-color: #0099ff;
            }
            #name{
                float: left;
                font-size: 300%;
            }
            #fullName{
                float:left;
                margin-top:5%;
                margin-left:-16.5%;
                font-size: 150%;
            }
            #Yourpolls{
                margin-left: 10%;
                margin-top:1%;
            }
            #home{
                float: center;
                margin-left: 40%;
                margin-top:-3%;
            }
            #other{
                float: right;
                margin-right:4%;
                margin-top:-3%
            }
            .YourPolls {
                display: block;
                height: 40px;
                width: 300px;
            }
            .Home {
                display: block;
                height: 40px;
                width: 300px;
            }
            .Other {
                display: block;
                height: 40px;
                width: 300px;
            }
            #CreateLabel{
                text-align: center;
                float: center;
                font-size: 500%;
                border-style: solid;
                border-radius: 25px;
                margin-left: 30%;
                margin-top: 2%;
                margin-right: 30%;
            }
            #PollTitle{
                position:relative;
                margin-top:5%;
                margin-left:23%;
                z-index:12;
                font-size:200%;
            }
            #LinkTo{
                position:relative;
                float: right;
                margin-top:-5%;
                margin-right: 20%;
                z-index:13;
                font-size:200%;
            }
            #PollDesc{
                position:relative;
                margin-top:2%;
                margin-left: 23%;
                z-index:13;
                font-size:200%;
            }
            #PostPoll{
                float:center;
                margin-top:10%;
                margin-left: 30%;
                position:relative;
                z-index:13;
            }
            .postPoll {
                border-radius:25px;
                background-color: #0099ff;
                border: none;
                color: white;
                padding: 65px 192px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 50px;
            }
            .postPoll:hover {
                box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
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
                    Dedicated Electronic Representative Polling
                </div>
            </div>
            <div id = "buttonBar">
                <div id = "Yourpolls">
                    <a href="http://designshack.net/" class="YourPolls">Your Polls</a>
                </div>
                <div id = "home">
                    <a href="http://designshack.net/" class="Home">Home</a>
                </div>
                <div id = "other">
                    <a href="http://designshack.net/" class="Other">Other</a>
                </div>
                <div id = "login">
                	<a href="login" class = "Login">Login</a>
                </div>
            </div>
            <div id = "CreateLabel">
                Create Poll
            </div>
            <form action="${pageContext.servletContext.contextPath}/createPoll" method="post">
            <div id = "PollTitle">
                Poll Title <br>
                <input type="text" name="title" size="30" value="${title}" />
            </div>
            <div id = "LinkTo">
                Personal Link To <br>
                <input type="text" name="link" size="60" value="${link}" />
            </div>
            <div id = "PollDesc">
                Poll Description <br>
                <input type="text" name="description" size="133" value="${description}" />
            </div>
            <div id = "PostPoll">
            	<input type="Submit" name="submit" value="Create Poll">
                <button type="button" class="btn postPoll" id="post" value ="pollpage" onclick="storeInfo()">Post Poll</button>
            </form>
            </div>
        </div>
        
        
    <script>
        function storeInfo(){
            var title,desc,link,hyper;
            title=document.getElementById("form");
            desc=title.elements["pollDesc"].value;
            link=title.elements["linkTo"].value;
            title=title.elements["pollTitle"].value;
            var agree="0";
            var disagree="0";
        }
    </script>
    
    
    
    </body>
</html>
