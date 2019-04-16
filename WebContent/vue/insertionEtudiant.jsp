<%-- 
    Document   : InsererEtudiant
    Created on : 19 mai 2017, 23:44:37
    Author     : Side Sarr
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <h1>Ajout Etudiant</h1><hr/>
    <form id="upload" enctype="multipart/form-data" method="POST" action="Controleur">
        <input type="file" name="fichier" value="${param.fichier}" required> 
        <input type="submit"  name="action" value="Inserer etudiant">
    </form>
    <c:if test="${!empty reussi}">
    <p class="reussi"><img src='img/bon.png' alt='bon' width="40"/>${reussi}</p>
    </c:if>
    <c:if test="${!empty erreur}">
    <p class="erreur">${erreur}</p>
</c:if>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 