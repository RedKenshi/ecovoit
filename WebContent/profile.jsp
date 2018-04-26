<!DOCTYPE html>
<html>
	<head>
		<title>ECO-Voiturage</title>
		<meta charset="utf-8">
		<!-- Required meta tags -->
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="css/main.css">
		
		<!-- Material Design for Bootstrap fonts and icons -->
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">
	</head>
	<body>
		<div id="main-wrapper">
			<div id="topbar">
					<div class="topright">
						<button id="btn-log-wrapper" class="btn">CONNEXION</button>
					</div>
				<div class="topright">
					<a href="profile.jsp" class="btn">PROFIL</a>
				</div>
			</div>
			<div id="sidebar-wrapper">
				<ul id="sidebar-nav">
					<div id="bluePad">
						<li id="avatarWrapper">
						  <img src='res/ecologo.png'>
						</li>
						<li id="titleWrapper">
							<p>Eco Voiturage</p>
						</li>
					</div>
					<hr>
					<li>
						<input type="text" id="searchOnMap">
					</li>
					<hr>
					<li>
						<a href="" id="back">PROPOSER UN TRAJET</a>
					</li>
					<li>
						<a href="" id="back">RECHERCHER UN TRAJET</a>
					</li>
					<li>
						<input placeholder="Lieu de départ" type="text" id="searchOnMapDepart">
					</li>
					<br>
					<li>
						<input placeholder="Lieu d'arrivé" type="text" id="searchOnMapArrive">
					</li>
					<li>
						<a href="" id="btn-travel-launch">LET'S GO</a>
					</li>
					<hr>
					<li>
						<a href="index.php">LOG OUT</a>
					</li>
				</ul>
			</div>
			<div id="main_wrapper">
				<form method="post" action="register">
			           <fieldset>
							<input class="form-control textfield" id="firstname" name="prenom" value="${form['prenom']}" placeholder="Prenom ..." type="text" autocomplete="new-password">
							<span class="erreur">${erreurs['email']}</span>
							<input class="form-control textfield" name="name" value="${form['name']}" placeholder="Nom ..." type="text" autocomplete="new-password">
							<span class="erreur">${erreurs['name']}</span>
							<input class="form-control textfield" id="phone" name="telephone" value="${form['telephone']}" placeholder="TÃ©lÃ©phone ..." type="text" autocomplete="new-password">
							<input class="form-control textfield" id="mail1" name="email" value="${form['email']}" placeholder="Mail address ..." type="text" autocomplete="new-password">
							<input class="form-control textfield" id="pass1" name="pwd1" value="${form['pwd1']}" placeholder="Password ..." type="password" autocomplete="new-password">
							<input class="form-control textfield" id="pass2" name="pwd2" value="${form['pwd2']}" placeholder="Confirm password ..." type="password" autocomplete="new-password">
							<br>
							<input id="register" type='submit' class='btn' value="Enregistrement">
							<p class="info">${actionMessage}</p>
					</fieldset>
			    </form>
			</div>
		</div>
	</body>
		
	<!-- JS -->
	
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>