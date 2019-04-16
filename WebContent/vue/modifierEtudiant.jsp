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
        <form action="Controleur" method="POST">
            <table>
                <tr><td><label for="nom">Nom</label></td>
                    <td><input id="nom" type="text" name="nom" value="${etudiant.nom}" placeholder="EX: NDIAYE"/></td>
                    
                    <td><label for="ufr">ufr</label></td>
                    <td><input id="ufr" type="text" name="ufr" value="${etudiant.ufr}"/></td>
                    
                </tr>
                <tr><td><label for="prenom">Prenom</label></td>
                    <td><input id="prenom" type="text" name="prenom" value="${etudiant.prenom}" placeholder="EX: Ibrahima"/></td>
                    
                   <td><label for="departement">Departement</label></td>
                    <td><input id="departement" type="text" name="departement" value="${etudiant.departement}"/></td>
                </tr>
                <tr>
                    <td><label for="numero">Code Permanent</label></td>
                    <td><input id="numero" type="text" name="numero" value="${etudiant.numeroEtudiant}"/></td>
                
                    <td><label for="form">Formation :</label></td>
                    <td><input id="form" type="text" name="formation" value="${etudiant.formation}"/></td>
                </tr>
                <tr>
                    <td><label for="sexe">Sexe</label></td>
                    <td>
                        <select name="sexe" id="sexe" >
                            <option value="M" >Masculin</option>
                            <option value="F" >Feminin</option>
                        </select>
                    </td>
                        
                        <td><label for="niveau">Niveau</label></td>
                        <td><input id="niveau" type="number" name="niveau" value="${etudiant.niveau}" max="8" min="1"/></td>
                </tr>
                <tr>
                    <td><label for="dateN">Date</label></td>
                    <td><input id="dateN" type="date" name="dateN" value="${etudiant.dateNai}" placeholder="aaaa-mm-jj"/></td>
                    
                    <td><label for="email">Email :</label></td>
                    <td><input id="email" type="text" name="email" value="${etudiant.email}"/></td>
                    <td><p class="erreur">${erreurFormat}</p></td>
                </tr>
                
                <tr>
                    <td><label for="lieu">Lieu</label></td>
                    <td><input id="lieu" type="text" name="lieu" value="${etudiant.lieuNai}"/></td>
                <input type="hidden" name="adm" value="amodEtu"/>
                </tr>
                <tr>
                    <c:if test="${!empty etudiant}">
                    <td><input type="hidden" name="idEtu" value="${etudiant.idEtudiant}"/></td>
                    <td colspan="2"><input type="submit" name="action" value="Modifier etudiant"/></td>
                    </c:if>
                </tr>
            </table>
            
        </form>
        <c:if test="${!empty reussi}">
        <p class="reussi"><img src='img/bon.png' alt='bon' width="40"/>${reussi}</p>
    </c:if>
    <c:if test="${!empty erreur}">
        <p class="erreur">${erreur}</p>
    </c:if>
    </section>


<%@include file="../WEB-INF/footer.jspf" %> 