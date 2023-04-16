<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>

<style type="text/css">
a {
	margin: 20px;
}
#links{
margin-bottom: 50px;
}

table{
           position: relative;
           z-index:2;
           left: 60%;
           top:50%;
           transform: translate(-50%,-50%);
           width: 80%;
           top : 80px;
           overflow: hidden;
           border-spacing: 0;
           border-radius: 12px 12px 0 0;
           box-shadow: 0 2px 12px rgba(32,32,32,.3);
           background: #fafafa;
           text-align: center;
           }
           
           th,td{
           padding: 6px, 10px;
           }

th{
        background: #ba68c8;
        color: #fafafa;
        text-transform: uppercase;}
}



form{

	 margin-top: 25px;
}
input{
    display: block;
    font-size:11px;
    padding:4px 2px;
    border:solid 1px #aacfe4;
    width:120px;
    margin:2px 0 20px 10px;

}

tr:nth-child(odd) {background-color: #eeeeee}
body{
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  padding: 40px;
  background: linear-gradient(115deg, #56d8e4 10%, #9f01ea 90%);
}
.container{
  
  background: #fff;
  width: 900px;
  height: 900px;
  padding: 25px 40px 10px 40px;
  box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
}

.topnav {
  overflow: hidden;
  background: linear-gradient(115deg, #56d8e4 10%, #9f01ea 90%);
   border-radius: 25px;
    height: 80px;
  width: 100%;
  border-spacing: 0 px;

}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
  border-radius: 25px;

}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #4CAF50;
  color: white;
}


</style>
</head>
<body>
 <div class="container">
 
 <div class="topnav">
	<div id="links">
		<a href="modules">Modules</a>
		<a href="professeurs">Professeurs</a>
		<a href="assistants">Assistants</a>
	</div>
</div>
	<c:if test="${fn:endsWith(currentPage, 'modules')}">
		<table>
			<tr>
				<th>Titre</th>
				<th>Heures cours</th>
				<th>Heures TP</th>
				<th>Heures TD</th>
				<th>Professeur Responsable</th>
			</tr>
			<c:forEach items="${modules}" var="module">
			<tr>
				<td>${module.titre} </td>
		        <td>${module.nb_heures_cours} </td>
		        <td>${module.nb_heure_tp} </td>
		        <td>${module.nb_heure_td} </td>
		        <td>${module.responsable.nom} </td>
			</tr>
	          
			</c:forEach>
		</table>
		
		<form method="post" action="/modules">
			<label>Ajouter un module</label>
			<input required name="titre"  placeholder="Titre"/>
			<input required type="number"  name="nb_heures_cours"  placeholder="Nombre heures cours"/>
			<input required type="number" name="nb_heure_tp"  placeholder="Nombre heures TP"/>
			<input required type="number" name="nb_heure_td"  placeholder="Nombre heures TD"/>
			<select required name="id_responsable">
				<c:forEach items="${professeurs}" var="professeur">
					<option value="${professeur.id}">${professeur.nom} -  ${professeur.prenom}</option>
				</c:forEach>
			</select>
			<button type="submit">Ajouter</button>
		</form>
	</c:if>


	<c:if test="${fn:endsWith(currentPage, 'assistants')}">
		<table>
			<tr>
		
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date de naissance</th>
				<th>Diplome</th>

			</tr>
			<c:forEach items="${assistants}" var="assistant">
			<tr>
	
		        <td>${assistant.nom} </td>
		        <td>${assistant.prenom} </td>
		        <td>${assistant.date_naissance} </td>
		        <td>${assistant.diplome} </td>

	
			</tr>
	          
			</c:forEach>
		</table>
		
				<form method="post" action="/assistants">
				
			<label>Ajouter un assistant</label>
		
			<input required name="nom"  placeholder="Nom"/>
			<input required name="prenom"  placeholder="Prenom"/>
			<input required name="date_naissance"  placeholder="Date de naissance"/>
			<input required name="diplome"  placeholder="Diplome"/>
		    <select required name="id_responsable">
				<c:forEach items="${professeurs}" var="professeur">
					<option value="${professeur.id}">${professeur.nom} -  ${professeur.prenom}</option>
				</c:forEach>
			</select>
			<button type="submit">Ajouter</button>
			
		</form>
	</c:if>
		
		
		<c:if test="${fn:endsWith(currentPage, 'professeurs')}">
		<table>
			<tr>
				
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date de naissance</th>
				<th>Diplome</th>
		
			</tr>
			<c:forEach items="${professeurs}" var="professeur">
			<tr>
			
		        <td>${professeur.nom} </td>
		        <td>${professeur.prenom} </td>
		        <td>${professeur.date_naissance} </td>
		        <td>${professeur.diplome} </td>
			</tr>
	          
			</c:forEach>
		</table>
		
		<form method="post" action="/professeurs" >
			<label>Ajouter un professeur</label>
		
			<input required name="nom"  placeholder="Nom"/>
			<input required name="prenom"  placeholder="Prenom"/>
			<input required name="date_naissance"  placeholder="Date de naissance"/>
			<input required name="diplome"  placeholder="Diplome"/>
		
			<button type="submit">Ajouter</button>
		</form>

	</c:if>		

	
		</div>
</body>
</html>