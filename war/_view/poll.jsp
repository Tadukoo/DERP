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
                background-color: #0099ff
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
            #yourPolls{
                margin-left: 27%;
                margin-top:2%;
            }
            #home{
                float: center;
                margin-left: 5%;
                margin-top:-3.5%;
            }
            #newPoll{
                float: right;
                margin-right:-3%;
                margin-top:-3%
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
            .NewPoll {
                display: block;
                height: 40px;
                width: 300px;
            }
            #title{
                font-size: 350%;
                text-align:center;
            }
            #desc{
                border-style:solid;
                font-size: 200%;
                margin-top:10%;
                margin-left:15%;
                text-align:center-left;
                margin-right:50%;
            }
            #link{
                border-style:solid;
                font-size: 200%;
                margin-top:-10%;
                margin-left:55%;
                text-align:center-right;
                margin-right:18%;
            }
        #Agreebtn{
            float:center;
            margin-top:8%;
            margin-left: 18%;
            position:relative;
            z-index:13;
        }
        .Agreebtn {
            border-radius:25px;
            background-color: #0099ff;
            border: none;
            color: white;
            padding: 65px 172px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 50px;
        }
        .Agreebtn:hover {
            box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
        }
        #Disagreebtn{
            float:center;
            margin-top:-1%;
            margin-left: 40%;
            position:relative;
            z-index:13;
        }
        .Disagreebtn {
            border-radius:25px;
            background-color: #0099ff;
            border: none;
            color: white;
            padding: 65px 172px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 50px;
        }
        .Disagreebtn:hover {
            box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
        }

        #agree{
            margin-left:33%;
            margin-top:3%;
    
        }
        #disagree{
            margin-left:63%;
            margin-top:-4%;
            
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
                <div id = "yourPolls">
                    <a href="http://designshack.net/" class="YourPolls">Your Polls</a>
                </div>
                <div id = "home">
                <a href="http://designshack.net/" class="Home">Home</a>
                </div>
                <div id = "other">
                <a href="http://designshack.net/" class="Other">Other</a>
                </div>
                <div id = "newPoll">
                    <a href="http://designshack.net/" class="NewPoll">NewPoll</a>
                </div>
            </div>
        </div>
        <div id="title">
        ${title}
        </div>
        <div id="desc">
            Description of Poll:<br>
            ${description}
        </div>
        <div id="link">
            Link to the Poll:<br>
            ${link}
        </div>
        <div id="agree">
            Agree<br>
            <p id="Agree"></p>
        </div>
        <div id="disagree">
            Disagree<br>
            <p id="Disagree"></p>
        </div>
        <script language="JavaScript">
            function getParameter() {
                var val = window.location.href;
                var title = val.slice(val.indexOf("?")+1,val.indexOf("="));
                var link = val.slice(val.indexOf("=")+1,val.indexOf("+"));
                var desc = val.slice(val.indexOf("+")+1,val.indexOf("-"));
                var agree = val.slice(val.indexOf("-")+1,val.indexOf("#"));
                var disagree = val.slice(val.indexOf("#")+1);
                title = title.replace("%20"," ");
                desc = desc.replace("%20"," ");
                link = link.replace("%20"," ");
                document.getElementById("Title").innerHTML = title;
                document.getElementById("Desc").innerHTML = desc;
                document.getElementById("Link").innerHTML = link;
                document.getElementById("Agree").innerHTML = agree;
                document.getElementById("Disagree").innerHTML = disagree;
            }
            getParameter();
        </script>
        <div id = "Agreebtn">
            <button type="button" class="btn postPoll" id="Agreebtn" value ="pollpage">Agree</button>
        </div>
        <div id = "Disagreebtn">
            <button type="button" class="btn postPoll" id="Disagreebtn" value ="pollpage">Disagree</button>
        </div>
    </body>
</html>
