<%-- 
    Document   : supprimerPavion
    Created on : 2 août 2017, 11:03:59
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf"%>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
<section>
    <h1>Supprimer pavillon</h1><hr/>
    <form action="Controleur" method="POST">
        <label for="pav"><h3>veuillez choisir le pavillon à supprimer</h3></label>
        <select id="pav" name="pavion">
            <c:forEach var="pv" items="${lesPavion}">
                <option value="${pv.idPavion}">${pv.nomPavion}</option>    
            </c:forEach>
        </select>
        <input type="hidden" name="action" value="supPavion"/>
        <input type="submit"  value="Supprimer"/>
    </form> 
    <c:if test="${!empty reussi}">
    <p class="reussi"><img src='img/bon.png' alt='bon' width="40"/>${reussi}</p>
    </c:if>
    <c:if test="${!empty erreur}">
    <p class="erreur">${erreur}</p>
</c:if>
</section>


<%@include file="../WEB-INF/footer.jspf" %> 

