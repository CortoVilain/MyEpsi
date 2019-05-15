<%@page import="fr.epsi.jeeProject.beans.Blog"%>
<%@page import="fr.epsi.jeeProject.dao.BlogDao"%>
<%@page import="fr.epsi.jeeProject.beans.Utilisateur"%>
<%@page import="fr.epsi.jeeProject.dao.UtilisateurDao"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<title>My EPSI - Accueil</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/accueil.css" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class='col-12'>
				<h1 class='title'>My EPSI - Accueil</h1>
				<!-- Mes articles -->
				<div class='btnAjout'>
					<button data-toggle="modal" data-target="#modalAjoutArticle"
						class='btn btn-success'>Créer un nouvel article</button>
				</div>
				<h3 class='title'>Mes articles</h3>
				<% 
					Utilisateur loggedUser = (Utilisateur) session.getAttribute("loggedUser");
					BlogDao blogDao = new BlogDao();
					List<Blog> listBlogUser = blogDao.getBlogs(loggedUser);
					if(listBlogUser != null) {
						for(Blog itBlog : listBlogUser) {
							int idBlog = itBlog.getId();
				%>
				<div class='row'>
					<div class='col-2'></div>
					<div class='col-8'>
						<div id="Myaccordion">
							<div class="card">
								<div class="card-header">
									<h5 class="mb-0">
										<button class="btn btn-link" data-toggle="collapse"
											data-target="#collapseMy" aria-expanded="true"
											aria-controls="collapseMy">
											<!-- logo - titre - Auteur - Date Modif -->
											<img alt='icone mon post'
												src="https://img.icons8.com/material/26/000000/checked-user-male.png">
											<%= itBlog.getTitre() %> - <%= itBlog.getCreateur().getNom() %> - <%= itBlog.getDateModification() %> - <%= itBlog.getStatut().getDescription() %>
										</button>
									</h5>
								</div>
								<!-- <img alt='mon post annulé' src="https://img.icons8.com/ios-glyphs/26/000000/remove-user-male.png"> -->
								<!-- image post annulé -->

								<div id="collapseMy" class="collapse show"
									data-parent="#Myaccordion">
									<div class="card-body text-justify">
										<%= itBlog.getDescription() %>
									</div>
									<div class="card-footer">
										<input type='submit' id="supprimer" value='Supprimer le post'
											class='btn btn-danger btnModal' />
										<!-- Modifiable si pas annulé -->
										<input type='submit' value='Modifier le post'
											class='btn btn-info btnModal' />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class='col-2'></div>
				</div>
				<br/>
				<% 
						}
					} else {
						
				%>
				<div class='row'>
					<div class='col-2'></div>
					<div class='col-8'>
						<p>Vous n'avez aucun post !</p>
					</div>
					<div class='col-2'></div>
				</div>
				<br/>
				<% } %>
				<!-- Les autres articles -->
				<h3 class='title'>Les articles des autres utilisateurs</h3>
				<%
					List<Blog> listBlogOtherUsers = blogDao.getAllBlogs(loggedUser);
					if(listBlogOtherUsers != null) {
						for(Blog itBlog : listBlogOtherUsers) {
							int idBlog = itBlog.getId();
				%>
				<div class='row'>
					<div class='col-2'></div>
					<div class='col-8'>
						<div id="accordion">
							<div class="card">
								<div class="card-header" id="headingOne">
									<h5 class="mb-0">
										<button class="btn btn-link" data-toggle="collapse"
											data-target="#collapseOne" aria-expanded="false"
											aria-controls="collapseOne">
											<!-- logo - titre - Auteur - Date Modif -->
											<img alt='icone autre post'
												src="https://img.icons8.com/metro/26/000000/user-group-man-man.png">
											<%= itBlog.getTitre() %> - <%= itBlog.getCreateur().getNom() %> - <%= itBlog.getDateModification() %>
										</button>
									</h5>
								</div>

								<div id="collapseOne" class="collapse show"
									aria-labelledby="headingOne" data-parent="#accordion">
									<div class="card-body text-justify">
										<%= itBlog.getDescription() %>
									</div>
									<div class="card-footer">
										<button data-toggle="modal"
											data-target="#modalAjoutCommentaire"
											class='btn btn-info btnModal'>Ajoutez un commentaire</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class='col-2'></div>
				</div>
				<br/>
				<%
						}
					} else {
						
				%>
				<div class='row'>
					<div class='col-2'></div>
					<div class='col-8'>
						<p>Aucun article trouvé !</p>
					</div>
					<div class='col-2'></div>
				</div>
				<br/>
				<% } %>
			</div>
		</div>
	</div>

	<div class="modal" id="modalAjoutArticle">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Ajout d'un nouvel article</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<form method='post'>
					<div class="modal-body">
						<label class='label'>Titre :</label> <input type='text'
							name='titre' placeholder="Saisissez un titre"
							class='form-control' required /> <label class='label'>Contenu
							de l'article :</label>
						<textarea id='labelle' name='labelle'
							placeholder='Saisissez un contenu à votre article'
							class='form-control' required></textarea>
					</div>
					<div class="modal-footer">
						<input type='submit' value='Ajouter !' id='ajouter' name='ajouter'
							class='btn btn-success' /> <input type='submit' value='Annuler !'
							id='annuler' name='annuler' class='btn btn-danger' />
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>