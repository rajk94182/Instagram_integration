<%-- 
    Document   : index
    Created on : 22 Jan, 2016, 12:07:46 PM
    Author     : Lue Infoservices
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagram</title>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    </head>

    <body style="background-color: silver; margin-top: 10%;">
        <div class="container" >
            <div class="panel panel-primary">

                <div class="panel-heading">Instagram_Integration</div>
                <form action="analyse" method="post">
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="control-label col-sm-3">Instagram account_id</label>
                            <div class="col-sm-4">          
                                <input type="text" class="form-control" name="userId" placeholder="enter Instagram account_id" required>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="control-label col-sm-3" >number of posts to analyze</label>
                            <div class="col-sm-4">          
                                <input type="number" name="numberofPost" class="form-control" placeholder="enter number of posts to analyze" required>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="form-group" style="padding-left: 40%;">
                            <br/>  <input type="submit" class="btn btn-lg btn-primary active" value="submit">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
