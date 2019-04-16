<%-- 
    Document   : InsertionOccupe
    Created on : 23 mai 2017, 21:50:35
    Author     : youssou
--%>

<%@include file="../WEB-INF/entete.jspf" %>
<c:if test="${empty sessionScope.profile}">
    <jsp:forward page="connexion.jsp">
        <jsp:param name="erreurAuth" value="Veuillez vous authentifier"/>
    </jsp:forward> 
</c:if>
       <section>
        <h1>Attrubition chambre</h1><hr/>
        <form action="Controleur" method="POST">
            <table>
                <tr>
                    <td><label for="nomPavion">Nom pavion:</label></td>
                    <td>
                        ${nomPavion}<input type="hidden" name="nomPavion" id="nomPavion" value="${nomPavion}"/>
                        <input type="hidden" name="sec" value="atChambre"/>
                    </td>
                    
                </tr>
                <tr>
                    <td><label for="numchambre">Numero chambre:</label></td>
                    <td>${idChambre}<input type="hidden" name="numChambre" id="numchambre" value="${idChambre}"/></td>
                </tr>
                 <tr>
                    <td><label for="numEtudiant">Numero de carte permanant:</label></td>
                    <td><input type="text" name="numEtudiant" id="numEtudiant"/></td>
                </tr>
                 <tr>
                    <td><label for="anne">Annee:</label></td>
                    <td><input type="text" name="annee" id="annee"/></td>
                </tr>
                 <tr>
                    <td><input type="submit" name="action" value="inserez occupe"/></td>
                </tr>
            </table>
        </form>
   </section>


<%@include file="../WEB-INF/footer.jspf" %> 