<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="java.util.*" %>
<html>
<head>
	<meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
	<title>My EPSI - Connexion</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/index.css"/>
</head>
<body>
        <div class="container-fluid">
            <div class="row">
                <div class='col-12'>
                    <h1 class='title form'>My EPSI</h1>
                </div>
                <div class="col-6 form">
                    <h2 class='title'>Connexion</h2>
                    <div class='col-12 form-group'>
                        <form method='post'>
                            <label class='label'>Email :</label>
                            <input type='email' id="con_email" name='con_email' placeholder='Tapez votre email' class='form-control' required/>
                            <label class='label'>Mot de passe :</label>
                            <input type='password' id="con_pwd" name='con_pwd' placeholder='Tapez votre mot de passe' class='form-control' required/>
                            <input type='submit' value='Connexion !' name='connexion' class='btn btn-success btnForm'/>
                        </form>
                    </div>
                </div>
                <div class="col-6 form">
                    <h2 class='title'>Inscription</h2>

                    <div class='col-12 form-group'>
                        <form method='post'>
                            <label class='label'>Email :</label>
                            <input type='email' name='ins_email' placeholder='Tapez votre email' class='form-control' required/>
                            <label class='label'>Nom :</label>
                            <input type='text' name='ins_name' placeholder='Tapez votre nom' class='form-control' required/>
                            <label class='label'>Mot de passe :<a id='mdpCourt'></a></label>
                            <input type='password' id='ins_pwd1' name='ins_pwd1' placeholder='Tapez votre mot de passe' class='form-control' required/>
                            <label class='label'>Mot de passe :<a id='valMdp'></a></label>
                            <input type='password' id='ins_pwd2' name='ins_pwd2' placeholder='Confimez votre mot de passe' class='form-control' required/>
                            <input type='submit' value='Inscription !' id='inscription' name='inscription' class='btn btn-success btnForm'/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="js/formConfirm.js"></script>
    </body>
</html>